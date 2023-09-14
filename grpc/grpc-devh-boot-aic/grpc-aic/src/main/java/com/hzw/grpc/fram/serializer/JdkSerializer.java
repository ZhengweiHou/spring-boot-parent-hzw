package com.hzw.grpc.fram.serializer;

import org.springframework.util.SerializationUtils;

/**
 * @ClassName JdkSerializer
 * @Description TODO
 * @Author houzw
 * @Date 2023/8/7
 **/
public class JdkSerializer implements Serializer{
    @Override
    public <T> byte[] serialize(T object) {
        return SerializationUtils.serialize(object);
    }

    @Override
    public <T> byte[] serialize(Class<T> clazz, T object) {
        return this.serialize(object);
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return clazz.cast(SerializationUtils.deserialize(data));
    }
}
