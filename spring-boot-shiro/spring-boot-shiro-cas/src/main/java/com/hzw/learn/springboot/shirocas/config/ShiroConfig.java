package com.hzw.learn.springboot.shirocas.config;

import com.hzw.learn.springboot.shirocas.shiro.MyCasRealm;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    private Environment environment;
    String SERVER_PORT = "";

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //配置自定义casFilter
        Map<String, Filter> filters = new LinkedHashMap<>();
        CasFilter casFilter = new CasFilter();
        casFilter.setFailureUrl("/casFailure");
        filters.put("cas", casFilter);
        shiroFilterFactoryBean.setFilters(filters);

        //配置filter调用链
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        //anno为匿名，不拦截
        filterChainDefinitionMap.put("/static/**", "anon");
//        filterChainDefinitionMap.put("/hello/**", "anon");
        filterChainDefinitionMap.put("/casFailure", "anon");
        //拦截CAS Server返回的ticket
        filterChainDefinitionMap.put("/cas", "cas");
        //退出登录
        filterChainDefinitionMap.put("/logout", "anon");
        filterChainDefinitionMap.put("/logouttips", "anon");
        //需要登录访问的页面
        filterChainDefinitionMap.put("/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        //service指定了登录成功后的回调地址，回调/cas将被CasFilter拦截，获取服务端返回的Service Ticket进行登录
        shiroFilterFactoryBean.setLoginUrl("http://localhost:8443/cas/login?service=http://localhost:"+ SERVER_PORT +"/cas");
        //登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/hello");
        //未授权跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        return shiroFilterFactoryBean;
    }


    @Bean
    public MyCasRealm casRealm(){
        //使用自定义Realm
        MyCasRealm casRealm = new MyCasRealm();
        casRealm.setCachingEnabled(true);
        casRealm.setAuthenticationCachingEnabled(true);
        casRealm.setAuthenticationCacheName("authenticationCache");
        casRealm.setAuthorizationCachingEnabled(true);
        casRealm.setAuthorizationCacheName("authorizationCache");
        //指定CAS服务端地址
        casRealm.setCasServerUrlPrefix("http://localhost:8443/cas");
        //当前应用的CAS服务URL，用于接收和处理CAS服务端的Ticket
        casRealm.setCasService("http://localhost:"+SERVER_PORT+"/cas");
        return casRealm;
    }

    @Bean
    @DependsOn(value = "casRealm")
    public SecurityManager securityManager(@Autowired CasRealm casRealm){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(casRealm);
        return securityManager;
    }

    /**
     * Shiro生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启Shiro AOP的注解支持(如@RequiresRoles,@RequiresPermissions)
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
