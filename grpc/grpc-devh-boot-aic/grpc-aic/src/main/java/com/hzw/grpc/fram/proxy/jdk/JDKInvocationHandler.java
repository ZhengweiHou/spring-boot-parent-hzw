package com.hzw.grpc.fram.proxy.jdk;

import com.google.common.util.concurrent.ListenableFuture;
import com.hzw.grpc.AicGrpcCommonServiceGrpc;
import com.hzw.grpc.GrpcRequest;
import com.hzw.grpc.GrpcResponse;
import com.hzw.grpc.fram.common.utils.ClassTypeUtils;
import com.hzw.grpc.fram.exception.AicGrpcRpcException;
import com.hzw.grpc.fram.message.AicGrpcMessageBuilder;
import com.hzw.grpc.fram.message.AicGrpcResponse;
import com.hzw.grpc.fram.message.ByteArrayWrapper;
import com.hzw.grpc.fram.serializer.SerializerFactory;
import io.grpc.stub.AbstractStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName JDKInvocationHandler
 * @Description grpc jdk动态代理处理器
 * @Author houzw
 * @Date 2023/7/24
 **/
public class JDKInvocationHandler implements InvocationHandler {
    Logger log = LoggerFactory.getLogger(getClass());
    // grpc 客户端调用服务端的存根
//    private final AicGrpcCommonServiceGrpc.AicGrpcCommonServiceStub aicGrpcCommonServiceFutureStub;
    private final AbstractStub stub;

    private final String stubType;

    private final Byte serializerCode;


    public JDKInvocationHandler(AbstractStub stub, String stubType, Byte serializerCode) {
        this.stub = stub;
        this.stubType = stubType;
        this.serializerCode = serializerCode;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] paramValues) throws Throwable {
        String methodName = method.getName();
        Class[] paramTypes = method.getParameterTypes();
        if ("toString".equals(methodName) && paramTypes.length == 0) {
            return stub.toString();
        } else if ("hashCode".equals(methodName) && paramTypes.length == 0) {
            return stub.hashCode();
        } else if ("equals".equals(methodName) && paramTypes.length == 1) {
            Object another = paramValues[0];
            return proxy == another;
        }

        // 1. build aicgrpcrequest
        GrpcRequest grpcRequest = AicGrpcMessageBuilder.buildGrpcRequest(serializerCode, method.getDeclaringClass(), method, paramTypes, paramValues);

        // 2. do invoke
        ListenableFuture<GrpcResponse> future = ((AicGrpcCommonServiceGrpc.AicGrpcCommonServiceFutureStub) stub).rpcInvoke(grpcRequest);

        GrpcResponse response = future.get(60, TimeUnit.SECONDS); // 超时处理

        // 3. 返回值处理
        AicGrpcResponse aicGrpcResponse = SerializerFactory.getSerializer(SerializerFactory.PROTO_SERIALIZER_CODE)
                .deserialize(response.getAicGrpcResponse().toByteArray(), AicGrpcResponse.class);

        Object appResp = aicGrpcResponse.getAppResponse();
        Object deAppResp = null;

        if (aicGrpcResponse.isError()) {
            if(ObjectUtils.isEmpty(aicGrpcResponse.getReturnSig()) || "NULL".equals(aicGrpcResponse.getReturnSig())){
                throw new AicGrpcRpcException(aicGrpcResponse.getErrorMsg());
            }
            deAppResp = SerializerFactory.getSerializer(serializerCode)
                    .deserialize(
                            ((ByteArrayWrapper) appResp).array(),
                            ClassTypeUtils.getClass(aicGrpcResponse.getReturnSig()));
            if (deAppResp instanceof Throwable) {
                throw (Throwable) deAppResp;
            }else {
                // 返回显示有异常，但异常信息不是异常对象，目前不知道什么情况会发生这个现象
                log.warn("AicGrpcResp is error, but appResp is not Throwable");
                return deAppResp;
            }
        }

        if (appResp instanceof ByteArrayWrapper){
            deAppResp = SerializerFactory.getSerializer(serializerCode).deserialize(((ByteArrayWrapper) appResp).array(), method.getReturnType());
            if (deAppResp instanceof Throwable) {
                throw (Throwable) deAppResp;
            }
            return deAppResp;
        }else if (appResp instanceof Throwable) {
            throw (Throwable) appResp;
        } else {
            return appResp;
        }
    }



}
