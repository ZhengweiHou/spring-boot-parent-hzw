package com.hzw.grpc.fram.client;

import com.google.common.collect.Lists;
import com.hzw.grpc.fram.common.AicGrpcConstant;
import com.hzw.grpc.fram.proxy.ProxyFactory;
import com.hzw.grpc.fram.serializer.SerializerFactory;
import io.grpc.Channel;
import io.grpc.ClientInterceptor;
import lombok.Getter;
import lombok.Setter;
import net.devh.boot.grpc.client.channelfactory.GrpcChannelFactory;
import net.devh.boot.grpc.client.config.GrpcChannelsProperties;
import net.devh.boot.grpc.client.nameresolver.NameResolverRegistration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @ClassName AicGrpcClientFactoryBean
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/28
 **/
@Getter
@Setter
public class AicGrpcClientFactoryBean extends AbstractFactoryBean implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private GrpcChannelFactory channelFactory = null;

    private String serializerType;
    private String channelKey;
    private Class<?> interfaceClass;
    private String stubType;
    private Class<? extends ClientInterceptor>[] interceptors;
    private String[] interceptorNames;
    boolean sortInterceptors;

    public void checkProperties() {
        Assert.notNull(channelKey,"channelKey is null");
        Assert.notNull(interfaceClass,"interfaceClass is null");
        Assert.notNull(applicationContext, "spring applicationContext is null, check the injection status");
        if(stubType == null ||
            (
                !stubType.equals(AicGrpcConstant.AIC_GRPC_STUB_TYPE_FUTURE)
                && !stubType.equals(AicGrpcConstant.AIC_GRPC_STUB_TYPE_ASYNC)
                && !stubType.equals(AicGrpcConstant.AIC_GRPC_STUB_TYPE_BLOCKING)
            )
        ){
            throw new IllegalArgumentException("stubType illegal:" + stubType);
        }
        // 非法的serializerCode抛出异常
        SerializerFactory.getSerializerCode(serializerType);
    }

    public AicGrpcClientFactoryBean(){
//        this(AicGrpcConstant.PROTO_SERIALIZER_TYPE,GrpcChannelsProperties.GLOBAL_PROPERTIES_KEY,String.class,AicGrpcConstant.AIC_GRPC_STUB_TYPE_FUTURE,null,null,false,null);

//        this(String.class);
        this.serializerType = AicGrpcConstant.PROTO_SERIALIZER_TYPE;
        this.channelKey = GrpcChannelsProperties.GLOBAL_PROPERTIES_KEY;
        this.stubType = AicGrpcConstant.AIC_GRPC_STUB_TYPE_FUTURE;

        this.interceptors = null;
        this.interceptorNames = null;
        this.sortInterceptors = false;
    }
    public AicGrpcClientFactoryBean(Class<?> interfaceClass){
        this(GrpcChannelsProperties.GLOBAL_PROPERTIES_KEY,interfaceClass);
    }
    public AicGrpcClientFactoryBean(Class<?> interfaceClass,ApplicationContext applicationContext){
        this(GrpcChannelsProperties.GLOBAL_PROPERTIES_KEY,interfaceClass,applicationContext);
    }
    public AicGrpcClientFactoryBean(String channelKey, Class<?> interfaceClass){
        this(AicGrpcConstant.PROTO_SERIALIZER_TYPE,channelKey,interfaceClass,AicGrpcConstant.AIC_GRPC_STUB_TYPE_FUTURE,null,null,false,null);
    }

    public AicGrpcClientFactoryBean(String channelKey, Class<?> interfaceClass, ApplicationContext applicationContext){
        this(AicGrpcConstant.PROTO_SERIALIZER_TYPE,channelKey,interfaceClass,AicGrpcConstant.AIC_GRPC_STUB_TYPE_FUTURE,null,null,false,applicationContext);
    }

    public AicGrpcClientFactoryBean(String serializerType, String channelKey, Class<?> interfaceClass, String stubType, Class<? extends ClientInterceptor>[] interceptors, String[] interceptorNames, boolean sortInterceptors, ApplicationContext applicationContext) {
        this.serializerType = serializerType;
        this.channelKey = channelKey;
        this.interfaceClass = interfaceClass;
        this.stubType = stubType;
        this.interceptors = interceptors;
        this.interceptorNames = interceptorNames;
        this.sortInterceptors = sortInterceptors;
        this.applicationContext = applicationContext;
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceClass;
    }

    @Override
    protected Object createInstance() throws Exception {
        checkProperties();
        return createAicGrpcProxy();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected Object createAicGrpcProxy(){
        final List<ClientInterceptor> interceptors = findInterceptors();
        final Channel channel;
        try {
            channel = getChannelFactory().createChannel(this.channelKey, interceptors, this.sortInterceptors);
            if (channel == null) {
                throw new IllegalStateException("Channel factory created a null channel for " + channelKey);
            }
        } catch (final RuntimeException e) {
            throw new IllegalStateException("Failed to create channel: " + channelKey, e);
        }

        Object proxy = ProxyFactory.buildClientProxy(SerializerFactory.getSerializerCode(serializerType), interfaceClass, channel, stubType);

        return proxy;
    }

    // 从spring容器中获取ChannelFactory
    private GrpcChannelFactory getChannelFactory() {
        if (this.channelFactory == null) {
            // Ensure that the NameResolverProviders have been registered
            this.applicationContext.getBean(NameResolverRegistration.class);
            final GrpcChannelFactory factory = this.applicationContext.getBean(GrpcChannelFactory.class);
            this.channelFactory = factory;
            return factory;
        }
        return this.channelFactory;
    }

    protected List<ClientInterceptor> findInterceptors() throws BeansException {
        final List<ClientInterceptor> list = Lists.newArrayList();
        if (interceptors != null) {
            for (final Class<? extends ClientInterceptor> interceptorClass : this.interceptors) {
                final ClientInterceptor clientInterceptor;
                if (this.applicationContext.getBeanNamesForType(interceptorClass).length > 0) {
                    clientInterceptor = this.applicationContext.getBean(interceptorClass);
                } else {
                    // 容器中没有则实例化创建
                    try {
                        clientInterceptor = interceptorClass.getConstructor().newInstance();
                    } catch (final Exception e) {
                        throw new BeanCreationException("Failed to create interceptor instance", e);
                    }
                }
                list.add(clientInterceptor);
            }
        }
        if (interceptorNames != null) {
            // 通过实例名获取拦截器对象
            for (final String interceptorName : this.interceptorNames) {
                list.add(this.applicationContext.getBean(interceptorName, ClientInterceptor.class));
            }
        }
        return list;
    }
}
