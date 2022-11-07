package com.hzw.learn.springboot.springbootbase.filtertest;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @ClassName FiltertestConfiguration
 * @Description TODO
 * @Author houzw
 * @Date 2022/11/4
 **/
@Configuration
public class FiltertestConfiguration {
    @Bean
    public FilterRegistrationBean hFilterRegister(){
        FilterRegistrationBean<Filter> hfregBean = new FilterRegistrationBean<>();
        hfregBean.setFilter(new HFilter());
        hfregBean.addUrlPatterns(new String[]{"/*"});
        return hfregBean;
    }

    @Bean
    public ServletRegistrationBean newServlet(){
        return new ServletRegistrationBean(new NewServlet(), "/newservlet/*");
    }

}
