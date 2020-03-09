package com.douyu.service;

import com.douyu.dao.UserMapper;
import com.douyu.pojo.Role;
import com.douyu.pojo.User;
import com.douyu.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/8 16:26
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public User selectUserByUserName(String userName) {
        UserExample userExample=new UserExample();
        userExample.or().andUserNameEqualTo(userName);
        List<User> users = userMapper.selectByExample(userExample);
        if (users==null){
            return null;
        }
        return users.get(0);
    }

    @Override
    public List<Role> selectRoleByUserName(String userName) {

        return this.userMapper.selectRoleByUserName(userName);
    }


}
