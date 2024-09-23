package cn.bnt.shoppingcart.dao.impl;

import java.util.ArrayList;
import java.util.List;

import cn.bnt.shoppingcart.beans.Product;
import cn.bnt.shoppingcart.dao.ProductDao;
import cn.bnt.shoppingcart.utils.DbUtils;
import java.sql.*;
public class ProductDaoImplByJDBC implements ProductDao {

	@Override
	public boolean save(Product p) {
		String sql="insert into tb_product(pid,name,price,imagePath,pdesc) values(?,?,?,?,?)";
		return DbUtils.update(sql, new Object[] {p.getPid(),p.getName(),p.getPrice(),p.getImagePath(),p.getPDesc()});
		
	}

	@Override
	public Product findByPid(String pid) {
		String sql="select * from tb_product where pid=?";
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Product p=null;
		con=DbUtils.getConnection();
		if(con!=null) {
			try {
				ps=con.prepareStatement(sql);
				ps.setString(1, pid);
				rs=ps.executeQuery();
				if(rs.next()) {
					p=new Product();
					p.setPid(rs.getString(1));
					p.setName(rs.getString(2));
					p.setPrice(rs.getDouble(3));
					p.setImagePath(rs.getString(4));
					p.setPDesc(rs.getString(5));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DbUtils.close(con, ps, rs);
			}
		}
		return p;
	}

	@Override
	public List<Product> findAll() {
		String sql="select * from tb_product";
		List<Product> list=new ArrayList<Product>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		con=DbUtils.getConnection();
		if(con!=null) {
			try {
				ps=con.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()) {
					Product p=new Product();
					p=new Product();
					p.setPid(rs.getString(1));
					p.setName(rs.getString(2));
					p.setPrice(rs.getDouble(3));
					p.setImagePath(rs.getString(4));
					p.setPDesc(rs.getString(5));
					list.add(p);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DbUtils.close(con, ps, rs);
			}
		}
		
		return list;
	}

	@Override
	public List<Product> queryForPage(int pageNum, int pageSize) {
		if(pageNum<0) {
			pageNum=0;
		}else {
			int totalPages=this.totalPages(pageSize);
			if(pageNum>=totalPages) {
				pageNum=totalPages;
			}
		}
		String sql="select * from tb_product limit ?,?";
		List<Product> list=new ArrayList<Product>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		con=DbUtils.getConnection();
		if(con!=null) {
			try {
				ps=con.prepareStatement(sql);
				ps.setInt(1, pageNum);
				ps.setInt(2, pageSize);
				rs=ps.executeQuery();
				while(rs.next()) {
					Product p=new Product();
					p=new Product();
					p.setPid(rs.getString(1));
					p.setName(rs.getString(2));
					p.setPrice(rs.getDouble(3));
					p.setImagePath(rs.getString(4));
					p.setPDesc(rs.getString(5));
					list.add(p);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DbUtils.close(con, ps, rs);
			}
		}
		
		return list;
	}

	@Override
	public void update(Product p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByPid(String pid) {
		// TODO Auto-generated method stub

	}

	@Override
	public int totalPages(int pageSize) {
		String sql="select count(*) from tb_product";
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int sum=0;
		con=DbUtils.getConnection();
		if(con!=null) {
			try {
				ps=con.prepareStatement(sql);
				rs=ps.executeQuery();
				if(rs.next()) {
					sum= rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DbUtils.close(con, ps, rs);
			}
		}
		int totalPages=sum%pageSize==0?sum/pageSize:sum/pageSize+1;
		return totalPages;
	}

}
