package com.hzw.learn.springboot.shiro.config;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * SecurityManager
     */
//    @Bean( name = "sm_def")
//    public SecurityManager securityManager(
//            @Autowired @Qualifier("realm_sa") Realm realm,
//            @Autowired @Qualifier("cm_memory") CacheManager  cacheManager
//    ){
//        DefaultSecurityManager dfSM = new DefaultSecurityManager();
//        dfSM.setRealm(realm);
//        dfSM.setCacheManager(cacheManager);
//        return dfSM;
//    }
    @Bean( name = "sm_defWeb")
    public SecurityManager webSecurityManager(
            @Autowired @Qualifier("realm_sa") Realm realm,
            @Autowired @Qualifier("cm_memory") CacheManager  cacheManager
    ){
        DefaultWebSecurityManager dfWebSM = new DefaultWebSecurityManager();
        dfWebSM.setRealm(realm);
        dfWebSM.setCacheManager(cacheManager);
        return dfWebSM;
    }
//    public SecurityManager securityManager1(){
//       return new IniSecurityManagerFactory("classpath:shiro.ini").getInstance();
//    }

    /**
     * CacheManager
     * 缓存控制器，来管理如用户、角色、权限等的缓存的；因为这些数据基本上很少去改变，放到缓存中后可以提高访问的性能
     */
    @Bean(name = "cm_memory")
    public CacheManager memoryConstrainedCacheManager(){
        return new MemoryConstrainedCacheManager();
    }

    /**
     * Realm
     */
    @Bean( name = "realm_sa")
    public Realm realm(){
        SimpleAccountRealm realm = new SimpleAccountRealm();
        realm.addAccount("houzw","123456","admin","houzw");
        realm.addAccount("houzw1","123456","admin","houzw1");
        return realm;
    }

    /**
     * shiroFilter
     * TODO 该过滤器被谁设置，何时调用
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(
            @Autowired @Qualifier("sm_defWeb") SecurityManager securityManager
    ){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        // customer filter
//        shiroFilter.setFilters();

        shiroFilter.setLoginUrl("/hello/loginUrl");
        shiroFilter.setSuccessUrl("/hello/successUrl");
        shiroFilter.setUnauthorizedUrl("/hello/unauthorizedUrl");

        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap(); // 有顺序，优先级递减
        filterChainDefinitionMap.put("/hello/anon","anon");         // 不需要认证
        filterChainDefinitionMap.put("/hello/rememberme","user");   // 验证通过或RemeberMe
        filterChainDefinitionMap.put("/hello/authc","authc");       // 需要认证
        filterChainDefinitionMap.put("/hello/loginUrl","anon");         // 不需要认证
        filterChainDefinitionMap.put("/hello/*","authc");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilter;
    }




}
