package com.douyu;

import com.douyu.pojo.OrderPaidEvent;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/19 16:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Producer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Test
    public void testRocketMq1() {

        String name = "aaa";
        rocketMQTemplate.convertAndSend("test-topic-1", name);
        rocketMQTemplate.send("test-topic-2", MessageBuilder.withPayload(new OrderPaidEvent("aa,22",new BigDecimal("22"))).build());

        System.err.println("发送成功...");

    }

}
