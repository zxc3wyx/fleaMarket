package cn.bnt.shoppingcart.test;

import org.junit.Test;

import cn.bnt.shoppingcart.beans.Product;
import cn.bnt.shoppingcart.dao.ProductDao;
import cn.bnt.shoppingcart.utils.BeanFactoryUtils;

public class ProductDaoTest {

    @Test
    public void test() {
        ProductDao pdao = BeanFactoryUtils.getBean(ProductDao.class);

//		Product p = new Product("10001", "Java���˼��", 89.10, "1.png", "ѧϰJava��һ���ǳ������ͼ�飡");
//		Product p = new Product("10002", "JavaWeb�����������", 77.90, "2.png", "ѧϰJava Web������һ���ǳ������ͼ�飡");
//		Product p = new Product("10003", "Java EE��ҵ��Ӧ�ÿ���", 69.00, "3.png", "ѧϰSSM������ϼ�����һ���ǳ��õ�ͼ�飡");
//		Product p = new Product("10004", "Spring Boot���˼��", 112.10, "4.png", "ѧϰSpringBoot������һ���ǳ��õ�ͼ�飡");
//
        Product p = new Product("10005", "Python���", 70.20, "5.png", "ѧϰPython���Ե�һ���ǳ��õ�ͼ�飡");
        System.out.println(pdao.save(p));
    }

    @Test
    public void test2() {
        ProductDao pDao = BeanFactoryUtils.getBean(ProductDao.class);
        System.out.println(pDao.totalPages(2));
    }
}
