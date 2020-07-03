package com.hzw.learn.springboot.springbase.DI.DIDependsOn;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @ClassName C
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/3
 **/
@Component
@DependsOn("a")
public class C extends XBase {

    @PreDestroy
    public void destroy() throws Exception {
        super.pdestory();
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        super.pinit();
    }
}
