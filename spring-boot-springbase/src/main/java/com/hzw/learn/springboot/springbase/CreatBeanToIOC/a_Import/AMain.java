package com.hzw.learn.springboot.springbase.CreatBeanToIOC.a_Import;

import com.hzw.learn.springboot.springbase.CreatBeanToIOC.z_BaseBean.H;
import com.hzw.learn.springboot.springbase.CreatBeanToIOC.z_BaseBean.Z;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @ClassName AMain
 * @Description TODO
 * @Author houzw
 * @Date 2022/11/14
 **/

@Import({H.class, Z.class}) // 指定类名,让spring为指定类创建对象
@ComponentScan
public class AMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext con =
                new AnnotationConfigApplicationContext(AMain.class);

        H h = con.getBean(H.class);
        Z z = con.getBean(Z.class);
        System.out.println(h);
        System.out.println(z);
    }
}
