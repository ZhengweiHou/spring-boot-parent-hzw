package com.hzw.grpc.fram.server;

import com.google.protobuf.ByteString;
import com.google.protobuf.ProtocolStringList;
import com.hzw.grpc.AicGrpcCommonServiceGrpc;
import com.hzw.grpc.GrpcRequest;
import com.hzw.grpc.GrpcResponse;
import com.hzw.grpc.fram.common.cache.ReflectCache;
import com.hzw.grpc.fram.common.utils.ClassTypeUtils;
import com.hzw.grpc.fram.message.AicGrpcMessageBuilder;
import com.hzw.grpc.fram.message.AicGrpcRequest;
import com.hzw.grpc.fram.message.AicGrpcResponse;
import com.hzw.grpc.fram.message.ProtoStuffSerializer;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        AicGrpcRequest request = ProtoStuffSerializer.deserialize(grpcRequest.getAicGrpcRequest().toByteArray(), AicGrpcRequest.class);

        // grpc 服务端解析request
        String interfaceName = request.getInterfaceName();
        String methodName = request.getMethodName();
        String[] methodArgSigs = request.getMethodArgSigs();
        Class[] methodArgClass = ClassTypeUtils.getClasses(methodArgSigs);

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
        try {
            appResponse = method.invoke(serviceDef.getRef(), request.getMethodArgs());
        } catch (IllegalAccessException e) {
            return AicGrpcMessageBuilder.buildGrpcResponse(e);
//            return AicGrpcMessageBuilder.buildGrpcErrorResponse(e.getMessage());
        } catch (InvocationTargetException e){
            return AicGrpcMessageBuilder.buildGrpcResponse(e.getTargetException());
        } catch (Exception e){
            // 其他业务异常透传到客户端
            return AicGrpcMessageBuilder.buildGrpcResponse(e);
        }

        GrpcResponse response = AicGrpcMessageBuilder.buildGrpcResponse(appResponse);
        return response;
    }
}
