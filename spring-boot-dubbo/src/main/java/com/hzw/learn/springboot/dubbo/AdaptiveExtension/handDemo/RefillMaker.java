package com.hzw.learn.springboot.dubbo.AdaptiveExtension.handDemo;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.SPI;

@SPI
public interface RefillMaker {
    Refill makeRefill(URL url);
}
