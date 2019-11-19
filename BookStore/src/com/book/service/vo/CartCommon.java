package com.book.service.vo;

public class CartCommon {
private Integer id;
private Integer cid;
private Integer uid;
private Integer bid;
private Integer bnum;

public Integer getCid() {
	return cid;
}
public void setCid(Integer cid) {
	this.cid = cid;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getUid() {
	return uid;
}
public void setUid(Integer uid) {
	this.uid = uid;
}
public Integer getBid() {
	return bid;
}
public void setBid(Integer bid) {
	this.bid = bid;
}
public Integer getBnum() {
	return bnum;
}
public void setBnum(Integer bnum) {
	this.bnum = bnum;
}
@Override
public String toString() {
	return "CartCommon [id=" + id + ", cid=" + cid + ", uid=" + uid + ", bid=" + bid + ", bnum=" + bnum + "]";
}

}
