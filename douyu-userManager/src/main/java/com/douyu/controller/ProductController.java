package com.douyu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/8 14:53
 */
@Controller
@RequestMapping("/product")
@Api(tags = "产品相关接口")
public class ProductController {
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "currentUser",notes = "当前用户")
    String currentUser() {
        String currentUser = "";
        Object principl = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principl instanceof UserDetails) {
            currentUser = ((UserDetails)principl).getUsername();
        }else {
            currentUser = principl.toString();
        }
        return " some product info,currentUser is: "+currentUser;
    }
}
