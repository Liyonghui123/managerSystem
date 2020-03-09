package com.douyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/6 21:37
 */
@MapperScan("com.douyu.dao")
@SpringBootApplication
public class UserManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserManagerApplication.class,args);
    }

}
