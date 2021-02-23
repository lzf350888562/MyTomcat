package com.hunnu.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lzf
 * @date 2021/2/23
 */
public class UserServlet extends HttpServlet {
    /**
     * 启动服务器时显示
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        System.out.println("User被加载");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String header = req.getHeader("content-type");
        System.out.println(header);
        //服务器按utf-8处理数据
        //resp.setCharacterEncoding("utf-8");
        //服务器和浏览器按utf-8处理数据
        resp.addHeader("content-type","text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>hello XML文档  User</h1>");
        writer.flush();
        writer.close();
        
    }
}
