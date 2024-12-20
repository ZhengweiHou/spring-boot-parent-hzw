package com.hzw.learn.nacos.boot.nacosconfig;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName StartupRunner
 * @Description TODO
 * @Author houzw
 * @Date 2024/9/10
 **/
@Component
public class StartupRunner implements ApplicationRunner {
    @Autowired
    HzwProperties hzwProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("===" + new Gson().toJson(hzwProperties));
    }
}
