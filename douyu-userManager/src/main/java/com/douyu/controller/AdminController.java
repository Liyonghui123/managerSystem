package com.douyu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/8 14:58
 */
@Controller
@Api(tags = "管理员相关接口")
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(value = "/indexPage",method = RequestMethod.GET)
    @ApiOperation(value = "indexPage",notes = "管理员首页")
    @ResponseBody
    public String indexPage(){
        return " admin home page ";
    }
}
