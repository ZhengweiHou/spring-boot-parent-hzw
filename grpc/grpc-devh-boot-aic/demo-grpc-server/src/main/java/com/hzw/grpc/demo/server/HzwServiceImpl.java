package com.hzw.grpc.demo.server;

import com.hzw.grpc.demo.api.HzwApi;
import com.hzw.grpc.fram.server.AicGrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AicGrpcService
public class HzwServiceImpl implements HzwApi {
    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String sayHello(String name) {
        System.out.println(name);
        if (new Random().nextInt(10) < 5) {
            log.info("抛出异常测试");
            getRunException();
        }
        return "hello--- " + name;
    }

    @Override
    public void voidHello(String name) {
        System.out.println(name);
    }

    private void getRunException(){
        throw new RuntimeException("hzw 的错误测试");
    }
}
