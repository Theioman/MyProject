package com.book.domain;

public class UsersSite {

	/**
	 * 用户信息地址表
	 */
	private Integer id;
	private String addressee;
	private String content;
	private String mailCode;
	private String phone;
	private Integer uid;
	public UsersSite() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UsersSite(Integer id, String addressee, String content,
			String mailCode, String phone, Integer uid) {
		super();
		this.id = id;
		this.addressee = addressee;
		this.content = content;
		this.mailCode = mailCode;
		this.phone = phone;
		this.uid = uid;
	}
	
	public UsersSite(Integer id,String addressee, String content,
			 String phone){
		super();
		this.id = id;
		this.addressee =addressee;
		this.content = content;
		this.phone = phone;
		 
	}
	
	
	public UsersSite(String addressee, String content,String mailCode,
			 String phone,Integer uid){
		super();
		this.addressee =addressee;
		this.content = content;
		this.mailCode = mailCode;
		this.phone = phone;
		this.uid = uid;
		 
	}
	
	@Override
	public String toString() {
		return "UsersSite [id=" + id + ", addressee=" + addressee
				+ ", content=" + content + ", mailCode=" + mailCode
				+ ", phone=" + phone + ", uid=" + uid + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddressee() {
		return addressee;
	}
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMailCode() {
		return mailCode;
	}
	public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	
}
