package com.hzw.learn.springboot.springbase.Pattern.singleton.lazy;

/*
类加载时不创建实例，使用实例时，若实例不存在则初始化和赋值，若存在则直接使用
1. 使用时创建

懒加载时要考虑线程安全问题,该案例有着明显的线程安全问题
*/
public class Lazy_one {
    private Lazy_one(){}

    private static Lazy_one lazy = null;

    public static Lazy_one getInstance(){
        if(lazy == null)
            lazy = new Lazy_one();
        return lazy;
    }

}
