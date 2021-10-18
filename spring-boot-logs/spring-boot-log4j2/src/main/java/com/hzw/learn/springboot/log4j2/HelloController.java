package com.hzw.learn.springboot.log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    Logger logger= LoggerFactory.getLogger(HelloController.class);
    Logger logger2= LoggerFactory.getLogger("HZW");

    int i=0;

    @RequestMapping("/hello")
    public String hello(){
        i++;

        logger.trace("logger trace:" + i);
        logger.debug("logger debug:" + i);
        logger.info ("logger info :" + i);
        logger.warn ("logger warn :" + i);
        logger.error("logger error:" +i);

        logger2.trace("logger trace:" + i);
        logger2.debug("logger debug:" + i);
        logger2.info ("logger info :" + i);
        logger2.warn ("logger warn :" + i);
        logger2.error("logger error:" +i);

        return "hello";
    }
}
