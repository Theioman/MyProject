package com.book.domain;

import java.io.Serializable;
import java.util.List;
/**
 * 购物车实体类
 * @author hp
 *
 */
public class Cart implements Serializable{
private Integer id;
private List<CartDetail> cartDetails;
private Users user;


public Users getUser() {
	return user;
}
public void setUser(Users user) {
	this.user = user;
}
public List<CartDetail> getCartDetails() {
	return cartDetails;
}
public void setCartDetails(List<CartDetail> cartDetails) {
	this.cartDetails = cartDetails;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
/*@Override
public String toString() {
	return "Cart [id=" + id + ", totalprice=" + totalprice + ", totalnum=" + totalnum + "]";
}*/
@Override
public String toString() {
	return "Cart [id=" + id + ",  cartDetails=" + cartDetails + ", user=" + user
			+ "]";
}

}
