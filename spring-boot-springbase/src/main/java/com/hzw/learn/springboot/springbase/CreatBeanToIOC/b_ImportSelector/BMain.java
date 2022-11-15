package com.hzw.learn.springboot.springbase.CreatBeanToIOC.b_ImportSelector;

import com.hzw.learn.springboot.springbase.CreatBeanToIOC.a_Import.AMain;
import com.hzw.learn.springboot.springbase.CreatBeanToIOC.z_BaseBean.H;
import com.hzw.learn.springboot.springbase.CreatBeanToIOC.z_BaseBean.W;
import com.hzw.learn.springboot.springbase.CreatBeanToIOC.z_BaseBean.Z;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * @ClassName BMain
 * @Description TODO
 * @Author houzw
 * @Date 2022/11/14
 **/
@W({
        "com.hzw.learn.springboot.springbase.CreatBeanToIOC.z_BaseBean.H"
        ,"com.hzw.learn.springboot.springbase.CreatBeanToIOC.z_BaseBean.Z"
})
@Import(BImportSelector.class)  // 引入自定义的ImportSelector,通过解析当前位置的注解W内容动态返回类名数组,构建对象
public class BMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext con =
                new AnnotationConfigApplicationContext(BMain.class);

        H h = con.getBean(H.class);
        Z z = con.getBean(Z.class);
        System.out.println(h);
        System.out.println(z);
    }
}
