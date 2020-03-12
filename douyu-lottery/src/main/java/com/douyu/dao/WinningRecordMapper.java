package com.douyu.dao;

import com.douyu.pojo.WinningRecord;
import com.douyu.pojo.WinningRecordExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WinningRecordMapper {
    int countByExample(WinningRecordExample example);

    int deleteByExample(WinningRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WinningRecord record);

    int insertSelective(WinningRecord record);

    List<WinningRecord> selectByExample(WinningRecordExample example);

    WinningRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WinningRecord record, @Param("example") WinningRecordExample example);

    int updateByExample(@Param("record") WinningRecord record, @Param("example") WinningRecordExample example);

    int updateByPrimaryKeySelective(WinningRecord record);

    int updateByPrimaryKey(WinningRecord record);
}