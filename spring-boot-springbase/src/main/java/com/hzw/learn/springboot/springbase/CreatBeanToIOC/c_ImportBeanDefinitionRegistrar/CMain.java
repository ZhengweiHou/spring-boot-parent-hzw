package com.hzw.learn.springboot.springbase.CreatBeanToIOC.c_ImportBeanDefinitionRegistrar;

import com.google.gson.Gson;
import com.hzw.learn.springboot.springbase.CreatBeanToIOC.z_BaseBean.H;
import com.hzw.learn.springboot.springbase.CreatBeanToIOC.z_BaseBean.W;
import com.hzw.learn.springboot.springbase.CreatBeanToIOC.z_BaseBean.Z;
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
@Import(CImportBeanDefinitionRegistrar.class)  // 引入自定义的ImportSelector,通过解析当前位置的注解W内容动态返回类名数组,构建对象
public class CMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext con =
                new AnnotationConfigApplicationContext(CMain.class);

        String[] names1 = con.getBeanNamesForType(H.class);
        String[] names2 = con.getBeanNamesForType(Z.class);

        System.out.println(new Gson().toJson(names1));
        System.out.println(new Gson().toJson(names2));
    }
}
