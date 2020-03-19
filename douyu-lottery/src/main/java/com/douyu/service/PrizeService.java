package com.douyu.service;

import com.douyu.pojo.Prize;
import com.douyu.pojo.PrizeExample;

import java.util.List;

public interface PrizeService {
    List<Prize> selectByExample(PrizeExample example);

    Prize selectByPrimaryKey(int intValue);
}
