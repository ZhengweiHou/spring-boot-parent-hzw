package com.hzw.learn.springboot.springbase.Pattern.singleton.hungry;

/*
类加载时就实例化对象
1. 快、效率高，即用即拿
2. 不管是否使用，该类都会被实例，浪费资源
*/
public class Hungry {
    private Hungry(){}

    private static final Hungry hungry = new Hungry();

    public static Hungry getInstance(){ return hungry;}
}
