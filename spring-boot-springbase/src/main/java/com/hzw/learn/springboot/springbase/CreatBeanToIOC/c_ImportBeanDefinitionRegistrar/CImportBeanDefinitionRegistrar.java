package com.hzw.learn.springboot.springbase.CreatBeanToIOC.c_ImportBeanDefinitionRegistrar;

import com.hzw.learn.springboot.springbase.CreatBeanToIOC.z_BaseBean.W;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.Map;

/**
 * @ClassName CImportBeanDefinitionRegistrar
 * @Description TODO
 * @Author houzw
 * @Date 2022/11/14
 **/
public class CImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // importingClassMetadata 此处能获取到@Import引入该ImportSelector的类上的所有注解
        Map<String, Object> anAtt = importingClassMetadata.getAnnotationAttributes(W.class.getName());
        String[] value = (String[]) anAtt.get("value");

        Arrays.stream(value).forEach(v -> {
            BeanDefinitionBuilder bdBuilder = BeanDefinitionBuilder.genericBeanDefinition(v);

            AbstractBeanDefinition beanDefinition = bdBuilder.getBeanDefinition();
            String name = v.substring(v.lastIndexOf(".") + 1) + "_xx";
            System.out.println("CImportBeanDefinitionRegistrar 注册BeanDefinition:" + name);
            registry.registerBeanDefinition(name, beanDefinition);

        });



    }
}
