package com.itcast.test;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLOutput;

public class PoolHttpClientTest {
    @Test
    public void test() {
        //创建连接池管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        //最大连接数
        cm.setMaxTotal(200);
        //单个主机最大连接数
        cm.setDefaultMaxPerRoute(10);
        //使用连接池管理器发起请求
        doGet(cm);
        doGet(cm);
    }

    private void doGet(PoolingHttpClientConnectionManager cm) {
        CloseableHttpClient build = HttpClients.custom().setConnectionManager(cm).build();
        HttpGet get = new HttpGet("https://www.baidu.com");
        //配置请求信息
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000)//设置创建连接最长时间
                .setSocketTimeout(10*1000)//设置数据传输的最长时间
                .setConnectionRequestTimeout(500).build();//设置获取连接最长时间
        get.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            response = build.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String s = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(s);
                System.out.println(s.length());
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
