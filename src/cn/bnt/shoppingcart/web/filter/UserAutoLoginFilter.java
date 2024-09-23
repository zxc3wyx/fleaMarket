package cn.bnt.shoppingcart.web.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bnt.shoppingcart.beans.User;
import cn.bnt.shoppingcart.service.UserService;
import cn.bnt.shoppingcart.utils.BeanFactoryUtils;

//该过滤器的作用是实现用户的自动登录功能
@WebFilter(value = "/*")
public class UserAutoLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //获取所有的 Cookie，然后进行比对
        String uid = null;
        String loginPwd = null;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            String cname = cookies[i].getName();
            if (cname.equalsIgnoreCase("uid")) {
                uid = cookies[i].getValue();
                break;
            }
        }
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            String cname = cookies[i].getName();
            if (cname.equalsIgnoreCase("loginPwd")) {
                loginPwd = cookies[i].getValue();
                break;
            }
        }
        if (uid != null && loginPwd != null) {
            UserService us = BeanFactoryUtils.getBean(UserService.class);
            User user = us.loginUser(uid, loginPwd);
            //把该用户保存到Session域中
            request.getSession().setAttribute("user", user);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
