package com.douyu.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Prize {
    public Prize(Integer id,Integer prizeNum) {
        this.id=id;
        this.prizeNum=prizeNum;
    }
    private Integer id;

    private String lotteryId;

    private Integer prizeType;

    private Integer classId;

    private String productId;

    private String productAttributesId;

    private String prizeName;

    private BigDecimal prizePrice;

    private Integer point;

    private Integer prizeNum;

    private Integer prizeTotailPoint;

    private Integer prizeLev;

    private BigDecimal prizeProbability;

    private Date createTime;

    private String remarks;


}