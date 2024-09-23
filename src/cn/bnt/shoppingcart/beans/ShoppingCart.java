package cn.bnt.shoppingcart.beans;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Item> items = new ArrayList<Item>();//表示购物车中的多有条目（也就是商品清单）

    //private User user;//表示该购物车是哪一个用户的
    //提供对应的getter/setter方法
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
        //表示总金额
        Double totalMoney = 0.0;
        for (Item item : this.items) {
            totalMoney += item.getSumMoney();
        }
        return totalMoney;
    }

    /**
     * 功能：向购物车中添加一个新的商品
     *
     * @param p
     */
    public void addProduct(Product p) {
        //进行检查，该商品之前是否购买过
        boolean flag = true;
        for (Item item : this.getItems()) {
            if (item.getProduct().getPid().equals(p.getPid())) {
                flag = false;
                //把该条目对应商品的数量加一
                item.setNumber(item.getNumber() + 1);
                break;
            }
        }
        if (flag) {//表明该商品之前没有购买过
            Item item = new Item(this.getItems().size() + 1, p, 1);
            this.items.add(item);
        }
    }

    /**
     * 功能：从购物车中把该商品移除掉
     *
     * @param p
     */
    public void removeProduct(Product p) {
        //首先判断该商品是否在购物车中，如果在，则移除
        for (Item item : this.getItems()) {
            if (item.getProduct().getPid().equals(p.getPid())) {
                this.items.remove(item);
                break;
            }
        }
    }
}
