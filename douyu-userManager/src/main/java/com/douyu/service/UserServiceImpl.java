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

        return userMapper.selectRoleByUserName(userName);
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKey(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }


}
