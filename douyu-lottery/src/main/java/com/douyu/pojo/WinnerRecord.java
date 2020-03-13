package com.douyu.pojo;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class WinnerRecord {
    public WinnerRecord(String prizeId,String redeemCode,String prizeName,String userId,String userName,Integer prizeNum,Timestamp createTime){
        this.prizeId=prizeId;
        this.redeemCode=redeemCode;
        this.prizeName=prizeName;
        this.userId=userId;
        this.userName=userName;
        this.prizeName=prizeName;
        this.prizeNum=prizeNum;
        this.createTime=createTime;
    }
    private Integer id;

    private String prizeId;

    private String redeemCode;

    private String prizeName;

    private String userId;

    private String userName;

    private Integer prizeNum;

    private Timestamp createTime;

    private String field1;

    private String field2;

    private String field3;

}