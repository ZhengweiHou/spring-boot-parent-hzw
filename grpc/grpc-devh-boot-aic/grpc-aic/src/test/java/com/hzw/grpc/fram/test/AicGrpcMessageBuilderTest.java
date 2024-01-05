package com.hzw.grpc.fram.test;

import com.hzw.grpc.GrpcResponse;
import com.hzw.grpc.fram.common.utils.ClassTypeUtils;
import com.hzw.grpc.fram.exception.AicGrpcRpcException;
import com.hzw.grpc.fram.message.AicGrpcMessageBuilder;
import com.hzw.grpc.fram.message.AicGrpcResponse;
import com.hzw.grpc.fram.message.ByteArrayWrapper;
import com.hzw.grpc.fram.serializer.SerializerFactory;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

/**
 * @ClassName AicGrpcMessageBuilderTest
 * @Description TODO
 * @Author houzw
 * @Date 2023/10/24
 **/
public class AicGrpcMessageBuilderTest {

    @Test
    public void test1() throws Throwable {
        Byte ps = SerializerFactory.PROTO_SERIALIZER_CODE;


        GrpcResponse rep1 = AicGrpcMessageBuilder.buildGrpcResponse(ps, null);

        AicGrpcResponse aicGrpcResponse = SerializerFactory.getSerializer(SerializerFactory.PROTO_SERIALIZER_CODE)
                .deserialize(rep1.getAicGrpcResponse().toByteArray(), AicGrpcResponse.class);
        Object aa = grpcclientdeseri(aicGrpcResponse);
        System.out.println(aa);

    }

    private Object grpcclientdeseri(AicGrpcResponse aicGrpcResponse) throws Throwable {
        Byte serializerCode = SerializerFactory.PROTO_SERIALIZER_CODE;
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
                return deAppResp;
            }
        }

        if (appResp instanceof ByteArrayWrapper){
            deAppResp = SerializerFactory.getSerializer(serializerCode).deserialize(((ByteArrayWrapper) appResp).array(), Void.class);
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
