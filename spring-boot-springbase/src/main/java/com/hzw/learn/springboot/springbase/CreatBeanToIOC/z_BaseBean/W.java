package com.hzw.learn.springboot.springbase.CreatBeanToIOC.z_BaseBean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // runtime范围,否则运行时反射取不到该注解
@Target(ElementType.TYPE)
public @interface W {
    String[] value() default {};
}
