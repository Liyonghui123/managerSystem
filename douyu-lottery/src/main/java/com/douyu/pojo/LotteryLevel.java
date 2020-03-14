package com.douyu.pojo;

import lombok.Data;

@Data
public class LotteryLevel {
    private Integer levelId;

    private String lotteryId;

    private String userLevel;

    private Integer limitTime;

}