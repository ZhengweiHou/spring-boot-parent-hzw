package com.hzw.grpc.demo.server;

import io.grpc.Context;
import io.grpc.Metadata;

/**
 * @ClassName ContextHolder
 * @Description 当前线程上下文变量
 * @Author houzw
 * @Date 2023/7/21
 **/
public class ContextHolder {
    public static ThreadLocal<String> username = new ThreadLocal<>();

    public static String getUsername(){
        return username.get();
    }

    public static void setUsername(String username){
        ContextHolder.username.set(username);
    }

    public static final Metadata.Key<String> username_meta_key = Metadata.Key.of("username", Metadata.ASCII_STRING_MARSHALLER);
    public static final Context.Key<Metadata> METADATA_CONTEXT_KEY = Context.key("Metadata");

}
