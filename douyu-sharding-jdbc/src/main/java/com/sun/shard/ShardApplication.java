package com.sun.shard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/20 17:41
 */
@SpringBootApplication
@MapperScan("com.sun.shard.mapper")
public class ShardApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardApplication.class, args);
    }
}
