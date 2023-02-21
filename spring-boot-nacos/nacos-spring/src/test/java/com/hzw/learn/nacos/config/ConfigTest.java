package com.hzw.learn.nacos.nacosconfig.configs;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Properties;

@FixMethodOrder(MethodSorters.JVM)
public class ConfigTest {
    @Test
    public void test1(){
        // nacos api 手动获取配置
        try {
            String serverAddr = "localhost:8848";
            String dataId = "test.properties";
            String group = "test";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
}
