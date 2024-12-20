package com.hzw.learn.xxljob.frameless;

import com.hzw.learn.xxljob.frameless.conf.FramelessXxlJobConfig;
import com.xxl.job.core.executor.impl.XxlJobSimpleExecutor;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName FramelessApplication
 * @Description TODO
 * @Author houzw
 * @Date 2024/7/3
 **/
public class FramelessApplication {

    public static void main(String[] args) {
        XxlJobSimpleExecutor executor = FramelessXxlJobConfig.getInstance().createXxlJobSimpleExecutor();
        try {
            executor.start();
            System.out.println("runing...");
            // Blocks until interrupted
            while (true) {
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }finally {
           executor.destroy();
        }

    }
}
