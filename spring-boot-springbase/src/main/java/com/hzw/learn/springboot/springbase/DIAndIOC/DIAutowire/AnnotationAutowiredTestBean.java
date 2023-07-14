package com.hzw.learn.springboot.springbase.DIAndIOC.DIAutowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @ClassName AnnotationAutowiredTestBean
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/2
 **/

/*
1. @Autowired 优先byName方式 进行注入，若找不到同名Bean，则使用byType方式 进行注入
2. @Autowried加在变量上，实际上会通过field.set方式进行注入，而非通过setter进行注入
*/

@Component
public class AnnotationAutowiredTestBean {

    @Autowired         // 变量上使用@Autowired注解，不会调用这里的set方法
    private ABean aBean; // 注入时 spring 容器会使用属性名从容器中寻找 bean 注入

    private ABean annotationABean;

    public void setaBean(ABean aBean) {
        System.out.println("" + this.getClass().getName() + "的setaBean方法执行");
        System.out.println("变量上使用@Autowired注解，不会调用这里的set方法");
        System.out.println("其实际上是使用反射，通过field.set的方式进行赋值的");
        this.aBean = aBean;
    }

    @Autowired
    public void setAnnotationABean(ABean annotationABean) {
        System.out.println("" + this.getClass().getName() + "的setAnnotationABean方法执行");
        this.annotationABean = annotationABean;
    }



    public void sayHello(){
        Arrays.stream(this.getClass().getDeclaredFields()).forEach(field -> {
            field.setAccessible(true);
            try {
                System.out.println(field.getName() + ":" +((ABean)field.get(this)).getName() );
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

    }

}
