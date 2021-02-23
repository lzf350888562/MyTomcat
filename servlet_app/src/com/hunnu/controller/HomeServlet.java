package com.hunnu.controller;

import javax.servlet.ServletConfig;
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
public class HomeServlet extends HttpServlet {
    private ServletConfig config = null;
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config=config;
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //输出xml文件传来的参数
        System.out.println(config.getInitParameter("a"));
        System.out.println(config.getInitParameter("b"));
        
        
        String header = req.getHeader("content-type");   //null  默认为html
        System.out.println(header);
        //服务器按utf-8处理数据
        //resp.setCharacterEncoding("utf-8");
        //服务器和浏览器按utf-8处理数据
        resp.addHeader("content-type","text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>hello XML文档 Home</h1>");
        writer.flush();
        writer.close();
        
    }
}
