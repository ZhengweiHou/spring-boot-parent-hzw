package com.hzw.learn.springboot.springbootbase.applicationrunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @ClassName CommandLineStartupRunner
 * @Description TODO
 * @Author houzw
 * @Date 2022/10/13
 **/
@Component
@Order(1)
public class CommandLineStartupRunner1 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.getClass().getSimpleName() + Arrays.toString(args));
    }
}
