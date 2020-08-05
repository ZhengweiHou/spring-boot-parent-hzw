package com.hzw.learn.springboot.hzwrpc.protocol.http;

import com.hzw.learn.springboot.hzwrpc.common.URL;
import com.hzw.learn.springboot.hzwrpc.rpcapi.Exporter;
import com.hzw.learn.springboot.hzwrpc.rpcapi.Invoker;
import com.hzw.learn.springboot.hzwrpc.rpcapi.Protocol;

public class HttpProtocol implements Protocol {
    @Override
    public <T> Exporter<T> export(Invoker<T> invoker) {
        return null;
    }

    @Override
    public <T> Invoker<T> refer(Class<T> type, URL url) {
        return null;
    }
}
