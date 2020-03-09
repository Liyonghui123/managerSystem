package com.douyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/8 14:58
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/home")
    @ResponseBody
    public String productInfo(){
        return " admin home page ";
    }
}
