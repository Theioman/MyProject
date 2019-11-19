package com.book.dao;

import com.book.domain.Users;
/**
 * 鐢ㄦ埛dao
 * @author hp
 *
 */
public interface UsersDao {
/**
 * 鐧诲綍 
 * @return 杩斿洖Users锛孶sers涓簄ull鍒欑櫥褰曞け璐� 鍚﹀垯鎴愬姛
 */
Users login(Users user);
/**
 * 娉ㄥ唽
 */
void register(Users user);
/**
 * 通过username查找用户
 */
Users findUserByUserName(String username);


}
