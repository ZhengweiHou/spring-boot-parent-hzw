package com.hzw.learn.springboot.springbootbase.applicationrunner;

import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @ClassName CommandLineStartupRunner
 * @Description TODO
 * @Author houzw
 * @Date 2022/10/13
 **/
@Component
@Order(2)
public class CommandLineStartupRunner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.getClass().getSimpleName() + Arrays.toString(args));
        System.out.println(this.getClass().getSimpleName() + new Gson().toJson(args));
    }
}
