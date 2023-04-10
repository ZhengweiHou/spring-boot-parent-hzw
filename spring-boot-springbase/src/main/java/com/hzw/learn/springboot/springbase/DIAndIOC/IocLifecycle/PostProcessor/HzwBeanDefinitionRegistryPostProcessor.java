package com.hzw.learn.springboot.springbase.DIAndIOC.IocLifecycle.PostProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.Arrays;

public class HzwBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("2=HzwBeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry");
        log.debug("2=HzwBeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry");
        Arrays.stream(registry.getBeanDefinitionNames()).forEach(name ->{
            BeanDefinition df = registry.getBeanDefinition(name);
            if(df.getBeanClassName().equals("Hzw")){ // 移除满足条件的BeanDefinition
                registry.removeBeanDefinition(name);
            }
        });
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("3=HzwBeanDefinitionRegistryPostProcessor.postProcessBeanFactory");
        log.debug("3=HzwBeanDefinitionRegistryPostProcessor.postProcessBeanFactory");
    }
}
