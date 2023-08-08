package com.hzw.grpc.fram.proxy.jdk;

import com.google.common.util.concurrent.ListenableFuture;
import com.hzw.grpc.AicGrpcCommonServiceGrpc;
import com.hzw.grpc.GrpcRequest;
import com.hzw.grpc.GrpcResponse;
import com.hzw.grpc.fram.exception.AicGrpcRpcException;
import com.hzw.grpc.fram.message.AicGrpcMessageBuilder;
import com.hzw.grpc.fram.message.AicGrpcResponse;
import com.hzw.grpc.fram.message.ByteArrayWrapper;
import com.hzw.grpc.fram.serializer.ProtoStuffSerializer;
import com.hzw.grpc.fram.serializer.Serializer;
import com.hzw.grpc.fram.serializer.SerializerFactory;
import io.grpc.stub.AbstractStub;

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

        AicGrpcResponse aicGrpcResponse = SerializerFactory.getSerializer(SerializerFactory.PROTO_SERIALIZER_CODE)
                .deserialize(response.getAicGrpcResponse().toByteArray(), AicGrpcResponse.class);

        if (aicGrpcResponse.isError()) {
            throw new AicGrpcRpcException(aicGrpcResponse.getErrorMsg());
        }
        Object ret = aicGrpcResponse.getAppResponse();
        if (ret instanceof Throwable) {
            throw (Throwable) ret;
        } else if (ret instanceof ByteArrayWrapper){
            Object ret2 = SerializerFactory.getSerializer(serializerCode).deserialize(((ByteArrayWrapper) ret).array(), Object.class);
            if (ret2 instanceof Throwable) {
                throw (Throwable) ret2;
            }
            return ret2;
        }else {
            return ret;
        }
    }
}
