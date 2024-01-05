package com.hzw.grpc.fram.serializer.protostuff;

/**
 * Protostuff can only serialize/deserialize POJOs, for those it can't deal with, use this Wrapper.
 */
public class Wrapper<T> {
    private T data;

    public Wrapper(T data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
