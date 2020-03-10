package com.douyu.pojo;

import lombok.Data;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/10 12:03
 */
@Data
public class Award {
    /**编号*/
    public String id;

    /**数量（该类奖品数量）*/
    public int count;

    /**价值（该类奖品价值积分）*/
    public int pointVal;

    /**剩余数量（该类奖品剩余数量）*/
    public int remainCount;

    /**
     *
     * @param id *编号*
     * @param count 数量（该类奖品数量）*
     * @param pointVal 价值（该类奖品价值积分）*
     * @param remainCount 剩余数量（该类奖品剩余数量）
     */
    public Award( String id, int count, int pointVal, int remainCount) {
        this.id = id;
        this.count = count;
        this.pointVal = pointVal;
        this.remainCount = remainCount;
    }
}
