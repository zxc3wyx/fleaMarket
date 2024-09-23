package cn.bnt.shoppingcart.test;

import org.junit.Test;

import cn.bnt.shoppingcart.beans.Product;
import cn.bnt.shoppingcart.dao.ProductDao;
import cn.bnt.shoppingcart.utils.BeanFactoryUtils;

public class ProductDaoTest {

    @Test
    public void test() {
        ProductDao pdao = BeanFactoryUtils.getBean(ProductDao.class);

//		Product p = new Product("10001", "Java编程思想", 89.10, "1.png", "学习Java的一本非常经典的图书！");
//		Product p = new Product("10002", "JavaWeb开发技术详解", 77.90, "2.png", "学习Java Web技术的一本非常经典的图书！");
//		Product p = new Product("10003", "Java EE企业级应用开发", 69.00, "3.png", "学习SSM框架整合技术的一本非常好的图书！");
//		Product p = new Product("10004", "Spring Boot编程思想", 112.10, "4.png", "学习SpringBoot技术的一本非常好的图书！");
//
        Product p = new Product("10005", "Python编程", 70.20, "5.png", "学习Python语言的一本非常好的图书！");
        System.out.println(pdao.save(p));
    }

    @Test
    public void test2() {
        ProductDao pDao = BeanFactoryUtils.getBean(ProductDao.class);
        System.out.println(pDao.totalPages(2));
    }
}
