package com.wenda.domain;
/**
 * 收藏问题的实体类
 * @author hp
 *
 */
public class Collection {
      private int uid;
      private int qid;
	public Collection() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Collection(int uid, int qid) {
		super();
		this.uid = uid;
		this.qid = qid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
      
}
