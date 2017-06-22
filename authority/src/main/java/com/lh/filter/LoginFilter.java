package com.lh.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by LH 2446059046@qq.com on 2017/6/22.
 */
public class LoginFilter implements Filter {
    private String[] excluded;

    public void init(FilterConfig filterConfig) throws ServletException {
        excluded = filterConfig.getInitParameter("excludedURLs").split(";");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session =  request.getSession();

        boolean isExcluded = false;
        if (null != excluded && 0 < excluded.length) {
            for (String s : excluded) {
                if (request.getRequestURI().equals(s))
                    isExcluded = true;
            }
        }

        boolean isLogin = session.getAttribute("isLogin") != null && (Boolean) session.getAttribute("isLogin");

        if (isLogin || isExcluded)
            filterChain.doFilter(servletRequest, servletResponse);
        else
            request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    public void destroy() {

    }
}
