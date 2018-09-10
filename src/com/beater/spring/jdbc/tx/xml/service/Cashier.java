package com.beater.spring.jdbc.tx.xml.service;

import java.util.List;

public interface Cashier {
	void batchPurchase(String userName, List<String> isbns);
}
