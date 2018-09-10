package com.beater.spring.jdbc.tx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("cashier")
public class CashierImpl implements Cashier {

	@Autowired
	private BookService bookService;

	@Transactional
	@Override
	public void batchPurchase(String userName, List<String> isbns) {
		// TODO Auto-generated method stub
		for(String isbn : isbns) {
			bookService.purchase(userName, isbn);
		}
	}

}
