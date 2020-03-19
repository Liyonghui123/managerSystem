package com.douyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/18 13:44
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class XxljobApplication {
    public static void main(String[] args) {
        SpringApplication.run(XxljobApplication.class, args);
    }

}
