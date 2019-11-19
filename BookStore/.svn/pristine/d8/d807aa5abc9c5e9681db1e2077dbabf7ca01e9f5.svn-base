package com.book.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.dao.BookDao;
import com.book.domain.Book;
import com.book.service.vo.Common;

@Service
public class BookService {
@Autowired
private BookDao bookDao;
public List<Book> findAllBook(Integer pagenum){
	Map<String,Integer> map=new HashMap<String,Integer>();
	map.put("pagenum", (pagenum-1)*20);
	return bookDao.findAllBook(map);
}
public Integer findBooksCount() {
	return bookDao.findBooksCount();
}

public List<Book> findBookByBtype(String bname){
	return bookDao.findBookByBtype(bname);
}
public Book findBookById(Integer id) {
	return bookDao.findBookById(id);
}

public Common queryPriceAndNum(Integer id){
	return bookDao.queryPriceAndNum(id);
}
public List<Book> findBookByCondition(String condition,Integer pagenum) {
	Map<String,Object> map=new HashMap<String,Object>();
	map.put("condition", condition);
	map.put("pagenum", (pagenum-1)*20);
	return bookDao.findBookByCondition(map);

}



public int findBookByConditionCount(String condition) {
	Map<String,String> map=new HashMap<String,String>();
	map.put("condition", condition);
	return bookDao.findBookByConditionCount(map);
}
/**
 * 根据类型查找所有对应的书
 * @param btype
 * @return
 */
public List<Book> findBookByType(String btype,Integer pagenum) {
	Map<String,Object> map=new HashMap<String,Object>();
	map.put("btype", btype);
	map.put("pagenum", (pagenum-1)*20);
	return bookDao.findBookByType(map);
}
public Integer findBookTypeCount(String btype) {
	return bookDao.findBookTypeCount(btype);
}
}

