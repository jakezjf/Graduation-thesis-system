package com.jf.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JFGroupInfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    public JFGroupInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
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

        public Criteria andGroIdIsNull() {
            addCriterion("gro_id is null");
            return (Criteria) this;
        }

        public Criteria andGroIdIsNotNull() {
            addCriterion("gro_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroIdEqualTo(String value) {
            addCriterion("gro_id =", value, "groId");
            return (Criteria) this;
        }

        public Criteria andGroIdNotEqualTo(String value) {
            addCriterion("gro_id <>", value, "groId");
            return (Criteria) this;
        }

        public Criteria andGroIdGreaterThan(String value) {
            addCriterion("gro_id >", value, "groId");
            return (Criteria) this;
        }

        public Criteria andGroIdGreaterThanOrEqualTo(String value) {
            addCriterion("gro_id >=", value, "groId");
            return (Criteria) this;
        }

        public Criteria andGroIdLessThan(String value) {
            addCriterion("gro_id <", value, "groId");
            return (Criteria) this;
        }

        public Criteria andGroIdLessThanOrEqualTo(String value) {
            addCriterion("gro_id <=", value, "groId");
            return (Criteria) this;
        }

        public Criteria andGroIdLike(String value) {
            addCriterion("gro_id like", value, "groId");
            return (Criteria) this;
        }

        public Criteria andGroIdNotLike(String value) {
            addCriterion("gro_id not like", value, "groId");
            return (Criteria) this;
        }

        public Criteria andGroIdIn(List<String> values) {
            addCriterion("gro_id in", values, "groId");
            return (Criteria) this;
        }

        public Criteria andGroIdNotIn(List<String> values) {
            addCriterion("gro_id not in", values, "groId");
            return (Criteria) this;
        }

        public Criteria andGroIdBetween(String value1, String value2) {
            addCriterion("gro_id between", value1, value2, "groId");
            return (Criteria) this;
        }

        public Criteria andGroIdNotBetween(String value1, String value2) {
            addCriterion("gro_id not between", value1, value2, "groId");
            return (Criteria) this;
        }

        public Criteria andGroAddressIsNull() {
            addCriterion("gro_address is null");
            return (Criteria) this;
        }

        public Criteria andGroAddressIsNotNull() {
            addCriterion("gro_address is not null");
            return (Criteria) this;
        }

        public Criteria andGroAddressEqualTo(String value) {
            addCriterion("gro_address =", value, "groAddress");
            return (Criteria) this;
        }

        public Criteria andGroAddressNotEqualTo(String value) {
            addCriterion("gro_address <>", value, "groAddress");
            return (Criteria) this;
        }

        public Criteria andGroAddressGreaterThan(String value) {
            addCriterion("gro_address >", value, "groAddress");
            return (Criteria) this;
        }

        public Criteria andGroAddressGreaterThanOrEqualTo(String value) {
            addCriterion("gro_address >=", value, "groAddress");
            return (Criteria) this;
        }

        public Criteria andGroAddressLessThan(String value) {
            addCriterion("gro_address <", value, "groAddress");
            return (Criteria) this;
        }

        public Criteria andGroAddressLessThanOrEqualTo(String value) {
            addCriterion("gro_address <=", value, "groAddress");
            return (Criteria) this;
        }

        public Criteria andGroAddressLike(String value) {
            addCriterion("gro_address like", value, "groAddress");
            return (Criteria) this;
        }

        public Criteria andGroAddressNotLike(String value) {
            addCriterion("gro_address not like", value, "groAddress");
            return (Criteria) this;
        }

        public Criteria andGroAddressIn(List<String> values) {
            addCriterion("gro_address in", values, "groAddress");
            return (Criteria) this;
        }

        public Criteria andGroAddressNotIn(List<String> values) {
            addCriterion("gro_address not in", values, "groAddress");
            return (Criteria) this;
        }

        public Criteria andGroAddressBetween(String value1, String value2) {
            addCriterion("gro_address between", value1, value2, "groAddress");
            return (Criteria) this;
        }

        public Criteria andGroAddressNotBetween(String value1, String value2) {
            addCriterion("gro_address not between", value1, value2, "groAddress");
            return (Criteria) this;
        }

        public Criteria andGroTimeIsNull() {
            addCriterion("gro_time is null");
            return (Criteria) this;
        }

        public Criteria andGroTimeIsNotNull() {
            addCriterion("gro_time is not null");
            return (Criteria) this;
        }

        public Criteria andGroTimeEqualTo(Date value) {
            addCriterion("gro_time =", value, "groTime");
            return (Criteria) this;
        }

        public Criteria andGroTimeNotEqualTo(Date value) {
            addCriterion("gro_time <>", value, "groTime");
            return (Criteria) this;
        }

        public Criteria andGroTimeGreaterThan(Date value) {
            addCriterion("gro_time >", value, "groTime");
            return (Criteria) this;
        }

        public Criteria andGroTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("gro_time >=", value, "groTime");
            return (Criteria) this;
        }

        public Criteria andGroTimeLessThan(Date value) {
            addCriterion("gro_time <", value, "groTime");
            return (Criteria) this;
        }

        public Criteria andGroTimeLessThanOrEqualTo(Date value) {
            addCriterion("gro_time <=", value, "groTime");
            return (Criteria) this;
        }

        public Criteria andGroTimeIn(List<Date> values) {
            addCriterion("gro_time in", values, "groTime");
            return (Criteria) this;
        }

        public Criteria andGroTimeNotIn(List<Date> values) {
            addCriterion("gro_time not in", values, "groTime");
            return (Criteria) this;
        }

        public Criteria andGroTimeBetween(Date value1, Date value2) {
            addCriterion("gro_time between", value1, value2, "groTime");
            return (Criteria) this;
        }

        public Criteria andGroTimeNotBetween(Date value1, Date value2) {
            addCriterion("gro_time not between", value1, value2, "groTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table groupinfo
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

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

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value) {
            super();
            this.condition = condition;
            this.value = value;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.betweenValue = true;
        }
    }
}