package com.hzw.learn.aop.aspectj;

import com.hzw.learn.aop.aspectj.dep.HzwAspect;
import org.springframework.context.annotation.*;

/**
 * @ClassName HzwAspectConfig
 * @Description TODO
 * @Author houzw
 * @Date 2023/10/11
 **/
@Configuration
@ImportResource(locations = { "classpath:service-context.xml" })
@EnableAspectJAutoProxy
public class HzwAspectConfig {

//    @Bean
//    HzwAspect hzwAspect(){
//        return new HzwAspect();
//    }
}
