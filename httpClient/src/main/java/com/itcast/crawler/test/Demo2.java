package com.itcast.crawler.test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Demo2 {
    public static void main(String[] args) throws IOException {
        //创建Http
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建get请求
        HttpGet get = new HttpGet("https://www.baidu.com");
        CloseableHttpResponse response = httpClient.execute(get);
        //响应成功
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(content);
        }
    }
}
