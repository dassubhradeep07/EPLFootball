package com.example.sb.thursdaywork.controller;

import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BroadcastController {

	@Autowired
	MongoServiceDao mongoServiceDao;
	@Autowired
	MySqlDao mySqlDao;

	@RequestMapping(value = "/api/epl/headlines", method = RequestMethod.GET)
	public ResponseEntity<Object> getFootballHeadLines() {
		org.bson.Document doc = mongoServiceDao.retrieveData("epl_news");
		JsonWriterSettings writerSettings = new JsonWriterSettings(JsonMode.SHELL, true);
		doc.remove("_id");
		String jsonResponse = doc.toJson(writerSettings);
		return new ResponseEntity<Object>(jsonResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/epl/transfernews", method = RequestMethod.GET)
	public ResponseEntity<Object> getTransferNews() {
		System.out.println("In getTransferNews Method>>>>>");
		org.bson.Document doc = mongoServiceDao.retrieveData("transfer_news");
		JsonWriterSettings writerSettings = new JsonWriterSettings(JsonMode.SHELL, true);
		doc.remove("_id");
		String jsonResponse = doc.toJson(writerSettings);
		return new ResponseEntity<Object>(jsonResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/epl/fixtures", method = RequestMethod.GET)
	public ResponseEntity<Object> getFixture() {
		System.out.println("In getFixture Method>>>>>");
		org.bson.Document doc = mongoServiceDao.retrieveDataFixture("fixture");
		JsonWriterSettings writerSettings = new JsonWriterSettings(JsonMode.SHELL, true);
		System.out.println("getFixture() Doc-" + doc);
		doc.remove("_id");
		String jsonResponse = doc.toJson(writerSettings);
		return new ResponseEntity<Object>(jsonResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/epl/results", method = RequestMethod.GET)
	public ResponseEntity<Object> getResults() {
		System.out.println("In getResults Method>>>>>");
		org.bson.Document doc = mongoServiceDao.retrieveDataResults("result");
		JsonWriterSettings writerSettings = new JsonWriterSettings(JsonMode.SHELL, true);
		System.out.println("getResults() Doc-" + doc);
		doc.remove("_id");
		String jsonResponse = doc.toJson(writerSettings);
		return new ResponseEntity<Object>(jsonResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/epl/latestscores", method = RequestMethod.GET)
	public ResponseEntity<Object> getLatestScore() {
		System.out.println("In getLatestScore Method>>>>>");
		org.bson.Document doc = mongoServiceDao.retrieveDataResults("result");
		JsonWriterSettings writerSettings = new JsonWriterSettings(JsonMode.SHELL, true);
		System.out.println("getLatestScore() Doc-" + doc);
		doc.remove("_id");
		String jsonResponse = doc.toJson(writerSettings);
		return new ResponseEntity<Object>(jsonResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/mysql/testdata", method = RequestMethod.GET)
	public ResponseEntity<Object> testMySqlData() {
		System.out.println("In testdata Method>>>>>");
		int recordCount = mySqlDao.getRecordCount();
		System.out.println("recordCount-" + recordCount);
		return new ResponseEntity<Object>(recordCount, HttpStatus.OK);
	}
}
