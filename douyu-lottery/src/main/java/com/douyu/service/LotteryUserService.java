package com.douyu.service;

import com.douyu.pojo.LotteryUser;

public interface LotteryUserService {
    LotteryUser selectByPrimaryKey(String enginerrId);
}
