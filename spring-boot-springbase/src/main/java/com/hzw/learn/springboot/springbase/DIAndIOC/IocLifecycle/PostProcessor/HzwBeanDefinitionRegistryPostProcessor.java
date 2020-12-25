package com.hzw.learn.springboot.springbase.DIAndIOC.IocLifecycle.PostProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class HzwBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("2=HzwBeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry");
        log.debug("2=HzwBeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("3=HzwBeanDefinitionRegistryPostProcessor.postProcessBeanFactory");
        log.debug("3=HzwBeanDefinitionRegistryPostProcessor.postProcessBeanFactory");
    }
}
