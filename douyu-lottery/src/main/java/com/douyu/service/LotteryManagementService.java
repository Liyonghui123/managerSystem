package com.douyu.service;

import com.douyu.pojo.LotteryManagement;
import com.douyu.pojo.LotteryManagementExample;

import java.util.List;
public interface LotteryManagementService {
    List<LotteryManagement> selectByExample(LotteryManagementExample lotteryManagementExample);

    LotteryManagement selectByPrimaryKey(String lotteryId);

    void updateByPrimaryKey(LotteryManagement lotteryManagement);
}
