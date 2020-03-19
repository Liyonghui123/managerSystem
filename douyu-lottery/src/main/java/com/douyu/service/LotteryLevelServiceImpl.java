package com.douyu.service;

import com.douyu.dao.LotteryLevelMapper;
import com.douyu.pojo.LotteryLevel;
import com.douyu.pojo.LotteryLevelExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/15 16:04
 */
@Service
public class LotteryLevelServiceImpl implements LotteryLevelService{
    @Autowired
    private LotteryLevelMapper lotteryLevelMapper;
    @Override
    public List<LotteryLevel> selectByExample(LotteryLevelExample lotteryLevelExample) {
        List<LotteryLevel> lotteryLevels = lotteryLevelMapper.selectByExample(lotteryLevelExample);
        return lotteryLevels;
    }
}
