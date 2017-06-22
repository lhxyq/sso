package com.lh.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by LH 2446059046@qq.com on 2017/6/22.
 */
public class LoginFilter  implements Filter{
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session =  request.getSession();

        boolean isLogin = session.getAttribute("isLogin") != null && (Boolean) session.getAttribute("isLogin");

        if (isLogin)
            filterChain.doFilter(servletRequest, servletResponse);

        String token = request.getParameter("token");
        if (null == token) {
            String reUrl = "http://localhost:8081/sys001";
            response.sendRedirect("http://localhost:8080/user/test?reUrl=" + reUrl);
        } else {
            //验证token
            
        }

    }

    public void destroy() {

    }
}
