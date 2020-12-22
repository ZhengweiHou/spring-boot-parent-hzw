package com.hzw.learn.springboot.springbase.DIAndIOC.DIHello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.hzw.learn.springboot.springbase.DIAndIOC.DIHello")
public class SpringDiConfig {



//    <!--====构造器注入====-->
//    <!--    通过index、name和type注入构造参数-->
//    <bean id="hellodi1-index" class="com.hzw.learn.springboot.springbase.DIAndIOC.DIHello.HelloDiImpl">
//        <constructor-arg index="0" value="15"/>
//        <constructor-arg index="1" value="小明"/>
//    </bean>
//    <bean id="hellodi1-name" class="com.hzw.learn.springboot.springbase.DIAndIOC.DIHello.HelloDiImpl">
//        <constructor-arg name="age" value="18"/>
//        <constructor-arg name="name" value="小明他哥"/>
//    </bean>
//    <bean id="hellodi1-type" class="com.hzw.learn.springboot.springbase.DIAndIOC.DIHello.HelloDiImpl">
//        <constructor-arg type="java.lang.String" value="小红"/>
//        <constructor-arg type="int" value="9"/>
//    </bean>
    @Bean
    public HelloDIapi hellodi1(){
        return new HelloDiImpl(15,"小明");
    }



//    <!--====setter注入====-->
//    <bean id="hellodi2_0" class="com.hzw.learn.springboot.springbase.DIAndIOC.DIHello.HelloDiImpl">
//        <property name="name" value="张飞"/>
//        <property name="age"  value="100"/>
//        <property name="goodPerson" value="0"/>
//    </bean>
    @Bean
    public HelloDIapi hellodi2_0(){
            HelloDiImpl temp = new HelloDiImpl();
            temp.setName("张飞");
            temp.setAge(100);
            temp.setGoodPerson(false);
            return temp;
    }

//    <!--=注入bean id= ??????这有什么用？？-->
//    <bean id="name1" class="java.lang.String" c:_0="aaaa"/>
//    <bean id="hellodi3_0" class="com.hzw.learn.springboot.springbase.DIAndIOC.DIHello.HelloDiImpl">
//        <property name="name"><idref bean="name1"/> </property> <!-- 这里的值是bean的id:"name1"-->
//    </bean>
//    <bean id="hellodi3_1" class="com.hzw.learn.springboot.springbase.DIAndIOC.DIHello.HelloDiImpl">
//        <property name="name"><ref bean="name1"/> </property> <!-- 这里注入的是目标bean，即String:aaaa-->
//    </bean>

    @Bean
    public String name1(){return new String("aaaa");}
    @Bean
    public HelloDIapi hellodi3_0(){
        // TODO 只注入bean id，咋操作？
        return new HelloDiImpl();
    }
    @Bean
    public HelloDIapi hellodi3_1(){
        HelloDiImpl temp = new HelloDiImpl();
        temp.setName(name1());
        return temp;
    }

//    <!--=注入集合、数组和字典等=-->
//    FIXME 这就不演示了，java里操作这个简直太正常不过了；我们重点关注xml里怎么操作的


//    <!-- bean注入测试 -->
//    <bean id="beanDi1name" class="java.lang.String" c:_0="孙悟空"/>
//    <bean id="beanDi1" class="com.hzw.learn.springboot.springbase.DIAndIOC.DIHello.HelloDiImpl">
//        <property name="name" ref="beanDi1name"/>
//    </bean>
//    <!--    内部bean-->
//    <bean id="beanDi2" class="com.hzw.learn.springboot.springbase.DIAndIOC.DIHello.HelloDiImpl">
//        <property name="name">
//            <bean class="java.lang.String" c:_0="沙悟净"/>
//        </property>
//    </bean>
//    <!--使用p名称空间-->
//    <bean id="beanDi3" class="com.hzw.learn.springboot.springbase.DIAndIOC.DIHello.HelloDiImpl"
//    p:name-ref="beanDi1name"
//    p:age="1000"
//    />
    @Bean
    public String beanDi1name(){return new String("孙悟空");}
    @Bean
    public HelloDIapi beanDi2(){
        HelloDiImpl temp = new HelloDiImpl();
        temp.setName(new String("沙悟净"));
        return temp;
    }
    @Bean
    public HelloDIapi beanDi3(){
        HelloDiImpl temp = new HelloDiImpl();
        temp.setName(beanDi1name());
        temp.setAge(1000);
        return temp;
    }


}
