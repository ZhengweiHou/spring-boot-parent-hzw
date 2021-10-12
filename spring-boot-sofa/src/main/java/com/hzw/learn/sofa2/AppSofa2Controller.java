package com.hzw.learn.sofa2;

import com.hzw.learn.sofa1.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppSofa2Controller {

    @Autowired
    HelloService helloService;

    @RequestMapping("/hello")
    public String hello(){

        return helloService.hello("hello");
    }
}
