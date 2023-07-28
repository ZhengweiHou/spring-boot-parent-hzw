package com.hzw.grpc.fram.exception;


public class AicGrpcRpcException extends RuntimeException {

    protected AicGrpcRpcException() {
    }

    public AicGrpcRpcException(String message) {
        super(message);
    }

    public AicGrpcRpcException(Throwable cause) {
        super(cause);
    }

    public AicGrpcRpcException(String message, Throwable cause) {
        super(message, cause);
    }

}
