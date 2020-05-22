package com.example.sb.thursdaywork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MySqlDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int getRecordCount() {
		return jdbcTemplate.queryForObject("select count(*) from actor", Integer.class);
	}

}
