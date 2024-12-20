package com.hzw.learn.springboot.springbase.ordered;

import org.springframework.core.Ordered;

public class Omin implements Ordered {

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
