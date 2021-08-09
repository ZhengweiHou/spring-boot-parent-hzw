package com.hzw.learn.springboot.jvm.javaindocker;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class PrintXmxXms{
    public static void main(String[] args) throws InterruptedException{
        int mb = 1024 * 1024;
        MemoryMXBean mmb = ManagementFactory.getMemoryMXBean();
        System.out.println("PrintXmxXms");

        boolean oom = false;
        if (args.length >0 && "oom".equalsIgnoreCase(args[0])){
            oom = true;
        }

        ArrayList al =  new ArrayList<Byte[]>();
        for(int i =0 ; i < 1000 ; i++){
            Thread.sleep(300);
            if (oom)
                al.add(new Byte[1024 * 1024]);
            else
                al.add(new SoftReference(new Byte[1024 * 1024]));

            long xmx = mmb.getHeapMemoryUsage().getMax()   / mb;
            long xms = mmb.getHeapMemoryUsage().getInit()  / mb;
            long used = mmb.getHeapMemoryUsage().getUsed() / mb;
            System.out.println(
                    "xms:" + xms + "mb," +
                    "xmx:" + xmx + "mb," +
                    "used:" + used + "mb");
        }
    }
}
