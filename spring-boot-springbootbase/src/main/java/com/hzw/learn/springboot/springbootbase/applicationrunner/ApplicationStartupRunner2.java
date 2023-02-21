package com.hzw.learn.springboot.springbootbase.applicationrunner;

import com.google.gson.Gson;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName ApplicationStartupRunner
 * @Description TODO
 * @Author houzw
 * @Date 2022/10/13
 **/
@Component
@Order(2)
public class ApplicationStartupRunner2 implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(this.getClass().getSimpleName() + new Gson().toJson(args));
    }
}
