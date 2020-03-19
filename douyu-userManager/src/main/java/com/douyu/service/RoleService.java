package com.douyu.service;

import com.douyu.pojo.Role;

public interface RoleService {
    void updateByPrimaryKey(Role role);

    void deleteByPrimaryKey(Long id);

    void insert(Role role);

    Role selectByPrimaryKey(Long id);
}
