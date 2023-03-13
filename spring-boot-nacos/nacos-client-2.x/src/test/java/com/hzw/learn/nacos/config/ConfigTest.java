package com.hzw.learn.nacos.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Properties;
import java.util.concurrent.Executor;

@FixMethodOrder(MethodSorters.JVM)
public class ConfigTest {
    String serverAddr;
    String namespace;
    String dataId;
    String group;

    ConfigService configService;

    @Before
    public void init() throws NacosException {
        this.serverAddr = "localhost:8148";
        this.namespace = "test";
        this.dataId = "test";
        this.group = "test";

        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        properties.put("namespace", namespace);
        // nacos 服务开启权限验证后需要提供用户名和密码
        properties.put("username","nacos");
        properties.put("password","nacos");
        configService = NacosFactory.createConfigService(properties);
    }

    @Test
    public void getConfigTest() throws NacosException {
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
    }

    @Test
    public void configListenerTest() throws NacosException, InterruptedException {
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("recieve1:" + configInfo);
            }
            @Override
            public Executor getExecutor() {
                return null;
            }
        });

        // 测试让主线程不退出，因为订阅配置是守护线程，主线程退出守护线程就会退出。
        Thread.sleep(100 * 1000);
    }

    @Test
    public void publishConfigTest() throws NacosException {
        boolean isPublishOk = configService.publishConfig(dataId, group, "a: a\nb: b");
        System.out.println(isPublishOk);
    }

}
