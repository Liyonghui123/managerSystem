package com.douyu.service;

import com.douyu.dao.UserRoleMapper;
import com.douyu.pojo.UserRoleExample;
import com.douyu.pojo.UserRoleKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/15 15:47
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;
    @Override
    public void insert(UserRoleKey userRole) {
        userRoleMapper.insert(userRole);
    }

    @Override
    public void deleteByPrimaryKey(UserRoleKey id) {
        userRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UserRoleKey> selectByExample(UserRoleExample userRoleExample) {
        List<UserRoleKey> userRoleKeys = userRoleMapper.selectByExample(userRoleExample);
        return userRoleKeys;
    }
}
