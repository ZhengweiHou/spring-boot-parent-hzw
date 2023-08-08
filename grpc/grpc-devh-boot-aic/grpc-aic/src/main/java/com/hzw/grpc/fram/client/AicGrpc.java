package com.hzw.grpc.fram.client;

import com.hzw.grpc.fram.common.AicGrpcConstant;
import com.hzw.grpc.fram.serializer.SerializerFactory;
import io.grpc.ClientInterceptor;

import java.lang.annotation.*;

/**
 * @ClassName AicGrpc
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/21
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AicGrpc {

    String value();

    Class<? extends ClientInterceptor>[] interceptors() default {};

    String[] interceptorNames() default {};

    boolean sortInterceptors() default false;

    String stubType() default AicGrpcConstant.AIC_GRPC_STUB_TYPE_FUTURE;

    String serializer() default AicGrpcConstant.PROTO_SERIALIZER_TYPE;

}
