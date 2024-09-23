package cn.bnt.shoppingcart.beans;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Item> items = new ArrayList<Item>();//��ʾ���ﳵ�еĶ�����Ŀ��Ҳ������Ʒ�嵥��

    //private User user;//��ʾ�ù��ﳵ����һ���û���
    //�ṩ��Ӧ��getter/setter����
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    //	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
    public Double getTotalMoney() {
        //��ʾ�ܽ��
        Double totalMoney = 0.0;
        for (Item item : this.items) {
            totalMoney += item.getSumMoney();
        }
        return totalMoney;
    }

    /**
     * ���ܣ����ﳵ�����һ���µ���Ʒ
     *
     * @param p
     */
    public void addProduct(Product p) {
        //���м�飬����Ʒ֮ǰ�Ƿ����
        boolean flag = true;
        for (Item item : this.getItems()) {
            if (item.getProduct().getPid().equals(p.getPid())) {
                flag = false;
                //�Ѹ���Ŀ��Ӧ��Ʒ��������һ
                item.setNumber(item.getNumber() + 1);
                break;
            }
        }
        if (flag) {//��������Ʒ֮ǰû�й����
            Item item = new Item(this.getItems().size() + 1, p, 1);
            this.items.add(item);
        }
    }

    /**
     * ���ܣ��ӹ��ﳵ�аѸ���Ʒ�Ƴ���
     *
     * @param p
     */
    public void removeProduct(Product p) {
        //�����жϸ���Ʒ�Ƿ��ڹ��ﳵ�У�����ڣ����Ƴ�
        for (Item item : this.getItems()) {
            if (item.getProduct().getPid().equals(p.getPid())) {
                this.items.remove(item);
                break;
            }
        }
    }
}
