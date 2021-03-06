package com.wenda.domain;

import java.io.Serializable;

/**
 * 问题标签的实体类
 * @author jh
 *
 */
public class Tag implements Serializable{
private Integer id;
private String content;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
@Override
public String toString() {
	return "Tag [id=" + id + ", content=" + content + "]";
}



}
