package cn.bnt.shoppingcart.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bnt.shoppingcart.beans.PageInfo;
import cn.bnt.shoppingcart.beans.Product;
import cn.bnt.shoppingcart.service.ProductService;
import cn.bnt.shoppingcart.utils.BeanFactoryUtils;

@WebServlet("/product/*")
public class ProductServlet extends HttpServlet {

    private final ProductService service = BeanFactoryUtils.getBean(ProductService.class);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.endsWith("/shows")) {
            //List<Product> list=service.findProductsAll();
            String page = request.getParameter("page");
            int pageNum = 0;
            if (page != null) {
                pageNum = Integer.parseInt(page);
            }
            System.out.println(pageNum);
            PageInfo pageInfo = service.findProductsForPage(pageNum, 2);
            //�Ѽ������õ���������
            request.setAttribute("pageInfo", pageInfo);
            //����ת������ʾҳ��
            request.getRequestDispatcher("/WEB-INF/jsp/product/shows.jsp").forward(request, response);
        }
    }


}
