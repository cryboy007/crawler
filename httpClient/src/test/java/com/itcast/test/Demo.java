package com.itcast.test;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    @Test
    public void testPost() {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://www.itcast.cn");
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                }
            }
        }
    }

    //Post带参数
    @Test
    public void testPost2() throws URISyntaxException, UnsupportedEncodingException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://yun.itheima.com/search");
      /*  //创建uriBuilder
        URIBuilder uriBuilder = new URIBuilder("http://yun.itheima.com/search");
        //设置参数
        uriBuilder.setParameter("keys","java");
        post.setURI(uriBuilder.build());*/
        //第二种方式
        List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
        valuePairs.add(new BasicNameValuePair("keys","java"));
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(valuePairs,"utf-8");
        post.setEntity(formEntity);
        CloseableHttpResponse execute = null;
        try {
            execute = httpClient.execute(post);
            if (execute.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(execute.getEntity(), "utf-8");
                System.out.println(content);
                System.out.println("共"+content.length());
                System.out.println(42764/60/60);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                execute.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
