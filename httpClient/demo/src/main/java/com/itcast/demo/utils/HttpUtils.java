package com.itcast.demo.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import sun.security.krb5.Config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Component
public class HttpUtils {
    private PoolingHttpClientConnectionManager cm;

    public HttpUtils() {
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
    }
    //获取html
    public String doGetHtml(String url){
        CloseableHttpClient build = HttpClients.custom().setConnectionManager(cm).build();
        HttpGet get = new HttpGet(url);
        //加载配置
        get.setConfig(getConfig());
        get.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
        CloseableHttpResponse execute = null;
        String content = null;
        try {
            execute = build.execute(get);
            if (execute.getStatusLine().getStatusCode() == 200) {
                if (execute.getEntity() != null) {
                    content = EntityUtils.toString(execute.getEntity(), "utf-8");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (execute != null){
                    execute.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 获取图片
     * @return
     */
    public String doGetImage(String url) {
        CloseableHttpClient build = HttpClients.custom().setConnectionManager(cm).build();
        HttpGet get = new HttpGet(url);
        //加载配置
        get.setConfig(getConfig());
        CloseableHttpResponse execute = null;
        String content = null;
        try {
            execute = build.execute(get);
            if (execute.getStatusLine().getStatusCode() == 200) {
                if (execute.getEntity() != null) {
                    //获取图片后缀
                    String extName = url.substring(url.lastIndexOf("."));
                    //创建图片名,重命名图片
                    String uuid = UUID.randomUUID().toString().replace("-","")+extName;
                    //下载图片
                    OutputStream os = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\img",uuid));
                    execute.getEntity().writeTo(os);
                    //返回图片名称
                    content = uuid;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (execute != null){
                    execute.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }
    public RequestConfig getConfig() {
        RequestConfig build = RequestConfig.custom().setConnectionRequestTimeout(500).setConnectTimeout(1000).setSocketTimeout(10 * 10000).build();
        return build;
    }

}
