package com.hzw.grpc.fram.test;

import com.hzw.grpc.fram.exception.AicGrpcRpcException;
import com.hzw.grpc.fram.serializer.Serializer;
import com.hzw.grpc.fram.serializer.SerializerFactory;
import io.protostuff.GraphIOUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * @ClassName SerializerFactoryTest
 * @Description SerializerFactory 测试
 * @Author houzw
 * @Date 2023/8/24
 **/
@FixMethodOrder(MethodSorters.JVM)
public class SerializerFactoryTest {

    @Test
    public void test1(){
        Serializer serializer = SerializerFactory.getSerializer("jdk");
        UndeclaredThrowableException err = new UndeclaredThrowableException(new AicGrpcRpcException("hello"));
        byte[] seria_err = serializer.serialize(err);
        System.out.println(seria_err.length);
        UndeclaredThrowableException er = serializer.deserialize(seria_err, UndeclaredThrowableException.class);
        System.out.println(er.getCause().getMessage());
    }

    @Test
    public void test2(){
        Serializer serializer = SerializerFactory.getSerializer("proto");
        UndeclaredThrowableException err = new UndeclaredThrowableException(new AicGrpcRpcException("hello"));
        byte[] seria_err = serializer.serialize(err);
        System.out.println(seria_err.length);

//        UndeclaredThrowableException er = serializer.deserialize(seria_err, UndeclaredThrowableException.class);
        RuntimeSchema<UndeclaredThrowableException> schema = RuntimeSchema.createFrom(UndeclaredThrowableException.class);
        UndeclaredThrowableException er = schema.newMessage();
        GraphIOUtil.mergeFrom(seria_err, er, schema);

        System.out.println(er.getCause().getMessage());

    }

}
