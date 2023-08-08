package com.hzw.grpc.fram.client;

import com.google.common.collect.Lists;
import com.hzw.grpc.fram.proxy.ProxyFactory;
import com.hzw.grpc.fram.serializer.SerializerFactory;
import io.grpc.Channel;
import io.grpc.ClientInterceptor;
import net.devh.boot.grpc.client.channelfactory.GrpcChannelFactory;
import net.devh.boot.grpc.client.nameresolver.NameResolverRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

/**
 * @ClassName AicClientBeanPostProcessor
 * @Description 实现aic grpc client的proxy创建和注入
 * @Author houzw
 * @Date 2023/7/21
 **/
public class AicGrpcClientBeanPostProcessor implements BeanPostProcessor {
    Logger log = LoggerFactory.getLogger(getClass());
    private final ApplicationContext applicationContext;
    private GrpcChannelFactory channelFactory = null;
    public static final ConcurrentMap<String,Object> grpcClienProxyBeansMap = new ConcurrentHashMap<>();

    public AicGrpcClientBeanPostProcessor(ApplicationContext applicationContext) {
        this.applicationContext = requireNonNull(applicationContext, "applicationContext");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        do {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(AicGrpc.class)) {
                    AicGrpc anno = field.getAnnotation(AicGrpc.class);
                    Class<?> ftype = field.getType();

                    field.setAccessible(true);
                    try {
                        log.info("==aic grpc inject {}","hello");
                        field.set(bean, createAicGrpcProxy(field,field.getType(),anno));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            clazz = clazz.getSuperclass();
        } while (clazz != null);

        return bean;
    }

    protected <T> T createAicGrpcProxy(final Field field, final Class<T> fieldType, final AicGrpc annotation){

        // 借用spring factory bean
        AicGrpcClientFactoryBean grpcFactory = new AicGrpcClientFactoryBean(
                annotation.serializer(),
                annotation.value(),
                fieldType,
                annotation.stubType(),
                annotation.interceptors(),
                annotation.interceptorNames(),
                annotation.sortInterceptors(),
                this.applicationContext
        );

        try {
            return fieldType.cast(grpcFactory.createInstance());
        } catch (Exception e) {
            throw new IllegalStateException("aic grpc create error ",e);
        }
    }
}
