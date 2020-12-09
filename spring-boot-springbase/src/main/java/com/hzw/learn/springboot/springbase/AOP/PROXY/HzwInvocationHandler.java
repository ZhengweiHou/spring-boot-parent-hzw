package com.hzw.learn.springboot.springbase.AOP.PROXY;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HzwInvocationHandler<T> implements InvocationHandler {

    T holder;

    public HzwInvocationHandler(T beHolder){
        this.holder = beHolder; // 真正被代理的对象，作为一个属性注入到handler中，被invoker代理方法中使用
        // （其实本质上代理对象和我们要切面的目标实例没有关联关系，这里通过holder强行注入目标实例，实现所谓对holder的AOP封装
        //  从这里看，proxy的代理方式和CGLIB是不一样的，CGLIB的代理对象直接是目标类的子类，其自身就继承了目标类的所有方法）
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行前...");       // 所谓的前切面
        Object object = method.invoke(holder,args);    // 真正执行目标对象的方法，，注意，入参proxy是InvocationHandler本身
        System.out.println("执行前...");       // 所谓的后切面
        return object;
    }
}
