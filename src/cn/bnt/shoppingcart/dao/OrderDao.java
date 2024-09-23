package cn.bnt.shoppingcart.dao;

import java.util.List;

import cn.bnt.shoppingcart.beans.Order;

public interface OrderDao {

	public void save(Order order);
	//通过用户编号来查询该用户所下的订单
	public List<Order> queryForUser(String uid);
	//通过订单编号来查询该订单的信息
	public Order queryForOid(String oid);
	//查询订单详情（包括该订单下有哪些商品）
	public Order queryInfoForOid(String oid);
	
	
}
