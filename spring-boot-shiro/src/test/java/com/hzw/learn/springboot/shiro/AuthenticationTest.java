package com.hzw.learn.springboot.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {

    SimpleAccountRealm realm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        realm.addAccount("houzw","123456","role_admin","role_houzw");
    }

    @Test
    public void testAuthentication(){

        // 1.构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm);
//        securityManager.setAuthenticator();

        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(securityManager); // 设置SecurityManager环境
        Subject subject = SecurityUtils.getSubject(); // 获取当前主体

        UsernamePasswordToken token = new UsernamePasswordToken("houzw", "123456");
        subject.login(token); // 登录

        // subject.isAuthenticated()方法返回一个boolean值,用于判断用户是否认证成功
        System.out.println("isAuthenticated:" + subject.isAuthenticated()); // 输出true

        Assert.assertTrue(subject.hasRole("role_admin"));
        subject.checkRole("role_admin");
        try {
            subject.checkRole("xxxx");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof UnauthorizedException);
        }

        subject.logout(); // 登出
        System.out.println("isAuthenticated:" + subject.isAuthenticated()); // 输出false

    }
}
