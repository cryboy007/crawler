package com.itcast.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class JsoupTest {
    @Test
    public void test() throws IOException {
        Document parse = Jsoup.parse(new URL("https://www.baidu.com"),1000);
        Element title = parse.getElementsByTag("title").first();
        System.out.println(parse.body().html());
        System.out.println(title.text());
    }

    @Test
    public void test2() throws IOException {
        Document parse = Jsoup.parse(new URL("https://www.baidu.com"),1000);
        //标签选择器
        /*Elements elements = parse.select("span");
        for (Element element : elements) {
            System.out.println(element.html());
        }*/
        //
        Elements select = parse.select("#mMenu");
        System.out.println(select.prevAll());
    }
}
