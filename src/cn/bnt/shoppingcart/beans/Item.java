package cn.bnt.shoppingcart.beans;

public class Item {//可以把该条目类想象成大家购买商品的一个清单

    private int id;//表示条目的编号
    private Product product;//表示该条目所对应的商品
    private int number = 1;//表示购买该商品的数量,默认为1

    //提供对应的getter/setter方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Double getSumMoney() {
        //表示该条目的总金额
        double sumMoney;
        //每个条目的总金额=商品的价格*商品的数量
        sumMoney = this.getNumber() * this.getProduct().getPrice();
        return sumMoney;
    }

    public Item() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Item(int id, Product product, int number) {
        super();
        this.id = id;
        this.product = product;
        this.number = number;
    }

}
