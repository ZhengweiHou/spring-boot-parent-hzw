package com.hzw.learn.springboot.springbase.DI.DICircle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCircleConfig {

//    <!-- 1. 通过setter注入方式，构造循环依赖情况  这个方式spring能解决循环依赖 -->
//    <bean id="aBean" class="com.hzw.learn.springboot.springbase.DI.DICircle.CircleABean" p:bBean-ref="bBean"/>
//    <bean id="bBean" class="com.hzw.learn.springboot.springbase.DI.DICircle.CircleBBean" p:cBean-ref="cBean"/>
//    <bean id="cBean" class="com.hzw.learn.springboot.springbase.DI.DICircle.CircleCBean" p:aBean-ref="aBean"/>

    // TODO 为啥异常：java.lang.NoClassDefFoundError: Could not initialize class org.springframework.beans.factory.BeanCreationException
    @Bean
    public CircleABean aBean(){
        CircleABean temp = new CircleABean(); temp.setbBean(bBean());
        return temp;
    }
    @Bean
    public CircleBBean bBean(){
        CircleBBean temp = new CircleBBean(); temp.setcBean(cBean());
        return temp;
    }
    @Bean
    public CircleCBean cBean(){
        CircleCBean temp = new CircleCBean(); temp.setaBean(aBean());
        return temp;
    }


//    <!-- 2. 通过构造器注入循环依赖情况 若aBean不是构造参数依赖注入的话，则其不处于创建池中，当创建cBean注入aBean时不会有Requested bean is currently in creation异常 -->
//    <bean id="aBeanC0" class="com.hzw.learn.springboot.springbase.DI.DICircle.CircleABean" p:bBean-ref="bBeanC0"/>
//    <bean id="bBeanC0" class="com.hzw.learn.springboot.springbase.DI.DICircle.CircleBBean" c:_0-ref="cBeanC0"/>
//    <bean id="cBeanC0" class="com.hzw.learn.springboot.springbase.DI.DICircle.CircleCBean" c:_0-ref="aBeanC0"/>
//
//    <!-- 3. 通过构造器注入方式构造循环依赖情况   这个方式spring处理不了 会产生 aBeanC1:Requested bean is currently in creation 异常 -->
//    <bean id="aBeanC1" class="com.hzw.learn.springboot.springbase.DI.DICircle.CircleABean" c:_0-ref="bBeanC1"/>
//    <bean id="bBeanC1" class="com.hzw.learn.springboot.springbase.DI.DICircle.CircleBBean" p:cBean-ref="cBeanC1"/>
//    <bean id="cBeanC1" class="com.hzw.learn.springboot.springbase.DI.DICircle.CircleCBean" p:aBean-ref="aBeanC1"/>


}
