package com.lh.authority;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by LH 2446059046@qq.com on 2017/6/22.
 */
public class AuthorityCenter extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        PrintWriter out = response.getWriter();

        ServletContext context = request.getServletContext();
        context.getAttribute("token");

        String token = req.getParameter("token");
        if (token.equals(context.getAttribute("token")))
            out.write("true");
        else
            out.write("false");
    }
}
