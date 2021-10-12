package com.hzw.learn.sofa1;

public class HelloServiceImpl implements HelloService{
    @Override
    public String hello(String mesg) {
        System.out.println("接收到消息：" + mesg);
        return "你好，我是Hello服务端";
    }
}
