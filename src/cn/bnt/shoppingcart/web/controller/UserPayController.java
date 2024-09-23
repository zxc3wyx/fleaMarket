package cn.bnt.shoppingcart.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bnt.shoppingcart.beans.User;

@WebServlet("/pay")
public class UserPayController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.首先判断用户是否已经登录，如果登录，则会用一个页面来显示订单的详情及用户的详情，如果没有登录，则重定向到用户登录界面
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {//表名用户还没有登录
            //请求转发来处理（因为我们都会把JSP页面存到WEB-INF目录下，请求重定向做不到）
            request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
		} else {
            //请求转发到订单页面
            request.getRequestDispatcher("/WEB-INF/jsp/order/orderInfo.jsp").forward(request, response);
		}
    }


}
