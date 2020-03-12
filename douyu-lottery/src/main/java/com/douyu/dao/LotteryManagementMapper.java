package com.douyu.dao;

import com.douyu.pojo.LotteryManagement;
import com.douyu.pojo.LotteryManagementExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotteryManagementMapper {
    int countByExample(LotteryManagementExample example);

    int deleteByExample(LotteryManagementExample example);

    int deleteByPrimaryKey(String lotteryId);

    int insert(LotteryManagement record);

    int insertSelective(LotteryManagement record);

    List<LotteryManagement> selectByExample(LotteryManagementExample example);

    LotteryManagement selectByPrimaryKey(String lotteryId);

    int updateByExampleSelective(@Param("record") LotteryManagement record, @Param("example") LotteryManagementExample example);

    int updateByExample(@Param("record") LotteryManagement record, @Param("example") LotteryManagementExample example);

    int updateByPrimaryKeySelective(LotteryManagement record);

    int updateByPrimaryKey(LotteryManagement record);
}