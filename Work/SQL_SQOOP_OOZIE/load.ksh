clear

echo "Initalizing load ..." 
echo "Reading Property file ..."

file="./config.properties"

TOTAL_START=$(date +%s)  


if [ -f "$file" ] #{
then
echo "$file found."
. $file

	databaseFile="./$database.properties"
	echo $databaseFile
	if [ ! -f "$databaseFile" ] #{
	then
		echo "$databaseFile not found."
		exit
	fi #}
	. $databaseFile 
	last_time_query="SELECT cast(max(LAST_UPDATED_DATE) as timestamp) FROM $sql_database.$sql_table"
	echo $last_time_query; 
	last_time=$(hive -e "$last_time_query")
	echo "Last updated record in Server '$last_time'"
	
	hive_table=$sql_table
	hive_incr_table=$sql_table"_incr"

	if [ "$last_time" = "" ] || ["$initialLoadForce" = "Y"]; #{
	then
 		last_time="$start_date"
		echo "last_time is $last_time;  start_date is $start_date"
		hive_drop_Table="use $sql_database; drop table if EXISTS $sql_table;"
		echo $hive_drop_Table
		hive -e "$hive_drop_Table";
		#hive -e 'use $sql_database; select count(*) from $sql_table;';
		#hive -e 'use $sql_database; show tables;'
		isInitLoad=true;
		echo "******************WELCOME TO INITIAL LOAD******************"

	else 
		isInitLoad=false;
		echo "******************WELCOME TO INCREMENTAL LOAD******************"

	fi #}
 	
	echo "WHERER_CLUSE : $sql_where$last_time"
	
	if $isInitLoad  #{
	then
		echo "Removing Data if already exising data is present. $isInitLoad"  
		hadoop fs -rm -r $data_folder 
		new_data_folder=$data_folder
 	else 
		echo "Removing Incremental Data if already exising data is present." 
		hadoop fs -rm -r $inc_data_folder
		new_data_folder=$inc_data_folder
	fi #}
 
	echo "Loading Base Folder Contents"
	hadoop fs -ls $base_folder
	 
	#where_str="$sql_where TIMESTAMP(\"$last_time\")"
	#echo  "QUERY : select * from $sql_table where $where_str"
	 
	sqoop import --connect $connect --table $sql_table --username $sql_username --password $sql_password --driver $sql_driver --as-avrodatafile --target-dir $new_data_folder --split-by $sql_split_by -m $map_jobs --where "$sql_where"

	echo "Loading Base Folder Contents" 
	hadoop fs -ls $new_data_folder
   	echo "Removing _SUCCESS file for futher processing"  
	hadoop fs -rm  $new_data_folder/_SUCCESS	
	#echo "Printing data file"   
    #hadoop fs -cat $new_data_folder/part*
	echo "Cleaning unwanted old data file"   
	
	cd $local_work 
		rm *.avro 
		rm *.java 
		rm *.avsc 
	echo "Copying Data File from HDFS"   
	hadoop fs -get $new_data_folder/part-m-00000.avro  
	echo "Creating schema file from data file"   
	avro-tools getschema  part-m-00000.avro > schema.avsc
	 #echo "Printing Data File"   
	 #cat schema.avsc
	
	echo "Move the schema file from local to HDFS."
	hadoop fs -rm -r $schema_folder
 
	echo "Create directory for schema file"
	hadoop fs -mkdir $schema_folder
 
	echo "Copy Schema to directory for schema file"
	hadoop fs -put schema.avsc $schema_folder

	echo "Check if the file is moved to the schema location" 
	hadoop fs -ls $schema_folder
 		  
	echo "Login in to Hive. Create a table in the schema required."
	hive_table_str="use $hive_schema; CREATE EXTERNAL TABLE IF NOT EXISTS $hive_table STORED AS AVRO location '$data_folder' TBLPROPERTIES('avro.schema.url' = '$hive_schema_url');"
    echo $hive_table_str
	hive -e "$hive_table_str"
	
   
 	if $isInitLoad #{
	then 	
		echo "No Action"		
	else 
	
		echo "Login in to Hive. Create a Incremental table in the schema required."
		hive_incr_table_str="use $hive_schema; CREATE EXTERNAL TABLE IF NOT EXISTS $hive_incr_table STORED AS AVRO location '$inc_data_folder' TBLPROPERTIES('avro.schema.url' = '$hive_schema_url');"
		echo $hive_incr_table_str
		hive -e "$hive_incr_table_str"

		hive_count_query="SELECT count(*) FROM $hive_schema.$hive_incr_table";
		hive_count=$(hive -e "$hive_count_query")
		echo "Count of records Hive Incremental Table : '$hive_count'"
		
		if [ $hive_count -gt 0 ] #{
		then 
			JOIN_START=$(date +%s) 
			 
			echo "Create a reconcile_view and query it to verify the changes."
			hive_rec_table_query="use $hive_schema; drop view reconcile_view; CREATE VIEW reconcile_view AS SELECT t1.* FROM (SELECT * FROM $hive_table UNION ALL SELECT * from $hive_incr_table ) t1 JOIN (SELECT employee_id, max(LAST_UPDATED_DATE) max_modified FROM (SELECT * FROM $hive_table UNION ALL SELECT * from $hive_incr_table ) t3 GROUP by employee_id) t2 ON t1.employee_id = t2.employee_id AND t1.LAST_UPDATED_DATE = t2.max_modified AND t1.deleted_date IS NULL;";
			echo $hive_rec_table_query
			hive -e "$hive_rec_table_query"
			
			
			hive_count_query="SELECT count(*) FROM $hive_schema.reconcile_view";
			hive_count=$(hive -e "$hive_count_query")
			echo "Count of records Hive reconcile Table : '$hive_count'"
			
			echo "Create a reporting_table and query it to verify the changes."
			hive_rep_table_query="use $hive_schema; DROP TABLE if EXISTS reporting_table; CREATE TABLE reporting_table AS SELECT * FROM reconcile_view;";
			echo $hive_rep_table_query
			hive -e "$hive_rep_table_query"
			 
			echo "Remove the incremental data location"
			hadoop fs -rm -r $inc_data_folder

			echo "point the base table to the reconcile_view." 
			hive_final_table_query="use $hive_schema; INSERT OVERWRITE TABLE $hive_table SELECT * FROM reporting_table;";
			echo $hive_final_table_query
			hive -e "$hive_final_table_query"
			JOIN_END=$(date +%s)
		fi #}
	fi #}
else #}{
	echo "Config File not found."
fi #}

	hive_count_query="use $hive_schema; SELECT count(*) FROM $hive_table;";
	hive_count=$(hive -e "$hive_count_query")
	echo "Count of records Hive table : '$hive_count'"
	
	TOTAL_JOIN_DIFF=$(echo "$JOIN_END - $JOIN_START" | bc)
	echo "Total Join Time taken  : $TOTAL_JOIN_DIFF seconds "
	
	TOTAL_END=$(date +%s)
	TOTAL_DIFF=$(echo "$TOTAL_END - $TOTAL_START" | bc)
	echo "Total Time taken  : $TOTAL_DIFF seconds "
 
echo "End load ..."
