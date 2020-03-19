package com.douyu.service;

import com.douyu.dao.LotteryUserMapper;
import com.douyu.pojo.LotteryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/15 16:23
 */
@Service
public class LotteryUserServiceImpl implements LotteryUserService{
    @Autowired
    private LotteryUserMapper lotteryUserMapper;

    @Override
    public LotteryUser selectByPrimaryKey(String enginerrId) {
        LotteryUser lotteryUser = lotteryUserMapper.selectByPrimaryKey(enginerrId);
        return lotteryUser;
    }
}
