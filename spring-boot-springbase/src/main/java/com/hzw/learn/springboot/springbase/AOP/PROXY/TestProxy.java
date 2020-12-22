package com.hzw.learn.springboot.springbase.AOP.PROXY;

import com.hzw.learn.springboot.springbase.AOP.CGLIB.HzwHello$$EnhancerByCGLIB$$b2f16848;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

@FixMethodOrder(MethodSorters.JVM)
public class TestProxy {

    @Test
    public void test1() throws InterruptedException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /*
            正常思路反射获取目标对象，Spring容器中的对象本质上也是这种方式实例化Bean的（当然Proxy提供了更简便的方法来实例对象）
            1. 加载类
            2. 获取构造器对象
            3. 通过构造器实例化对象
         */
        Thread.sleep(100000);

        // 获取目标代理类
        Class<?> clazz = Proxy.getProxyClass(HelloApi.class.getClassLoader(), new Class[]{HelloApi.class});
        // 获取代理类中入参为InvocationHandler的构造器
        Constructor constructor = clazz.getConstructor(InvocationHandler.class);
        // 通过构造器实例化目标实例
        HelloApi hello = (HelloApi) constructor.newInstance(new HzwInvocationHandler(new HzwHello()));

        /**
         * 这里的hello对象，反编译后：{@link $Proxy5}
         **/

        // 调用代理对象的目标方法，注意，此时hello并不是HzwHello本体
        hello.sayHello();
    }

    // 该案例演示Proxy的用法，下面演示一下Proxy封装的简单实例方式（和上例没有本质区别）
    @Test
    public void testSimple() throws InterruptedException {
        HelloApi hello = (HelloApi) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),           // 类加载器
                new Class[]{HelloApi.class},                // 目标接口
                new HzwInvocationHandler(new HzwHello())    // 指定InvocationHandler（真正干活的）
        );

        /**
         * 这里的hello对象，反编译后：{@link $Proxy5} (和上面没有区别)
         **/

        hello.sayHello();
    }
}
