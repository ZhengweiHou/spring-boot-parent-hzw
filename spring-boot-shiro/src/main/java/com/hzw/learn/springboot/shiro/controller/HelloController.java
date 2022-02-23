package com.hzw.learn.springboot.shiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hello")
public class HelloController {

    int a=1;

    @RequestMapping(path = "/sayhello")
    public String sayHello(){
        return "hello!";
    }

    @RequestMapping(path = "/loginUrl")
    public String login(){
        return "login...!";
    }

    @RequestMapping(path = "/successUrl")
    public String successUrl(){
        return "success!";
    }

    @RequestMapping(path = "/unauthorizedUrl")
    public String unauthorizedUrl(){
        return "unauthorized!";
    }

    @RequestMapping(path = "/rememberme")
    public String rememberme(){
        return "rememberme!";
    }

    @RequestMapping(path = "/anon")
    public String anon(){
        return "anon! " + ++a;
    }

    @RequestMapping(path = "/authc")
    public String authc(){
        return "authc!";
    }



}
