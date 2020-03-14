package com.douyu.dao;

import com.douyu.pojo.LotteryLevel;
import com.douyu.pojo.LotteryLevelExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LotteryLevelMapper {
    int countByExample(LotteryLevelExample example);

    int deleteByExample(LotteryLevelExample example);

    int deleteByPrimaryKey(Integer levelId);

    int insert(LotteryLevel record);

    int insertSelective(LotteryLevel record);

    List<LotteryLevel> selectByExample(LotteryLevelExample example);

    LotteryLevel selectByPrimaryKey(Integer levelId);

    int updateByExampleSelective(@Param("record") LotteryLevel record, @Param("example") LotteryLevelExample example);

    int updateByExample(@Param("record") LotteryLevel record, @Param("example") LotteryLevelExample example);

    int updateByPrimaryKeySelective(LotteryLevel record);

    int updateByPrimaryKey(LotteryLevel record);
}