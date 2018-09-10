package com.beater.spring.jdbc.tx;

public interface BookDao {

	// 查询书的价格
	double queryBookPrice(String isbn);

	// 查询书库存
	int queryBookStock(String isbn);

	// 更新书库存
	void updateBookStock(String isbn);

	// 查询账户余额
	double queryBalance(String userName);

	// 更新账户余额
	void updateBalance(String userName, String isbn);
}
