package com.hzw.learn.sofa_consumer2multiprovider.server;

import com.hzw.learn.ext.HelloService;
import com.hzw.learn.ext.HzwException;
import com.hzw.learn.ext.W;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;

public class HelloServiceImpl implements HelloService {
    @Autowired
    Environment env;

    static int times=0;
    ArrayList al =  new ArrayList<Byte[]>();

    @Autowired
    private String jvmProcessID;

    @Override
    public String hello(String mesg) {
        System.out.println("Provider[" + jvmProcessID + "]接收消息：" + mesg);

        showJVMInfo();
        times++;
//        al.add(new SoftReference(new byte[1 * 1024 * 1024])); // 弱引用占住内存
        return "Provider[" + jvmProcessID + "]-" + times +"  " + env.getProperty("uniqueid");
    }

    @Override
    public String hello2() {
        return "hello too!!";
    }

    @Override
    public W hellow(W w) {
        return null;
    }

    @Override
    public String helloException(String msg) throws HzwException {
        return null;
    }

    private String showJVMInfo(){
        MemoryMXBean mmb = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = mmb.getHeapMemoryUsage();

        long max = heapMemoryUsage.getMax();
        long init = heapMemoryUsage.getInit();
        long committed = heapMemoryUsage.getCommitted();
        long used = heapMemoryUsage.getUsed();
        System.out.println(String.format("max:%-10s init:%-10s committed:%-10s used%-10s",max>>20,init>>20,committed>>20,used>>20));

        return null;
    }
}
