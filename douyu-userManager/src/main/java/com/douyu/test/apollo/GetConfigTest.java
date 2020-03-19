package com.douyu.test.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/16 14:32
 */
@RestController
public class GetConfigTest {
    public static void main(String[] args) throws InterruptedException {
        Config config = ConfigService.getConfig("userManager.spring");
        String somekey = "spring.application.name";
        while (true) {
            String value = config.getProperty(somekey, null);
            System.out.println(value);
            Thread.sleep(1000);
        }
    }

    @GetMapping("/test")
    public String test(@Value("${spring.datasource.type}")String test){
        return test;
    }
}
