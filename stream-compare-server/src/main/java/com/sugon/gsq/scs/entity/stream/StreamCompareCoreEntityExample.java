package com.sugon.gsq.scs.entity.stream;

import java.util.ArrayList;
import java.util.List;

public class StreamCompareCoreEntityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public StreamCompareCoreEntityExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStormTaskSourceIsNull() {
            addCriterion("stormTaskSource is null");
            return (Criteria) this;
        }

        public Criteria andStormTaskSourceIsNotNull() {
            addCriterion("stormTaskSource is not null");
            return (Criteria) this;
        }

        public Criteria andStormTaskSourceEqualTo(String value) {
            addCriterion("stormTaskSource =", value, "stormTaskSource");
            return (Criteria) this;
        }

        public Criteria andStormTaskSourceNotEqualTo(String value) {
            addCriterion("stormTaskSource <>", value, "stormTaskSource");
            return (Criteria) this;
        }

        public Criteria andStormTaskSourceGreaterThan(String value) {
            addCriterion("stormTaskSource >", value, "stormTaskSource");
            return (Criteria) this;
        }

        public Criteria andStormTaskSourceGreaterThanOrEqualTo(String value) {
            addCriterion("stormTaskSource >=", value, "stormTaskSource");
            return (Criteria) this;
        }

        public Criteria andStormTaskSourceLessThan(String value) {
            addCriterion("stormTaskSource <", value, "stormTaskSource");
            return (Criteria) this;
        }

        public Criteria andStormTaskSourceLessThanOrEqualTo(String value) {
            addCriterion("stormTaskSource <=", value, "stormTaskSource");
            return (Criteria) this;
        }

        public Criteria andStormTaskSourceLike(String value) {
            addCriterion("stormTaskSource like", value, "stormTaskSource");
            return (Criteria) this;
        }

        public Criteria andStormTaskSourceNotLike(String value) {
            addCriterion("stormTaskSource not like", value, "stormTaskSource");
            return (Criteria) this;
        }

        public Criteria andStormTaskSourceIn(List<String> values) {
            addCriterion("stormTaskSource in", values, "stormTaskSource");
            return (Criteria) this;
        }

        public Criteria andStormTaskSourceNotIn(List<String> values) {
            addCriterion("stormTaskSource not in", values, "stormTaskSource");
            return (Criteria) this;
        }

        public Criteria andStormTaskSourceBetween(String value1, String value2) {
            addCriterion("stormTaskSource between", value1, value2, "stormTaskSource");
            return (Criteria) this;
        }

        public Criteria andStormTaskSourceNotBetween(String value1, String value2) {
            addCriterion("stormTaskSource not between", value1, value2, "stormTaskSource");
            return (Criteria) this;
        }

        public Criteria andBlacklistSourceIsNull() {
            addCriterion("blacklistSource is null");
            return (Criteria) this;
        }

        public Criteria andBlacklistSourceIsNotNull() {
            addCriterion("blacklistSource is not null");
            return (Criteria) this;
        }

        public Criteria andBlacklistSourceEqualTo(String value) {
            addCriterion("blacklistSource =", value, "blacklistSource");
            return (Criteria) this;
        }

        public Criteria andBlacklistSourceNotEqualTo(String value) {
            addCriterion("blacklistSource <>", value, "blacklistSource");
            return (Criteria) this;
        }

        public Criteria andBlacklistSourceGreaterThan(String value) {
            addCriterion("blacklistSource >", value, "blacklistSource");
            return (Criteria) this;
        }

        public Criteria andBlacklistSourceGreaterThanOrEqualTo(String value) {
            addCriterion("blacklistSource >=", value, "blacklistSource");
            return (Criteria) this;
        }

        public Criteria andBlacklistSourceLessThan(String value) {
            addCriterion("blacklistSource <", value, "blacklistSource");
            return (Criteria) this;
        }

        public Criteria andBlacklistSourceLessThanOrEqualTo(String value) {
            addCriterion("blacklistSource <=", value, "blacklistSource");
            return (Criteria) this;
        }

        public Criteria andBlacklistSourceLike(String value) {
            addCriterion("blacklistSource like", value, "blacklistSource");
            return (Criteria) this;
        }

        public Criteria andBlacklistSourceNotLike(String value) {
            addCriterion("blacklistSource not like", value, "blacklistSource");
            return (Criteria) this;
        }

        public Criteria andBlacklistSourceIn(List<String> values) {
            addCriterion("blacklistSource in", values, "blacklistSource");
            return (Criteria) this;
        }

        public Criteria andBlacklistSourceNotIn(List<String> values) {
            addCriterion("blacklistSource not in", values, "blacklistSource");
            return (Criteria) this;
        }

        public Criteria andBlacklistSourceBetween(String value1, String value2) {
            addCriterion("blacklistSource between", value1, value2, "blacklistSource");
            return (Criteria) this;
        }

        public Criteria andBlacklistSourceNotBetween(String value1, String value2) {
            addCriterion("blacklistSource not between", value1, value2, "blacklistSource");
            return (Criteria) this;
        }

        public Criteria andCompareFieldIsNull() {
            addCriterion("compareField is null");
            return (Criteria) this;
        }

        public Criteria andCompareFieldIsNotNull() {
            addCriterion("compareField is not null");
            return (Criteria) this;
        }

        public Criteria andCompareFieldEqualTo(String value) {
            addCriterion("compareField =", value, "compareField");
            return (Criteria) this;
        }

        public Criteria andCompareFieldNotEqualTo(String value) {
            addCriterion("compareField <>", value, "compareField");
            return (Criteria) this;
        }

        public Criteria andCompareFieldGreaterThan(String value) {
            addCriterion("compareField >", value, "compareField");
            return (Criteria) this;
        }

        public Criteria andCompareFieldGreaterThanOrEqualTo(String value) {
            addCriterion("compareField >=", value, "compareField");
            return (Criteria) this;
        }

        public Criteria andCompareFieldLessThan(String value) {
            addCriterion("compareField <", value, "compareField");
            return (Criteria) this;
        }

        public Criteria andCompareFieldLessThanOrEqualTo(String value) {
            addCriterion("compareField <=", value, "compareField");
            return (Criteria) this;
        }

        public Criteria andCompareFieldLike(String value) {
            addCriterion("compareField like", value, "compareField");
            return (Criteria) this;
        }

        public Criteria andCompareFieldNotLike(String value) {
            addCriterion("compareField not like", value, "compareField");
            return (Criteria) this;
        }

        public Criteria andCompareFieldIn(List<String> values) {
            addCriterion("compareField in", values, "compareField");
            return (Criteria) this;
        }

        public Criteria andCompareFieldNotIn(List<String> values) {
            addCriterion("compareField not in", values, "compareField");
            return (Criteria) this;
        }

        public Criteria andCompareFieldBetween(String value1, String value2) {
            addCriterion("compareField between", value1, value2, "compareField");
            return (Criteria) this;
        }

        public Criteria andCompareFieldNotBetween(String value1, String value2) {
            addCriterion("compareField not between", value1, value2, "compareField");
            return (Criteria) this;
        }

        public Criteria andCompareFieldOfJdbcIsNull() {
            addCriterion("compareFieldOfJdbc is null");
            return (Criteria) this;
        }

        public Criteria andCompareFieldOfJdbcIsNotNull() {
            addCriterion("compareFieldOfJdbc is not null");
            return (Criteria) this;
        }

        public Criteria andCompareFieldOfJdbcEqualTo(String value) {
            addCriterion("compareFieldOfJdbc =", value, "compareFieldOfJdbc");
            return (Criteria) this;
        }

        public Criteria andCompareFieldOfJdbcNotEqualTo(String value) {
            addCriterion("compareFieldOfJdbc <>", value, "compareFieldOfJdbc");
            return (Criteria) this;
        }

        public Criteria andCompareFieldOfJdbcGreaterThan(String value) {
            addCriterion("compareFieldOfJdbc >", value, "compareFieldOfJdbc");
            return (Criteria) this;
        }

        public Criteria andCompareFieldOfJdbcGreaterThanOrEqualTo(String value) {
            addCriterion("compareFieldOfJdbc >=", value, "compareFieldOfJdbc");
            return (Criteria) this;
        }

        public Criteria andCompareFieldOfJdbcLessThan(String value) {
            addCriterion("compareFieldOfJdbc <", value, "compareFieldOfJdbc");
            return (Criteria) this;
        }

        public Criteria andCompareFieldOfJdbcLessThanOrEqualTo(String value) {
            addCriterion("compareFieldOfJdbc <=", value, "compareFieldOfJdbc");
            return (Criteria) this;
        }

        public Criteria andCompareFieldOfJdbcLike(String value) {
            addCriterion("compareFieldOfJdbc like", value, "compareFieldOfJdbc");
            return (Criteria) this;
        }

        public Criteria andCompareFieldOfJdbcNotLike(String value) {
            addCriterion("compareFieldOfJdbc not like", value, "compareFieldOfJdbc");
            return (Criteria) this;
        }

        public Criteria andCompareFieldOfJdbcIn(List<String> values) {
            addCriterion("compareFieldOfJdbc in", values, "compareFieldOfJdbc");
            return (Criteria) this;
        }

        public Criteria andCompareFieldOfJdbcNotIn(List<String> values) {
            addCriterion("compareFieldOfJdbc not in", values, "compareFieldOfJdbc");
            return (Criteria) this;
        }

        public Criteria andCompareFieldOfJdbcBetween(String value1, String value2) {
            addCriterion("compareFieldOfJdbc between", value1, value2, "compareFieldOfJdbc");
            return (Criteria) this;
        }

        public Criteria andCompareFieldOfJdbcNotBetween(String value1, String value2) {
            addCriterion("compareFieldOfJdbc not between", value1, value2, "compareFieldOfJdbc");
            return (Criteria) this;
        }

        public Criteria andSignIsNull() {
            addCriterion("sign is null");
            return (Criteria) this;
        }

        public Criteria andSignIsNotNull() {
            addCriterion("sign is not null");
            return (Criteria) this;
        }

        public Criteria andSignEqualTo(Integer value) {
            addCriterion("sign =", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotEqualTo(Integer value) {
            addCriterion("sign <>", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignGreaterThan(Integer value) {
            addCriterion("sign >", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignGreaterThanOrEqualTo(Integer value) {
            addCriterion("sign >=", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLessThan(Integer value) {
            addCriterion("sign <", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLessThanOrEqualTo(Integer value) {
            addCriterion("sign <=", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignIn(List<Integer> values) {
            addCriterion("sign in", values, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotIn(List<Integer> values) {
            addCriterion("sign not in", values, "sign");
            return (Criteria) this;
        }

        public Criteria andSignBetween(Integer value1, Integer value2) {
            addCriterion("sign between", value1, value2, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotBetween(Integer value1, Integer value2) {
            addCriterion("sign not between", value1, value2, "sign");
            return (Criteria) this;
        }
    }

    /**
     */
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