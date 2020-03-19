package com.douyu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.douyu.dubbo.api.RemoteUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/19 14:27
 */
@Component
@Service(version = "1.0.0",timeout = 10000,interfaceClass = RemoteUserService.class)
public class RemoteUserServiceImpl implements RemoteUserService {
    private static final Logger logger = LoggerFactory.getLogger(RemoteUserServiceImpl.class);
    @Override
    public String sayHello(String name) {
        return "Hello "+name;

    }
}
