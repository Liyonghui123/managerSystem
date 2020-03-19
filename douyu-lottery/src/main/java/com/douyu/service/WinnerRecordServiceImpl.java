package com.douyu.service;

import com.douyu.dao.WinnerRecordMapper;
import com.douyu.pojo.WinnerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/15 16:27
 */
@Service
public class WinnerRecordServiceImpl implements WinnerRecordService{
    @Autowired
    private WinnerRecordMapper winnerRecordMapper;
    @Override
    public void insert(WinnerRecord winnerRecord) {
        winnerRecordMapper.insert(winnerRecord);
    }
}
