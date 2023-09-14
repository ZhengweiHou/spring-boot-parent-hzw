package com.hzw.grpc.fram.message;

import com.google.protobuf.ByteString;
import com.hzw.grpc.GrpcRequest;
import com.hzw.grpc.GrpcResponse;
import com.hzw.grpc.fram.common.utils.ClassTypeUtils;
import com.hzw.grpc.fram.serializer.SerializerFactory;

import java.lang.reflect.Method;

/**
 * @ClassName AicGrpcMessageBuilder
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/25
 **/
public class AicGrpcMessageBuilder {


    /**
     *
     * @param serializerCode - 序列化类型
     * @param clazz - 接口类
     * @param method - 方法
     * @param argTypes - 方法参数类型
     * @param args -  方法参数
     * @return aic grpc 远程调用请求
     */
    public static GrpcRequest buildGrpcRequest(Byte serializerCode,Class<?> clazz, Method method, Class[] argTypes, Object[] args){

        AicGrpcRequest req = new AicGrpcRequest();
        req.setInterfaceName(clazz.getName());
        req.setMethodName(method.getName());
        req.setMethodArgSigs(ClassTypeUtils.getTypeStrs(argTypes, true));
        req.setSerializerCode(serializerCode);

        // 请求参数序列化方
        ByteArrayWrapper[] argsBytes;
        if (args == null || args.length == 0) {
            argsBytes = new ByteArrayWrapper[0];
        } else {
            argsBytes = new ByteArrayWrapper[args.length];
            for (int i=0; i<argTypes.length; i++){
                argsBytes[i] = new ByteArrayWrapper(
                        SerializerFactory.getSerializer(serializerCode).serialize(argTypes[i],args[i])
                );
            }
        }
        req.setMethodArgs(argsBytes);

        GrpcRequest.Builder reqBuilder = GrpcRequest.newBuilder();
        reqBuilder.setAicGrpcRequest(
            ByteString.copyFrom(SerializerFactory.getSerializer(
                    SerializerFactory.PROTO_SERIALIZER_CODE
            ).serialize(req))
        );

        return reqBuilder.build();
    }


    public static GrpcResponse buildGrpcResponse(Byte serializerCode,Object arg){
        AicGrpcResponse rep = new AicGrpcResponse();
        if(serializerCode == SerializerFactory.PROTO_SERIALIZER_CODE) {
            rep.setAppResponse(arg);
        }else {
            rep.setAppResponse(new ByteArrayWrapper(SerializerFactory.getSerializer(serializerCode).serialize(arg)));
        }

        GrpcResponse response = GrpcResponse.newBuilder()
                .setAicGrpcResponse(
                        ByteString.copyFrom(
                                SerializerFactory.getSerializer(SerializerFactory.PROTO_SERIALIZER_CODE).serialize(rep)
                        )
                )
                .build();
        return response;
    }

    public static GrpcResponse buildGrpcErrorResponse(String errorMsg){
        AicGrpcResponse rep = new AicGrpcResponse();
        rep.setErrorMsg(errorMsg);

        GrpcResponse response = GrpcResponse.newBuilder()
                .setAicGrpcResponse(
                        ByteString.copyFrom(
                                SerializerFactory.getSerializer(
                                        SerializerFactory.PROTO_SERIALIZER_CODE
                                ).serialize(rep)
                        )
                )
                .build();
        return response;
    }

}
