package cn.bnt.shoppingcart.beans;

public class Item {//���԰Ѹ���Ŀ������ɴ�ҹ�����Ʒ��һ���嵥

    private int id;//��ʾ��Ŀ�ı��
    private Product product;//��ʾ����Ŀ����Ӧ����Ʒ
    private int number = 1;//��ʾ�������Ʒ������,Ĭ��Ϊ1

    //�ṩ��Ӧ��getter/setter����
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
        //��ʾ����Ŀ���ܽ��
        double sumMoney;
        //ÿ����Ŀ���ܽ��=��Ʒ�ļ۸�*��Ʒ������
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
