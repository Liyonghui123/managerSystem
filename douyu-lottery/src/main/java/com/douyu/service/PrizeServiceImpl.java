package com.douyu.service;

import com.douyu.dao.PrizeMapper;
import com.douyu.pojo.Prize;
import com.douyu.pojo.PrizeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/15 16:06
 */
@Service
public class PrizeServiceImpl implements PrizeService{
    @Autowired
    private PrizeMapper prizeMapper;

    @Override
    public List<Prize> selectByExample(PrizeExample example) {
        List<Prize> prizes = prizeMapper.selectByExample(example);
        return prizes;
    }

    @Override
    public Prize selectByPrimaryKey(int intValue) {
        Prize prize = prizeMapper.selectByPrimaryKey(intValue);
        return prize;
    }
}
