package com.beater.spring.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

class JdbcTest {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		dataSource = (DataSource) ctx.getBean("dataSource");
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) ctx.getBean("namedParameterJdbcTemplate");
	}
	
	@Test
	void testConnection() {
		try {
			System.out.println(dataSource.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testbatchUpdate() {
		String sql = "insert into customer values(?,?,?)";
		List<Object[]> batchArgs = new ArrayList<>();
		batchArgs.add(new Object[]{"1","bat1","aa"});
		batchArgs.add(new Object[]{"2","bat2","bb"});
		batchArgs.add(new Object[]{"3","bat3","cc"});
		batchArgs.add(new Object[]{"4","bat4","dd"});
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	@Test
	void testUpdate() {
		String sql = "update customer set name = 'bat0' where name = ?";
		jdbcTemplate.update(sql, "bat1");
	}
	
	@Test
	void testBatchQuery() {
		String sql = "select id,name,email from customer";
		RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
		List<Customer> customers = jdbcTemplate.query(sql, rowMapper);
		System.out.println(customers);
	}
	
	@Test
	void testQueryForObject() {
		String sql = "select id,name,email from customer where id=?";
		RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
		Customer customer = jdbcTemplate.queryForObject(sql, rowMapper, "4");
		System.out.println(customer);
	}
	
	@Test
	void testQuerForObject() {
		String sql = "select count(*) from customer";
		Long count = jdbcTemplate.queryForObject(sql, long.class);
		System.out.println(count);
	}
	
	@Test
	void testNamedParameterJdbcTemplate() {
		String sql = "insert into customer(id,name,email) values(:id,:name,:email)";
		Customer customer = new Customer();
		customer.setId("5");
		customer.setName("bat5");
		customer.setEmail("ee");
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(customer);
		namedParameterJdbcTemplate.update(sql, paramSource);
	}
}
