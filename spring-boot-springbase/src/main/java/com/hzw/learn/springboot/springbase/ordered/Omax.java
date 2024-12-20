package com.hzw.learn.springboot.springbase.ordered;

import org.springframework.core.Ordered;

public class Omax implements Ordered {

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
