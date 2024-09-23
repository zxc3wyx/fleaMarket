package cn.bnt.shoppingcart.dao.impl;

import cn.bnt.shoppingcart.beans.User;
import cn.bnt.shoppingcart.dao.UserDao;
import cn.bnt.shoppingcart.utils.DbUtils;
import java.sql.*;
public class UserDaoImplByJDBC implements UserDao{

	@Override
	public void save(User user) {
		String sql="insert into tb_user(uid,userName,loginPwd,mobile,addr) values(?,?,?,?,?)";
		DbUtils.update(sql, new Object[] {user.getUid(),user.getUserName(),user.getLoginPwd(),user.getMobile(),user.getAddr()});
	}

	@Override
	public User queryByUidAndLoginPwd(String uid, String loginPwd) {
		String sql="select * from tb_user where uid=? and loginPwd=?";
		User user=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		con=DbUtils.getConnection();
		if(con!=null) {
			try {
				ps=con.prepareStatement(sql);
				//要给对应的问号位置赋值
				ps.setString(1, uid);
				ps.setString(2, loginPwd);
				//执行查询并返回一个结果集对象
				rs=ps.executeQuery();
				if(rs.next()) {
					user=new User();
					user.setUid(rs.getString(1));
					user.setUserName(rs.getString(2));
					user.setLoginPwd(rs.getString("loginPwd"));
					user.setMobile(rs.getString(4));
					user.setAddr(rs.getString(5));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				//释放资源
				DbUtils.close(con,ps,rs);
			}
			
		}
		return user;
	}

	@Override
	public void update(User user) {
		String sql="update tb_user set userName=?,loginPwd=?,mobile=?,addr=? where uid=?";
		DbUtils.update(sql, new Object[] {user.getUserName(),user.getLoginPwd(),user.getMobile(),user.getAddr(),user.getUid()});
		
	}

	@Override
	public void deleteByUid(String uid) {
		String sql="delete from tb_user where uid=?";
		DbUtils.update(sql, new Object[] {uid});
		
	}

	@Override
	public User queryByUid(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

}
