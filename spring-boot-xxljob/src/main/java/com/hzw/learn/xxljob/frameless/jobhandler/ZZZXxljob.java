package com.hzw.learn.xxljob.frameless.jobhandler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ZZZXxljob
 * @Description TODO
 * @Author houzw
 * @Date 2024/7/3
 **/
public class ZZZXxljob {

    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        XxlJobHelper.log("XXL-JOB, Hello World.");

        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
