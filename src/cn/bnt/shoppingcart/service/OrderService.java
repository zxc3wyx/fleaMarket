package cn.bnt.shoppingcart.service;

import cn.bnt.shoppingcart.beans.Order;
import cn.bnt.shoppingcart.dao.OrderDao;
import cn.bnt.shoppingcart.utils.BeanFactoryUtils;

public class OrderService {

    private final OrderDao orderDao = BeanFactoryUtils.getBean(OrderDao.class);

    public void createMyOrder(Order order) {
        orderDao.save(order);
    }
}
