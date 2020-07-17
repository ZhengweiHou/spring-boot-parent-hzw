package com.hzw.learn.springboot.dubbo.AdaptiveExtension.dubboDemo;

import com.hzw.learn.springboot.dubbo.AdaptiveExtension.handDemo.Refill;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

@SPI
public interface RefillMaker {
    @Adaptive
    Refill makeRefill(URL url);


    @Adaptive({"makerkey","makername"})
    Refill makeRefillLight(URL url);
}
