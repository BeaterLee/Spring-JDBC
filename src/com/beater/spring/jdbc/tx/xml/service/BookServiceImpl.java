package com.beater.spring.jdbc.tx.xml.service;

import com.beater.spring.jdbc.tx.xml.Dao.BookDao;

public class BookServiceImpl implements BookService {
	
	private BookDao bookDao;
	
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	public void purchase(String userName,String isbn) {
		bookDao.updateBookStock(isbn);
		bookDao.updateBalance(userName, isbn);
	}
}
