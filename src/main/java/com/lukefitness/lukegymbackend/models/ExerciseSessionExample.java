package com.lukefitness.lukegymbackend.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExerciseSessionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExerciseSessionExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andExerciseSessionIdIsNull() {
            addCriterion("exercise_session_id is null");
            return (Criteria) this;
        }

        public Criteria andExerciseSessionIdIsNotNull() {
            addCriterion("exercise_session_id is not null");
            return (Criteria) this;
        }

        public Criteria andExerciseSessionIdEqualTo(Integer value) {
            addCriterion("exercise_session_id =", value, "exerciseSessionId");
            return (Criteria) this;
        }

        public Criteria andExerciseSessionIdNotEqualTo(Integer value) {
            addCriterion("exercise_session_id <>", value, "exerciseSessionId");
            return (Criteria) this;
        }

        public Criteria andExerciseSessionIdGreaterThan(Integer value) {
            addCriterion("exercise_session_id >", value, "exerciseSessionId");
            return (Criteria) this;
        }

        public Criteria andExerciseSessionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exercise_session_id >=", value, "exerciseSessionId");
            return (Criteria) this;
        }

        public Criteria andExerciseSessionIdLessThan(Integer value) {
            addCriterion("exercise_session_id <", value, "exerciseSessionId");
            return (Criteria) this;
        }

        public Criteria andExerciseSessionIdLessThanOrEqualTo(Integer value) {
            addCriterion("exercise_session_id <=", value, "exerciseSessionId");
            return (Criteria) this;
        }

        public Criteria andExerciseSessionIdIn(List<Integer> values) {
            addCriterion("exercise_session_id in", values, "exerciseSessionId");
            return (Criteria) this;
        }

        public Criteria andExerciseSessionIdNotIn(List<Integer> values) {
            addCriterion("exercise_session_id not in", values, "exerciseSessionId");
            return (Criteria) this;
        }

        public Criteria andExerciseSessionIdBetween(Integer value1, Integer value2) {
            addCriterion("exercise_session_id between", value1, value2, "exerciseSessionId");
            return (Criteria) this;
        }

        public Criteria andExerciseSessionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exercise_session_id not between", value1, value2, "exerciseSessionId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNull() {
            addCriterion("card_id is null");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNotNull() {
            addCriterion("card_id is not null");
            return (Criteria) this;
        }

        public Criteria andCardIdEqualTo(Integer value) {
            addCriterion("card_id =", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotEqualTo(Integer value) {
            addCriterion("card_id <>", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThan(Integer value) {
            addCriterion("card_id >", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_id >=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThan(Integer value) {
            addCriterion("card_id <", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThanOrEqualTo(Integer value) {
            addCriterion("card_id <=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdIn(List<Integer> values) {
            addCriterion("card_id in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotIn(List<Integer> values) {
            addCriterion("card_id not in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdBetween(Integer value1, Integer value2) {
            addCriterion("card_id between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotBetween(Integer value1, Integer value2) {
            addCriterion("card_id not between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andSessionTypeIsNull() {
            addCriterion("session_type is null");
            return (Criteria) this;
        }

        public Criteria andSessionTypeIsNotNull() {
            addCriterion("session_type is not null");
            return (Criteria) this;
        }

        public Criteria andSessionTypeEqualTo(String value) {
            addCriterion("session_type =", value, "sessionType");
            return (Criteria) this;
        }

        public Criteria andSessionTypeNotEqualTo(String value) {
            addCriterion("session_type <>", value, "sessionType");
            return (Criteria) this;
        }

        public Criteria andSessionTypeGreaterThan(String value) {
            addCriterion("session_type >", value, "sessionType");
            return (Criteria) this;
        }

        public Criteria andSessionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("session_type >=", value, "sessionType");
            return (Criteria) this;
        }

        public Criteria andSessionTypeLessThan(String value) {
            addCriterion("session_type <", value, "sessionType");
            return (Criteria) this;
        }

        public Criteria andSessionTypeLessThanOrEqualTo(String value) {
            addCriterion("session_type <=", value, "sessionType");
            return (Criteria) this;
        }

        public Criteria andSessionTypeLike(String value) {
            addCriterion("session_type like", value, "sessionType");
            return (Criteria) this;
        }

        public Criteria andSessionTypeNotLike(String value) {
            addCriterion("session_type not like", value, "sessionType");
            return (Criteria) this;
        }

        public Criteria andSessionTypeIn(List<String> values) {
            addCriterion("session_type in", values, "sessionType");
            return (Criteria) this;
        }

        public Criteria andSessionTypeNotIn(List<String> values) {
            addCriterion("session_type not in", values, "sessionType");
            return (Criteria) this;
        }

        public Criteria andSessionTypeBetween(String value1, String value2) {
            addCriterion("session_type between", value1, value2, "sessionType");
            return (Criteria) this;
        }

        public Criteria andSessionTypeNotBetween(String value1, String value2) {
            addCriterion("session_type not between", value1, value2, "sessionType");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Integer value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Integer value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Integer value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Integer value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Integer value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Integer> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Integer> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Integer value1, Integer value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andRepsIsNull() {
            addCriterion("reps is null");
            return (Criteria) this;
        }

        public Criteria andRepsIsNotNull() {
            addCriterion("reps is not null");
            return (Criteria) this;
        }

        public Criteria andRepsEqualTo(Integer value) {
            addCriterion("reps =", value, "reps");
            return (Criteria) this;
        }

        public Criteria andRepsNotEqualTo(Integer value) {
            addCriterion("reps <>", value, "reps");
            return (Criteria) this;
        }

        public Criteria andRepsGreaterThan(Integer value) {
            addCriterion("reps >", value, "reps");
            return (Criteria) this;
        }

        public Criteria andRepsGreaterThanOrEqualTo(Integer value) {
            addCriterion("reps >=", value, "reps");
            return (Criteria) this;
        }

        public Criteria andRepsLessThan(Integer value) {
            addCriterion("reps <", value, "reps");
            return (Criteria) this;
        }

        public Criteria andRepsLessThanOrEqualTo(Integer value) {
            addCriterion("reps <=", value, "reps");
            return (Criteria) this;
        }

        public Criteria andRepsIn(List<Integer> values) {
            addCriterion("reps in", values, "reps");
            return (Criteria) this;
        }

        public Criteria andRepsNotIn(List<Integer> values) {
            addCriterion("reps not in", values, "reps");
            return (Criteria) this;
        }

        public Criteria andRepsBetween(Integer value1, Integer value2) {
            addCriterion("reps between", value1, value2, "reps");
            return (Criteria) this;
        }

        public Criteria andRepsNotBetween(Integer value1, Integer value2) {
            addCriterion("reps not between", value1, value2, "reps");
            return (Criteria) this;
        }

        public Criteria andSetsIsNull() {
            addCriterion("sets is null");
            return (Criteria) this;
        }

        public Criteria andSetsIsNotNull() {
            addCriterion("sets is not null");
            return (Criteria) this;
        }

        public Criteria andSetsEqualTo(Integer value) {
            addCriterion("sets =", value, "sets");
            return (Criteria) this;
        }

        public Criteria andSetsNotEqualTo(Integer value) {
            addCriterion("sets <>", value, "sets");
            return (Criteria) this;
        }

        public Criteria andSetsGreaterThan(Integer value) {
            addCriterion("sets >", value, "sets");
            return (Criteria) this;
        }

        public Criteria andSetsGreaterThanOrEqualTo(Integer value) {
            addCriterion("sets >=", value, "sets");
            return (Criteria) this;
        }

        public Criteria andSetsLessThan(Integer value) {
            addCriterion("sets <", value, "sets");
            return (Criteria) this;
        }

        public Criteria andSetsLessThanOrEqualTo(Integer value) {
            addCriterion("sets <=", value, "sets");
            return (Criteria) this;
        }

        public Criteria andSetsIn(List<Integer> values) {
            addCriterion("sets in", values, "sets");
            return (Criteria) this;
        }

        public Criteria andSetsNotIn(List<Integer> values) {
            addCriterion("sets not in", values, "sets");
            return (Criteria) this;
        }

        public Criteria andSetsBetween(Integer value1, Integer value2) {
            addCriterion("sets between", value1, value2, "sets");
            return (Criteria) this;
        }

        public Criteria andSetsNotBetween(Integer value1, Integer value2) {
            addCriterion("sets not between", value1, value2, "sets");
            return (Criteria) this;
        }

        public Criteria andIntensityIsNull() {
            addCriterion("intensity is null");
            return (Criteria) this;
        }

        public Criteria andIntensityIsNotNull() {
            addCriterion("intensity is not null");
            return (Criteria) this;
        }

        public Criteria andIntensityEqualTo(String value) {
            addCriterion("intensity =", value, "intensity");
            return (Criteria) this;
        }

        public Criteria andIntensityNotEqualTo(String value) {
            addCriterion("intensity <>", value, "intensity");
            return (Criteria) this;
        }

        public Criteria andIntensityGreaterThan(String value) {
            addCriterion("intensity >", value, "intensity");
            return (Criteria) this;
        }

        public Criteria andIntensityGreaterThanOrEqualTo(String value) {
            addCriterion("intensity >=", value, "intensity");
            return (Criteria) this;
        }

        public Criteria andIntensityLessThan(String value) {
            addCriterion("intensity <", value, "intensity");
            return (Criteria) this;
        }

        public Criteria andIntensityLessThanOrEqualTo(String value) {
            addCriterion("intensity <=", value, "intensity");
            return (Criteria) this;
        }

        public Criteria andIntensityLike(String value) {
            addCriterion("intensity like", value, "intensity");
            return (Criteria) this;
        }

        public Criteria andIntensityNotLike(String value) {
            addCriterion("intensity not like", value, "intensity");
            return (Criteria) this;
        }

        public Criteria andIntensityIn(List<String> values) {
            addCriterion("intensity in", values, "intensity");
            return (Criteria) this;
        }

        public Criteria andIntensityNotIn(List<String> values) {
            addCriterion("intensity not in", values, "intensity");
            return (Criteria) this;
        }

        public Criteria andIntensityBetween(String value1, String value2) {
            addCriterion("intensity between", value1, value2, "intensity");
            return (Criteria) this;
        }

        public Criteria andIntensityNotBetween(String value1, String value2) {
            addCriterion("intensity not between", value1, value2, "intensity");
            return (Criteria) this;
        }

        public Criteria andTempoIsNull() {
            addCriterion("tempo is null");
            return (Criteria) this;
        }

        public Criteria andTempoIsNotNull() {
            addCriterion("tempo is not null");
            return (Criteria) this;
        }

        public Criteria andTempoEqualTo(String value) {
            addCriterion("tempo =", value, "tempo");
            return (Criteria) this;
        }

        public Criteria andTempoNotEqualTo(String value) {
            addCriterion("tempo <>", value, "tempo");
            return (Criteria) this;
        }

        public Criteria andTempoGreaterThan(String value) {
            addCriterion("tempo >", value, "tempo");
            return (Criteria) this;
        }

        public Criteria andTempoGreaterThanOrEqualTo(String value) {
            addCriterion("tempo >=", value, "tempo");
            return (Criteria) this;
        }

        public Criteria andTempoLessThan(String value) {
            addCriterion("tempo <", value, "tempo");
            return (Criteria) this;
        }

        public Criteria andTempoLessThanOrEqualTo(String value) {
            addCriterion("tempo <=", value, "tempo");
            return (Criteria) this;
        }

        public Criteria andTempoLike(String value) {
            addCriterion("tempo like", value, "tempo");
            return (Criteria) this;
        }

        public Criteria andTempoNotLike(String value) {
            addCriterion("tempo not like", value, "tempo");
            return (Criteria) this;
        }

        public Criteria andTempoIn(List<String> values) {
            addCriterion("tempo in", values, "tempo");
            return (Criteria) this;
        }

        public Criteria andTempoNotIn(List<String> values) {
            addCriterion("tempo not in", values, "tempo");
            return (Criteria) this;
        }

        public Criteria andTempoBetween(String value1, String value2) {
            addCriterion("tempo between", value1, value2, "tempo");
            return (Criteria) this;
        }

        public Criteria andTempoNotBetween(String value1, String value2) {
            addCriterion("tempo not between", value1, value2, "tempo");
            return (Criteria) this;
        }

        public Criteria andRestIsNull() {
            addCriterion("rest is null");
            return (Criteria) this;
        }

        public Criteria andRestIsNotNull() {
            addCriterion("rest is not null");
            return (Criteria) this;
        }

        public Criteria andRestEqualTo(Integer value) {
            addCriterion("rest =", value, "rest");
            return (Criteria) this;
        }

        public Criteria andRestNotEqualTo(Integer value) {
            addCriterion("rest <>", value, "rest");
            return (Criteria) this;
        }

        public Criteria andRestGreaterThan(Integer value) {
            addCriterion("rest >", value, "rest");
            return (Criteria) this;
        }

        public Criteria andRestGreaterThanOrEqualTo(Integer value) {
            addCriterion("rest >=", value, "rest");
            return (Criteria) this;
        }

        public Criteria andRestLessThan(Integer value) {
            addCriterion("rest <", value, "rest");
            return (Criteria) this;
        }

        public Criteria andRestLessThanOrEqualTo(Integer value) {
            addCriterion("rest <=", value, "rest");
            return (Criteria) this;
        }

        public Criteria andRestIn(List<Integer> values) {
            addCriterion("rest in", values, "rest");
            return (Criteria) this;
        }

        public Criteria andRestNotIn(List<Integer> values) {
            addCriterion("rest not in", values, "rest");
            return (Criteria) this;
        }

        public Criteria andRestBetween(Integer value1, Integer value2) {
            addCriterion("rest between", value1, value2, "rest");
            return (Criteria) this;
        }

        public Criteria andRestNotBetween(Integer value1, Integer value2) {
            addCriterion("rest not between", value1, value2, "rest");
            return (Criteria) this;
        }

        public Criteria andUpdateAtIsNull() {
            addCriterion("update_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdateAtIsNotNull() {
            addCriterion("update_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateAtEqualTo(Date value) {
            addCriterion("update_at =", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtNotEqualTo(Date value) {
            addCriterion("update_at <>", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtGreaterThan(Date value) {
            addCriterion("update_at >", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtGreaterThanOrEqualTo(Date value) {
            addCriterion("update_at >=", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtLessThan(Date value) {
            addCriterion("update_at <", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtLessThanOrEqualTo(Date value) {
            addCriterion("update_at <=", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtIn(List<Date> values) {
            addCriterion("update_at in", values, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtNotIn(List<Date> values) {
            addCriterion("update_at not in", values, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtBetween(Date value1, Date value2) {
            addCriterion("update_at between", value1, value2, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtNotBetween(Date value1, Date value2) {
            addCriterion("update_at not between", value1, value2, "updateAt");
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