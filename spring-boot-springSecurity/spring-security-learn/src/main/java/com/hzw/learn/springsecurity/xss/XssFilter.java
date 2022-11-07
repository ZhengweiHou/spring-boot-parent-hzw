package com.hzw.learn.springsecurity.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName XssFilter
 * @Description 防止注入行为
 * @Author houzw
 * @Date 2022/11/1
 **/
public class XssFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        XssHttpServletRequestWrapper xsslReq = new XssHttpServletRequestWrapper((HttpServletRequest) request);

        chain.doFilter(xsslReq,response);
    }

    public void destroy() {

    }
}
