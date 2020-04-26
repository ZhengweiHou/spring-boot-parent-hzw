package com.hzw.learn.springboot.dubbo.router;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.MetadataReportConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance;
import org.springframework.beans.factory.InitializingBean;

import com.hzw.learn.springboot.dubbo.router.api.RouterHiApi;
import com.hzw.learn.springboot.dubbo.router.api.RouterHiImpl;
import com.hzw.learn.springboot.dubbo.router.common.HzwServiceConfig;

public class Provider_API{
    //例 -Dappname=app1 -Dappport=20881 -Dappservergroup=111 -Dappserverversion=0.0.1

	String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");
    String consulHost = System.getProperty("consul.address", "127.0.0.1");
    String zookeeperUrl = "zookeeper://" + zookeeperHost + ":2181";
    String APPNAME = System.getProperty("appname", "application");
    String APPPORT = System.getProperty("appport", "20881");
    String APPGROUP = System.getProperty("appgroup", "dubbo");
    
    String APPTAG = System.getProperty("apptag");
    String APPSERVERGROUP = System.getProperty("appservergroup", "1111");
    String APPSERVERVERSION = System.getProperty("appserverversion", "0.0.1");
    
	public void init(){
	    String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");
	    String consulHost = System.getProperty("consul.address", "127.0.0.1");
	    String zookeeperUrl = "zookeeper://" + zookeeperHost + ":2181";
	    String APPNAME = System.getProperty("appname", "application");
	    String APPPORT = System.getProperty("appport", "20881");
	    String APPGROUP = System.getProperty("appgroup", "dubbo");
	    
	    String APPTAG = System.getProperty("apptag","tag1");
	    String APPSERVERGROUP = System.getProperty("appservergroup", "1111");
	    String APPSERVERVERSION = System.getProperty("appserverversion", "0.0.1");
		
    	
    	System.getProperties().put("appname", APPNAME);
    	System.getProperties().put("appport", APPPORT);
	    
	}

    public void exportServer() {

    	ApplicationConfig applicationConfig = new ApplicationConfig(APPNAME);
//    	applicationConfig.setQosPort(33333); app
    	
    	RegistryConfig registry_zookeeper = new RegistryConfig(zookeeperUrl);
    	registry_zookeeper.setGroup(APPGROUP);
        ArrayList<RegistryConfig> registries = new ArrayList<>();
        registries.add(registry_zookeeper);

        ProtocolConfig protocol = new ProtocolConfig("dubbo",Integer.parseInt(APPPORT));
        protocol.setTelnet("cd,ps,select,log,ls,clear,count,invoke,exit,help,trace,pwd,shutdown,status");
        
        MetadataReportConfig metadata = new MetadataReportConfig();
        metadata.setAddress(zookeeperUrl);
        metadata.getMetaData().put("testMetaData1", "testMetaData2");
        
//        ServiceConfig<RouterHiApi> service = new ServiceConfig<>();
        HzwServiceConfig<RouterHiApi> service = new HzwServiceConfig<>();
//        service.setQueuename("123123123");
        
//        service.setProvider(provider);
//        service.setMonitor(monitor);
        service.setApplication(applicationConfig);
        
        service.setRegistries(registries);	
        service.setMetadataReportConfig(metadata);
        service.setProtocol(protocol);
        service.setTag(APPTAG);
        service.setGroup(APPSERVERGROUP);
        service.setInterface(RouterHiApi.class);
        service.setRef(new RouterHiImpl());
        service.setVersion(APPSERVERVERSION);
        service.setLoadbalance(RoundRobinLoadBalance.NAME);
        
        service.export();


        System.out.println("dubbo service started!  appport:" + System.getProperty("appport"));
        
    
    }
    

    public static void main(String[] args) throws Exception {
    	
    	Provider_API papi = new Provider_API();
    	papi.init();
    	papi.exportServer();
    	new CountDownLatch(1).await();
    }




}
