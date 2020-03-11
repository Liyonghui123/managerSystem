package com.douyu.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class WinningRecord {
    private Integer id;

    private String prizeId;

    private String prizeName;

    private String userId;

    private String userName;

    private Integer prizeNum;

    private Date createTime;

    private String field1;

    private String field2;

    private String field3;

}