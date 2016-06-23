package hbase;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;

public class ConnectHbase {
	static Configuration conf = new Configuration();
	
	public static void main(String[] args) throws Exception {
		conf.set("hbase.zookeeper.quorum", "master.bigdata.labs,slave3.bigdata.labs,slave1.bigdata.labs");
		conf.set("hbase.zookeeper.property.clientPort", "2181");

		Connection connect = ConnectionFactory.createConnection(conf);
		Table table = connect.getTable(TableName.valueOf("dummy2"));

		Scan scan = new Scan();
		ResultScanner rscan = table.getScanner(scan);

		for (Result res : rscan) {
			System.out.println(res);
		}
	}
}/*	<dependencies>
		<dependency>
			<groupId>org.apache.hbase</groupId>
			<artifactId>hbase-client</artifactId>
			<version>1.0.0</version>
		</dependency>
		<dependency>
			<groupId>org.apache.hbase</groupId>
			<artifactId>hbase-common</artifactId>
			<version>1.0.0</version>
		</dependency>
	</dependencies>*/