package com.douyu.service;

import com.douyu.pojo.UserRoleExample;
import com.douyu.pojo.UserRoleKey;

import java.util.List;

public interface UserRoleService {
    void insert(UserRoleKey userRole);

    void deleteByPrimaryKey(UserRoleKey id);

    List<UserRoleKey> selectByExample(UserRoleExample userRoleExample);
}
