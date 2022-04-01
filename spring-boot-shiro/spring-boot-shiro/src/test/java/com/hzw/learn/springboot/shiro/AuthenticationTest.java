package com.hzw.learn.springboot.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
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
//        realm.setRolePermissionResolver();
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

        UsernamePasswordToken token = new UsernamePasswordToken("houzw", "1234567");
        // 3. 登录
        try {
            subject.login(token); // 登录
        }catch (UnknownAccountException uas){
            // 未知的用户名
            log("There is no user with username of" + token.getPrincipal());
        }catch (IncorrectCredentialsException ice){
            // 用户存在但密码不匹配
            log("Password for account" + token.getPrincipal() + " was incorrect");
        }catch (LockedAccountException lae){
            // 账户被锁定
            log("The account for username " + token.getPrincipal() + " is locked.");
        }catch (AuthenticationException ae){
            // 其他未知的情况
        }

        // subject.isAuthenticated()方法返回一个boolean值,用于判断用户是否认证成功
        log("isAuthenticated:" + subject.isAuthenticated()); // 输出true

        // 4. 检查用户角色
        Assert.assertTrue(subject.hasRole("role_admin"));
        subject.checkRole("role_admin");

        try {
            subject.checkRole("xxxx");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof UnauthorizedException);
        }

        subject.logout(); // 登出
        log("isAuthenticated:" + subject.isAuthenticated()); // 输出false
    }

    public static void log(String str){
        System.out.println(str);
    }
}
