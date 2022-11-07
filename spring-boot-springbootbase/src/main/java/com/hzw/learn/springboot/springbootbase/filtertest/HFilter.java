package com.hzw.learn.springboot.springbootbase.filtertest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName HFilter
 * @Description TODO
 * @Author houzw
 * @Date 2022/11/4
 **/
public class HFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("====HFilter:url["+req.getRequestURL().toString()+"]====");
        chain.doFilter(request,response);
    }
}
