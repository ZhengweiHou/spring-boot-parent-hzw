package com.hzw.grpc.fram.server;

import com.hzw.grpc.AicGrpcCommonServiceGrpc;
import com.hzw.grpc.GrpcRequest;
import com.hzw.grpc.GrpcResponse;
import com.hzw.grpc.fram.common.utils.ClassTypeUtils;
import com.hzw.grpc.fram.message.AicGrpcMessageBuilder;
import com.hzw.grpc.fram.message.AicGrpcRequest;
import com.hzw.grpc.fram.message.ByteArrayWrapper;
import com.hzw.grpc.fram.serializer.Serializer;
import com.hzw.grpc.fram.serializer.SerializerFactory;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName AicGrpcHandler
 * @Description aic grpc 远程调用处理
 * @Author houzw
 * @Date 2023/7/25
 **/
@GrpcService
public class AicGrpcHandler extends AicGrpcCommonServiceGrpc.AicGrpcCommonServiceImplBase {
    Logger log = LoggerFactory.getLogger(getClass());

    private AicGrpcServiceFactory aicGrpcServiceFactory;

    private static Field causeField;

    static {
        try {
            causeField = Throwable.class.getDeclaredField("cause");
            causeField.setAccessible(true);
        } catch (Exception e) {
            causeField = null;
        }
    }


    public AicGrpcHandler(AicGrpcServiceFactory aicGrpcServiceFactory) {
        this.aicGrpcServiceFactory = aicGrpcServiceFactory;
    }

    @Override
    public void rpcInvoke(GrpcRequest grpcRequest, StreamObserver<GrpcResponse> responseObserver) {
        GrpcResponse response = doInvoke(grpcRequest);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private GrpcResponse doInvoke(GrpcRequest grpcRequest) {
        AicGrpcRequest request = SerializerFactory.getSerializer(
                SerializerFactory.PROTO_SERIALIZER_CODE
        ).deserialize(grpcRequest.getAicGrpcRequest().toByteArray(), AicGrpcRequest.class);

        // grpc 服务端解析request
        String interfaceName = request.getInterfaceName();
        String methodName = request.getMethodName();
        String[] methodArgSigs = request.getMethodArgSigs();
        Class[] methodArgClass = ClassTypeUtils.getClasses(methodArgSigs);
        // 序列化类型
        Byte serializerCode = request.getSerializerCode();
        Object[] methodArgs = request.getMethodArgs();

        // 反序列化实际参数对象
        Serializer serializer = SerializerFactory.getSerializer(serializerCode);
        methodArgs = new Object[methodArgSigs.length];
        for (int i = 0; i < methodArgSigs.length; i++) {
            methodArgs[i] = serializer.deserialize(
                    ((ByteArrayWrapper) request.getMethodArgs()[i]).array(),
                    ClassTypeUtils.getClass(methodArgSigs[i])
            );
        }

        // get interface Clazz by interfaceName
        Class interfaceClass = ClassTypeUtils.getClass(interfaceName);

        // get Method by method name
        Method method;
        try {
            method = interfaceClass.getMethod(methodName, methodArgClass);
        } catch (NoSuchMethodException e) {
            return AicGrpcMessageBuilder.buildGrpcErrorResponse(e.getMessage());
        }

        // get aic grpc service def by interface class
        AicGrpcServiceDefinition serviceDef = aicGrpcServiceFactory.getAicGrpcDef(interfaceClass);

        // do invoke
        Object appResponse;
        GrpcResponse response;
        try {
            appResponse = method.invoke(serviceDef.getRef(), methodArgs);
            Class<?> returnType = method.getReturnType();
            response = AicGrpcMessageBuilder.buildGrpcResponse(serializerCode, returnType, appResponse); // TODO 序列化int类型属于客户端反序列化有问题
        } catch (IllegalArgumentException e) { // 非法参数，接口和实现类有差异
            response = AicGrpcMessageBuilder.buildGrpcErrorResponse(e.getMessage());
        } catch (IllegalAccessException e) {  // 方法不可访问
            response = AicGrpcMessageBuilder.buildGrpcErrorResponse(e.getMessage());
        } catch (InvocationTargetException e) { // 业务代码抛出异常
            cutCause(e.getCause());
            response = AicGrpcMessageBuilder.buildGrpcResponse(serializerCode, e.getCause());
        } catch (Exception e) { // 其他异常
            cutCause(e.getCause());
            response = AicGrpcMessageBuilder.buildGrpcResponse(serializerCode, e.getCause());
//            response = AicGrpcMessageBuilder.buildGrpcResponse(serializerCode, e);
        }

        return response;
    }


    /**
     * 把业务层抛出的业务异常或者RuntimeException/Error，
     * 截断Cause，以免客户端因为无法找到cause类而出现反序列化失败.
     */
    public void cutCause(Throwable bizException) {
        if (causeField == null) {
            return;
        }
        Throwable rootCause = bizException;
        while (null != rootCause.getCause()) {
            rootCause = rootCause.getCause();
        }
        if (rootCause != bizException) {
            bizException.setStackTrace(rootCause.getStackTrace());
            try {
                causeField.set(bizException, bizException); // SELF-CAUSE
            } catch (Exception e) {
                log.warn("cutCause error", e);
            }
        }
    }
}
