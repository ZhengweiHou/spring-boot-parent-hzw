package com.hzw.grpc.fram.proxy;

import com.hzw.grpc.AicGrpcCommonServiceGrpc;
import com.hzw.grpc.fram.common.AicGrpcConstant;
import com.hzw.grpc.fram.proxy.jdk.JDKInvocationHandler;
import io.grpc.stub.AbstractStub;

import java.lang.reflect.Proxy;

/**
 * @ClassName ProxyFactory
 * @Description aic grpc 代理创建工厂类
 * @Author houzw
 * @Date 2023/7/24
 **/
public class ProxyFactory {

    /**
     * 构造代理类实例
     */
    public static <T> T buildClientProxy(final Class<T> interfaceClazz, final io.grpc.Channel channel, String stubType){
        AbstractStub stub = null;
        switch (stubType){
            case AicGrpcConstant.AIC_GRPC_STUB_TYPE_FUTURE:
                stub = AicGrpcCommonServiceGrpc.newFutureStub(channel);
                break;
//            case AicGrpcConstant.AIC_GRPC_STUB_TYPE_ASYNC:
//                stub = AicGrpcCommonServiceGrpc.newStub(channel);
//                break;
            case AicGrpcConstant.AIC_GRPC_STUB_TYPE_BLOCKING:
                stub = AicGrpcCommonServiceGrpc.newBlockingStub(channel);
                break;
            default:
                throw new IllegalStateException("Stub type:" + stubType + " is not supported!");
        }

        // 创建接口的动态代理对象
        JDKInvocationHandler handler = new JDKInvocationHandler(stub, stubType);
        Object proxy = Proxy.newProxyInstance(getCurrentClassLoader(), new Class[]{interfaceClazz}, handler);
        return interfaceClazz.cast(proxy);
    }

    public static ClassLoader getCurrentClassLoader() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = ProxyFactory.class.getClassLoader();
        }
        return cl == null ? ClassLoader.getSystemClassLoader() : cl;
    }
}
