package com.hzw.learn.apachehttp;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpClientDemo {
    private static final Gson gson = new Gson();
    
    public static void main(String[] args) {
        // GET请求示例
        String getUrl = "https://jsonplaceholder.typicode.com/posts/1";
        try {
            String getResponse = sendGetRequest(getUrl);
            System.out.println("GET响应: " + getResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // POST请求示例
        String postUrl = "https://jsonplaceholder.typicode.com/posts";
        Map<String, String> postData = new HashMap<>();
        postData.put("title", "foo");
        postData.put("body", "bar");
        postData.put("userId", "1");
        
        try {
            String postResponse = sendPostRequest(postUrl, postData);
            System.out.println("POST响应: " + postResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String sendGetRequest(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        }
    }

    public static String sendPostRequest(String url, Map<String, String> data) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        
        // 设置请求头
        httpPost.setHeader("Content-type", "application/json");
        
        // 转换数据为JSON
        String json = gson.toJson(data);
        httpPost.setEntity(new StringEntity(json));
        
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        }
    }
}
