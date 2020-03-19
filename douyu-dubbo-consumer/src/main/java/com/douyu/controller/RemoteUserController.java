package com.douyu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.douyu.dubbo.api.RemoteUserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/19 14:32
 */
@RestController
public class RemoteUserController {
    @Reference(version = "1.0.0")
    private RemoteUserService remoteUserService;

    @RequestMapping(value="/dubbo/say/{name}",method = RequestMethod.GET)
    public String sayHello(@PathVariable("name") String name){

        String result=remoteUserService.sayHello(name);
        return result;
    }
}
