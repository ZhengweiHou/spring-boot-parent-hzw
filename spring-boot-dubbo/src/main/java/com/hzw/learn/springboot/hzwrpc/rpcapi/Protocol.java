package com.hzw.learn.springboot.hzwrpc.rpcapi;

import com.hzw.learn.springboot.hzwrpc.common.URL;

public interface Protocol {
    <T> Exporter<T> export(Invoker<T> invoker);

    <T> Invoker<T> refer(Class<T> type, URL url);

}
