package com.hzw.learn.springboot.springbase.DIAndIOC.IocLifecycle.PostProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;


public class HzwBfppBdrpp implements BeanFactoryPostProcessor, BeanDefinitionRegistryPostProcessor {
    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.debug("{} : {}",this.getClass().getSimpleName(), "postProcessBeanFactory");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        log.debug("{} : {}",this.getClass().getSimpleName(), "postProcessBeanDefinitionRegistry");
    }
}
