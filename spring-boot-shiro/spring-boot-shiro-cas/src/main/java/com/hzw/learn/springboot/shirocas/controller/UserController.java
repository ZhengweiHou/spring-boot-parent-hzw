package com.hzw.learn.springboot.shirocas.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//@Controller
@RestController
public class UserController {

	@GetMapping("/hello")
	public String hello(){
		return "hello";
	}

	@GetMapping("/")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("role","VISITOR!");
		return mav;
	}

	@RequiresPermissions({"admin"})
	@GetMapping("/admin")
	public ModelAndView admin(){
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("role","ADMIN!");
		return mav;
	}

	@GetMapping("/logouttips")
	public ModelAndView logoutTips(){
		ModelAndView mav = new ModelAndView("tips");
		return mav;
	}

	@GetMapping("/logout")
	public String logout(){
		SecurityUtils.getSubject().logout();
		return "redirect:http://localhost:8443/cas/logout?service=http://localhost:8081/logouttips";
	}
}
