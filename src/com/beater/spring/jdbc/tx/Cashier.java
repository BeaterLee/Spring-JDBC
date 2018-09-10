package com.beater.spring.jdbc.tx;

import java.util.List;

public interface Cashier {
	void batchPurchase(String userName, List<String> isbns);
}
