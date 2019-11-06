package com.sugon.gsq.scs.entity.stream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StreamStormTaskEntityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public StreamStormTaskEntityExample() {
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

        public Criteria andBelongUserIsNull() {
            addCriterion("belongUser is null");
            return (Criteria) this;
        }

        public Criteria andBelongUserIsNotNull() {
            addCriterion("belongUser is not null");
            return (Criteria) this;
        }

        public Criteria andBelongUserEqualTo(String value) {
            addCriterion("belongUser =", value, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserNotEqualTo(String value) {
            addCriterion("belongUser <>", value, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserGreaterThan(String value) {
            addCriterion("belongUser >", value, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserGreaterThanOrEqualTo(String value) {
            addCriterion("belongUser >=", value, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserLessThan(String value) {
            addCriterion("belongUser <", value, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserLessThanOrEqualTo(String value) {
            addCriterion("belongUser <=", value, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserLike(String value) {
            addCriterion("belongUser like", value, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserNotLike(String value) {
            addCriterion("belongUser not like", value, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserIn(List<String> values) {
            addCriterion("belongUser in", values, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserNotIn(List<String> values) {
            addCriterion("belongUser not in", values, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserBetween(String value1, String value2) {
            addCriterion("belongUser between", value1, value2, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserNotBetween(String value1, String value2) {
            addCriterion("belongUser not between", value1, value2, "belongUser");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNull() {
            addCriterion("jobName is null");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNotNull() {
            addCriterion("jobName is not null");
            return (Criteria) this;
        }

        public Criteria andJobNameEqualTo(String value) {
            addCriterion("jobName =", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotEqualTo(String value) {
            addCriterion("jobName <>", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThan(String value) {
            addCriterion("jobName >", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThanOrEqualTo(String value) {
            addCriterion("jobName >=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThan(String value) {
            addCriterion("jobName <", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThanOrEqualTo(String value) {
            addCriterion("jobName <=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLike(String value) {
            addCriterion("jobName like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotLike(String value) {
            addCriterion("jobName not like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameIn(List<String> values) {
            addCriterion("jobName in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotIn(List<String> values) {
            addCriterion("jobName not in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameBetween(String value1, String value2) {
            addCriterion("jobName between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotBetween(String value1, String value2) {
            addCriterion("jobName not between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andWorkerNumIsNull() {
            addCriterion("workerNum is null");
            return (Criteria) this;
        }

        public Criteria andWorkerNumIsNotNull() {
            addCriterion("workerNum is not null");
            return (Criteria) this;
        }

        public Criteria andWorkerNumEqualTo(Integer value) {
            addCriterion("workerNum =", value, "workerNum");
            return (Criteria) this;
        }

        public Criteria andWorkerNumNotEqualTo(Integer value) {
            addCriterion("workerNum <>", value, "workerNum");
            return (Criteria) this;
        }

        public Criteria andWorkerNumGreaterThan(Integer value) {
            addCriterion("workerNum >", value, "workerNum");
            return (Criteria) this;
        }

        public Criteria andWorkerNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("workerNum >=", value, "workerNum");
            return (Criteria) this;
        }

        public Criteria andWorkerNumLessThan(Integer value) {
            addCriterion("workerNum <", value, "workerNum");
            return (Criteria) this;
        }

        public Criteria andWorkerNumLessThanOrEqualTo(Integer value) {
            addCriterion("workerNum <=", value, "workerNum");
            return (Criteria) this;
        }

        public Criteria andWorkerNumIn(List<Integer> values) {
            addCriterion("workerNum in", values, "workerNum");
            return (Criteria) this;
        }

        public Criteria andWorkerNumNotIn(List<Integer> values) {
            addCriterion("workerNum not in", values, "workerNum");
            return (Criteria) this;
        }

        public Criteria andWorkerNumBetween(Integer value1, Integer value2) {
            addCriterion("workerNum between", value1, value2, "workerNum");
            return (Criteria) this;
        }

        public Criteria andWorkerNumNotBetween(Integer value1, Integer value2) {
            addCriterion("workerNum not between", value1, value2, "workerNum");
            return (Criteria) this;
        }

        public Criteria andCompareTypeIsNull() {
            addCriterion("compareType is null");
            return (Criteria) this;
        }

        public Criteria andCompareTypeIsNotNull() {
            addCriterion("compareType is not null");
            return (Criteria) this;
        }

        public Criteria andCompareTypeEqualTo(String value) {
            addCriterion("compareType =", value, "compareType");
            return (Criteria) this;
        }

        public Criteria andCompareTypeNotEqualTo(String value) {
            addCriterion("compareType <>", value, "compareType");
            return (Criteria) this;
        }

        public Criteria andCompareTypeGreaterThan(String value) {
            addCriterion("compareType >", value, "compareType");
            return (Criteria) this;
        }

        public Criteria andCompareTypeGreaterThanOrEqualTo(String value) {
            addCriterion("compareType >=", value, "compareType");
            return (Criteria) this;
        }

        public Criteria andCompareTypeLessThan(String value) {
            addCriterion("compareType <", value, "compareType");
            return (Criteria) this;
        }

        public Criteria andCompareTypeLessThanOrEqualTo(String value) {
            addCriterion("compareType <=", value, "compareType");
            return (Criteria) this;
        }

        public Criteria andCompareTypeLike(String value) {
            addCriterion("compareType like", value, "compareType");
            return (Criteria) this;
        }

        public Criteria andCompareTypeNotLike(String value) {
            addCriterion("compareType not like", value, "compareType");
            return (Criteria) this;
        }

        public Criteria andCompareTypeIn(List<String> values) {
            addCriterion("compareType in", values, "compareType");
            return (Criteria) this;
        }

        public Criteria andCompareTypeNotIn(List<String> values) {
            addCriterion("compareType not in", values, "compareType");
            return (Criteria) this;
        }

        public Criteria andCompareTypeBetween(String value1, String value2) {
            addCriterion("compareType between", value1, value2, "compareType");
            return (Criteria) this;
        }

        public Criteria andCompareTypeNotBetween(String value1, String value2) {
            addCriterion("compareType not between", value1, value2, "compareType");
            return (Criteria) this;
        }

        public Criteria andKafkaServerIsNull() {
            addCriterion("kafkaServer is null");
            return (Criteria) this;
        }

        public Criteria andKafkaServerIsNotNull() {
            addCriterion("kafkaServer is not null");
            return (Criteria) this;
        }

        public Criteria andKafkaServerEqualTo(String value) {
            addCriterion("kafkaServer =", value, "kafkaServer");
            return (Criteria) this;
        }

        public Criteria andKafkaServerNotEqualTo(String value) {
            addCriterion("kafkaServer <>", value, "kafkaServer");
            return (Criteria) this;
        }

        public Criteria andKafkaServerGreaterThan(String value) {
            addCriterion("kafkaServer >", value, "kafkaServer");
            return (Criteria) this;
        }

        public Criteria andKafkaServerGreaterThanOrEqualTo(String value) {
            addCriterion("kafkaServer >=", value, "kafkaServer");
            return (Criteria) this;
        }

        public Criteria andKafkaServerLessThan(String value) {
            addCriterion("kafkaServer <", value, "kafkaServer");
            return (Criteria) this;
        }

        public Criteria andKafkaServerLessThanOrEqualTo(String value) {
            addCriterion("kafkaServer <=", value, "kafkaServer");
            return (Criteria) this;
        }

        public Criteria andKafkaServerLike(String value) {
            addCriterion("kafkaServer like", value, "kafkaServer");
            return (Criteria) this;
        }

        public Criteria andKafkaServerNotLike(String value) {
            addCriterion("kafkaServer not like", value, "kafkaServer");
            return (Criteria) this;
        }

        public Criteria andKafkaServerIn(List<String> values) {
            addCriterion("kafkaServer in", values, "kafkaServer");
            return (Criteria) this;
        }

        public Criteria andKafkaServerNotIn(List<String> values) {
            addCriterion("kafkaServer not in", values, "kafkaServer");
            return (Criteria) this;
        }

        public Criteria andKafkaServerBetween(String value1, String value2) {
            addCriterion("kafkaServer between", value1, value2, "kafkaServer");
            return (Criteria) this;
        }

        public Criteria andKafkaServerNotBetween(String value1, String value2) {
            addCriterion("kafkaServer not between", value1, value2, "kafkaServer");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("`result` is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("`result` is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(String value) {
            addCriterion("`result` =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(String value) {
            addCriterion("`result` <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(String value) {
            addCriterion("`result` >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(String value) {
            addCriterion("`result` >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(String value) {
            addCriterion("`result` <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(String value) {
            addCriterion("`result` <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLike(String value) {
            addCriterion("`result` like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotLike(String value) {
            addCriterion("`result` not like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<String> values) {
            addCriterion("`result` in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<String> values) {
            addCriterion("`result` not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(String value1, String value2) {
            addCriterion("`result` between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(String value1, String value2) {
            addCriterion("`result` not between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andKafkaSourceIsNull() {
            addCriterion("kafkaSource is null");
            return (Criteria) this;
        }

        public Criteria andKafkaSourceIsNotNull() {
            addCriterion("kafkaSource is not null");
            return (Criteria) this;
        }

        public Criteria andKafkaSourceEqualTo(String value) {
            addCriterion("kafkaSource =", value, "kafkaSource");
            return (Criteria) this;
        }

        public Criteria andKafkaSourceNotEqualTo(String value) {
            addCriterion("kafkaSource <>", value, "kafkaSource");
            return (Criteria) this;
        }

        public Criteria andKafkaSourceGreaterThan(String value) {
            addCriterion("kafkaSource >", value, "kafkaSource");
            return (Criteria) this;
        }

        public Criteria andKafkaSourceGreaterThanOrEqualTo(String value) {
            addCriterion("kafkaSource >=", value, "kafkaSource");
            return (Criteria) this;
        }

        public Criteria andKafkaSourceLessThan(String value) {
            addCriterion("kafkaSource <", value, "kafkaSource");
            return (Criteria) this;
        }

        public Criteria andKafkaSourceLessThanOrEqualTo(String value) {
            addCriterion("kafkaSource <=", value, "kafkaSource");
            return (Criteria) this;
        }

        public Criteria andKafkaSourceLike(String value) {
            addCriterion("kafkaSource like", value, "kafkaSource");
            return (Criteria) this;
        }

        public Criteria andKafkaSourceNotLike(String value) {
            addCriterion("kafkaSource not like", value, "kafkaSource");
            return (Criteria) this;
        }

        public Criteria andKafkaSourceIn(List<String> values) {
            addCriterion("kafkaSource in", values, "kafkaSource");
            return (Criteria) this;
        }

        public Criteria andKafkaSourceNotIn(List<String> values) {
            addCriterion("kafkaSource not in", values, "kafkaSource");
            return (Criteria) this;
        }

        public Criteria andKafkaSourceBetween(String value1, String value2) {
            addCriterion("kafkaSource between", value1, value2, "kafkaSource");
            return (Criteria) this;
        }

        public Criteria andKafkaSourceNotBetween(String value1, String value2) {
            addCriterion("kafkaSource not between", value1, value2, "kafkaSource");
            return (Criteria) this;
        }

        public Criteria andLayoutIsNull() {
            addCriterion("layout is null");
            return (Criteria) this;
        }

        public Criteria andLayoutIsNotNull() {
            addCriterion("layout is not null");
            return (Criteria) this;
        }

        public Criteria andLayoutEqualTo(String value) {
            addCriterion("layout =", value, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutNotEqualTo(String value) {
            addCriterion("layout <>", value, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutGreaterThan(String value) {
            addCriterion("layout >", value, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutGreaterThanOrEqualTo(String value) {
            addCriterion("layout >=", value, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutLessThan(String value) {
            addCriterion("layout <", value, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutLessThanOrEqualTo(String value) {
            addCriterion("layout <=", value, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutLike(String value) {
            addCriterion("layout like", value, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutNotLike(String value) {
            addCriterion("layout not like", value, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutIn(List<String> values) {
            addCriterion("layout in", values, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutNotIn(List<String> values) {
            addCriterion("layout not in", values, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutBetween(String value1, String value2) {
            addCriterion("layout between", value1, value2, "layout");
            return (Criteria) this;
        }

        public Criteria andLayoutNotBetween(String value1, String value2) {
            addCriterion("layout not between", value1, value2, "layout");
            return (Criteria) this;
        }

        public Criteria andSettingIsNull() {
            addCriterion("setting is null");
            return (Criteria) this;
        }

        public Criteria andSettingIsNotNull() {
            addCriterion("setting is not null");
            return (Criteria) this;
        }

        public Criteria andSettingEqualTo(String value) {
            addCriterion("setting =", value, "setting");
            return (Criteria) this;
        }

        public Criteria andSettingNotEqualTo(String value) {
            addCriterion("setting <>", value, "setting");
            return (Criteria) this;
        }

        public Criteria andSettingGreaterThan(String value) {
            addCriterion("setting >", value, "setting");
            return (Criteria) this;
        }

        public Criteria andSettingGreaterThanOrEqualTo(String value) {
            addCriterion("setting >=", value, "setting");
            return (Criteria) this;
        }

        public Criteria andSettingLessThan(String value) {
            addCriterion("setting <", value, "setting");
            return (Criteria) this;
        }

        public Criteria andSettingLessThanOrEqualTo(String value) {
            addCriterion("setting <=", value, "setting");
            return (Criteria) this;
        }

        public Criteria andSettingLike(String value) {
            addCriterion("setting like", value, "setting");
            return (Criteria) this;
        }

        public Criteria andSettingNotLike(String value) {
            addCriterion("setting not like", value, "setting");
            return (Criteria) this;
        }

        public Criteria andSettingIn(List<String> values) {
            addCriterion("setting in", values, "setting");
            return (Criteria) this;
        }

        public Criteria andSettingNotIn(List<String> values) {
            addCriterion("setting not in", values, "setting");
            return (Criteria) this;
        }

        public Criteria andSettingBetween(String value1, String value2) {
            addCriterion("setting between", value1, value2, "setting");
            return (Criteria) this;
        }

        public Criteria andSettingNotBetween(String value1, String value2) {
            addCriterion("setting not between", value1, value2, "setting");
            return (Criteria) this;
        }

        public Criteria andModelIsNull() {
            addCriterion("model is null");
            return (Criteria) this;
        }

        public Criteria andModelIsNotNull() {
            addCriterion("model is not null");
            return (Criteria) this;
        }

        public Criteria andModelEqualTo(String value) {
            addCriterion("model =", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotEqualTo(String value) {
            addCriterion("model <>", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelGreaterThan(String value) {
            addCriterion("model >", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelGreaterThanOrEqualTo(String value) {
            addCriterion("model >=", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLessThan(String value) {
            addCriterion("model <", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLessThanOrEqualTo(String value) {
            addCriterion("model <=", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLike(String value) {
            addCriterion("model like", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotLike(String value) {
            addCriterion("model not like", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelIn(List<String> values) {
            addCriterion("model in", values, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotIn(List<String> values) {
            addCriterion("model not in", values, "model");
            return (Criteria) this;
        }

        public Criteria andModelBetween(String value1, String value2) {
            addCriterion("model between", value1, value2, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotBetween(String value1, String value2) {
            addCriterion("model not between", value1, value2, "model");
            return (Criteria) this;
        }

        public Criteria andKeysIsNull() {
            addCriterion("`keys` is null");
            return (Criteria) this;
        }

        public Criteria andKeysIsNotNull() {
            addCriterion("`keys` is not null");
            return (Criteria) this;
        }

        public Criteria andKeysEqualTo(String value) {
            addCriterion("`keys` =", value, "keys");
            return (Criteria) this;
        }

        public Criteria andKeysNotEqualTo(String value) {
            addCriterion("`keys` <>", value, "keys");
            return (Criteria) this;
        }

        public Criteria andKeysGreaterThan(String value) {
            addCriterion("`keys` >", value, "keys");
            return (Criteria) this;
        }

        public Criteria andKeysGreaterThanOrEqualTo(String value) {
            addCriterion("`keys` >=", value, "keys");
            return (Criteria) this;
        }

        public Criteria andKeysLessThan(String value) {
            addCriterion("`keys` <", value, "keys");
            return (Criteria) this;
        }

        public Criteria andKeysLessThanOrEqualTo(String value) {
            addCriterion("`keys` <=", value, "keys");
            return (Criteria) this;
        }

        public Criteria andKeysLike(String value) {
            addCriterion("`keys` like", value, "keys");
            return (Criteria) this;
        }

        public Criteria andKeysNotLike(String value) {
            addCriterion("`keys` not like", value, "keys");
            return (Criteria) this;
        }

        public Criteria andKeysIn(List<String> values) {
            addCriterion("`keys` in", values, "keys");
            return (Criteria) this;
        }

        public Criteria andKeysNotIn(List<String> values) {
            addCriterion("`keys` not in", values, "keys");
            return (Criteria) this;
        }

        public Criteria andKeysBetween(String value1, String value2) {
            addCriterion("`keys` between", value1, value2, "keys");
            return (Criteria) this;
        }

        public Criteria andKeysNotBetween(String value1, String value2) {
            addCriterion("`keys` not between", value1, value2, "keys");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("createDate is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("createDate is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("createDate =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("createDate <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("createDate >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("createDate >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("createDate <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("createDate <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("createDate in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("createDate not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("createDate between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("createDate not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("updateDate is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("updateDate is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("updateDate =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("updateDate <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("updateDate >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("updateDate >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("updateDate <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("updateDate <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("updateDate in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("updateDate not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("updateDate between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("updateDate not between", value1, value2, "updateDate");
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