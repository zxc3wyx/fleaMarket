package cn.bnt.shoppingcart.dao;

import java.util.List;

import cn.bnt.shoppingcart.beans.Product;

public interface ProductDao {

	//�����ݿ��г־û�������һ����¼��һ����Ʒ
	public boolean save(Product p);
	//ͨ����Ʒ�������ѯһ����Ʒ����Ϣ
	public Product findByPid(String pid);
	//��ѯ�����е���Ʒ
	public List<Product> findAll();
	//��ҳ��ʾ���ݣ�����pageNum��ʾҪ��ʾ�ڼ�ҳ���ݣ�pageSize��ʾÿҳ��ʾ��������¼
	public List<Product> queryForPage(int pageNum,int pageSize);
	//����һ����Ʒ����Ϣ
	public void update(Product p);
	//ͨ����Ʒ�����ɾ��һ����Ʒ��Ϣ(��Ʒ�¼�)
	public void deleteByPid(String pid);
	
	public int totalPages(int pageSize);
}
