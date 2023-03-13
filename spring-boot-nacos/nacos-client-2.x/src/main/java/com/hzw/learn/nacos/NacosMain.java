package com.hzw.learn.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

public class NacosMain {

    static String dataId = "test";
    static String group = "test";
    public static int nameport = 10000;
    public static String nacosAddress = System.getProperty("nacos", "localhost:8148,8248,8348");

    public static void main(String[] args) throws Exception {
        check(args);

//        String nacosAddress = System.getProperty("nacos", "localhost:8148,8248,8348");

        String type = "naming";
        String flag = "1";
        if (args.length > 0){
            type = args[0];
        }
        if (args.length > 1){
            flag = args[1];
        }
        String pNo = null;
        if (args.length > 2){
            pNo = args[2];
            nameport = new Integer(pNo) * 10000;
        }

        System.out.println("nacos:" + nacosAddress + " type:" + type +" flag:" + flag + " pNo:" + pNo );
        NamingService naming = getNaming();
        ConfigService config = getConfig();
        switch (type){
            case "naming":
                switch (flag){
                    case "1":
                        System.out.println("=="+pNo+"=namingSubscribeTest");
                        namingSubscribeTest(naming); // 服务订阅测试
                        break;

                    case "11":
                        while(true) {
                            getInstancesTest(naming);
                            Thread.sleep(3000);
                        }
//                        break;
                    case "2":
                        System.out.println("=="+pNo+"=registerInstanceTest");
                        registerInstanceTest(naming, nameport + 500); // 服务注册测试
                        break;
                    case "3":
                        System.out.println("=="+pNo+"=registerInstanceTest cycle");
                        Integer tempport = nameport;
                        int index = 0;
                        while(true) {
                            index ++;
                            try {
                                if (index>3){
                                    deregisterInstanceTest(naming, tempport + index - 3); // 注销掉旧的服务
//                                    Thread.sleep(1000);
                                }
                                registerInstanceTest(naming, tempport + index); // 服务注册测试
                            }catch (Exception e){
                                e.printStackTrace();
                                naming = getNaming();
                                index--;
                            }
                            Thread.sleep(4000);

                        }
                }
                break;
            case "config":
                switch (flag){
                    case "1":
                        System.out.println("=="+pNo+"=configListenerTest");
                        configListenerTest(config); // 配置订阅
                        break;
                    case "2":
                        System.out.println("=="+pNo+"=configPublishTest");
                        configPublishTest(config);
                        break;
                    case "3":
                        System.out.println("=="+pNo+"=configPublishTest cycle");
                        while(true) {
                            try {
                                configPublishTest(config); // 配置发布
                            }catch (Exception e){
                                e.printStackTrace();
                                config = getConfig();
                            }
                            Thread.sleep(5000);
                        }
//                        break;
                }
                break;
        }

    }

    public static NamingService getNaming() throws NacosException {
        String namespace = "test";

        Properties properties = new Properties();
        properties.put("serverAddr", nacosAddress);
        properties.put("namespace", namespace);
        // nacos 服务开启权限验证后需要提供用户名和密码
        properties.put("username","nacos");
        properties.put("password","nacos");
        NamingService namingService = NacosFactory.createNamingService(properties);
//        namingService = NacosFactory.createNamingService(serverAddr);
        return namingService;
    }

    public static ConfigService getConfig() throws NacosException {

        String namespace = "test";

        Properties properties = new Properties();
        properties.put("serverAddr", nacosAddress);
        properties.put("namespace", namespace);
        // nacos 服务开启权限验证后需要提供用户名和密码
        properties.put("username","nacos");
        properties.put("password","nacos");
        ConfigService configService = NacosFactory.createConfigService(properties);
        return configService;
    }

    public static void registerInstanceTest(NamingService namingService, Integer port) throws Exception {
        Instance instance = new Instance();
        instance.setIp("55.55.55.55");
        instance.setPort(port);
        instance.setHealthy(true);
        instance.setWeight(2.0);
        instance.setEphemeral(true); // 是否临时实例

        Map<String, String> instanceMeta = new HashMap<>();
        instanceMeta.put("site", "et2");
        instance.setMetadata(instanceMeta);
        instance.setServiceName("testService");
        instance.setClusterName("testCluster");

        System.out.println("注册服务" + new Gson().toJson(instance));
        namingService.registerInstance("hzw.test.service1", instance);

//        Thread.sleep(2000);

//        System.out.println("撤销服务" + new Gson().toJson(instance));
//        namingService.deregisterInstance("hzw.test.service1", instance);

//        Thread.sleep(1000);
    }

    public static void deregisterInstanceTest(NamingService namingService, Integer port) throws Exception {
        Instance instance = new Instance();
        instance.setIp("55.55.55.55");
        instance.setPort(port);
        instance.setHealthy(true);
        instance.setEphemeral(true); // 是否临时实例

        Map<String, String> instanceMeta = new HashMap<>();
        instanceMeta.put("site", "et2");
        instance.setMetadata(instanceMeta);
        instance.setServiceName("testService");
        instance.setClusterName("testCluster");

        System.out.println("撤销服务" + new Gson().toJson(instance));
        namingService.deregisterInstance("hzw.test.service1", instance);
    }

    public static void namingSubscribeTest(NamingService namingService) throws Exception {

        namingService.subscribe("hzw.test.service1", event -> {
            if (event instanceof NamingEvent) {
                System.out.print(((NamingEvent) event).getServiceName());
                List<String> ids = ((NamingEvent) event).getInstances().stream().map(
                        instance -> {
//                            return instance.getIp() + ":" + instance.getPort();
                            return "" + instance.getPort();
                        }
                ).sorted().collect(Collectors.toList());
                System.out.println(ids);
//                System.out.print(((NamingEvent) event).getServiceName());
//                System.out.println(((NamingEvent) event).getInstances());
            }
        });

        Thread.sleep(3600 * 1000);
    }

    public static void getInstancesTest(NamingService namingService) throws NacosException {

        List<String> ids = namingService.getAllInstances("hzw.test.service1",false).stream().map(
                instance -> {
//                            return instance.getIp() + ":" + instance.getPort();
                    return "" + instance.getPort();
                }
        ).sorted().collect(Collectors.toList());
        System.out.println(ids);
    }


    public static void configListenerTest(ConfigService configService) throws NacosException, InterruptedException {
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
        Thread.sleep(3600 * 1000);
    }

    public static void configPublishTest(ConfigService configService) throws NacosException {
        String content = configService.getConfig(dataId, group, 5000);
        String newcontent = content.substring(0, content.indexOf("time"));
        newcontent += "time: " + System.currentTimeMillis();
        boolean isPublishOk = configService.publishConfig(dataId, group, newcontent);
        System.out.println("" + isPublishOk + ":" + newcontent);
    }

    public static void check(String[] args){
        if (args.length<1){
            System.out.println("给个配置：\n" +
                    "1:订阅\n" +
                    "11:查询\n" +
                    "2：注册/修改\n" +
                    "3：注册/修改（循环）\n" +
                    "eg:\n" +
                    "naming/config 1/11/2/3 [1/2/3]\n" +
                    "-Dnacos=localhost:8148,8248,8348");
            System.exit(1);
        }
    }

}
