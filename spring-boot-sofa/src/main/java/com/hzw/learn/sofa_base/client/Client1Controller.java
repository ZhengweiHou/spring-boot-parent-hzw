package com.hzw.learn.sofa_base.client;

import com.alipay.sofa.rpc.api.GenericService;
import com.alipay.sofa.rpc.bootstrap.Bootstraps;
import com.alipay.sofa.rpc.bootstrap.bolt.BoltConsumerBootstrap;
import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.*;
import com.alipay.sofa.rpc.core.exception.RpcErrorType;
import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.invoke.Invoker;
import com.hzw.learn.ext.HelloService;
import com.hzw.learn.ext.W;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

@RestController
public class Client1Controller {

    Logger logger = LoggerFactory.getLogger(Client1Controller.class);

    static int times=0;
    static ConcurrentHashMap<String,Invoker> invokers = new ConcurrentHashMap<>();


    @Autowired
    RegistryConfig registryConfig;

    @Autowired
    private String jvmProcessID;

    @Autowired
    HelloService helloService;

    @RequestMapping("/test")
    public void test(){
        logger.error("oneko");
    }
    @RequestMapping("/hello")
    public String hello(){
        String rsp = helloService.hello("Consumer[" + jvmProcessID + "]-" + ++times);
        System.out.println("Consumer[" + jvmProcessID + "]接收返回消息："+rsp);
        return rsp;
    }

    @RequestMapping("/hellow")
    public W hellow(){
        W w = new W();
        w.msg = "Consumer[" + jvmProcessID + "]-" + ++times;
        W wr = helloService.hellow(w);
        System.out.println("Consumer[" + jvmProcessID + "]接收返回消息："+wr.msg);
        return wr;
    }

    @RequestMapping("/hello2")
    public Object hello2() throws Throwable {
        ConsumerConfig<?> consumer = new ConsumerConfig();
        consumer.setInterfaceId("com.hzw.learn.sofa_base.sofa1.HelloService")
                .setProtocol("bolt")
                .setProxy("javassist")
                .setRegistry(registryConfig);
//        consumer.refer();

        String serviceNameKey = buildServiceName(consumer, consumer.getProtocol());
        logger.info("=======" + serviceNameKey);
        if (!invokers.containsKey(serviceNameKey)) {
            BoltConsumerBootstrap<?> consumerBootstrap = (BoltConsumerBootstrap) Bootstraps.from(consumer);
            consumerBootstrap.refer();
            Invoker proxyInvoker = consumerBootstrap.getProxyInvoker();
            invokers.put(serviceNameKey,proxyInvoker);
        }
        Invoker invoker = invokers.get(serviceNameKey);

        SofaRequest sReq = new SofaRequest();
        sReq.setInterfaceName("com.hzw.learn.sofa_base.sofa1.HelloService");
        sReq.setMethodName("hello");
        sReq.setMethodArgs(new Object[]{"hello"});
        sReq.setMethodArgSigs(new String[]{"java.lang.String"});


        SofaResponse sResp =  invoker.invoke(sReq);

//        SofaResponse sResp = proxyInvoker.invoke(sReq);

        if (sResp.isError()) {
            throw new SofaRpcException(RpcErrorType.SERVER_UNDECLARED_ERROR, sResp.getErrorMsg());
        }
        Object ret = sResp.getAppResponse();
        if (ret instanceof Throwable) {
            throw (Throwable) ret;
        } else {
            if (ret == null) {
//                return ClassUtils.getDefaultPrimitiveValue(method.getReturnType());
                return "null";
            }
            return ret;
        }
    }


    @RequestMapping("/hello3")
    public Object hello3() throws Throwable {
        ConsumerConfig<GenericService> consumer = new ConsumerConfig();
        consumer.setInterfaceId("com.hzw.learn.sofa_base.sofa1.HelloService")
                .setProtocol("bolt")
                .setGeneric(true)  // 设置范化调用类型
                .setProxy("javassist")
                .setCheck(true)
                .setRegistry(registryConfig);


        GenericService ref = consumer.refer();

        // 调用方式1
        String o1 = (String) ref.$invoke("hello",new String[]{"java.lang.String"},new Object[]{"hello"});
        // 调用方式2
        String o2 = ref.$genericInvoke("hello",new String[]{"java.lang.String"},new Object[]{"hello"},String.class);
        // 调用方式3
//        GenericObject geo = new GenericObject("[参数类名]");
//        geo.putField("[字段1名]",[字段1值]);
//        geo.putField("[字段2名]",[字段2值]);
//        String o3 = ref.$genericInvoke("hello",new String[]{"[参数类名]"},new Object[]{geo},String.class);

        return o1 + "###" + o2;
    }

    static String buildServiceName(AbstractInterfaceConfig config, String protocol) {
        if (RpcConstants.PROTOCOL_TYPE_BOLT.equals(protocol)
                || RpcConstants.PROTOCOL_TYPE_TR.equals(protocol)) {
            return ConfigUniqueNameGenerator.getServiceName(config) + ":DEFAULT";
        } else {
            return ConfigUniqueNameGenerator.getServiceName(config) + ":" + protocol;
        }
    }
}
