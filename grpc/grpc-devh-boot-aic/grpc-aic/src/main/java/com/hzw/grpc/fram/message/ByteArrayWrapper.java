package com.hzw.grpc.fram.message;

/**
 * @ClassName ByteArrayWrapper
 * @Description TODO
 * @Author houzw
 * @Date 2023/8/7
 **/
public class ByteArrayWrapper {
    private final byte[] data;

    public ByteArrayWrapper(byte[] data) {
        this.data = data;
    }

    public byte[] array(){
        return data;
    }
}
