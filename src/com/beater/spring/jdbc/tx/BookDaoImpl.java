package com.beater.spring.jdbc.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public double queryBookPrice(String isbn) {
		// TODO Auto-generated method stub
		String sql = "select price from book where isbn = ?";
		double price = jdbcTemplate.queryForObject(sql, double.class, isbn);
		return price;
	}

	@Override
	public void updateBookStock(String isbn) {
		// TODO Auto-generated method stub
		String sql = "update bookstock set stock=stock-1 where isbn = ?";
		int stock = queryBookStock(isbn);
		if (stock == 0) {
			throw new BookStockException("库存不足");
		}
		jdbcTemplate.update(sql, isbn);
	}

	@Override
	public void updateBalance(String userName, String isbn) {
		// TODO Auto-generated method stub
		String sql = "update user set balance=balance-? where userName = ?";
		double price = queryBookPrice(isbn);
		double balance = queryBalance(userName);
		if (price > balance) {
			throw new UserBalanceException("余额不足");
		}
		jdbcTemplate.update(sql, price, userName);
	}

	@Override
	public double queryBalance(String userName) {
		// TODO Auto-generated method stub
		String sql = "select balance from user where userName = ?";
		double balance = jdbcTemplate.queryForObject(sql, double.class, userName);
		return balance;
	}

	@Override
	public int queryBookStock(String isbn) {
		// TODO Auto-generated method stub
		String sql = "select stock from bookstock where isbn = ?";
		int stock = jdbcTemplate.queryForObject(sql, int.class, isbn);
		return stock;
	}

}
