package com.beater.spring.jdbc.tx;


import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class BookDaoTest {
	private BookDao bookDao;
	private ApplicationContext ctx;
	private BookService bookService;
	private Cashier cashier;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookDao = (BookDao) ctx.getBean("bookDao");
		bookService = (BookService) ctx.getBean("bookService");
		cashier = (Cashier) ctx.getBean("cashier");
	}
	
	@Test
	void testCashier() {
		cashier.batchPurchase("AA", Arrays.asList("1001","1002"));
	}
	
	@Test
	void testTransaction() {
		bookService.purchase("AA", "1001");
	}
	
	@Test
	void testQueryBookPrice() {
		System.out.println(bookDao.queryBookPrice("1001"));
	}

	@Test
	void testUpdateBookStock() {
		bookDao.updateBookStock("1001");
	}

	@Test
	void testUpdateBalance() {
		bookDao.updateBalance("AA", "1001");
	}

	@Test
	void testQueryBalance() {
		System.out.println(bookDao.queryBalance("AA"));
	}

	@Test
	void testQueryBookStock() {
		System.out.println(bookDao.queryBookStock("1001"));
	}

}
