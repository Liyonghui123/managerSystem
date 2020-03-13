package com.douyu.dao;

import com.douyu.pojo.LotteryUser;
import com.douyu.pojo.LotteryUserExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LotteryUserMapper {
    int countByExample(LotteryUserExample example);

    int deleteByExample(LotteryUserExample example);

    int deleteByPrimaryKey(String userCode);

    int insert(LotteryUser record);

    int insertSelective(LotteryUser record);

    List<LotteryUser> selectByExample(LotteryUserExample example);

    LotteryUser selectByPrimaryKey(String userCode);

    int updateByExampleSelective(@Param("record") LotteryUser record, @Param("example") LotteryUserExample example);

    int updateByExample(@Param("record") LotteryUser record, @Param("example") LotteryUserExample example);

    int updateByPrimaryKeySelective(LotteryUser record);

    int updateByPrimaryKey(LotteryUser record);
}