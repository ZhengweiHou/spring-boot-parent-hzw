package com.hzw.grpc.fram.message;

import com.google.protobuf.ByteString;
import com.hzw.grpc.GrpcRequest;
import com.hzw.grpc.GrpcResponse;
import com.hzw.grpc.fram.common.utils.ClassTypeUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @ClassName AicGrpcMessageBuilder
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/25
 **/
public class AicGrpcMessageBuilder {


    /**
     * @param clazz - 接口类
     * @param method - 方法
     * @param argTypes - 方法参数类型
     * @param args -  方法参数
     * @return aic grpc 远程调用请求
     */
    public static GrpcRequest buildGrpcRequest(Class<?> clazz, Method method, Class[] argTypes, Object[] args){

        AicGrpcRequest req = new AicGrpcRequest();
        req.setInterfaceName(clazz.getName());
        req.setMethodName(method.getName());
        req.setMethodArgSigs(ClassTypeUtils.getTypeStrs(argTypes, true));
        req.setMethodArgs(args == null ? new Object[0] : args);

        ByteString[] argsBytes;

        if (args == null || args.length == 0){
            argsBytes = new ByteString[0];
        }else {
            argsBytes = new ByteString[args.length];
            for (int i = 0; i < args.length; i++) {
                argsBytes[i] = ByteString.copyFrom(
                    ProtoStuffSerializer.serialize(args[0])
                );
            }
        }
        GrpcRequest.Builder reqBuilder = GrpcRequest.newBuilder();
        reqBuilder.setAicGrpcRequest(
            ByteString.copyFrom(ProtoStuffSerializer.serialize(req))
        );

        return reqBuilder.build();
    }


    public static GrpcResponse buildGrpcResponse(Object arg){
        AicGrpcResponse rep = new AicGrpcResponse();
        rep.setAppResponse(arg);
        GrpcResponse response = GrpcResponse.newBuilder()
                .setAicGrpcResponse(
                        ByteString.copyFrom(ProtoStuffSerializer.serialize(rep))
                )
                .build();
        return response;
    }

    public static GrpcResponse buildGrpcErrorResponse(String errorMsg){
        AicGrpcResponse rep = new AicGrpcResponse();
        rep.setErrorMsg(errorMsg);

        GrpcResponse response = GrpcResponse.newBuilder()
                .setAicGrpcResponse(
                        ByteString.copyFrom(ProtoStuffSerializer.serialize(rep))
                )
                .build();
        return response;
    }

}
