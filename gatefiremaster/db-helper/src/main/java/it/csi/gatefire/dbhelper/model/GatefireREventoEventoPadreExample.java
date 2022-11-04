/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GatefireREventoEventoPadreExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public GatefireREventoEventoPadreExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
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
     * This method corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
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

        public Criteria andIEventoIsNull() {
            addCriterion("i_evento is null");
            return (Criteria) this;
        }

        public Criteria andIEventoIsNotNull() {
            addCriterion("i_evento is not null");
            return (Criteria) this;
        }

        public Criteria andIEventoEqualTo(Long value) {
            addCriterion("i_evento =", value, "iEvento");
            return (Criteria) this;
        }

        public Criteria andIEventoNotEqualTo(Long value) {
            addCriterion("i_evento <>", value, "iEvento");
            return (Criteria) this;
        }

        public Criteria andIEventoGreaterThan(Long value) {
            addCriterion("i_evento >", value, "iEvento");
            return (Criteria) this;
        }

        public Criteria andIEventoGreaterThanOrEqualTo(Long value) {
            addCriterion("i_evento >=", value, "iEvento");
            return (Criteria) this;
        }

        public Criteria andIEventoLessThan(Long value) {
            addCriterion("i_evento <", value, "iEvento");
            return (Criteria) this;
        }

        public Criteria andIEventoLessThanOrEqualTo(Long value) {
            addCriterion("i_evento <=", value, "iEvento");
            return (Criteria) this;
        }

        public Criteria andIEventoIn(List<Long> values) {
            addCriterion("i_evento in", values, "iEvento");
            return (Criteria) this;
        }

        public Criteria andIEventoNotIn(List<Long> values) {
            addCriterion("i_evento not in", values, "iEvento");
            return (Criteria) this;
        }

        public Criteria andIEventoBetween(Long value1, Long value2) {
            addCriterion("i_evento between", value1, value2, "iEvento");
            return (Criteria) this;
        }

        public Criteria andIEventoNotBetween(Long value1, Long value2) {
            addCriterion("i_evento not between", value1, value2, "iEvento");
            return (Criteria) this;
        }

        public Criteria andIdEventoPadreIsNull() {
            addCriterion("id_evento_padre is null");
            return (Criteria) this;
        }

        public Criteria andIdEventoPadreIsNotNull() {
            addCriterion("id_evento_padre is not null");
            return (Criteria) this;
        }

        public Criteria andIdEventoPadreEqualTo(Long value) {
            addCriterion("id_evento_padre =", value, "idEventoPadre");
            return (Criteria) this;
        }

        public Criteria andIdEventoPadreNotEqualTo(Long value) {
            addCriterion("id_evento_padre <>", value, "idEventoPadre");
            return (Criteria) this;
        }

        public Criteria andIdEventoPadreGreaterThan(Long value) {
            addCriterion("id_evento_padre >", value, "idEventoPadre");
            return (Criteria) this;
        }

        public Criteria andIdEventoPadreGreaterThanOrEqualTo(Long value) {
            addCriterion("id_evento_padre >=", value, "idEventoPadre");
            return (Criteria) this;
        }

        public Criteria andIdEventoPadreLessThan(Long value) {
            addCriterion("id_evento_padre <", value, "idEventoPadre");
            return (Criteria) this;
        }

        public Criteria andIdEventoPadreLessThanOrEqualTo(Long value) {
            addCriterion("id_evento_padre <=", value, "idEventoPadre");
            return (Criteria) this;
        }

        public Criteria andIdEventoPadreIn(List<Long> values) {
            addCriterion("id_evento_padre in", values, "idEventoPadre");
            return (Criteria) this;
        }

        public Criteria andIdEventoPadreNotIn(List<Long> values) {
            addCriterion("id_evento_padre not in", values, "idEventoPadre");
            return (Criteria) this;
        }

        public Criteria andIdEventoPadreBetween(Long value1, Long value2) {
            addCriterion("id_evento_padre between", value1, value2, "idEventoPadre");
            return (Criteria) this;
        }

        public Criteria andIdEventoPadreNotBetween(Long value1, Long value2) {
            addCriterion("id_evento_padre not between", value1, value2, "idEventoPadre");
            return (Criteria) this;
        }

        public Criteria andDataCreazioneIsNull() {
            addCriterion("data_creazione is null");
            return (Criteria) this;
        }

        public Criteria andDataCreazioneIsNotNull() {
            addCriterion("data_creazione is not null");
            return (Criteria) this;
        }

        public Criteria andDataCreazioneEqualTo(Date value) {
            addCriterion("data_creazione =", value, "dataCreazione");
            return (Criteria) this;
        }

        public Criteria andDataCreazioneNotEqualTo(Date value) {
            addCriterion("data_creazione <>", value, "dataCreazione");
            return (Criteria) this;
        }

        public Criteria andDataCreazioneGreaterThan(Date value) {
            addCriterion("data_creazione >", value, "dataCreazione");
            return (Criteria) this;
        }

        public Criteria andDataCreazioneGreaterThanOrEqualTo(Date value) {
            addCriterion("data_creazione >=", value, "dataCreazione");
            return (Criteria) this;
        }

        public Criteria andDataCreazioneLessThan(Date value) {
            addCriterion("data_creazione <", value, "dataCreazione");
            return (Criteria) this;
        }

        public Criteria andDataCreazioneLessThanOrEqualTo(Date value) {
            addCriterion("data_creazione <=", value, "dataCreazione");
            return (Criteria) this;
        }

        public Criteria andDataCreazioneIn(List<Date> values) {
            addCriterion("data_creazione in", values, "dataCreazione");
            return (Criteria) this;
        }

        public Criteria andDataCreazioneNotIn(List<Date> values) {
            addCriterion("data_creazione not in", values, "dataCreazione");
            return (Criteria) this;
        }

        public Criteria andDataCreazioneBetween(Date value1, Date value2) {
            addCriterion("data_creazione between", value1, value2, "dataCreazione");
            return (Criteria) this;
        }

        public Criteria andDataCreazioneNotBetween(Date value1, Date value2) {
            addCriterion("data_creazione not between", value1, value2, "dataCreazione");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoIsNull() {
            addCriterion("data_aggiornamento is null");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoIsNotNull() {
            addCriterion("data_aggiornamento is not null");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoEqualTo(Date value) {
            addCriterion("data_aggiornamento =", value, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoNotEqualTo(Date value) {
            addCriterion("data_aggiornamento <>", value, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoGreaterThan(Date value) {
            addCriterion("data_aggiornamento >", value, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoGreaterThanOrEqualTo(Date value) {
            addCriterion("data_aggiornamento >=", value, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoLessThan(Date value) {
            addCriterion("data_aggiornamento <", value, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoLessThanOrEqualTo(Date value) {
            addCriterion("data_aggiornamento <=", value, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoIn(List<Date> values) {
            addCriterion("data_aggiornamento in", values, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoNotIn(List<Date> values) {
            addCriterion("data_aggiornamento not in", values, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoBetween(Date value1, Date value2) {
            addCriterion("data_aggiornamento between", value1, value2, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoNotBetween(Date value1, Date value2) {
            addCriterion("data_aggiornamento not between", value1, value2, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataCancellazioneIsNull() {
            addCriterion("data_cancellazione is null");
            return (Criteria) this;
        }

        public Criteria andDataCancellazioneIsNotNull() {
            addCriterion("data_cancellazione is not null");
            return (Criteria) this;
        }

        public Criteria andDataCancellazioneEqualTo(Date value) {
            addCriterion("data_cancellazione =", value, "dataCancellazione");
            return (Criteria) this;
        }

        public Criteria andDataCancellazioneNotEqualTo(Date value) {
            addCriterion("data_cancellazione <>", value, "dataCancellazione");
            return (Criteria) this;
        }

        public Criteria andDataCancellazioneGreaterThan(Date value) {
            addCriterion("data_cancellazione >", value, "dataCancellazione");
            return (Criteria) this;
        }

        public Criteria andDataCancellazioneGreaterThanOrEqualTo(Date value) {
            addCriterion("data_cancellazione >=", value, "dataCancellazione");
            return (Criteria) this;
        }

        public Criteria andDataCancellazioneLessThan(Date value) {
            addCriterion("data_cancellazione <", value, "dataCancellazione");
            return (Criteria) this;
        }

        public Criteria andDataCancellazioneLessThanOrEqualTo(Date value) {
            addCriterion("data_cancellazione <=", value, "dataCancellazione");
            return (Criteria) this;
        }

        public Criteria andDataCancellazioneIn(List<Date> values) {
            addCriterion("data_cancellazione in", values, "dataCancellazione");
            return (Criteria) this;
        }

        public Criteria andDataCancellazioneNotIn(List<Date> values) {
            addCriterion("data_cancellazione not in", values, "dataCancellazione");
            return (Criteria) this;
        }

        public Criteria andDataCancellazioneBetween(Date value1, Date value2) {
            addCriterion("data_cancellazione between", value1, value2, "dataCancellazione");
            return (Criteria) this;
        }

        public Criteria andDataCancellazioneNotBetween(Date value1, Date value2) {
            addCriterion("data_cancellazione not between", value1, value2, "dataCancellazione");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated do_not_delete_during_merge Wed Nov 25 11:01:29 CET 2020
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table gatefire.gatefire_r_evento_evento_padre
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
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