package com.hzw.learn.springboot.springbase.DIAndIOC.DIBeanConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName BeanConfigBeImport
 * @Description TODO
 * @Author houzw
 * @Date 2023/2/23
 **/
@Configuration //
public class BeanConfigBeImport {
    @Bean
    public ABean aBean2(){
        System.out.println("aBean init");
        ABean aBean = new ABean();
        aBean.setName("hzw");
        return aBean;
    }
    @Bean("useABeanSimple2")
    public UseABeanSimple useABeanSimple1(){
        ABean abean1 = aBean2();
        ABean abean2 = aBean2();
        System.out.println("" + abean1 + "    " + abean2);
        UseABeanSimple useABeanSimple = new UseABeanSimple();
        useABeanSimple.setaBean(aBean2());
        return useABeanSimple;
    }
}
