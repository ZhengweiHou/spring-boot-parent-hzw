package com.hzw.learn.springboot.springbase.Pattern.singleton.lazy;
/*
- 防止单例类被反射入侵，防止重复调用单例类构造器
- 使用内部类实现懒加载
    内部类在调用前不会被加载，当getInstance方法调用内部类时，才会将内部类加载到方法区，其静态变量会在加载时被初始化
    巧妙的使用了内部类，兼顾防止内存浪费和synchronized性能问题
*/
public class Lazy_three {
    private static boolean initialized = false;
    private Lazy_three(){
        synchronized (Lazy_three.class){
            if(!initialized)
                initialized = true;
            else
                // 防止多次构造
                throw new RuntimeException("单例对象不可重复实例化");
        }
    }

    // final 防止方法被重载
    public static final Lazy_three getInstance(){
        return Lazy_three_holder.lazy;
    }

    private static class Lazy_three_holder{
        private static final Lazy_three lazy = new Lazy_three();
    }
}
