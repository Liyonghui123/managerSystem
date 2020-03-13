package com.douyu.dao;

import com.douyu.pojo.WinnerRecord;
import com.douyu.pojo.WinnerRecordExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WinnerRecordMapper {
    int countByExample(WinnerRecordExample example);

    int deleteByExample(WinnerRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WinnerRecord record);

    int insertSelective(WinnerRecord record);

    List<WinnerRecord> selectByExample(WinnerRecordExample example);

    WinnerRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WinnerRecord record, @Param("example") WinnerRecordExample example);

    int updateByExample(@Param("record") WinnerRecord record, @Param("example") WinnerRecordExample example);

    int updateByPrimaryKeySelective(WinnerRecord record);

    int updateByPrimaryKey(WinnerRecord record);
}