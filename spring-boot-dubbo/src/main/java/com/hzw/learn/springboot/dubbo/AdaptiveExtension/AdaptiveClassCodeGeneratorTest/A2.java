package com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.rpc.Invocation;

public interface A2 {
    @Adaptive
    void sayHello2(URL url);

    @Adaptive
    void sayHello(URL url, Invocation invocation);

    @Adaptive({"hhh","zzz","www"})
    void sayHello3(URL url);
}
