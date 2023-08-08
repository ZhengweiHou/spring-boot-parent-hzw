package com.hzw.grpc.demo.api;

/**
 * @ClassName HzwApi
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/21
 **/
public interface HzwApi {
    public String sayHello(String name);

    public void voidHello(String name);
}
