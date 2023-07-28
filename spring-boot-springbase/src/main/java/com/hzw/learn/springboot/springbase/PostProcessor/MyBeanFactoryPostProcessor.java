package com.hzw.learn.springboot.springbase.PostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory fac) throws BeansException {
//        fac.getBeanDefinition("aaaa");
        PenBean pen = new PenBean();
        // 不可同名bean
        fac.registerSingleton("hzw",pen);
        fac.registerSingleton("hzw",pen);

    }
}
