package com.hzw.learn.springboot.springbase.DIAndIOC.DIAutowire;

import java.util.Arrays;

/**
 * @ClassName XmlAutowireTestBean
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/2
 **/
public class XmlAutowireTestBean {
    private ABean aBean1;
    private ABean aBean2;

    public void setaBean1(ABean aBean1) {
        System.out.println("" + this.getClass().getName() + "的setaBean1方法执行");
        this.aBean1 = aBean1;
    }

    public void setaBean2(ABean aBean2) {
        System.out.println("" + this.getClass().getName() + "的setaBean2方法执行");
        this.aBean2 = aBean2;
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
