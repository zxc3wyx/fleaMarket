package cn.bnt.shoppingcart.dao;

import cn.bnt.shoppingcart.beans.User;

public interface UserDao {
   
	public void save(User user);
	//ͨ���û���ź͵�¼��������ѯһ���û�����Ϣ
	public User queryByUidAndLoginPwd(String uid,String loginPwd);
	
	public void update(User user);
	
	public void deleteByUid(String uid);
	
	public User queryByUid(String uid);
}
