package com.wenda.domain;

import java.io.Serializable;

/**
 * 用户实体类
 * @author jh
 *
 */
public class Users implements Serializable{
private Integer id;
private String username;
private String password;
private String email;
private String phone;
private Integer point;
private String image;

private String grdae;
private int money;

public Users() {
	
}

public Users(String username_value, String password_value, String email2, String telphone) {
	this.username=username_value;
	this.password=password_value;
	this.email=email2;
	this.phone=telphone;
	this.point=point;

}
public Users(String username_value, String password_value, String email2, String telphone,String grdae,int money) {
	this.username=username_value;
	this.password=password_value;
	this.email=email2;
	this.phone=telphone;
	this.point=point;
	this.grdae=grdae;
	this.money=money;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public Integer getPoint() {
	return point;
}
public void setPoint(Integer point) {
	this.point = point;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getGrdae() {
	return grdae;
}
public void setGrdae(String grdae) {
	this.grdae = grdae;
}
public int getMoney() {
	return money;
}
public void setMoney(int money) {
	this.money = money;
}
@Override
public String toString() {
	return "Users [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", phone="
			+ phone + ", point=" + point + ", image=" + image + "]";
}


}
