package cn.bnt.shoppingcart.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bnt.shoppingcart.beans.Product;
import cn.bnt.shoppingcart.beans.ShoppingCart;
import cn.bnt.shoppingcart.service.ProductService;
import cn.bnt.shoppingcart.utils.BeanFactoryUtils;

@WebServlet("/cart/*")
public class ShoppingCartServlet extends HttpServlet {

    private final ProductService service = BeanFactoryUtils.getBean(ProductService.class);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.endsWith("/add")) {
            //��ʾ�û�������ǰ���Ʒ��ӵ����ﳵ�Ĳ���
            String pid = request.getParameter("pid");
            if (pid != null) {
                Product p = service.findByPid(pid);
                //ͨ���ѹ��ﳵ������Session����
                HttpSession session = request.getSession();
                ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                if (cart == null) {
                    cart = new ShoppingCart();
                }
                cart.addProduct(p);
                //�������õ��Ự����
                session.setAttribute("cart", cart);
                //�ض�����ҳ
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } else if (path.endsWith("/show")) {
            //����ת����mycart.jspҳ��
            request.getRequestDispatcher("/WEB-INF/jsp/cart/mycart.jsp").forward(request, response);
        } else if (path.endsWith("/delete")) {
            HttpSession session = request.getSession();
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (cart != null) {
                String pid = request.getParameter("pid");
                System.out.println("pid=" + pid);
                if (pid != null) {
                    Product p = service.findByPid(pid);
                    cart.removeProduct(p);
                    //session.setAttribute("cart", cart);
                }

            }
            request.getRequestDispatcher("/WEB-INF/jsp/cart/mycart.jsp").forward(request, response);
        }

    }


}
