package com.itcast.demo.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcast.demo.domain.JdItemInfo;
import com.itcast.demo.service.JdService;
import com.itcast.demo.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Component
public class ItemTask {
    @Autowired
    private HttpUtils httpUtils;

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private JdService jdService;

    @Scheduled(fixedDelay = 100 * 1000)
    public void itemTask() throws Exception{
        String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&wq=%E6%89%8B%E6%9C%BA&pvid=520460c6d7c24d8092786696fd99c88f&page=";
        for (int i = 1;i < 100;i=i+2) {
            String uri = url + i;
            //获取html内容
            String content = httpUtils.doGetHtml(uri);
            //解析html内容  获取数据
            this.parse(content);
        }
    }

    private void parse(String content) throws JsonProcessingException {
        Document doc = Jsoup.parse(content);
        //获取spu
        Elements select = doc.select("#J_goodsList > ul >li");
        for (Element element : select) {
            String spu = element.attr("data-spu");
            Elements elements = element.select("li.ps-item img");
            for (Element ele : elements) {
                String skuId = ele.attr("data-sku");
                JdItemInfo jdItemInfo = new JdItemInfo();
                jdItemInfo.setSku(new BigInteger(skuId));
                List<JdItemInfo> list = jdService.findAll(jdItemInfo);
                if (list.size() != 0) {
                    //说明已经存储过了
                    continue;
                }
                jdItemInfo.setSpu(new BigInteger(spu));
                //存储连接
                String url = "https://item.jd.com/"+skuId+".html";
                jdItemInfo.setUrl(url);
                //图片
                String src = ele.attr("data-lazy-img");
                String imgUrl = "https:"+src.replace("/n9","/n1");
                String image = httpUtils.doGetImage(imgUrl);
                jdItemInfo.setPic(image);
                System.out.println(image);
                String pic = "https://p.3.cn/prices/mgets?skuIds=J_"+skuId;
                String json = this.httpUtils.doGetHtml(pic);
                //解析json数据
                double p = om.readTree(json).get(0).get("p").asDouble();
                jdItemInfo.setPrice(p);

                jdItemInfo.setCreated(new Date());
                //拉取标题
                String titleHtml = this.httpUtils.doGetHtml(jdItemInfo.getUrl());
                String text = Jsoup.parse(titleHtml).select(".sku-name").first().text();
                jdItemInfo.setTitle(text);
                //System.out.println(text);
                jdItemInfo.setUpdated(jdItemInfo.getCreated());


                //保存到数据库
                jdService.save(jdItemInfo);
            }
        }
    }
}
