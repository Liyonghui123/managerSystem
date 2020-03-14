package com.douyu.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author Dangerous
 */
@Data
public class LotteryManagement {
    private String lotteryId;

    private String lotteryName;

    private Integer sponsorPoint;

    private Integer lotteryType;

    private Integer lotteryPoint;

    private Date startDate;

    private Date stopDate;

    private Integer lotteryTotailNum;

    private Integer lotterySurplusNum;

    private Integer lotteryNum;

    private Integer lotteryOver;

    private Date createTime;
}