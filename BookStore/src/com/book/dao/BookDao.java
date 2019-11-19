package com.book.dao;

import java.util.List;
import java.util.Map;

import com.book.domain.Book;
import com.book.service.vo.Common;

public interface BookDao {
	List<Book> findAllBook(Map<String,Integer> map);
	Integer findBooksCount();
	List<Book> findBookByBtype(String Btype);
	Book findBookById(Integer id);

	Common queryPriceAndNum(Integer id);

	List<Book> findBookByCondition(Map<String,Object> map);
	Integer findBookByConditionCount(Map<String, String> map);
	List<Book> findBookByType(Map<String, Object> map);
	Integer findBookTypeCount(String btype);

}
