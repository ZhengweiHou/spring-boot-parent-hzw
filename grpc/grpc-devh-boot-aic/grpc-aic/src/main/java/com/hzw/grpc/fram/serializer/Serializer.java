package com.hzw.grpc.fram.serializer;

/**
 * @ClassName Serializer
 * @Description TODO
 * @Author houzw
 * @Date 2023/8/7
 **/
public interface Serializer {
    public <T> byte[] serialize(T object);

    public <T> T deserialize(byte[] data, Class<T> clazz);

}
