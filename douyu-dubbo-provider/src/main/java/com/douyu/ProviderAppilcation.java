package com.douyu;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/18 16:21
 */
@EnableDubboConfiguration
@SpringBootApplication
public class ProviderAppilcation {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ProviderAppilcation.class, args).registerShutdownHook();
        countDownLatch.await();
    }
}
