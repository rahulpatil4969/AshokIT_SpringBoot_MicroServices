package com.ait.ems.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

//@Component
public class MyProjectRunner implements CommandLineRunner {
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired 
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.update("insert into users values(?,?,?)", "shekher", encoder.encode("12345"), 1);
		jdbcTemplate.update("insert into users values(?,?,?)", "kelvin", encoder.encode("54321"), 0);
		jdbcTemplate.update("insert into users values(?,?,?)", "scott", encoder.encode("scott@123"), 0);
		
		jdbcTemplate.update("insert into authorities values(?,?)", "ROLE_MANAGER", "shekher");
		jdbcTemplate.update("insert into authorities values(?,?)", "ROLE_LEAD", "kelvin");
		jdbcTemplate.update("insert into authorities values(?,?)", "ROLE_MANAGER", "scott");
	}

}
