package com.book.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.dao.OrderDao;
import com.book.domain.Order;
import com.book.service.vo.Common;

@Service
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	
	public void addOrder(Order order) {
		orderDao.addOrder(order);
	}

	public List<Order> findAllOrder(Integer pagenum,Integer uid) {
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("pagenum", (pagenum-1)*4);
		map.put("uid", uid);
		return orderDao.findAllOrder(map);
	}
	
	public Integer findOrdersCount(Integer uid) {
		return orderDao.findOrdersCount(uid);
	}
	
	public int addOrder(Common common){
		System.out.println(common.getSbprice()+":"+common.getUid()+":"+common.getOrderid());
		return orderDao.addOrder(common);
	}
	
	public void addOrderDetail(List<Common> commons){
		orderDao.addOrderDetail(commons);
	}

	public void deleteCartDetail(List<Integer> list) {
		orderDao.deleteCartDetail(list);
	}

	public Common findInfoByid(Integer bid) {
		return orderDao.findInfoByid(bid);
	}

}
