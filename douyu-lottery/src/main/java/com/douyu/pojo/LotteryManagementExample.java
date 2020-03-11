package com.douyu.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class LotteryManagementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LotteryManagementExample() {
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

        public Criteria andLotteryNameIsNull() {
            addCriterion("LOTTERY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLotteryNameIsNotNull() {
            addCriterion("LOTTERY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLotteryNameEqualTo(String value) {
            addCriterion("LOTTERY_NAME =", value, "lotteryName");
            return (Criteria) this;
        }

        public Criteria andLotteryNameNotEqualTo(String value) {
            addCriterion("LOTTERY_NAME <>", value, "lotteryName");
            return (Criteria) this;
        }

        public Criteria andLotteryNameGreaterThan(String value) {
            addCriterion("LOTTERY_NAME >", value, "lotteryName");
            return (Criteria) this;
        }

        public Criteria andLotteryNameGreaterThanOrEqualTo(String value) {
            addCriterion("LOTTERY_NAME >=", value, "lotteryName");
            return (Criteria) this;
        }

        public Criteria andLotteryNameLessThan(String value) {
            addCriterion("LOTTERY_NAME <", value, "lotteryName");
            return (Criteria) this;
        }

        public Criteria andLotteryNameLessThanOrEqualTo(String value) {
            addCriterion("LOTTERY_NAME <=", value, "lotteryName");
            return (Criteria) this;
        }

        public Criteria andLotteryNameLike(String value) {
            addCriterion("LOTTERY_NAME like", value, "lotteryName");
            return (Criteria) this;
        }

        public Criteria andLotteryNameNotLike(String value) {
            addCriterion("LOTTERY_NAME not like", value, "lotteryName");
            return (Criteria) this;
        }

        public Criteria andLotteryNameIn(List<String> values) {
            addCriterion("LOTTERY_NAME in", values, "lotteryName");
            return (Criteria) this;
        }

        public Criteria andLotteryNameNotIn(List<String> values) {
            addCriterion("LOTTERY_NAME not in", values, "lotteryName");
            return (Criteria) this;
        }

        public Criteria andLotteryNameBetween(String value1, String value2) {
            addCriterion("LOTTERY_NAME between", value1, value2, "lotteryName");
            return (Criteria) this;
        }

        public Criteria andLotteryNameNotBetween(String value1, String value2) {
            addCriterion("LOTTERY_NAME not between", value1, value2, "lotteryName");
            return (Criteria) this;
        }

        public Criteria andSponsorPointIsNull() {
            addCriterion("SPONSOR_POINT is null");
            return (Criteria) this;
        }

        public Criteria andSponsorPointIsNotNull() {
            addCriterion("SPONSOR_POINT is not null");
            return (Criteria) this;
        }

        public Criteria andSponsorPointEqualTo(Integer value) {
            addCriterion("SPONSOR_POINT =", value, "sponsorPoint");
            return (Criteria) this;
        }

        public Criteria andSponsorPointNotEqualTo(Integer value) {
            addCriterion("SPONSOR_POINT <>", value, "sponsorPoint");
            return (Criteria) this;
        }

        public Criteria andSponsorPointGreaterThan(Integer value) {
            addCriterion("SPONSOR_POINT >", value, "sponsorPoint");
            return (Criteria) this;
        }

        public Criteria andSponsorPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("SPONSOR_POINT >=", value, "sponsorPoint");
            return (Criteria) this;
        }

        public Criteria andSponsorPointLessThan(Integer value) {
            addCriterion("SPONSOR_POINT <", value, "sponsorPoint");
            return (Criteria) this;
        }

        public Criteria andSponsorPointLessThanOrEqualTo(Integer value) {
            addCriterion("SPONSOR_POINT <=", value, "sponsorPoint");
            return (Criteria) this;
        }

        public Criteria andSponsorPointIn(List<Integer> values) {
            addCriterion("SPONSOR_POINT in", values, "sponsorPoint");
            return (Criteria) this;
        }

        public Criteria andSponsorPointNotIn(List<Integer> values) {
            addCriterion("SPONSOR_POINT not in", values, "sponsorPoint");
            return (Criteria) this;
        }

        public Criteria andSponsorPointBetween(Integer value1, Integer value2) {
            addCriterion("SPONSOR_POINT between", value1, value2, "sponsorPoint");
            return (Criteria) this;
        }

        public Criteria andSponsorPointNotBetween(Integer value1, Integer value2) {
            addCriterion("SPONSOR_POINT not between", value1, value2, "sponsorPoint");
            return (Criteria) this;
        }

        public Criteria andLotteryTypeIsNull() {
            addCriterion("LOTTERY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andLotteryTypeIsNotNull() {
            addCriterion("LOTTERY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andLotteryTypeEqualTo(Integer value) {
            addCriterion("LOTTERY_TYPE =", value, "lotteryType");
            return (Criteria) this;
        }

        public Criteria andLotteryTypeNotEqualTo(Integer value) {
            addCriterion("LOTTERY_TYPE <>", value, "lotteryType");
            return (Criteria) this;
        }

        public Criteria andLotteryTypeGreaterThan(Integer value) {
            addCriterion("LOTTERY_TYPE >", value, "lotteryType");
            return (Criteria) this;
        }

        public Criteria andLotteryTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("LOTTERY_TYPE >=", value, "lotteryType");
            return (Criteria) this;
        }

        public Criteria andLotteryTypeLessThan(Integer value) {
            addCriterion("LOTTERY_TYPE <", value, "lotteryType");
            return (Criteria) this;
        }

        public Criteria andLotteryTypeLessThanOrEqualTo(Integer value) {
            addCriterion("LOTTERY_TYPE <=", value, "lotteryType");
            return (Criteria) this;
        }

        public Criteria andLotteryTypeIn(List<Integer> values) {
            addCriterion("LOTTERY_TYPE in", values, "lotteryType");
            return (Criteria) this;
        }

        public Criteria andLotteryTypeNotIn(List<Integer> values) {
            addCriterion("LOTTERY_TYPE not in", values, "lotteryType");
            return (Criteria) this;
        }

        public Criteria andLotteryTypeBetween(Integer value1, Integer value2) {
            addCriterion("LOTTERY_TYPE between", value1, value2, "lotteryType");
            return (Criteria) this;
        }

        public Criteria andLotteryTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("LOTTERY_TYPE not between", value1, value2, "lotteryType");
            return (Criteria) this;
        }

        public Criteria andLotteryPointIsNull() {
            addCriterion("LOTTERY_POINT is null");
            return (Criteria) this;
        }

        public Criteria andLotteryPointIsNotNull() {
            addCriterion("LOTTERY_POINT is not null");
            return (Criteria) this;
        }

        public Criteria andLotteryPointEqualTo(Integer value) {
            addCriterion("LOTTERY_POINT =", value, "lotteryPoint");
            return (Criteria) this;
        }

        public Criteria andLotteryPointNotEqualTo(Integer value) {
            addCriterion("LOTTERY_POINT <>", value, "lotteryPoint");
            return (Criteria) this;
        }

        public Criteria andLotteryPointGreaterThan(Integer value) {
            addCriterion("LOTTERY_POINT >", value, "lotteryPoint");
            return (Criteria) this;
        }

        public Criteria andLotteryPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("LOTTERY_POINT >=", value, "lotteryPoint");
            return (Criteria) this;
        }

        public Criteria andLotteryPointLessThan(Integer value) {
            addCriterion("LOTTERY_POINT <", value, "lotteryPoint");
            return (Criteria) this;
        }

        public Criteria andLotteryPointLessThanOrEqualTo(Integer value) {
            addCriterion("LOTTERY_POINT <=", value, "lotteryPoint");
            return (Criteria) this;
        }

        public Criteria andLotteryPointIn(List<Integer> values) {
            addCriterion("LOTTERY_POINT in", values, "lotteryPoint");
            return (Criteria) this;
        }

        public Criteria andLotteryPointNotIn(List<Integer> values) {
            addCriterion("LOTTERY_POINT not in", values, "lotteryPoint");
            return (Criteria) this;
        }

        public Criteria andLotteryPointBetween(Integer value1, Integer value2) {
            addCriterion("LOTTERY_POINT between", value1, value2, "lotteryPoint");
            return (Criteria) this;
        }

        public Criteria andLotteryPointNotBetween(Integer value1, Integer value2) {
            addCriterion("LOTTERY_POINT not between", value1, value2, "lotteryPoint");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("START_DATE =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("START_DATE <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("START_DATE >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("START_DATE >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterionForJDBCDate("START_DATE <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("START_DATE <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("START_DATE in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("START_DATE not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("START_DATE between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("START_DATE not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStopDateIsNull() {
            addCriterion("STOP_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStopDateIsNotNull() {
            addCriterion("STOP_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStopDateEqualTo(Date value) {
            addCriterionForJDBCDate("STOP_DATE =", value, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("STOP_DATE <>", value, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateGreaterThan(Date value) {
            addCriterionForJDBCDate("STOP_DATE >", value, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("STOP_DATE >=", value, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateLessThan(Date value) {
            addCriterionForJDBCDate("STOP_DATE <", value, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("STOP_DATE <=", value, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateIn(List<Date> values) {
            addCriterionForJDBCDate("STOP_DATE in", values, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("STOP_DATE not in", values, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("STOP_DATE between", value1, value2, "stopDate");
            return (Criteria) this;
        }

        public Criteria andStopDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("STOP_DATE not between", value1, value2, "stopDate");
            return (Criteria) this;
        }

        public Criteria andLotteryTotailNumIsNull() {
            addCriterion("LOTTERY_TOTAIL_NUM is null");
            return (Criteria) this;
        }

        public Criteria andLotteryTotailNumIsNotNull() {
            addCriterion("LOTTERY_TOTAIL_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andLotteryTotailNumEqualTo(Integer value) {
            addCriterion("LOTTERY_TOTAIL_NUM =", value, "lotteryTotailNum");
            return (Criteria) this;
        }

        public Criteria andLotteryTotailNumNotEqualTo(Integer value) {
            addCriterion("LOTTERY_TOTAIL_NUM <>", value, "lotteryTotailNum");
            return (Criteria) this;
        }

        public Criteria andLotteryTotailNumGreaterThan(Integer value) {
            addCriterion("LOTTERY_TOTAIL_NUM >", value, "lotteryTotailNum");
            return (Criteria) this;
        }

        public Criteria andLotteryTotailNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("LOTTERY_TOTAIL_NUM >=", value, "lotteryTotailNum");
            return (Criteria) this;
        }

        public Criteria andLotteryTotailNumLessThan(Integer value) {
            addCriterion("LOTTERY_TOTAIL_NUM <", value, "lotteryTotailNum");
            return (Criteria) this;
        }

        public Criteria andLotteryTotailNumLessThanOrEqualTo(Integer value) {
            addCriterion("LOTTERY_TOTAIL_NUM <=", value, "lotteryTotailNum");
            return (Criteria) this;
        }

        public Criteria andLotteryTotailNumIn(List<Integer> values) {
            addCriterion("LOTTERY_TOTAIL_NUM in", values, "lotteryTotailNum");
            return (Criteria) this;
        }

        public Criteria andLotteryTotailNumNotIn(List<Integer> values) {
            addCriterion("LOTTERY_TOTAIL_NUM not in", values, "lotteryTotailNum");
            return (Criteria) this;
        }

        public Criteria andLotteryTotailNumBetween(Integer value1, Integer value2) {
            addCriterion("LOTTERY_TOTAIL_NUM between", value1, value2, "lotteryTotailNum");
            return (Criteria) this;
        }

        public Criteria andLotteryTotailNumNotBetween(Integer value1, Integer value2) {
            addCriterion("LOTTERY_TOTAIL_NUM not between", value1, value2, "lotteryTotailNum");
            return (Criteria) this;
        }

        public Criteria andLotterySurplusNumIsNull() {
            addCriterion("LOTTERY_SURPLUS_NUM is null");
            return (Criteria) this;
        }

        public Criteria andLotterySurplusNumIsNotNull() {
            addCriterion("LOTTERY_SURPLUS_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andLotterySurplusNumEqualTo(Integer value) {
            addCriterion("LOTTERY_SURPLUS_NUM =", value, "lotterySurplusNum");
            return (Criteria) this;
        }

        public Criteria andLotterySurplusNumNotEqualTo(Integer value) {
            addCriterion("LOTTERY_SURPLUS_NUM <>", value, "lotterySurplusNum");
            return (Criteria) this;
        }

        public Criteria andLotterySurplusNumGreaterThan(Integer value) {
            addCriterion("LOTTERY_SURPLUS_NUM >", value, "lotterySurplusNum");
            return (Criteria) this;
        }

        public Criteria andLotterySurplusNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("LOTTERY_SURPLUS_NUM >=", value, "lotterySurplusNum");
            return (Criteria) this;
        }

        public Criteria andLotterySurplusNumLessThan(Integer value) {
            addCriterion("LOTTERY_SURPLUS_NUM <", value, "lotterySurplusNum");
            return (Criteria) this;
        }

        public Criteria andLotterySurplusNumLessThanOrEqualTo(Integer value) {
            addCriterion("LOTTERY_SURPLUS_NUM <=", value, "lotterySurplusNum");
            return (Criteria) this;
        }

        public Criteria andLotterySurplusNumIn(List<Integer> values) {
            addCriterion("LOTTERY_SURPLUS_NUM in", values, "lotterySurplusNum");
            return (Criteria) this;
        }

        public Criteria andLotterySurplusNumNotIn(List<Integer> values) {
            addCriterion("LOTTERY_SURPLUS_NUM not in", values, "lotterySurplusNum");
            return (Criteria) this;
        }

        public Criteria andLotterySurplusNumBetween(Integer value1, Integer value2) {
            addCriterion("LOTTERY_SURPLUS_NUM between", value1, value2, "lotterySurplusNum");
            return (Criteria) this;
        }

        public Criteria andLotterySurplusNumNotBetween(Integer value1, Integer value2) {
            addCriterion("LOTTERY_SURPLUS_NUM not between", value1, value2, "lotterySurplusNum");
            return (Criteria) this;
        }

        public Criteria andLotteryNumIsNull() {
            addCriterion("LOTTERY_NUM is null");
            return (Criteria) this;
        }

        public Criteria andLotteryNumIsNotNull() {
            addCriterion("LOTTERY_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andLotteryNumEqualTo(Integer value) {
            addCriterion("LOTTERY_NUM =", value, "lotteryNum");
            return (Criteria) this;
        }

        public Criteria andLotteryNumNotEqualTo(Integer value) {
            addCriterion("LOTTERY_NUM <>", value, "lotteryNum");
            return (Criteria) this;
        }

        public Criteria andLotteryNumGreaterThan(Integer value) {
            addCriterion("LOTTERY_NUM >", value, "lotteryNum");
            return (Criteria) this;
        }

        public Criteria andLotteryNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("LOTTERY_NUM >=", value, "lotteryNum");
            return (Criteria) this;
        }

        public Criteria andLotteryNumLessThan(Integer value) {
            addCriterion("LOTTERY_NUM <", value, "lotteryNum");
            return (Criteria) this;
        }

        public Criteria andLotteryNumLessThanOrEqualTo(Integer value) {
            addCriterion("LOTTERY_NUM <=", value, "lotteryNum");
            return (Criteria) this;
        }

        public Criteria andLotteryNumIn(List<Integer> values) {
            addCriterion("LOTTERY_NUM in", values, "lotteryNum");
            return (Criteria) this;
        }

        public Criteria andLotteryNumNotIn(List<Integer> values) {
            addCriterion("LOTTERY_NUM not in", values, "lotteryNum");
            return (Criteria) this;
        }

        public Criteria andLotteryNumBetween(Integer value1, Integer value2) {
            addCriterion("LOTTERY_NUM between", value1, value2, "lotteryNum");
            return (Criteria) this;
        }

        public Criteria andLotteryNumNotBetween(Integer value1, Integer value2) {
            addCriterion("LOTTERY_NUM not between", value1, value2, "lotteryNum");
            return (Criteria) this;
        }

        public Criteria andLotteryOverIsNull() {
            addCriterion("LOTTERY_OVER is null");
            return (Criteria) this;
        }

        public Criteria andLotteryOverIsNotNull() {
            addCriterion("LOTTERY_OVER is not null");
            return (Criteria) this;
        }

        public Criteria andLotteryOverEqualTo(Integer value) {
            addCriterion("LOTTERY_OVER =", value, "lotteryOver");
            return (Criteria) this;
        }

        public Criteria andLotteryOverNotEqualTo(Integer value) {
            addCriterion("LOTTERY_OVER <>", value, "lotteryOver");
            return (Criteria) this;
        }

        public Criteria andLotteryOverGreaterThan(Integer value) {
            addCriterion("LOTTERY_OVER >", value, "lotteryOver");
            return (Criteria) this;
        }

        public Criteria andLotteryOverGreaterThanOrEqualTo(Integer value) {
            addCriterion("LOTTERY_OVER >=", value, "lotteryOver");
            return (Criteria) this;
        }

        public Criteria andLotteryOverLessThan(Integer value) {
            addCriterion("LOTTERY_OVER <", value, "lotteryOver");
            return (Criteria) this;
        }

        public Criteria andLotteryOverLessThanOrEqualTo(Integer value) {
            addCriterion("LOTTERY_OVER <=", value, "lotteryOver");
            return (Criteria) this;
        }

        public Criteria andLotteryOverIn(List<Integer> values) {
            addCriterion("LOTTERY_OVER in", values, "lotteryOver");
            return (Criteria) this;
        }

        public Criteria andLotteryOverNotIn(List<Integer> values) {
            addCriterion("LOTTERY_OVER not in", values, "lotteryOver");
            return (Criteria) this;
        }

        public Criteria andLotteryOverBetween(Integer value1, Integer value2) {
            addCriterion("LOTTERY_OVER between", value1, value2, "lotteryOver");
            return (Criteria) this;
        }

        public Criteria andLotteryOverNotBetween(Integer value1, Integer value2) {
            addCriterion("LOTTERY_OVER not between", value1, value2, "lotteryOver");
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