package com.hzw.learn.springboot.springbase.DI.DIAutowire;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * @ClassName ConstructorAutowiredTestBean
 * @Description 构造器自动注入测试类
 * @Author houzw
 * @Date 2020/7/16
 **/
public class ConstructorAutowiredTestBean {
    private ABean aBean;

    @Autowired
    public ConstructorAutowiredTestBean(ABean aBean) {
        this.aBean = aBean;
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
