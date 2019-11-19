package com.wenda.service;

import java.util.List;

import com.wenda.dao.UserDao;
import com.wenda.domain.Users;

public class UserService {
	
	UserDao userdao=UserDao.getInstance();

	private static UserService userservice=null;
	
	private UserService(){}
	
	public static UserService getInstance(){
		if(userservice==null){
			userservice=new UserService();
		}
		return userservice;
	}
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	public Users login(String username,String password){
		return userdao.loginOf(username,password);
	}
	/**
	 * 注册
	 * @param user
	 */
	public void register(Users user)
	{
		userdao.insertUser(user);
	}
	/**
	 * 查找所有用户
	 * @return
	 */
	public List<Users> showExperts() {
		// TODO Auto-generated method stub
		System.out.println("a");
		return userdao.showExperts();
	}

	/*public void sendQuestion(String title, String content, int reward,int id) {
		// TODO Auto-generated method stub
		userdao.sendQuestion(title,content,reward,id);
	}*/
}
