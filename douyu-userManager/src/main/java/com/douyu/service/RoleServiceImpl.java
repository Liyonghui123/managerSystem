package com.douyu.service;

import com.douyu.dao.RoleMapper;
import com.douyu.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/15 15:42
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void updateByPrimaryKey(Role role) {

    }

    @Override
    public void deleteByPrimaryKey(Long id) {

    }

    @Override
    public void insert(Role role) {

    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return null;
    }
}
