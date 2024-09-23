package cn.bnt.shoppingcart.dao;

import java.util.List;

import cn.bnt.shoppingcart.beans.Product;

public interface ProductDao {

	//向数据库中持久化（插入一条记录）一个商品
	public boolean save(Product p);
	//通过商品编号来查询一个商品的信息
	public Product findByPid(String pid);
	//查询出所有的商品
	public List<Product> findAll();
	//分页显示数据，其中pageNum表示要显示第几页数据，pageSize表示每页显示多少条记录
	public List<Product> queryForPage(int pageNum,int pageSize);
	//更新一个商品的信息
	public void update(Product p);
	//通过商品编号来删除一个商品信息(商品下架)
	public void deleteByPid(String pid);
	
	public int totalPages(int pageSize);
}
