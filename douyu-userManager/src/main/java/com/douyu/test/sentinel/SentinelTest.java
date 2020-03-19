package com.douyu.test.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/16 16:39
 */
@RestController
public class SentinelTest {
    @GetMapping("hello")
    @SentinelResource(value = "test.hello", fallback = "helloError")
    public String hello(String name){
        return "hello,"+name;
    }

    public String helloError(String name, Throwable e){
        return "error,"+name;
    }
}
