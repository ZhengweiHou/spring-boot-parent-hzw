package com.hzw.grpc.demo.api;


import java.util.Date;
import java.util.HashMap;

/**
 * @ClassName HzwApi
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/21
 **/
public interface HzwApi {
    public String  sayHello(String name);
    public int     sayHello(int num);
    public HashMap  sayMap(HashMap map);
    public boolean sayHello(boolean b);
    public Integer sayHello(Integer b);
    public String sayHello(String name, int age);
    public String sayHello(String name, Date date);
    public void voidHello(String name);
    public void exceptionHello(String name);
}
