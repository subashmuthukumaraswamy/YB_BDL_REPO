package DAO;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ConnectMongo {
	public static void main(String[] args) {
		try {
			long startTime = System.currentTimeMillis();
			MongoClient mongoClient = new MongoClient("138.201.50.12", 27017);
			/*
			 * 138.201.50.12 or slave3.bigdata.labs
			 */
			// printAll(mongoClient);
  			System.out.println("Time Taken Mong Connection: " + Utility.JsonTransformer.getDurationBreakdown(System.currentTimeMillis() - startTime));
			printTable(mongoClient, "subash", "jsonDoc", "events");
  			System.out.println("Time Taken Total: " + Utility.JsonTransformer.getDurationBreakdown(System.currentTimeMillis() - startTime));
			mongoClient.close();
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void printTable(MongoClient mongoClient, String dbName, String tableName, String columnName) {
		
		long startTime = System.currentTimeMillis();
		DB db = mongoClient.getDB(dbName);
		DBCollection collection = db.getCollection(tableName);
		DBCursor curs = collection.find();
		Iterator<DBObject> fields = curs.iterator();
		System.out.println("Time Taken Mong Query: " + Utility.JsonTransformer.getDurationBreakdown(System.currentTimeMillis() - startTime));
		startTime = System.currentTimeMillis();
		while (fields.hasNext()) {  
			Object value = fields.next().get(columnName);
			if (null != value) {
				if (value instanceof BasicDBList) {
					System.out.println("BasicDBList : " + value);
					Utility.JsonTransformer.iterateList(columnName, (List<Object>) value);
				} else if (value instanceof BasicDBObject) {
					System.out.println("BasicDBObject : " + value);
					Utility.JsonTransformer.iterateMap(columnName, (Map<String, Object>) value);
				} else {
					System.out.println("columnValue : " + value);
				}
			}
		}
		System.out.println("Time Taken Iterater: " + Utility.JsonTransformer.getDurationBreakdown(System.currentTimeMillis() - startTime));
		
	}

	public static void printAll(MongoClient mongoClient) {
		List<String> databases = mongoClient.getDatabaseNames();

		for (String dbName : databases) {
			System.out.println("- Database: " + dbName);
			DB db = mongoClient.getDB(dbName);
			Set<String> collections = db.getCollectionNames();
			for (String colName : collections) {
				System.out.println("\t + Collection: " + colName);
			}
		}
	}
}