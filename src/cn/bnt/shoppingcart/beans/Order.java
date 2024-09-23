package cn.bnt.shoppingcart.beans;

public class Order {//表示订单

    private String oid;//表示订单编号
    private User user;//表示该订单对应的用户信息 ,根据分析在购物车中不需要关联用户
    private ShoppingCart shoppingCart;//表示该订单对应的商品信息
    private Double totalMoney;

    //提供对应的getter/setter方法
    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Double getTotalMoney() {
        return this.shoppingCart.getTotalMoney();
    }

}
