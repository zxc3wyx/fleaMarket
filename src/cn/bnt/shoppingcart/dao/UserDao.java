package cn.bnt.shoppingcart.dao;

import cn.bnt.shoppingcart.beans.User;

public interface UserDao {
   
	public void save(User user);
	//通过用户编号和登录密码来查询一个用户的信息
	public User queryByUidAndLoginPwd(String uid,String loginPwd);
	
	public void update(User user);
	
	public void deleteByUid(String uid);
	
	public User queryByUid(String uid);
}
