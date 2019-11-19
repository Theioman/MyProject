package com.book.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * 订单实体类
 * @author hp
 *
 */
public class Order implements Serializable{
private Integer id;
private String orderid;
private Date time;
private String status;
private Double totalprice;
private Users user;
private List<OrderDetail> orderDetail;

public List<OrderDetail> getOrderDetail() {
	return orderDetail;
}
public void setOrderDetail(List<OrderDetail> orderDetail) {
	this.orderDetail = orderDetail;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}

public String getOrderid() {
	return orderid;
}
public void setOrderid(String orderid) {
	this.orderid = orderid;
}
public String getTime() {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	return sdf.format(time);
}
public void setTime(Date time) {
	this.time = time;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Double getTotalprice() {
	return totalprice;
}
public void setTotalprice(Double totalprice) {
	this.totalprice = totalprice;
}
public Users getUser() {
	return user;
}
public void setUser(Users user) {
	this.user = user;
}
@Override
public String toString() {
	return "Order [id=" + id + ", orderid=" + orderid + ", time=" + time + ", status=" + status + ", totalprice="
			+ totalprice + ", user=" + user + "]";
}

}
