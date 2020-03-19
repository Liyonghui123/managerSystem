package com.douyu.controller;

import com.douyu.pojo.User;
import com.douyu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Value("${default.password}")
    private String defaultPassword;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/indexPage", method = RequestMethod.GET)
    @ApiOperation(value = "indexPage", notes = "管理员首页")
    @ResponseBody
    public ResponseEntity indexPage() {
        return ResponseEntity.ok(" admin home page ");
    }


    @RequestMapping(value = "/resetPassword", method = RequestMethod.PUT)
    @ApiOperation(value = "resetPassword", notes = "重置密码")
    @ResponseBody
    public ResponseEntity<Void> resetPassword(Long id) {
        try {
            User user = userService.selectByPrimaryKey(id);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            } else {
                user.setUserPassword(defaultPassword);
                userService.updateByPrimaryKey(user);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
