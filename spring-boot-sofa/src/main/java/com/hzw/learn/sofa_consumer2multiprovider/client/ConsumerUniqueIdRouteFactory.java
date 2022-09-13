package com.hzw.learn.sofa_consumer2multiprovider.client;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.hzw.learn.ext.HelloService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Proxy;

/**
 * @ClassName ConsumerUniqueIdRouteFactory
 * @Description TODO
 * @Author houzw
 * @Date 2022/9/13
 **/

public class ConsumerUniqueIdRouteFactory <T> extends AbstractFactoryBean<T> {
    private Class<T> serviceInterface;

    @Autowired
    private ConsumerRoute consumerRoute;

    @Autowired
    private RegistryConfig registryConfig;

    @Override
    public Class<?> getObjectType() {
        return serviceInterface;
    }

    @Override
    protected T createInstance() throws Exception {
        ConsumerUniqueIdRouteHandeler cuirHander = new ConsumerUniqueIdRouteHandeler();
        cuirHander.setServiceInterface(serviceInterface);
        cuirHander.setRegistryConfig(registryConfig);
        cuirHander.setConsumerRoute(consumerRoute);

        return (T) Proxy.newProxyInstance(
                serviceInterface.getClassLoader(),
                new Class[]{serviceInterface},
                cuirHander
                );
    }

    public void setServiceInterface(Class<T> serviceInterface) {
        this.serviceInterface = serviceInterface;
    }
}
