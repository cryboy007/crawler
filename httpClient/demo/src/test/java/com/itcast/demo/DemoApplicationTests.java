package com.itcast.demo;

import com.itcast.demo.task.ItemTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private ItemTask itemTask;
    @Test
    void contextLoads() throws Exception {
        itemTask.itemTask();
    }

}
