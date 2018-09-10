package com.beater.spring.jdbc.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookDao bookDao;
	
	//@Transactional注解声明该方法为事务
	//使用propafation指定事务的传播行为
	//默认使用Propagation.REQUIRED，子事务使用父事务
	//使用Propagation.REQUIRES_NEW时，子事务使用自己的事务
	//isolation指定事务隔离级别，最常用的取值为Isolation.READ_COMMITTED(读已提交)
	//noRollbackFor指定发生指定异常时事务不进行回滚，默认情况Spring的声明式事务对所以的运行时异常进行回滚
	//readOnly指定该事务是否为只读。表示这个事务只读数据但不更新数据，指定该属性可以帮助数据库引擎优化事务
	//timeout指定事务过期时间，事务处理超过设置的时间时进行强制回滚
	@Transactional(propagation=Propagation.REQUIRED,
			isolation=Isolation.READ_COMMITTED,
			noRollbackFor= {UserBalanceException.class},
			readOnly=false,timeout=3)
	public void purchase(String userName,String isbn) {
		bookDao.updateBookStock(isbn);
		bookDao.updateBalance(userName, isbn);
	}
}
