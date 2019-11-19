package com.wenda.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论实体类
 * @author jh
 *
 */
public class Answer implements Serializable{
private Integer id;
private Date time;
private String content;
private Integer Support;
private Integer status;
private Users user;
private Question question;


public Question getQuestion() {
	return question;
}
public void setQuestion(Question question) {
	this.question = question;
}
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
public Date getTime() {
	return time;
}
public void setTime(Date time) {
	this.time = time;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Integer getSupport() {
	return Support;
}
public void setSupport(Integer support) {
	Support = support;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
@Override
public String toString() {
	return "Answer [id=" + id + ", time=" + time + ", content=" + content + ", Support=" + Support + ", status="
			+ status + "]";
}

}
