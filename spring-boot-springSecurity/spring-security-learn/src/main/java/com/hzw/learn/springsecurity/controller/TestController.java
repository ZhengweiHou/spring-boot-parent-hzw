package com.hzw.learn.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("hello")
    public String hello(){
        return "hello security!";
    }

    @RequestMapping("hellopage")
    public ModelAndView helloPage(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg",
                "我是controller返回的msg，时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
//        mav.setViewName("/WEB_INF/pages/hello.jsp");

//        因springmvc-config.xml中配置了
//        <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//            <property name="prefix" value="/WEB-INF/pages/"/>
//            <property name="suffix" value=".jsp"/>
//        </bean>
//        所以这里viewName只要是hello就定位到/WEB_INF/pages/hello.jsp了
        mav.setViewName("hello");
        return mav;
    }

    // 注入攻击测试，例：http://localhost:8003/test/xssltest?msg=%3CScRiPt%3Ealert(1)%3C/sCrIpT%3E
    @RequestMapping("xssltest")
    public ModelAndView xssltest(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {

        String msg = req.getParameter("msg");
        String ch = req.getCharacterEncoding();
        msg = new String(msg.getBytes("ISO-8859-1"),"UTF-8");
        System.out.println("msg:" + msg);
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg",msg);
        mav.setViewName("hello");
        return mav;
    }
}

