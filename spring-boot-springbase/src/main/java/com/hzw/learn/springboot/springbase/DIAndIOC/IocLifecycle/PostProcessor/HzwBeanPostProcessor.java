package com.hzw.learn.springboot.springbase.DIAndIOC.IocLifecycle.PostProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class HzwBeanPostProcessor implements BeanPostProcessor {

    Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.debug("4=HzwBeanPostProcessor.postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.debug("5=HzwBeanPostProcessor.postProcessAfterInitialization");
        return bean;
    }
}
