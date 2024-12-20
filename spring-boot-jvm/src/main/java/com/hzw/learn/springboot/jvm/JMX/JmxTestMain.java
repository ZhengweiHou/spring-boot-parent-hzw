package com.hzw.learn.springboot.jvm.JMX;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;

/**
 * @ClassName JmxTestMain
 * @Description TODO
 * @Author houzw
 * @Date 2024/7/30
 **/
public class JmxTestMain {
    public static void main(String[] args) throws Exception {

        MBeanServer mserver = ManagementFactory.getPlatformMBeanServer();
//        // 创建RMI注册表
//        LocateRegistry.createRegistry(9999);
//        // 创建JMX服务URL
//        JMXServiceURL jmxUrl = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
//        // 创建并启动JMX连接器服务器
//        JMXConnectorServer jmxServer = JMXConnectorServerFactory.newJMXConnectorServer(jmxUrl, null, mserver);
//        jmxServer.start();

        Hello hello = new Hello();

        ObjectName oname = new ObjectName("jmxBean:name=hello");
        ObjectName oname2 = new ObjectName("hzw:type=hello");

        mserver.registerMBean(hello,oname);
        mserver.registerMBean(hello,oname2);

        while (true){
            Thread.sleep(1000);
            hello.index ++;
        }
    }
}
