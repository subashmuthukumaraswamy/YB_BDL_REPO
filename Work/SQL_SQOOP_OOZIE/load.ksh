clear

echo "Initalizing load ..." 
echo "Reading Property file ..."

file="./config.properties"


if [ -f "$file" ] #{
then
echo "$file found."
. $file

	echo "Removing Data if already exising data is present." 
	hadoop fs -rm -r $data_folder 

	echo "Loading Base Folder Contents"
	hadoop fs -ls $base_folder
	
	if [ $where != "" ]; then #{
		sqoop import --connect $connect --table $sql_table --username $sql_username --password $sql_password --driver $sql_driver  --as-avrodatafile --target-dir $data_folder --where $sql_where --split-by $sql_split_by -m $map_jobs --direct 
	else #}{
		sqoop import --connect $connect --table $sql_table --username $sql_username --password $sql_password --driver $sql_driver --as-avrodatafile --target-dir $data_folder --split-by $sql_split_by -m $map_jobs --direct 
	fi #}
	
	echo "Loading Base Folder Contents" 
	hadoop fs -ls $data_folder
   	echo "Removing _SUCCESS file for futher processing"  
	hadoop fs -rm  $data_folder/_SUCCESS	
	#echo "Printing data file"   
    #hadoop fs -cat $data_folder/part*
	echo "Cleaning unwanted old data file"   
	
	cd $local_work 
		rm *.avro 
		rm *.java 
		rm *.avsc 
	echo "Copying Data File from HDFS"   
	hadoop fs -get $data_folder/part-m-00000.avro  
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
	hive_table_str="use $hive_schema; CREATE EXTERNAL TABLE IF NOT EXISTS EMPLOYEE_INFO STORED AS AVRO location '$data_folder' TBLPROPERTIES('avro.schema.url' = '$hive_schema_url');"
    echo $hive_table_str
	hive -e "$hive_table_str"

	 #echo "Login in to Hive. Create a table in the schema required."
	 #hive -e 'use $hive_schema; SELECT * FROM EMPLOYEE_INFO';

 
else #}{
	echo "Config File not found."
fi #}


echo "End load ..."
