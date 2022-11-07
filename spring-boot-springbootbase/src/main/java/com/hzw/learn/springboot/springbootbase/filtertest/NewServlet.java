package com.hzw.learn.springboot.springbootbase.filtertest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName NewServlet
 * @Description TODO
 * @Author houzw
 * @Date 2022/11/4
 **/
public class NewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
       resp.getOutputStream().write("test".getBytes());
    }
}
