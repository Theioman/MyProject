package com.book.dao;

import java.util.List;
import java.util.Map;

import com.book.domain.Order;
import com.book.service.vo.Common;
/**
 * 订单Dao
 * @author hp
 *
 */
public interface OrderDao {
void addOrder(Order order);

List<Order> findAllOrder(Map<String, Integer> map);

Integer findOrdersCount(Integer uid);

List<Integer> findTotalPrice(Integer uid);

int addOrder(Common common);

void addOrderDetail(List<Common> commons);

void deleteCartDetail(List<Integer> list);

Common findInfoByid(Integer bid);

}
