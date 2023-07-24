package com.lukefitness.lukegymbackend.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProgramExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProgramExample() {
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

        public Criteria andProgramIdIsNull() {
            addCriterion("program_id is null");
            return (Criteria) this;
        }

        public Criteria andProgramIdIsNotNull() {
            addCriterion("program_id is not null");
            return (Criteria) this;
        }

        public Criteria andProgramIdEqualTo(Integer value) {
            addCriterion("program_id =", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdNotEqualTo(Integer value) {
            addCriterion("program_id <>", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdGreaterThan(Integer value) {
            addCriterion("program_id >", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("program_id >=", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdLessThan(Integer value) {
            addCriterion("program_id <", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdLessThanOrEqualTo(Integer value) {
            addCriterion("program_id <=", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdIn(List<Integer> values) {
            addCriterion("program_id in", values, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdNotIn(List<Integer> values) {
            addCriterion("program_id not in", values, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdBetween(Integer value1, Integer value2) {
            addCriterion("program_id between", value1, value2, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdNotBetween(Integer value1, Integer value2) {
            addCriterion("program_id not between", value1, value2, "programId");
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

        public Criteria andAttendanceIsNull() {
            addCriterion("attendance is null");
            return (Criteria) this;
        }

        public Criteria andAttendanceIsNotNull() {
            addCriterion("attendance is not null");
            return (Criteria) this;
        }

        public Criteria andAttendanceEqualTo(Boolean value) {
            addCriterion("attendance =", value, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceNotEqualTo(Boolean value) {
            addCriterion("attendance <>", value, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceGreaterThan(Boolean value) {
            addCriterion("attendance >", value, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceGreaterThanOrEqualTo(Boolean value) {
            addCriterion("attendance >=", value, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceLessThan(Boolean value) {
            addCriterion("attendance <", value, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceLessThanOrEqualTo(Boolean value) {
            addCriterion("attendance <=", value, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceIn(List<Boolean> values) {
            addCriterion("attendance in", values, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceNotIn(List<Boolean> values) {
            addCriterion("attendance not in", values, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceBetween(Boolean value1, Boolean value2) {
            addCriterion("attendance between", value1, value2, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceNotBetween(Boolean value1, Boolean value2) {
            addCriterion("attendance not between", value1, value2, "attendance");
            return (Criteria) this;
        }

        public Criteria andFeedbackGivenIsNull() {
            addCriterion("feedback_given is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackGivenIsNotNull() {
            addCriterion("feedback_given is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackGivenEqualTo(Boolean value) {
            addCriterion("feedback_given =", value, "feedbackGiven");
            return (Criteria) this;
        }

        public Criteria andFeedbackGivenNotEqualTo(Boolean value) {
            addCriterion("feedback_given <>", value, "feedbackGiven");
            return (Criteria) this;
        }

        public Criteria andFeedbackGivenGreaterThan(Boolean value) {
            addCriterion("feedback_given >", value, "feedbackGiven");
            return (Criteria) this;
        }

        public Criteria andFeedbackGivenGreaterThanOrEqualTo(Boolean value) {
            addCriterion("feedback_given >=", value, "feedbackGiven");
            return (Criteria) this;
        }

        public Criteria andFeedbackGivenLessThan(Boolean value) {
            addCriterion("feedback_given <", value, "feedbackGiven");
            return (Criteria) this;
        }

        public Criteria andFeedbackGivenLessThanOrEqualTo(Boolean value) {
            addCriterion("feedback_given <=", value, "feedbackGiven");
            return (Criteria) this;
        }

        public Criteria andFeedbackGivenIn(List<Boolean> values) {
            addCriterion("feedback_given in", values, "feedbackGiven");
            return (Criteria) this;
        }

        public Criteria andFeedbackGivenNotIn(List<Boolean> values) {
            addCriterion("feedback_given not in", values, "feedbackGiven");
            return (Criteria) this;
        }

        public Criteria andFeedbackGivenBetween(Boolean value1, Boolean value2) {
            addCriterion("feedback_given between", value1, value2, "feedbackGiven");
            return (Criteria) this;
        }

        public Criteria andFeedbackGivenNotBetween(Boolean value1, Boolean value2) {
            addCriterion("feedback_given not between", value1, value2, "feedbackGiven");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
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