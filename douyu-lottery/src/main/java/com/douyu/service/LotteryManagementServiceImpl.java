package com.douyu.service;

import com.douyu.dao.LotteryManagementMapper;
import com.douyu.pojo.LotteryManagement;
import com.douyu.pojo.LotteryManagementExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/15 15:57
 */
@Service
public class LotteryManagementServiceImpl implements LotteryManagementService{
    @Autowired
    private LotteryManagementMapper lotteryManagementMapper;
    @Override
    public List<LotteryManagement> selectByExample(LotteryManagementExample lotteryManagementExample) {
        List<LotteryManagement> lotteryManagements = lotteryManagementMapper.selectByExample(lotteryManagementExample);
        return lotteryManagements;
    }

    @Override
    public LotteryManagement selectByPrimaryKey(String lotteryId) {
        LotteryManagement lotteryManagement = lotteryManagementMapper.selectByPrimaryKey(lotteryId);
        return lotteryManagement;
    }

    @Override
    public void updateByPrimaryKey(LotteryManagement lotteryManagement) {
        lotteryManagementMapper.updateByPrimaryKey(lotteryManagement);
    }
}
