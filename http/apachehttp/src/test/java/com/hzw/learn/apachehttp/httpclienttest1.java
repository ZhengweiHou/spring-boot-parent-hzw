package com.hzw.learn.apachehttp;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class httpclienttest1 {
    @Test
    public void testHello() throws ClientProtocolException, IOException, InterruptedException {

        String getUrl = "http://localhost:8099/actuator/prometheus";
        String getUrl2 = "http://localhost:8099/actuator/health";
        String getUrl3 = "http://192.168.105.63:8099/11111";
        Boolean keepAlive = true;
        // Boolean keepAlive = false;

        // 1. 构建RequestConfig
        RequestConfig config = RequestConfig.custom()
            // 连接超时时间
            .setConnectTimeout(5000)
            // 从连接池中获取连接的超时时间
            .setConnectionRequestTimeout(1000)
            // 数据传输的超时时间
            .setSocketTimeout(5000)
            .build();


        // 2. 创建HttpClientBuilder
        HttpClientBuilder builder = HttpClients.custom()
            .setDefaultRequestConfig(config);
        // 设置keepalive策略
        if (keepAlive) {
            builder.setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE);
            // builder.setKeepAliveStrategy((response, context) -> 60 * 1000);
        }else {
            builder.setConnectionReuseStrategy(NoConnectionReuseStrategy.INSTANCE);
        }
        // 设置连接池
        builder.setMaxConnPerRoute(15);
        builder.setMaxConnTotal(100);
        // builder.setConnectionTimeToLive(5, TimeUnit.SECONDS);
        builder.evictIdleConnections(5, TimeUnit.SECONDS);// httpclient 4.5版本 开始支持idle驱逐功能 目前最高版本4.5.14

        // 3. 创建HttpClient
        CloseableHttpClient httpClient = builder.build();


        // 4. 创建请求
        HttpGet httpGet = new HttpGet(getUrl);
   
        // 5. 发送请求
        CloseableHttpResponse resp = httpClient.execute(httpGet);
        String body = EntityUtils.toString(resp.getEntity());

        // 6. 处理响应
        System.out.println(body);

        // 并发请求，控制同时发送
        CountDownLatch cd = new CountDownLatch(1);
        for (int i = 0; i < 5; i++) {

            new Thread(() -> {
                try {
                    HttpGet req = new HttpGet(getUrl);
                    cd.await();
                    CloseableHttpResponse resp2 = httpClient.execute(req);
                    // EntityUtils.toString(resp2.getEntity());
                    EntityUtils.toByteArray(resp2.getEntity());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start(); 
        }
        // Thread.sleep(1000);
        cd.countDown();

        // Thread.sleep(200000);

        for (int i = 0; i < 10; i++) {
            // resp = httpClient.execute(new HttpPost(getUrl));
            resp = httpClient.execute(new HttpPost(getUrl3));
            // resp = httpClient.execute(new HttpPost("https://jsonplaceholder.typicode.com/posts/1"));
            // EntityUtils.toString(resp.getEntity());
            EntityUtils.toByteArray(resp.getEntity());
        }
        // Thread.sleep(15000);
        System.out.println();
        /* 
        */


        



    }
    
}
