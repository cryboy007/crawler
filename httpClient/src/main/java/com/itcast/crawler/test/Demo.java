package com.itcast.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.swing.text.html.parser.Entity;

public class Demo {
    public static void main(String[] args) throws Exception{
        //1.打开浏览器,创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //访问的地址
        HttpGet httpGet = new HttpGet("https://voice.baidu.com/act/newpneumonia/newpneumonia/?from=osari_pc_3#tab0");
        //模拟按回车请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //判断响应是否是200
        if (response.getStatusLine().getStatusCode() == 200 ) {
            //如果响应成功,获取HttpEntity
            HttpEntity entity = response.getEntity();
            //使用Entity工具类
            String content = EntityUtils.toString(entity, "utf8");
            System.out.println(content);
        }
    }
}
