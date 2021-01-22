package com.hzw.learn.springboot.springbase.Pattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
注册式单例
*/
public class RegisterBean {
    // 线程安全HashMap
    private static final Map<String,Object> ioc = new ConcurrentHashMap<>();
    public RegisterBean(){}

    public Object getBean(String className){
        if (!ioc.containsKey(className)){
            synchronized (ioc){
                if (!ioc.containsKey(className)){
                    try {
                        ioc.put(className,Class.forName(className).newInstance());
                    } catch (Exception e) { e.printStackTrace(); }
                }
            }
        }
        return ioc.get(className);
    }
}





