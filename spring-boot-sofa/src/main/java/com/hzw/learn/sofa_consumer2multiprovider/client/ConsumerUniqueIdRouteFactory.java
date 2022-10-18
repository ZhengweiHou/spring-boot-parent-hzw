package com.hzw.learn.sofa_consumer2multiprovider.client;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AbstractFactoryBean;

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

    private ConsumerConfig<T> baseConsumerConfig;

    @Override
    public Class<?> getObjectType() {
        return serviceInterface;
    }

    @Override
    protected T createInstance() throws Exception {
        ConsumerConfig<T> baseConsumerConfig = new ConsumerConfig<T>();
        baseConsumerConfig.setRegistry(registryConfig);
        baseConsumerConfig.setInterfaceId(serviceInterface.getName());

        ConsumerUniqueIdRouteHandler cuirHander = new ConsumerUniqueIdRouteHandler();
        cuirHander.setServiceInterface(serviceInterface);
        cuirHander.setRegistryConfig(registryConfig);
        cuirHander.setConsumerRoute(consumerRoute);
        cuirHander.setBaseConsumerConfig(baseConsumerConfig);




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
