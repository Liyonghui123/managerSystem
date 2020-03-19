package com.douyu.consumer;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.douyu.pojo.OrderPaidEvent;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/19 16:40
 */
@Service
@RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
public class Consumer implements RocketMQListener<OrderPaidEvent> {
    @Override
    public void onMessage(OrderPaidEvent message) {
        System.out.print("------- OrderPaidEventConsumer received:"+ JSON.toJSONString(message));
    }
}
