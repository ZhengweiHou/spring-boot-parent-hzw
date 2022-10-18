package com.hzw.learn.sofa_consumer2multiprovider.client;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.hzw.learn.ext.HelloService;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @ClassName ConsumerUniqueIdRouteHandeler
 * @Description TODO
 * @Author houzw
 * @Date 2022/9/13
 **/
@Setter
public class ConsumerUniqueIdRouteHandler<T> implements InvocationHandler {

    private Class<T> serviceInterface;
    private ConcurrentHashMap<String,T> consumerMap = new ConcurrentHashMap<>();
    private RegistryConfig registryConfig;
    private ConsumerRoute consumerRoute;

    private ConsumerConfig<T> baseConsumerConfig;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        T consumer = getConsume(args);
        return method.invoke(consumer,args);
    }

    private T getConsume(Object[] args){
        String routeKey = consumerRoute.getRouteKey(args[0]);
        if (StringUtils.isEmpty(routeKey)){
            routeKey = "default";
        }
        if (consumerMap.get(routeKey) == null){
            initConsumer(routeKey);
        }

        return consumerMap.get(routeKey);
    }

    private synchronized void initConsumer(String uniqueId){
        if (consumerMap.get(uniqueId) != null){
            return;
        }

        ConsumerConfig<T> uniqueConsumerConfig = new ConsumerConfig<T>();

//        BeanUtils.copyProperties(baseConsumerConfig,uniqueConsumerConfig);
        com.alipay.sofa.rpc.common.utils.BeanUtils.copyProperties(baseConsumerConfig,uniqueConsumerConfig);

//        uniqueConsumerConfig.setRegistry(registryConfig);
//        uniqueConsumerConfig.setInterfaceId(serviceInterface.getName());
//        // TODO consumerConfig的其他配置项需提供修改修改入口
//        // uniqueConsumerConfig.setProtocol()

        if (!uniqueId.equals("default")) {
            uniqueConsumerConfig.setUniqueId(uniqueId);
        }
        T consumer = uniqueConsumerConfig.refer();

        consumerMap.put(uniqueId,consumer);
    }
}
