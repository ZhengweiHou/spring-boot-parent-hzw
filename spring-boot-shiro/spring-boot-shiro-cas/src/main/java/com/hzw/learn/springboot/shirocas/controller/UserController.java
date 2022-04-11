package com.hzw.learn.springboot.shirocas.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Controller
@RestController
public class UserController {
	@Value("${server.port}")
	int SERVER_PORT;

	@GetMapping("/hello")
	public String hello(){
		return "hello登录成功";
	}

	@GetMapping("/")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("role","VISITOR!");
		return mav;
	}

	@RequiresPermissions({"admin"}) // shiro 权限点注解
	@GetMapping("/admin")
	public String admin(){
		return "admin page";
	}

	@GetMapping("/user")
	public String user(HttpServletRequest request){
		Object a = SecurityUtils.getSubject().getPrincipal();
		return "当前登录用户：" + a;
	}

	@GetMapping("/logouttips")
	public String logoutTips(){
		return "登出成功！！";
	}

	@GetMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SecurityUtils.getSubject().logout();
		WebUtils.issueRedirect(request, response, "http://localhost:8443/cas/logout?service=http://localhost:"+SERVER_PORT+"/logouttips");
	}
}
