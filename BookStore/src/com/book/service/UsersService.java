package com.book.service;
/**
 * 用户service
 * @author hp
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.book.dao.UsersDao;
import com.book.domain.Users;

@Service
public class UsersService {
@Autowired
public UsersDao usersDao;
/**
 * 登录
 * @return
 */
public Users login(Users user) {
	return usersDao.login(user);
}
/**
 * 注册
 */
public boolean register(Users user) {
	if(usersDao.findUserByUserName(user.getUsername())!=null) {
		return false;
	}
	usersDao.register(user);
	return true;
}
}
