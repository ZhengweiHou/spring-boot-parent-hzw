package com.hzw.grpc.fram.serializer;

import io.protostuff.GraphIOUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ProtoStuffSerializer
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/25
 **/
public class ProtoStuffSerializer implements Serializer{

    private static final Map<Class<?>, Schema<?>> schemaCache = new ConcurrentHashMap<>();

    // 序列化对象为字节数组
    public <T> byte[] serialize(T object) {
        Class<T> clazz = (Class<T>) object.getClass();
        Schema<T> schema = getSchema(clazz);
        return GraphIOUtil.toByteArray(
                object,
                schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
//        return ProtostuffIOUtil.toByteArray(
//                object,
//                schema,
//                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }

    // 反序列化字节数组为对象
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        Schema<T> schema = getSchema(clazz);
        T obj = schema.newMessage();
        GraphIOUtil.mergeFrom(data, obj, schema);
        return obj;
    }

    private <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) schemaCache.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(clazz);
            schemaCache.put(clazz, schema);
        }
        return schema;
    }

}
