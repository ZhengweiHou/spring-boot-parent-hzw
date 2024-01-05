package com.hzw.grpc.demo.server;

/**
 * @ClassName HServerException
 * @Description TODO
 * @Author houzw
 * @Date 2023/10/23
 **/
public class HServerException extends RuntimeException{
    public HServerException(String message) {
        super(message);
    }
}
