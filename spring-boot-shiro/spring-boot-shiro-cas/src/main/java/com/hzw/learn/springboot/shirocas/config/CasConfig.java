package com.hzw.learn.springboot.shirocas.config;

import com.hzw.learn.springboot.shirocas.cas.MyRedisSessionMappingStorage;
import org.jasig.cas.client.session.SessionMappingStorage;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CasConfig {

    @Bean
    public SessionMappingStorage sessionMappingStorage(){
        return new MyRedisSessionMappingStorage();
    }

    @Bean
    public FilterRegistrationBean singleSignOutFilterRegistration() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setSessionMappingStorage(sessionMappingStorage()); // 自定义的session管理

        FilterRegistrationBean registration = new FilterRegistrationBean(singleSignOutFilter); // CAS处理过滤器，用于处理recordSession和 destroySession;
        registration.addUrlPatterns("/*");
//        registration.setOrder(-1); // TODO 是否需要保证该过滤器优先级在shiroFilter之前？
        return registration;
    }

    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutListenerRegistration() {
        SingleSignOutHttpSessionListener singleSignOutHttpSessionListener = new SingleSignOutHttpSessionListener();

        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> registration =
                new ServletListenerRegistrationBean<>(singleSignOutHttpSessionListener);
        return registration;
    }
}
