package com.douyu.service;

import com.douyu.pojo.Role;
import com.douyu.pojo.User;

import java.util.List;
public interface UserService {

    User selectUserByUserName(String userName);

    List<Role> selectRoleByUserName(String userName);

    User selectByPrimaryKey(Long id);

    void updateByPrimaryKey(User user);

    void insert(User user);

    void deleteByPrimaryKey(Long id);
}
