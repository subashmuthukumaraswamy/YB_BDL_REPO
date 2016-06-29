# sh connectMySql.ksh
last_time=$(mysql -uroot -pBigboys@123 -se "SELECT max(LAST_UPDATED_DATE) FROM subash.EMPLOYEE_INFO")
echo $last_time
 
#mysql - 2016-06-27 15:39:52 
#sqlserver - 2016-06-27 15:18:52.407

# Hive : 
last_time=$(hive -e "SELECT cast(max(LAST_UPDATED_DATE) as timestamp)  FROM subash.EMPLOYEE_INFO")
echo "$last_time"


