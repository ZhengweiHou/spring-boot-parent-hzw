package com.hzw.learn.aop.aspectj;

import com.hzw.learn.aop.aspectj.dep.SomeTestBeanDep;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.hzw.learn.aop.aspectj"})
public class AspectjTestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext con =
                new AnnotationConfigApplicationContext(AspectjTestMain.class);

//        SomeTestBeanDep a = con.getBean(SomeTestBeanDep.class);
//        a.test1();

        SomeTestBean b = (SomeTestBean)con.getBean("someTestBean");
        b.test1();


    }
}
