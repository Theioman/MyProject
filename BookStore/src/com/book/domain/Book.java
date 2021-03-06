package com.book.domain;

import java.io.Serializable;
/**
 * 书实体类
 * @author hp
 *
 */
public class Book implements Serializable{
private Integer id;
private String bname;
private Double bprice;
private String imgpath;
private String bdesc;
private String btype;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getBname() {
	return bname;
}
public void setBname(String bname) {
	this.bname = bname;
}
public Double getBprice() {
	return bprice;
}
public void setBprice(Double bprice) {
	this.bprice = bprice;
}
public String getImgpath() {
	return imgpath;
}
public void setImgpath(String imgpath) {
	this.imgpath = imgpath;
}
public String getBdesc() {
	return bdesc;
}
public void setBdesc(String bdesc) {
	this.bdesc = bdesc;
}
public String getBtype() {
	return btype;
}
public void setBtype(String btype) {
	this.btype = btype;
}
/*@Override
public String toString() {
	return "Book [id=" + id + ", bname=" + bname + ", bprice=" + bprice + ", imgpath=" + imgpath + ", bdesc=" + bdesc
			+ ", btype=" + btype + "]";
}*/
@Override
public String toString() {
	return "Book [id=" + id + ", bname=" + bname + ", bprice=" + bprice
			+ ", imgpath=" + imgpath + ", bdesc=" + bdesc + ", btype=" + btype
			+ "]";
}

}
