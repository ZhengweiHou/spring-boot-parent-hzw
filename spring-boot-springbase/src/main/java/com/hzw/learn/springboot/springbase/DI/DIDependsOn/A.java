package com.hzw.learn.springboot.springbase.DI.DIDependsOn;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @ClassName A
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/3
 **/
public class A extends XBase implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        super.pdestory();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.pinit();
    }
}
