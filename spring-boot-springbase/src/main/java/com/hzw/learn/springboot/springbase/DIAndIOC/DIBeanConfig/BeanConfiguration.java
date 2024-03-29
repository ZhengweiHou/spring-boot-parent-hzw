package com.hzw.learn.springboot.springbase.DIAndIOC.DIBeanConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName BeanConfiguration
 * @Description TODO
 * @Author houzw
 * @Date 2023/2/21
 **/
@Configuration
@Import({BeanConfigBeImport.class})
public class BeanConfiguration {

//    @Bean
//    public ABean aBean(){
//        System.out.println("aBean init");
//        ABean aBean = new ABean();
//        aBean.setName("hzw");
//        return aBean;
//    }
//
//    // Autowired 方式注入
//    @Bean("useABeanAutowired")
//    public UseABeanAutowired useABeanAutowired(){
//        return new UseABeanAutowired();
//    }
//
//    // 使用configuration 中的@Bean方法获取目标bean
//    @Bean("useABeanSimple1")
//    public UseABeanSimple useABeanSimple1(){
//        ABean abean1 = aBean();
//        ABean abean2 = aBean();
//        System.out.println("" + abean1 + "    " + abean2);
//        UseABeanSimple useABeanSimple = new UseABeanSimple();
//        useABeanSimple.setaBean(aBean());
//        return useABeanSimple;
//    }
//
//    @Bean("useABeanSimple2")
//    public UseABeanSimple useABeanSimple2(ABean aBean){
//        UseABeanSimple useABeanSimple = new UseABeanSimple();
//        useABeanSimple.setaBean(aBean);
//        return useABeanSimple;
//    }

}
