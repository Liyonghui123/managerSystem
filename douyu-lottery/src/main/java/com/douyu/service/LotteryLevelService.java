package com.douyu.service;

import com.douyu.pojo.LotteryLevel;
import com.douyu.pojo.LotteryLevelExample;

import java.util.List;

public interface LotteryLevelService {
    List<LotteryLevel> selectByExample(LotteryLevelExample lotteryLevelExample);
}
