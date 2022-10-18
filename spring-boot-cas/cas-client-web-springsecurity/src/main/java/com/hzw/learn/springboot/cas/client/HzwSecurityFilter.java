package com.hzw.learn.springboot.cas.client;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @ClassName HzwSecurityFilter
 * @Description TODO
 * @Author houzw
 * @Date 2022/9/30
 **/
public class HzwSecurityFilter extends GenericFilterBean {

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("====" + this.getClass().getSimpleName());
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
