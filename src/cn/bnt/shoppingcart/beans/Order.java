package cn.bnt.shoppingcart.beans;

public class Order {//��ʾ����

    private String oid;//��ʾ�������
    private User user;//��ʾ�ö�����Ӧ���û���Ϣ ,���ݷ����ڹ��ﳵ�в���Ҫ�����û�
    private ShoppingCart shoppingCart;//��ʾ�ö�����Ӧ����Ʒ��Ϣ
    private Double totalMoney;

    //�ṩ��Ӧ��getter/setter����
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
