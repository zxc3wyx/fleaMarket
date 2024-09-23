package cn.bnt.shoppingcart.service;

import java.util.List;

import cn.bnt.shoppingcart.beans.PageInfo;
import cn.bnt.shoppingcart.beans.Product;
import cn.bnt.shoppingcart.dao.ProductDao;
import cn.bnt.shoppingcart.utils.BeanFactoryUtils;

public class ProductService {
    //Ä£ÄâÒÀÀµ×¢Èë
    private final ProductDao productDao = BeanFactoryUtils.getBean(ProductDao.class);

    public List<Product> findProductsAll() {
        return productDao.findAll();
    }

    public PageInfo findProductsForPage(int pageNum, int pageSize) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(productDao.queryForPage(pageNum, pageSize));
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotalPages(productDao.totalPages(pageSize));
        return pageInfo;
    }

    public Product findByPid(String pid) {
        return productDao.findByPid(pid);
    }
}
