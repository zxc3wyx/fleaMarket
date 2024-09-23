package cn.bnt.shoppingcart.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bnt.shoppingcart.beans.Order;
import cn.bnt.shoppingcart.beans.ShoppingCart;
import cn.bnt.shoppingcart.beans.User;
import cn.bnt.shoppingcart.service.OrderService;
import cn.bnt.shoppingcart.service.UserService;
import cn.bnt.shoppingcart.utils.BeanFactoryUtils;
import cn.bnt.shoppingcart.utils.WebUtils;

@WebServlet("/user/*")
public class UserControrller extends HttpServlet {

    private final UserService userService = BeanFactoryUtils.getBean(UserService.class);
    private final OrderService orderService = BeanFactoryUtils.getBean(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //��ȡ����·���������з�������
        String path = request.getRequestURI();
        if (path.endsWith("/register")) {//������һ��ע���û�������
            //��������ķ�ʽ
            String method = request.getMethod();
            if ("GET".equalsIgnoreCase(method)) {//��ע������ͻ���ʾ
                request.getRequestDispatcher("/WEB-INF/jsp/user/register.jsp").forward(request, response);
			} else if ("POST".equalsIgnoreCase(method)) {
                //��ȡ�������ݣ������зǿ���Ϸ���У��
                Map<String, String> errors = WebUtils.validate(request);
                if (errors.size() > 0) {
                    //����У��ʧ��
                    request.setAttribute("errors", errors);
                    request.getRequestDispatcher("/WEB-INF/jsp/user/register.jsp").forward(request, response);
                    return;
                }
                //���������ռ�
                User user = WebUtils.request2Bean(request, User.class);
                user.setUid(WebUtils.getUserUid());
                //���÷�����Ӧ�ķ��񷽷�
                userService.register(user);//��ת����ҳ�棬�����û�����Ϣ���浽�Ự����
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
			}


        } else if (path.endsWith("/login")) {//������һ����½����
            //��ȡ����ķ�ʽ
            String method = request.getMethod();
            if ("POST".equalsIgnoreCase(method)) {
                //���б��ǿ�У��
                Map<String, String> errors = WebUtils.validate(request);
                if (errors.size() > 0) {
                    request.setAttribute("errors", errors);
                    //����ת������¼ҳ��
                    request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
                    return;
                }
                //��ȡ����д������
                String uid = request.getParameter("uid");
                String loginPwd = request.getParameter("loginPwd");
                //�������ݿ�ĺϷ��Բ�ѯУ��
                User user = userService.loginUser(uid, loginPwd);

                if (user != null) {
                    System.out.println(user);
                    //����û��Ƿ�ѡ�����Զ���¼��ѡ��
                    boolean ifAutoLogin = Boolean.parseBoolean(request.getParameter("isAutoLogin"));
                    if (ifAutoLogin) {//��ʾѡ����
                        //�ѺϷ����û���Ϣ���浽cookie��
                        Cookie cookie = new Cookie("uid", user.getUid());
                        //������Ч��
                        cookie.setMaxAge(30 * 24 * 60 * 60);//����һ����
                        //������Ч����·��
                        cookie.setPath("/");
                        //�Ѹ�Cookie������ӵ�response��������
                        response.addCookie(cookie);
                        Cookie c2 = new Cookie("loginPwd", user.getLoginPwd());
                        c2.setMaxAge(30 * 24 * 60 * 60);
                        c2.setPath("/");
                        response.addCookie(c2);
                    }
                    //Ҫ��user�������õ��Ự������
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect(request.getContextPath() + "/index.jsp");

                }


            }


        } else if (path.endsWith("/order")) {
            //��ʾ�û�Ҫȷ���µ�
            Order order = null;
            //��ȡSession���б�����û���Ϣ�����й��ﳵ��Ϣ
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (user != null && cart != null) {
                order = new Order();
                order.setOid(WebUtils.getOrderOid());
                order.setShoppingCart(cart);
                order.setUser(user);
                //���ö��������еĴ�����������
                orderService.createMyOrder(order);
                request.setAttribute("order", order);
                //�ѵ�ǰ�û����ﳵ�е���Ϣ��յ�
                session.removeAttribute("cart");
                request.getRequestDispatcher("/WEB-INF/jsp/order/order.jsp").forward(request, response);
			}
        }
    }


}
