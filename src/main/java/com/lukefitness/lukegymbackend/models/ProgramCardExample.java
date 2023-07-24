package com.lukefitness.lukegymbackend.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ProgramCardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProgramCardExample() {
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
            List<java.sql.Date> dateList = new ArrayList<>();
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

        public Criteria andTrainerIdIsNull() {
            addCriterion("trainer_id is null");
            return (Criteria) this;
        }

        public Criteria andTrainerIdIsNotNull() {
            addCriterion("trainer_id is not null");
            return (Criteria) this;
        }

        public Criteria andTrainerIdEqualTo(Integer value) {
            addCriterion("trainer_id =", value, "trainerId");
            return (Criteria) this;
        }

        public Criteria andTrainerIdNotEqualTo(Integer value) {
            addCriterion("trainer_id <>", value, "trainerId");
            return (Criteria) this;
        }

        public Criteria andTrainerIdGreaterThan(Integer value) {
            addCriterion("trainer_id >", value, "trainerId");
            return (Criteria) this;
        }

        public Criteria andTrainerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("trainer_id >=", value, "trainerId");
            return (Criteria) this;
        }

        public Criteria andTrainerIdLessThan(Integer value) {
            addCriterion("trainer_id <", value, "trainerId");
            return (Criteria) this;
        }

        public Criteria andTrainerIdLessThanOrEqualTo(Integer value) {
            addCriterion("trainer_id <=", value, "trainerId");
            return (Criteria) this;
        }

        public Criteria andTrainerIdIn(List<Integer> values) {
            addCriterion("trainer_id in", values, "trainerId");
            return (Criteria) this;
        }

        public Criteria andTrainerIdNotIn(List<Integer> values) {
            addCriterion("trainer_id not in", values, "trainerId");
            return (Criteria) this;
        }

        public Criteria andTrainerIdBetween(Integer value1, Integer value2) {
            addCriterion("trainer_id between", value1, value2, "trainerId");
            return (Criteria) this;
        }

        public Criteria andTrainerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("trainer_id not between", value1, value2, "trainerId");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterionForJDBCDate("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterionForJDBCDate("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterionForJDBCDate("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterionForJDBCDate("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(Integer value) {
            addCriterion("duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(Integer value) {
            addCriterion("duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(Integer value) {
            addCriterion("duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(Integer value) {
            addCriterion("duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(Integer value) {
            addCriterion("duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(Integer value) {
            addCriterion("duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<Integer> values) {
            addCriterion("duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<Integer> values) {
            addCriterion("duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(Integer value1, Integer value2) {
            addCriterion("duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(Integer value1, Integer value2) {
            addCriterion("duration not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andTraineeIdIsNull() {
            addCriterion("trainee_id is null");
            return (Criteria) this;
        }

        public Criteria andTraineeIdIsNotNull() {
            addCriterion("trainee_id is not null");
            return (Criteria) this;
        }

        public Criteria andTraineeIdEqualTo(Integer value) {
            addCriterion("trainee_id =", value, "traineeId");
            return (Criteria) this;
        }

        public Criteria andTraineeIdNotEqualTo(Integer value) {
            addCriterion("trainee_id <>", value, "traineeId");
            return (Criteria) this;
        }

        public Criteria andTraineeIdGreaterThan(Integer value) {
            addCriterion("trainee_id >", value, "traineeId");
            return (Criteria) this;
        }

        public Criteria andTraineeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("trainee_id >=", value, "traineeId");
            return (Criteria) this;
        }

        public Criteria andTraineeIdLessThan(Integer value) {
            addCriterion("trainee_id <", value, "traineeId");
            return (Criteria) this;
        }

        public Criteria andTraineeIdLessThanOrEqualTo(Integer value) {
            addCriterion("trainee_id <=", value, "traineeId");
            return (Criteria) this;
        }

        public Criteria andTraineeIdIn(List<Integer> values) {
            addCriterion("trainee_id in", values, "traineeId");
            return (Criteria) this;
        }

        public Criteria andTraineeIdNotIn(List<Integer> values) {
            addCriterion("trainee_id not in", values, "traineeId");
            return (Criteria) this;
        }

        public Criteria andTraineeIdBetween(Integer value1, Integer value2) {
            addCriterion("trainee_id between", value1, value2, "traineeId");
            return (Criteria) this;
        }

        public Criteria andTraineeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("trainee_id not between", value1, value2, "traineeId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andSessionFocus1IsNull() {
            addCriterion("session_focus_1 is null");
            return (Criteria) this;
        }

        public Criteria andSessionFocus1IsNotNull() {
            addCriterion("session_focus_1 is not null");
            return (Criteria) this;
        }

        public Criteria andSessionFocus1EqualTo(String value) {
            addCriterion("session_focus_1 =", value, "sessionFocus1");
            return (Criteria) this;
        }

        public Criteria andSessionFocus1NotEqualTo(String value) {
            addCriterion("session_focus_1 <>", value, "sessionFocus1");
            return (Criteria) this;
        }

        public Criteria andSessionFocus1GreaterThan(String value) {
            addCriterion("session_focus_1 >", value, "sessionFocus1");
            return (Criteria) this;
        }

        public Criteria andSessionFocus1GreaterThanOrEqualTo(String value) {
            addCriterion("session_focus_1 >=", value, "sessionFocus1");
            return (Criteria) this;
        }

        public Criteria andSessionFocus1LessThan(String value) {
            addCriterion("session_focus_1 <", value, "sessionFocus1");
            return (Criteria) this;
        }

        public Criteria andSessionFocus1LessThanOrEqualTo(String value) {
            addCriterion("session_focus_1 <=", value, "sessionFocus1");
            return (Criteria) this;
        }

        public Criteria andSessionFocus1Like(String value) {
            addCriterion("session_focus_1 like", value, "sessionFocus1");
            return (Criteria) this;
        }

        public Criteria andSessionFocus1NotLike(String value) {
            addCriterion("session_focus_1 not like", value, "sessionFocus1");
            return (Criteria) this;
        }

        public Criteria andSessionFocus1In(List<String> values) {
            addCriterion("session_focus_1 in", values, "sessionFocus1");
            return (Criteria) this;
        }

        public Criteria andSessionFocus1NotIn(List<String> values) {
            addCriterion("session_focus_1 not in", values, "sessionFocus1");
            return (Criteria) this;
        }

        public Criteria andSessionFocus1Between(String value1, String value2) {
            addCriterion("session_focus_1 between", value1, value2, "sessionFocus1");
            return (Criteria) this;
        }

        public Criteria andSessionFocus1NotBetween(String value1, String value2) {
            addCriterion("session_focus_1 not between", value1, value2, "sessionFocus1");
            return (Criteria) this;
        }

        public Criteria andSessionFocus2IsNull() {
            addCriterion("session_focus_2 is null");
            return (Criteria) this;
        }

        public Criteria andSessionFocus2IsNotNull() {
            addCriterion("session_focus_2 is not null");
            return (Criteria) this;
        }

        public Criteria andSessionFocus2EqualTo(String value) {
            addCriterion("session_focus_2 =", value, "sessionFocus2");
            return (Criteria) this;
        }

        public Criteria andSessionFocus2NotEqualTo(String value) {
            addCriterion("session_focus_2 <>", value, "sessionFocus2");
            return (Criteria) this;
        }

        public Criteria andSessionFocus2GreaterThan(String value) {
            addCriterion("session_focus_2 >", value, "sessionFocus2");
            return (Criteria) this;
        }

        public Criteria andSessionFocus2GreaterThanOrEqualTo(String value) {
            addCriterion("session_focus_2 >=", value, "sessionFocus2");
            return (Criteria) this;
        }

        public Criteria andSessionFocus2LessThan(String value) {
            addCriterion("session_focus_2 <", value, "sessionFocus2");
            return (Criteria) this;
        }

        public Criteria andSessionFocus2LessThanOrEqualTo(String value) {
            addCriterion("session_focus_2 <=", value, "sessionFocus2");
            return (Criteria) this;
        }

        public Criteria andSessionFocus2Like(String value) {
            addCriterion("session_focus_2 like", value, "sessionFocus2");
            return (Criteria) this;
        }

        public Criteria andSessionFocus2NotLike(String value) {
            addCriterion("session_focus_2 not like", value, "sessionFocus2");
            return (Criteria) this;
        }

        public Criteria andSessionFocus2In(List<String> values) {
            addCriterion("session_focus_2 in", values, "sessionFocus2");
            return (Criteria) this;
        }

        public Criteria andSessionFocus2NotIn(List<String> values) {
            addCriterion("session_focus_2 not in", values, "sessionFocus2");
            return (Criteria) this;
        }

        public Criteria andSessionFocus2Between(String value1, String value2) {
            addCriterion("session_focus_2 between", value1, value2, "sessionFocus2");
            return (Criteria) this;
        }

        public Criteria andSessionFocus2NotBetween(String value1, String value2) {
            addCriterion("session_focus_2 not between", value1, value2, "sessionFocus2");
            return (Criteria) this;
        }

        public Criteria andSentAtIsNull() {
            addCriterion("sent_at is null");
            return (Criteria) this;
        }

        public Criteria andSentAtIsNotNull() {
            addCriterion("sent_at is not null");
            return (Criteria) this;
        }

        public Criteria andSentAtEqualTo(Date value) {
            addCriterion("sent_at =", value, "sentAt");
            return (Criteria) this;
        }

        public Criteria andSentAtNotEqualTo(Date value) {
            addCriterion("sent_at <>", value, "sentAt");
            return (Criteria) this;
        }

        public Criteria andSentAtGreaterThan(Date value) {
            addCriterion("sent_at >", value, "sentAt");
            return (Criteria) this;
        }

        public Criteria andSentAtGreaterThanOrEqualTo(Date value) {
            addCriterion("sent_at >=", value, "sentAt");
            return (Criteria) this;
        }

        public Criteria andSentAtLessThan(Date value) {
            addCriterion("sent_at <", value, "sentAt");
            return (Criteria) this;
        }

        public Criteria andSentAtLessThanOrEqualTo(Date value) {
            addCriterion("sent_at <=", value, "sentAt");
            return (Criteria) this;
        }

        public Criteria andSentAtIn(List<Date> values) {
            addCriterion("sent_at in", values, "sentAt");
            return (Criteria) this;
        }

        public Criteria andSentAtNotIn(List<Date> values) {
            addCriterion("sent_at not in", values, "sentAt");
            return (Criteria) this;
        }

        public Criteria andSentAtBetween(Date value1, Date value2) {
            addCriterion("sent_at between", value1, value2, "sentAt");
            return (Criteria) this;
        }

        public Criteria andSentAtNotBetween(Date value1, Date value2) {
            addCriterion("sent_at not between", value1, value2, "sentAt");
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