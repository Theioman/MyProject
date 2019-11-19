package com.book.domain;

import java.io.Serializable;
import java.util.List;
/**
 * 购物车详情
 * @author hp
 *
 */
public class CartDetail implements Serializable{
private Integer id;
private Integer bid;
private Integer cid;
private Integer bnum;
private List<Book> books;


public List<Book> getBooks() {
	return books;
}
public void setBooks(List<Book> books) {
	this.books = books;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getBid() {
	return bid;
}
public void setBid(Integer bid) {
	this.bid = bid;
}
public Integer getCid() {
	return cid;
}
public void setCid(Integer cid) {
	this.cid = cid;
}
public Integer getBnum() {
	return bnum;
}
public void setBnum(Integer bnum) {
	this.bnum = bnum;
}
/*@Override
public String toString() {
	return "CartDetail [id=" + id + ", bid=" + bid + ", cid=" + cid + ", bnum=" + bnum + "]";
}*/
@Override
public String toString() {
	return "CartDetail [id=" + id + ", bid=" + bid + ", cid=" + cid + ", bnum="
			+ bnum + ", books=" + books + "]";
}

}
