package cn.bnt.shoppingcart.dao.impl;

import java.util.List;

import cn.bnt.shoppingcart.beans.Item;
import cn.bnt.shoppingcart.beans.Order;
import cn.bnt.shoppingcart.dao.OrderDao;
import cn.bnt.shoppingcart.utils.DbUtils;

import java.sql.*;
public class OrderDaoImplByJDBC implements OrderDao {

	@Override
	public void save(Order order) {
		String sql1="insert into tb_order(oid,user_uid,totalMoney) values(?,?,?)";
		String sql2="insert into tb_item(id,product_pid,number,sumMoney,order_oid) values(?,?,?,?,?)";
	    DbUtils.update(sql1, new Object[] {order.getOid(),order.getUser().getUid(),order.getTotalMoney()});
	    for(Item item:order.getShoppingCart().getItems()) {
	    	DbUtils.update(sql2, new Object[] {item.getId(),item.getProduct().getPid(),item.getNumber(),item.getSumMoney(),order.getOid()});
	    }

	}

	@Override
	public List<Order> queryForUser(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order queryForOid(String oid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order queryInfoForOid(String oid) {
		// TODO Auto-generated method stub
		return null;
	}

}
