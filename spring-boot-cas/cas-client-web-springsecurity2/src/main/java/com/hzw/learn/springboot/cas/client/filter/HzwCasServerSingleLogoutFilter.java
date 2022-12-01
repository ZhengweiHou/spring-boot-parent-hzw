package com.hzw.learn.springboot.cas.client.filter;

import org.jasig.cas.client.util.AbstractConfigurationFilter;
import org.jasig.cas.client.util.CommonUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName HzwCasServerSingleLogoutFilter
 * @Description TODO
 * @Author houzw
 * @Date 2022/10/27
 **/
public class HzwCasServerSingleLogoutFilter extends AbstractConfigurationFilter {

    private String logoutParameterName = "logoutRequest";

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;

        if(this.isLogoutRequest(request)){
            System.out.println("cas-server的单点登出请求");
        }
        filterChain.doFilter(request,servletResponse);
    }

    public void destroy() {

    }


    private boolean isLogoutRequest(HttpServletRequest request){
        return "POST".equals(request.getMethod()) &&
                CommonUtils.isNotBlank(CommonUtils.safeGetParameter(request, this.logoutParameterName));
    }


}

