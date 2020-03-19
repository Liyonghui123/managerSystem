package com.douyu.controller;

import com.douyu.pojo.User;
import com.douyu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/7 11:56
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理相关接口")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 新增客户
     * @param user
     * @return
     */
    @ApiOperation(value = "saveUser",notes = "添加用户")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveUser(User user){
        try {
            userService.insert(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 更新客户
     * @param user
     * @return
     */

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUser(User user){
        try {
            userService.updateByPrimaryKey(user);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 删除客户
     * @param
     * @return
     */
    @ApiOperation(value = "deleteUser",notes = "删除用户")
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "id",defaultValue = "0")Long id ){
        try {
            if(id.intValue()==0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            userService.deleteByPrimaryKey(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 根据ids查询客户
     * @param ids
     * @return
     */
    @ApiOperation(value = "queryUserByIds",notes = "根据多个id查询用户")
    @RequestMapping(value="{ids}",method = RequestMethod.GET)
    public ResponseEntity<List<User>> queryUser(@PathVariable String ids ){
        List<User> users=new ArrayList<>();
        try {
            String[] stringIds=ids.split(",");
            for(int i=0;i<stringIds.length;i++){
                Long id=Long.parseLong(stringIds[i]);
                User user = userService.selectByPrimaryKey(id);
                users.add(user);
            }
            if(null==users){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(users);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
