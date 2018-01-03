package com.mp.web.controller;

import com.mp.mq.HelloSender;
import com.mp.mq.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

    @Autowired
    private HelloSender helloSender;

    @Autowired
    private TopicSender topicSender;

    @Test
    public void hello() throws Exception {
        helloSender.send();
    }

    @Test
    public void topic() throws Exception {
        topicSender.send1();
    }

    @Test
    public void topic2() throws Exception {
        topicSender.send2();
    }

}