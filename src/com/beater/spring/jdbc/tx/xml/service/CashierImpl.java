package com.beater.spring.jdbc.tx.xml.service;

import java.util.List;

public class CashierImpl implements Cashier {

	private BookService bookService;
	
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@Override
	public void batchPurchase(String userName, List<String> isbns) {
		// TODO Auto-generated method stub
		for (String isbn : isbns) {
			bookService.purchase(userName, isbn);
		}
	}

}
