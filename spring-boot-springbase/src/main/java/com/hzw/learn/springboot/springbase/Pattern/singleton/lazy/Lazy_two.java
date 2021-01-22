package com.hzw.learn.springboot.springbase.Pattern.singleton.lazy;
/*
使用 synchronized 给实例方法加上同步锁，避免线程安全问题，但很影响效率
*/
public class Lazy_two {
    private Lazy_two(){}

    private static Lazy_two lazy = null;

    public static synchronized Lazy_two getInstance(){
        if(lazy == null)
            lazy = new Lazy_two();
        return lazy;
    }
}
