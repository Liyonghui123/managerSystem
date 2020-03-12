package com.douyu.dao;

import com.douyu.pojo.Prize;
import com.douyu.pojo.PrizeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrizeMapper {
    int countByExample(PrizeExample example);

    int deleteByExample(PrizeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Prize record);

    int insertSelective(Prize record);

    List<Prize> selectByExampleWithBLOBs(PrizeExample example);

    List<Prize> selectByExample(PrizeExample example);

    Prize selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Prize record, @Param("example") PrizeExample example);

    int updateByExampleWithBLOBs(@Param("record") Prize record, @Param("example") PrizeExample example);

    int updateByExample(@Param("record") Prize record, @Param("example") PrizeExample example);

    int updateByPrimaryKeySelective(Prize record);

    int updateByPrimaryKeyWithBLOBs(Prize record);

    int updateByPrimaryKey(Prize record);
}