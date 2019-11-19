package com.book.dao;

import com.book.domain.UsersSite;

public interface UsersSiteDao {
    
	
	UsersSite findUsersSiteById(Integer id);

	Object finUsersInfoById(Integer uid);
	
	void updateUsersSiteById(UsersSite usersSite);
	
	void addUsersSiteById(UsersSite usersSite)
;
}
