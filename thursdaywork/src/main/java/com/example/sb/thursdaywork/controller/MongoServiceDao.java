package com.example.sb.thursdaywork.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@Repository
public class MongoServiceDao {

	public void retrieveAllData() {
		MongoClient mongo = new MongoClient(readValuesFromProps("host"), 27017);
		MongoDatabase database = mongo.getDatabase(readValuesFromProps("db"));
		com.mongodb.client.MongoCollection<Document> collection = database
				.getCollection(readValuesFromProps("collection"));
		if (collection != null) {
			FindIterable<Document> iterDoc = collection.find();
			MongoCursor<Document> it = iterDoc.iterator();
			while (it.hasNext()) {
				System.out.println("Document - " + it.next());
			}
		}
		mongo.close();
	}

	public Document retrieveData(String id) {
		Document doc = null;
		Document returnDoc = null;
		MongoClient mongo = new MongoClient(readValuesFromProps("host"), 27017);
		MongoDatabase database = mongo.getDatabase(readValuesFromProps("db"));
		com.mongodb.client.MongoCollection<Document> collection = database
				.getCollection(readValuesFromProps("collection"));
		if (collection != null) {
			FindIterable<Document> iterDoc = collection.find();
			MongoCursor<Document> it = iterDoc.iterator();
			while (it.hasNext()) {
				doc = it.next();
				if (doc.containsKey(id)) {
					if (doc.get(id).equals(getCurrentDate())) {
						System.out.println("Document retrieveData()- " + doc);
						returnDoc = doc;
						System.out.println("!!! Found Key with Current Date");
						break;
					}
				}
			}
		}
		mongo.close();
		return returnDoc;
	}

	public Document retrieveDataFixture(String id) {
		Document doc = null;
		Document returnDoc = null;
		MongoClient mongo = new MongoClient(readValuesFromProps("host"), 27017);
		MongoDatabase database = mongo.getDatabase(readValuesFromProps("db"));
		com.mongodb.client.MongoCollection<Document> collection = database
				.getCollection(readValuesFromProps("collection"));
		if (collection != null) {
			System.out.println(id);
			FindIterable<Document> iterDoc = collection.find();
			MongoCursor<Document> it = iterDoc.iterator();
			while (it.hasNext()) {
				doc = it.next();
				if (doc.containsKey(id)) {
					if (doc.get(id).equals("epl")) {
						if (doc.get("date") != null && doc.get("date").equals(getCurrentDate())) {
							System.out.println("yyyyyyyyy");
							System.out.println("Document retrieveData()- " + doc);
							returnDoc = doc;
							System.out.println("!!! Found Key with Current Date");
							break;
						}
					}
				}
			}
		}
		mongo.close();
		return returnDoc;
	}

	public Document retrieveDataResults(String id) {
		Document doc = null;
		Document returnDoc = null;
		MongoClient mongo = new MongoClient(readValuesFromProps("host"), 27017);
		MongoDatabase database = mongo.getDatabase(readValuesFromProps("db"));
		com.mongodb.client.MongoCollection<Document> collection = database
				.getCollection(readValuesFromProps("collection"));
		if (collection != null) {
			FindIterable<Document> iterDoc = collection.find();
			MongoCursor<Document> it = iterDoc.iterator();
			while (it.hasNext()) {
				doc = it.next();
				if (doc.containsKey(id)) {
					if (doc.get(id).equals("epl")) {
						if (doc.get("date") != null && doc.get("date").equals(getCurrentDate())) {
							System.out.println("Document retrieveData()- " + doc);
							returnDoc = doc;
							System.out.println("!!! Found Key with Current Date");
							break;
						}
					}
				}
			}
		}
		mongo.close();
		return returnDoc;
	}

	public Document getLimitedTransferNews(String id) {
		System.out.println("1");
		Document doc = null;
		Document returnDoc = null;
		MongoClient mongo = new MongoClient(readValuesFromProps("host"), 27017);
		MongoDatabase database = mongo.getDatabase(readValuesFromProps("db"));
		com.mongodb.client.MongoCollection<Document> collection = database
				.getCollection(readValuesFromProps("collection"));
		if (collection != null) {
			System.out.println("2");
			FindIterable<Document> iterDoc = collection.find().limit(2);
			MongoCursor<Document> it = iterDoc.iterator();
			System.out.println("3");
			while (it.hasNext()) {
				System.out.println("31");
				doc = it.next();
				if (doc.containsKey(id)) {
					System.out.println("4");
					if (doc.get(id).equals(getCurrentDate())) {
						System.out.println("5");
						System.out.println("Limit Document getLimitedTransferNews() - " + doc);
						returnDoc = doc;
						System.out.println("!!! getLimitedTransferNews() - Found Key with Current Date");
						break;
					}
				}
			}
		}
		mongo.close();
		return returnDoc;
	}

	public String getCurrentDate() {
		String todayDate = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		todayDate = dateFormat.format(date).toString();
		// System.out.println("Current Date - " + todayDate);
		return todayDate;
	}

	public String readValuesFromProps(String param) {
		String result = "";
		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
			String propFileName = "db.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			// get the property value and print it out
			result = prop.getProperty(param);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
