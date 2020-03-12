package com.douyu.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PrizeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PrizeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLotteryIdIsNull() {
            addCriterion("LOTTERY_ID is null");
            return (Criteria) this;
        }

        public Criteria andLotteryIdIsNotNull() {
            addCriterion("LOTTERY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLotteryIdEqualTo(String value) {
            addCriterion("LOTTERY_ID =", value, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdNotEqualTo(String value) {
            addCriterion("LOTTERY_ID <>", value, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdGreaterThan(String value) {
            addCriterion("LOTTERY_ID >", value, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdGreaterThanOrEqualTo(String value) {
            addCriterion("LOTTERY_ID >=", value, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdLessThan(String value) {
            addCriterion("LOTTERY_ID <", value, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdLessThanOrEqualTo(String value) {
            addCriterion("LOTTERY_ID <=", value, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdLike(String value) {
            addCriterion("LOTTERY_ID like", value, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdNotLike(String value) {
            addCriterion("LOTTERY_ID not like", value, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdIn(List<String> values) {
            addCriterion("LOTTERY_ID in", values, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdNotIn(List<String> values) {
            addCriterion("LOTTERY_ID not in", values, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdBetween(String value1, String value2) {
            addCriterion("LOTTERY_ID between", value1, value2, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdNotBetween(String value1, String value2) {
            addCriterion("LOTTERY_ID not between", value1, value2, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeIsNull() {
            addCriterion("PRIZE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeIsNotNull() {
            addCriterion("PRIZE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeEqualTo(String value) {
            addCriterion("PRIZE_TYPE =", value, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeNotEqualTo(String value) {
            addCriterion("PRIZE_TYPE <>", value, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeGreaterThan(String value) {
            addCriterion("PRIZE_TYPE >", value, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PRIZE_TYPE >=", value, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeLessThan(String value) {
            addCriterion("PRIZE_TYPE <", value, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeLessThanOrEqualTo(String value) {
            addCriterion("PRIZE_TYPE <=", value, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeLike(String value) {
            addCriterion("PRIZE_TYPE like", value, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeNotLike(String value) {
            addCriterion("PRIZE_TYPE not like", value, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeIn(List<String> values) {
            addCriterion("PRIZE_TYPE in", values, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeNotIn(List<String> values) {
            addCriterion("PRIZE_TYPE not in", values, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeBetween(String value1, String value2) {
            addCriterion("PRIZE_TYPE between", value1, value2, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeNotBetween(String value1, String value2) {
            addCriterion("PRIZE_TYPE not between", value1, value2, "prizeType");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNull() {
            addCriterion("CLASS_ID is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("CLASS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(Integer value) {
            addCriterion("CLASS_ID =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(Integer value) {
            addCriterion("CLASS_ID <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(Integer value) {
            addCriterion("CLASS_ID >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CLASS_ID >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(Integer value) {
            addCriterion("CLASS_ID <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(Integer value) {
            addCriterion("CLASS_ID <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<Integer> values) {
            addCriterion("CLASS_ID in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<Integer> values) {
            addCriterion("CLASS_ID not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(Integer value1, Integer value2) {
            addCriterion("CLASS_ID between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CLASS_ID not between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("PRODUCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("PRODUCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("PRODUCT_ID =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("PRODUCT_ID <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("PRODUCT_ID >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_ID >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("PRODUCT_ID <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_ID <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("PRODUCT_ID like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("PRODUCT_ID not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("PRODUCT_ID in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("PRODUCT_ID not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("PRODUCT_ID between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_ID not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductAttributesIdIsNull() {
            addCriterion("PRODUCT_ATTRIBUTES_ID is null");
            return (Criteria) this;
        }

        public Criteria andProductAttributesIdIsNotNull() {
            addCriterion("PRODUCT_ATTRIBUTES_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProductAttributesIdEqualTo(String value) {
            addCriterion("PRODUCT_ATTRIBUTES_ID =", value, "productAttributesId");
            return (Criteria) this;
        }

        public Criteria andProductAttributesIdNotEqualTo(String value) {
            addCriterion("PRODUCT_ATTRIBUTES_ID <>", value, "productAttributesId");
            return (Criteria) this;
        }

        public Criteria andProductAttributesIdGreaterThan(String value) {
            addCriterion("PRODUCT_ATTRIBUTES_ID >", value, "productAttributesId");
            return (Criteria) this;
        }

        public Criteria andProductAttributesIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_ATTRIBUTES_ID >=", value, "productAttributesId");
            return (Criteria) this;
        }

        public Criteria andProductAttributesIdLessThan(String value) {
            addCriterion("PRODUCT_ATTRIBUTES_ID <", value, "productAttributesId");
            return (Criteria) this;
        }

        public Criteria andProductAttributesIdLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_ATTRIBUTES_ID <=", value, "productAttributesId");
            return (Criteria) this;
        }

        public Criteria andProductAttributesIdLike(String value) {
            addCriterion("PRODUCT_ATTRIBUTES_ID like", value, "productAttributesId");
            return (Criteria) this;
        }

        public Criteria andProductAttributesIdNotLike(String value) {
            addCriterion("PRODUCT_ATTRIBUTES_ID not like", value, "productAttributesId");
            return (Criteria) this;
        }

        public Criteria andProductAttributesIdIn(List<String> values) {
            addCriterion("PRODUCT_ATTRIBUTES_ID in", values, "productAttributesId");
            return (Criteria) this;
        }

        public Criteria andProductAttributesIdNotIn(List<String> values) {
            addCriterion("PRODUCT_ATTRIBUTES_ID not in", values, "productAttributesId");
            return (Criteria) this;
        }

        public Criteria andProductAttributesIdBetween(String value1, String value2) {
            addCriterion("PRODUCT_ATTRIBUTES_ID between", value1, value2, "productAttributesId");
            return (Criteria) this;
        }

        public Criteria andProductAttributesIdNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_ATTRIBUTES_ID not between", value1, value2, "productAttributesId");
            return (Criteria) this;
        }

        public Criteria andPrizeNameIsNull() {
            addCriterion("PRIZE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPrizeNameIsNotNull() {
            addCriterion("PRIZE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeNameEqualTo(String value) {
            addCriterion("PRIZE_NAME =", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotEqualTo(String value) {
            addCriterion("PRIZE_NAME <>", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameGreaterThan(String value) {
            addCriterion("PRIZE_NAME >", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRIZE_NAME >=", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameLessThan(String value) {
            addCriterion("PRIZE_NAME <", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameLessThanOrEqualTo(String value) {
            addCriterion("PRIZE_NAME <=", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameLike(String value) {
            addCriterion("PRIZE_NAME like", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotLike(String value) {
            addCriterion("PRIZE_NAME not like", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameIn(List<String> values) {
            addCriterion("PRIZE_NAME in", values, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotIn(List<String> values) {
            addCriterion("PRIZE_NAME not in", values, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameBetween(String value1, String value2) {
            addCriterion("PRIZE_NAME between", value1, value2, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotBetween(String value1, String value2) {
            addCriterion("PRIZE_NAME not between", value1, value2, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizePriceIsNull() {
            addCriterion("PRIZE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andPrizePriceIsNotNull() {
            addCriterion("PRIZE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andPrizePriceEqualTo(BigDecimal value) {
            addCriterion("PRIZE_PRICE =", value, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceNotEqualTo(BigDecimal value) {
            addCriterion("PRIZE_PRICE <>", value, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceGreaterThan(BigDecimal value) {
            addCriterion("PRIZE_PRICE >", value, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PRIZE_PRICE >=", value, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceLessThan(BigDecimal value) {
            addCriterion("PRIZE_PRICE <", value, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PRIZE_PRICE <=", value, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceIn(List<BigDecimal> values) {
            addCriterion("PRIZE_PRICE in", values, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceNotIn(List<BigDecimal> values) {
            addCriterion("PRIZE_PRICE not in", values, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRIZE_PRICE between", value1, value2, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRIZE_PRICE not between", value1, value2, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPointIsNull() {
            addCriterion("POINT is null");
            return (Criteria) this;
        }

        public Criteria andPointIsNotNull() {
            addCriterion("POINT is not null");
            return (Criteria) this;
        }

        public Criteria andPointEqualTo(Integer value) {
            addCriterion("POINT =", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotEqualTo(Integer value) {
            addCriterion("POINT <>", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThan(Integer value) {
            addCriterion("POINT >", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("POINT >=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThan(Integer value) {
            addCriterion("POINT <", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThanOrEqualTo(Integer value) {
            addCriterion("POINT <=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointIn(List<Integer> values) {
            addCriterion("POINT in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotIn(List<Integer> values) {
            addCriterion("POINT not in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointBetween(Integer value1, Integer value2) {
            addCriterion("POINT between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotBetween(Integer value1, Integer value2) {
            addCriterion("POINT not between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andPrizeNumIsNull() {
            addCriterion("PRIZE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andPrizeNumIsNotNull() {
            addCriterion("PRIZE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeNumEqualTo(Integer value) {
            addCriterion("PRIZE_NUM =", value, "prizeNum");
            return (Criteria) this;
        }

        public Criteria andPrizeNumNotEqualTo(Integer value) {
            addCriterion("PRIZE_NUM <>", value, "prizeNum");
            return (Criteria) this;
        }

        public Criteria andPrizeNumGreaterThan(Integer value) {
            addCriterion("PRIZE_NUM >", value, "prizeNum");
            return (Criteria) this;
        }

        public Criteria andPrizeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("PRIZE_NUM >=", value, "prizeNum");
            return (Criteria) this;
        }

        public Criteria andPrizeNumLessThan(Integer value) {
            addCriterion("PRIZE_NUM <", value, "prizeNum");
            return (Criteria) this;
        }

        public Criteria andPrizeNumLessThanOrEqualTo(Integer value) {
            addCriterion("PRIZE_NUM <=", value, "prizeNum");
            return (Criteria) this;
        }

        public Criteria andPrizeNumIn(List<Integer> values) {
            addCriterion("PRIZE_NUM in", values, "prizeNum");
            return (Criteria) this;
        }

        public Criteria andPrizeNumNotIn(List<Integer> values) {
            addCriterion("PRIZE_NUM not in", values, "prizeNum");
            return (Criteria) this;
        }

        public Criteria andPrizeNumBetween(Integer value1, Integer value2) {
            addCriterion("PRIZE_NUM between", value1, value2, "prizeNum");
            return (Criteria) this;
        }

        public Criteria andPrizeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("PRIZE_NUM not between", value1, value2, "prizeNum");
            return (Criteria) this;
        }

        public Criteria andPrizeTotailPointIsNull() {
            addCriterion("PRIZE_TOTAIL_POINT is null");
            return (Criteria) this;
        }

        public Criteria andPrizeTotailPointIsNotNull() {
            addCriterion("PRIZE_TOTAIL_POINT is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeTotailPointEqualTo(Integer value) {
            addCriterion("PRIZE_TOTAIL_POINT =", value, "prizeTotailPoint");
            return (Criteria) this;
        }

        public Criteria andPrizeTotailPointNotEqualTo(Integer value) {
            addCriterion("PRIZE_TOTAIL_POINT <>", value, "prizeTotailPoint");
            return (Criteria) this;
        }

        public Criteria andPrizeTotailPointGreaterThan(Integer value) {
            addCriterion("PRIZE_TOTAIL_POINT >", value, "prizeTotailPoint");
            return (Criteria) this;
        }

        public Criteria andPrizeTotailPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("PRIZE_TOTAIL_POINT >=", value, "prizeTotailPoint");
            return (Criteria) this;
        }

        public Criteria andPrizeTotailPointLessThan(Integer value) {
            addCriterion("PRIZE_TOTAIL_POINT <", value, "prizeTotailPoint");
            return (Criteria) this;
        }

        public Criteria andPrizeTotailPointLessThanOrEqualTo(Integer value) {
            addCriterion("PRIZE_TOTAIL_POINT <=", value, "prizeTotailPoint");
            return (Criteria) this;
        }

        public Criteria andPrizeTotailPointIn(List<Integer> values) {
            addCriterion("PRIZE_TOTAIL_POINT in", values, "prizeTotailPoint");
            return (Criteria) this;
        }

        public Criteria andPrizeTotailPointNotIn(List<Integer> values) {
            addCriterion("PRIZE_TOTAIL_POINT not in", values, "prizeTotailPoint");
            return (Criteria) this;
        }

        public Criteria andPrizeTotailPointBetween(Integer value1, Integer value2) {
            addCriterion("PRIZE_TOTAIL_POINT between", value1, value2, "prizeTotailPoint");
            return (Criteria) this;
        }

        public Criteria andPrizeTotailPointNotBetween(Integer value1, Integer value2) {
            addCriterion("PRIZE_TOTAIL_POINT not between", value1, value2, "prizeTotailPoint");
            return (Criteria) this;
        }

        public Criteria andPrizeLevIsNull() {
            addCriterion("PRIZE_LEV is null");
            return (Criteria) this;
        }

        public Criteria andPrizeLevIsNotNull() {
            addCriterion("PRIZE_LEV is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeLevEqualTo(Integer value) {
            addCriterion("PRIZE_LEV =", value, "prizeLev");
            return (Criteria) this;
        }

        public Criteria andPrizeLevNotEqualTo(Integer value) {
            addCriterion("PRIZE_LEV <>", value, "prizeLev");
            return (Criteria) this;
        }

        public Criteria andPrizeLevGreaterThan(Integer value) {
            addCriterion("PRIZE_LEV >", value, "prizeLev");
            return (Criteria) this;
        }

        public Criteria andPrizeLevGreaterThanOrEqualTo(Integer value) {
            addCriterion("PRIZE_LEV >=", value, "prizeLev");
            return (Criteria) this;
        }

        public Criteria andPrizeLevLessThan(Integer value) {
            addCriterion("PRIZE_LEV <", value, "prizeLev");
            return (Criteria) this;
        }

        public Criteria andPrizeLevLessThanOrEqualTo(Integer value) {
            addCriterion("PRIZE_LEV <=", value, "prizeLev");
            return (Criteria) this;
        }

        public Criteria andPrizeLevIn(List<Integer> values) {
            addCriterion("PRIZE_LEV in", values, "prizeLev");
            return (Criteria) this;
        }

        public Criteria andPrizeLevNotIn(List<Integer> values) {
            addCriterion("PRIZE_LEV not in", values, "prizeLev");
            return (Criteria) this;
        }

        public Criteria andPrizeLevBetween(Integer value1, Integer value2) {
            addCriterion("PRIZE_LEV between", value1, value2, "prizeLev");
            return (Criteria) this;
        }

        public Criteria andPrizeLevNotBetween(Integer value1, Integer value2) {
            addCriterion("PRIZE_LEV not between", value1, value2, "prizeLev");
            return (Criteria) this;
        }

        public Criteria andPrizeProbabilityIsNull() {
            addCriterion("PRIZE_PROBABILITY is null");
            return (Criteria) this;
        }

        public Criteria andPrizeProbabilityIsNotNull() {
            addCriterion("PRIZE_PROBABILITY is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeProbabilityEqualTo(BigDecimal value) {
            addCriterion("PRIZE_PROBABILITY =", value, "prizeProbability");
            return (Criteria) this;
        }

        public Criteria andPrizeProbabilityNotEqualTo(BigDecimal value) {
            addCriterion("PRIZE_PROBABILITY <>", value, "prizeProbability");
            return (Criteria) this;
        }

        public Criteria andPrizeProbabilityGreaterThan(BigDecimal value) {
            addCriterion("PRIZE_PROBABILITY >", value, "prizeProbability");
            return (Criteria) this;
        }

        public Criteria andPrizeProbabilityGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PRIZE_PROBABILITY >=", value, "prizeProbability");
            return (Criteria) this;
        }

        public Criteria andPrizeProbabilityLessThan(BigDecimal value) {
            addCriterion("PRIZE_PROBABILITY <", value, "prizeProbability");
            return (Criteria) this;
        }

        public Criteria andPrizeProbabilityLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PRIZE_PROBABILITY <=", value, "prizeProbability");
            return (Criteria) this;
        }

        public Criteria andPrizeProbabilityIn(List<BigDecimal> values) {
            addCriterion("PRIZE_PROBABILITY in", values, "prizeProbability");
            return (Criteria) this;
        }

        public Criteria andPrizeProbabilityNotIn(List<BigDecimal> values) {
            addCriterion("PRIZE_PROBABILITY not in", values, "prizeProbability");
            return (Criteria) this;
        }

        public Criteria andPrizeProbabilityBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRIZE_PROBABILITY between", value1, value2, "prizeProbability");
            return (Criteria) this;
        }

        public Criteria andPrizeProbabilityNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRIZE_PROBABILITY not between", value1, value2, "prizeProbability");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}