package com.douyu.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/19 16:39
 */
@Data
public class OrderPaidEvent {
    private String orderId;

    private BigDecimal paidMoney;
    public OrderPaidEvent() {

    }

    public OrderPaidEvent(String orderId, BigDecimal paidMoney) {
        this.orderId = orderId;
        this.paidMoney = paidMoney;
    }


}
