package com.hzw.learn.springboot.cas.server.config;

import com.hzw.learn.springboot.cas.server.auth.HzwAuthenticationHandler;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName HzwAuthenticationConfiguration
 * @Description TODO
 * @Author houzw
 * @Date 2022/4/2
 **/
@Configuration
@EnableConfigurationProperties(CasConfigurationProperties.class)
@ComponentScan("com.hzw.learn.springboot.cas.server")
public class HzwAuthenticationConfiguration implements AuthenticationEventExecutionPlanConfigurer {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired ServicesManager servicesManager;

    @Bean
    public AuthenticationHandler hzwAUthenticationHandler(){
        log.info("==new hzwAUthenticationHandler==>");
        return new HzwAuthenticationHandler(HzwAuthenticationHandler.class.getName(),servicesManager,new DefaultPrincipalFactory(),1);
    }

    public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
        log.info("==register hzwAUthenticationHandler==>");
        plan.registerAuthenticationHandler(hzwAUthenticationHandler());

    }
}
