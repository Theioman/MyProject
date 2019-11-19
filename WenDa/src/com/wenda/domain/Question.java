package com.wenda.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 问题实体类
 * @author jh
 *
 */
public class Question implements Serializable{
private Integer id;
private String title;
private String Content;
private Integer visit;
private Date time;
private Date lasttime;
private Integer reward;
private Integer top;
private Integer finish;
private Users users;
private Integer anum;



public Integer getAnum() {
	return anum;
}
public void setAnum(Integer anum) {
	this.anum = anum;
}
public Users getUsers() {
	return users;
}
public void setUsers(Users users) {
	this.users = users;
}
private Users user;
public Users getUser() {
	return user;
}
public void setUser(Users user) {
	this.user = user;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return Content;
}
public void setContent(String content) {
	Content = content;
}
public Integer getVisit() {
	return visit;
}
public void setVisit(Integer visit) {
	this.visit = visit;
}
public Date getTime() {
	return time;
}
public void setTime(Date time) {
	this.time = time;
}
public Date getLasttime() {
	return lasttime;
}
public void setLasttime(Date lasttime) {
	this.lasttime = lasttime;
}
public Integer getReward() {
	return reward;
}
public void setReward(Integer reward) {
	this.reward = reward;
}
public Integer getTop() {
	return top;
}
public void setTop(Integer top) {
	this.top = top;
}
public Integer getFinish() {
	return finish;
}
public void setFinish(Integer finish) {
	this.finish = finish;
}
@Override
public String toString() {
	return "Question [id=" + id + ", title=" + title + ", Content=" + Content + ", visit=" + visit + ", time=" + time
			+ ", lasttime=" + lasttime + ", reward=" + reward + ", top=" + top + ", finish=" + finish + ", users="
			+ users + ", tag=" + "]";
}


}
