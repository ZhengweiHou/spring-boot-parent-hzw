package com.hzw.grpc.demo.server;

import com.hzw.grpc.demo.api.HzwApi;
import com.hzw.grpc.fram.server.AicGrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service
@AicGrpcService
public class HzwServiceImpl implements HzwApi {
    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String sayHello(String name) {
        System.out.println("str:" + name);
        return "str:" + name;
    }

    @Override
    public int sayHello(int num) {
        System.out.println("int:" + num);
        return num * 10;
    }

    @Override
    public HashMap sayMap(HashMap map) {
        if (map != null) {
            map.put("H", "h");
        }
        return map;
    }

    @Override
    public boolean sayHello(boolean b) {
        System.out.println("boolean:" + b);
        return b;
    }

    @Override
    public Integer sayHello(Integer b) {
        System.out.println("Integer:" + b);
        return b * 10;
    }

    @Override
    public String sayHello(String name, int age) {
        System.out.println("str:" + name + ",int:"+age);
        return "str:" + name + ",int:"+age;
    }

    @Override
    public String sayHello(String name, Date date) {
        System.out.println("str:" + name + ",date:"+date);
        return "str:" + name + ",date:"+new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    @Override
    public void voidHello(String name) {
        System.out.println("str:" + name);
    }

    @Override
    public void exceptionHello(String name) {
        System.out.println("str:" + name);
        throw new RuntimeException("hzwtesterror");
//        throw new HServerException("hserver exception test");
    }


//
//    @Override
//    public String sayHello(String name) {
//        System.out.println(name);
//        int rn = new Random().nextInt(10);
//
//        log.info("rn:" + rn);
//
//        if ( rn < 5) {
//            log.info("hserver exception test");
//            throw new HServerException("hserver exception test");
//        }
//        if (rn < 11) {
//            log.info("抛出异常测试");
//            getRunException();
//        }
//        return "hello--- " + name;
//    }
//
//    @Override
//    public int sayHello(int num) {
//        log.info("int:" + num);
//        return num + 10000;
//    }
//
//    @Override
//    public String sayHello(boolean b) {
//        log.info("boolean:" + b);
//        return String.valueOf(b);
//    }
//
//    @Override
//    public String sayHello(String name, String age) {
//        log.info("sayHello");
////        System.out.println(ContextHolder.USERNAME_CONTEXT_KEY.get().get(ContextHolder.username_meta_key));
//        Metadata metadata = ContextHolder.METADATA_CONTEXT_KEY.get();
//        String username = metadata.get(ContextHolder.username_meta_key);
//        System.out.println(username);
//
//        System.out.println(ContextHolder.getUsername());
//        return "hello---" + name + " age:" + age ;
//    }
//
//    @Override
//    public void voidHello(String name) {
//        System.out.println(name);
//    }
//
//    @Override
//    public String sayHello(String name, Date date) {
//
//        return "hello:" + name + date.toString();
//    }
//
//    private void getRunException(){
//        throw new RuntimeException("hzw 的错误测试");
//    }
}
