package com.hzw.learn.xxljob.frameless.conf;

import com.hzw.learn.xxljob.frameless.jobhandler.ZZZXxljob;
import com.xxl.job.core.executor.impl.XxlJobSimpleExecutor;

import java.util.Arrays;

/**
 * @ClassName FramelessXxlJobConfig
 * @Description TODO
 * @Author houzw
 * @Date 2024/7/3
 **/
public class FramelessXxlJobConfig {
    static FramelessXxlJobConfig instance  =  new FramelessXxlJobConfig();

    public static FramelessXxlJobConfig getInstance(){
        return instance;
    }

    public XxlJobSimpleExecutor createXxlJobSimpleExecutor(){
        XxlJobSimpleExecutor executor = new XxlJobSimpleExecutor();

        // init executor
        executor.setAdminAddresses("http://localhost:8080/xxl-job-admin");
        executor.setAccessToken("default_token");
        executor.setAppname("hzw-xxljob-frameless");
//        executor.setAddress("");
//        executor.setIp("localhost");
        executor.setPort(9998);
        executor.setLogPath("/tmp/xxljob_frameless");
//        executor.setLogRetentionDays(10);

        // registry job bean
        executor.setXxlJobBeanList(Arrays.asList(new ZZZXxljob()));

        return executor;
    }
}
