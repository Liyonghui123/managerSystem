package com.douyu.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Prize {
    public Prize(Integer id,Integer val){
        this.id=id;
        this.val=val;
    }
    private Integer val;
    private Integer id;

    private String lotteryId;

    private String prizeType;

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



    @Override
    public int hashCode() {
        return val.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }

        if (getClass() != obj.getClass()){
            return false;
        }
        Prize other = (Prize) obj;
        if (val == null) {
            if (other.val != null){
                return false;
            }

        } else if (!val.equals(other.val)){
            return false;
        }
        return true;

    }
}

