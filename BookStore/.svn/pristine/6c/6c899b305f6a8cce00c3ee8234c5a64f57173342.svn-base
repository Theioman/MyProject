package com.book.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.dao.RecentDao;
import com.book.domain.Recent;

@Service
public class RecentService {
	
	@Autowired 
	 public RecentDao recentDao;
	/**
	 * 查询用户地址信息通过uid
	 */
	public List<Recent> selectRecent (Integer uid){
		return recentDao.selectRecent(uid);
	}
	/**
	 * 添加最近浏览商品
	 * @param bid
	 * @param uid
	 */
	public void addRecent(Integer bid, Integer uid) {
		// TODO Auto-generated method stub
		recentDao.addRecent(bid,uid);
	}
	
	/**
	 * 判断是否存在
	 */
    public boolean ifRidIsExists(Integer bid,Integer uid){
    	return recentDao.ifRidIsExists(bid, uid);
    }
}
