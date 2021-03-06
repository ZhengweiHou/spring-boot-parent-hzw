package com.hzw.learn.springboot.springbase.DIAndIOC.IocLifecycle.PostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HzwBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("1=HzwBeanFactoryPostProcessor.postProcessBeanFactory");
        log.debug("1=HzwBeanFactoryPostProcessor.postProcessBeanFactory");
    }
}
