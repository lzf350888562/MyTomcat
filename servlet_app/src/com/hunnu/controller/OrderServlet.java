package com.hunnu.controller;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 使用注解
 * @author lzf
 * @date 2021/2/23
 */
@WebServlet("/abc")
public class OrderServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String header = req.getHeader("content-type");
        System.out.println(header);
        //服务器按utf-8处理数据
        //resp.setCharacterEncoding("utf-8");
        //服务器和浏览器按utf-8处理数据
        resp.addHeader("content-type","text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>hello 注解</h1>");
        writer.flush();
        writer.close();
        
    }
}
