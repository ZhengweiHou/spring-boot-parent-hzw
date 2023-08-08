package com.hzw.grpc.fram.serializer;

import com.hzw.grpc.fram.exception.AicGrpcRpcException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName SerializerFactory
 * @Description TODO
 * @Author houzw
 * @Date 2023/8/7
 **/
public class SerializerFactory {

    public static Byte PROTO_SERIALIZER_CODE = 0;
    public static Map<String, Byte> serializerKeyMap = new ConcurrentHashMap<>();
    public static Map<Byte,Serializer> serializerMap = new ConcurrentHashMap<>();

    static {
        serializerKeyMap.putIfAbsent("proto", (byte) 0);
        serializerKeyMap.putIfAbsent("jdk", (byte) 1);

        serializerMap.putIfAbsent((byte) 0,new ProtoStuffSerializer());
        serializerMap.putIfAbsent((byte) 1,new JdkSerializer());
    }

    public static Serializer getSerializer(Byte serializerCode){
        Serializer serializer = serializerMap.get(serializerCode);
        if (serializer == null){
            throw new AicGrpcRpcException("unsupport serializer code:" + serializerCode);
        }
        return serializer;
    }

    public static Serializer getSerializer(String serializerType){
        return getSerializer(getSerializerCode(serializerType));
    }

    public static Byte getSerializerCode(String serializerType){
        Byte serializerCode = serializerKeyMap.get(serializerType);
        if (serializerCode == null){
            throw new AicGrpcRpcException("unsupport serializer type:" + serializerType);
        }
        return serializerCode;
    }

}
