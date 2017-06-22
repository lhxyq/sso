package com.lh.authority;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by LH 2446059046@qq.com on 2017/6/22.
 */
public class Login extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String pass = req.getParameter("pass");
        HttpSession session = req.getSession();

        if ("lh".equals(username) && "lh".equals(pass)) {
            session.setAttribute("isLogin", true);
            session.setAttribute("token", UUID.randomUUID().toString());
        } else {
            resp.sendRedirect("login.jsp");
        }
    }
}
