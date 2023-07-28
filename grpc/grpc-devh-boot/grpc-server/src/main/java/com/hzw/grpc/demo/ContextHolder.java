package com.hzw.grpc.demo;

/**
 * @ClassName ContextHolder
 * @Description 当前线程上下文变量
 * @Author houzw
 * @Date 2023/7/21
 **/
public class ContextHolder {
    private static ThreadLocal<String> username = new ThreadLocal<>();

    public static String getUsername(){
        return username.get();
    }

    public static void setUsername(String username){
        ContextHolder.username.set(username);
    }
}
