package com.hzw.learn.springboot.springbase.ordered;

import org.springframework.core.Ordered;

public class O2 implements Ordered {

    @Override
    public int getOrder() {
        return 2;
    }
}
