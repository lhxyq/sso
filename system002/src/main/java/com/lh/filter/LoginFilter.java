package com.lh.filter;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by LH 2446059046@qq.com on 2017/6/22.
 */
public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        boolean isLogion = session.getAttribute("isLogin") != null && (Boolean) session.getAttribute("isLogin");
        if (isLogion) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = (String) request.getParameter("token");
        if (null == token) {
            String reUrl = "http://localhost:8082/sys002";
            response.sendRedirect("http://localhost:8080/user/index?reUrl=" + reUrl);
        } else {
            //验证token
            CloseableHttpClient httpClient = null;
            CloseableHttpResponse httpResponse = null;

            httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://localhost:8080/user/verify?token=" + token);
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String result = EntityUtils.toString(httpEntity);

            if ("true".equals(result)) {
                session.setAttribute("isLogin", true);
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                System.out.println("verity fail...");
                return;
            }
        }
    }

    public void destroy() {

    }
}
