package it.csi.gatefire.dbhelper.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GatefireRRepositoryFunzioneExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table gatefire.gatefire_r_repository_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table gatefire.gatefire_r_repository_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table gatefire.gatefire_r_repository_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_repository_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public GatefireRRepositoryFunzioneExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_repository_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_repository_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_repository_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_repository_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_repository_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_repository_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_repository_funzione
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
     * This method corresponds to the database table gatefire.gatefire_r_repository_funzione
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
     * This method corresponds to the database table gatefire.gatefire_r_repository_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_repository_funzione
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
     * This class corresponds to the database table gatefire.gatefire_r_repository_funzione
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCodiceRepositoryIsNull() {
            addCriterion("codice_repository is null");
            return (Criteria) this;
        }

        public Criteria andCodiceRepositoryIsNotNull() {
            addCriterion("codice_repository is not null");
            return (Criteria) this;
        }

        public Criteria andCodiceRepositoryEqualTo(String value) {
            addCriterion("codice_repository =", value, "codiceRepository");
            return (Criteria) this;
        }

        public Criteria andCodiceRepositoryNotEqualTo(String value) {
            addCriterion("codice_repository <>", value, "codiceRepository");
            return (Criteria) this;
        }

        public Criteria andCodiceRepositoryGreaterThan(String value) {
            addCriterion("codice_repository >", value, "codiceRepository");
            return (Criteria) this;
        }

        public Criteria andCodiceRepositoryGreaterThanOrEqualTo(String value) {
            addCriterion("codice_repository >=", value, "codiceRepository");
            return (Criteria) this;
        }

        public Criteria andCodiceRepositoryLessThan(String value) {
            addCriterion("codice_repository <", value, "codiceRepository");
            return (Criteria) this;
        }

        public Criteria andCodiceRepositoryLessThanOrEqualTo(String value) {
            addCriterion("codice_repository <=", value, "codiceRepository");
            return (Criteria) this;
        }

        public Criteria andCodiceRepositoryLike(String value) {
            addCriterion("codice_repository like", value, "codiceRepository");
            return (Criteria) this;
        }

        public Criteria andCodiceRepositoryNotLike(String value) {
            addCriterion("codice_repository not like", value, "codiceRepository");
            return (Criteria) this;
        }

        public Criteria andCodiceRepositoryIn(List<String> values) {
            addCriterion("codice_repository in", values, "codiceRepository");
            return (Criteria) this;
        }

        public Criteria andCodiceRepositoryNotIn(List<String> values) {
            addCriterion("codice_repository not in", values, "codiceRepository");
            return (Criteria) this;
        }

        public Criteria andCodiceRepositoryBetween(String value1, String value2) {
            addCriterion("codice_repository between", value1, value2, "codiceRepository");
            return (Criteria) this;
        }

        public Criteria andCodiceRepositoryNotBetween(String value1, String value2) {
            addCriterion("codice_repository not between", value1, value2, "codiceRepository");
            return (Criteria) this;
        }

        public Criteria andCodiceFunzioneIsNull() {
            addCriterion("codice_funzione is null");
            return (Criteria) this;
        }

        public Criteria andCodiceFunzioneIsNotNull() {
            addCriterion("codice_funzione is not null");
            return (Criteria) this;
        }

        public Criteria andCodiceFunzioneEqualTo(String value) {
            addCriterion("codice_funzione =", value, "codiceFunzione");
            return (Criteria) this;
        }

        public Criteria andCodiceFunzioneNotEqualTo(String value) {
            addCriterion("codice_funzione <>", value, "codiceFunzione");
            return (Criteria) this;
        }

        public Criteria andCodiceFunzioneGreaterThan(String value) {
            addCriterion("codice_funzione >", value, "codiceFunzione");
            return (Criteria) this;
        }

        public Criteria andCodiceFunzioneGreaterThanOrEqualTo(String value) {
            addCriterion("codice_funzione >=", value, "codiceFunzione");
            return (Criteria) this;
        }

        public Criteria andCodiceFunzioneLessThan(String value) {
            addCriterion("codice_funzione <", value, "codiceFunzione");
            return (Criteria) this;
        }

        public Criteria andCodiceFunzioneLessThanOrEqualTo(String value) {
            addCriterion("codice_funzione <=", value, "codiceFunzione");
            return (Criteria) this;
        }

        public Criteria andCodiceFunzioneLike(String value) {
            addCriterion("codice_funzione like", value, "codiceFunzione");
            return (Criteria) this;
        }

        public Criteria andCodiceFunzioneNotLike(String value) {
            addCriterion("codice_funzione not like", value, "codiceFunzione");
            return (Criteria) this;
        }

        public Criteria andCodiceFunzioneIn(List<String> values) {
            addCriterion("codice_funzione in", values, "codiceFunzione");
            return (Criteria) this;
        }

        public Criteria andCodiceFunzioneNotIn(List<String> values) {
            addCriterion("codice_funzione not in", values, "codiceFunzione");
            return (Criteria) this;
        }

        public Criteria andCodiceFunzioneBetween(String value1, String value2) {
            addCriterion("codice_funzione between", value1, value2, "codiceFunzione");
            return (Criteria) this;
        }

        public Criteria andCodiceFunzioneNotBetween(String value1, String value2) {
            addCriterion("codice_funzione not between", value1, value2, "codiceFunzione");
            return (Criteria) this;
        }

        public Criteria andDataInizioValiditaIsNull() {
            addCriterion("data_inizio_validita is null");
            return (Criteria) this;
        }

        public Criteria andDataInizioValiditaIsNotNull() {
            addCriterion("data_inizio_validita is not null");
            return (Criteria) this;
        }

        public Criteria andDataInizioValiditaEqualTo(Date value) {
            addCriterion("data_inizio_validita =", value, "dataInizioValidita");
            return (Criteria) this;
        }

        public Criteria andDataInizioValiditaNotEqualTo(Date value) {
            addCriterion("data_inizio_validita <>", value, "dataInizioValidita");
            return (Criteria) this;
        }

        public Criteria andDataInizioValiditaGreaterThan(Date value) {
            addCriterion("data_inizio_validita >", value, "dataInizioValidita");
            return (Criteria) this;
        }

        public Criteria andDataInizioValiditaGreaterThanOrEqualTo(Date value) {
            addCriterion("data_inizio_validita >=", value, "dataInizioValidita");
            return (Criteria) this;
        }

        public Criteria andDataInizioValiditaLessThan(Date value) {
            addCriterion("data_inizio_validita <", value, "dataInizioValidita");
            return (Criteria) this;
        }

        public Criteria andDataInizioValiditaLessThanOrEqualTo(Date value) {
            addCriterion("data_inizio_validita <=", value, "dataInizioValidita");
            return (Criteria) this;
        }

        public Criteria andDataInizioValiditaIn(List<Date> values) {
            addCriterion("data_inizio_validita in", values, "dataInizioValidita");
            return (Criteria) this;
        }

        public Criteria andDataInizioValiditaNotIn(List<Date> values) {
            addCriterion("data_inizio_validita not in", values, "dataInizioValidita");
            return (Criteria) this;
        }

        public Criteria andDataInizioValiditaBetween(Date value1, Date value2) {
            addCriterion("data_inizio_validita between", value1, value2, "dataInizioValidita");
            return (Criteria) this;
        }

        public Criteria andDataInizioValiditaNotBetween(Date value1, Date value2) {
            addCriterion("data_inizio_validita not between", value1, value2, "dataInizioValidita");
            return (Criteria) this;
        }

        public Criteria andDataFineValiditaIsNull() {
            addCriterion("data_fine_validita is null");
            return (Criteria) this;
        }

        public Criteria andDataFineValiditaIsNotNull() {
            addCriterion("data_fine_validita is not null");
            return (Criteria) this;
        }

        public Criteria andDataFineValiditaEqualTo(Date value) {
            addCriterion("data_fine_validita =", value, "dataFineValidita");
            return (Criteria) this;
        }

        public Criteria andDataFineValiditaNotEqualTo(Date value) {
            addCriterion("data_fine_validita <>", value, "dataFineValidita");
            return (Criteria) this;
        }

        public Criteria andDataFineValiditaGreaterThan(Date value) {
            addCriterion("data_fine_validita >", value, "dataFineValidita");
            return (Criteria) this;
        }

        public Criteria andDataFineValiditaGreaterThanOrEqualTo(Date value) {
            addCriterion("data_fine_validita >=", value, "dataFineValidita");
            return (Criteria) this;
        }

        public Criteria andDataFineValiditaLessThan(Date value) {
            addCriterion("data_fine_validita <", value, "dataFineValidita");
            return (Criteria) this;
        }

        public Criteria andDataFineValiditaLessThanOrEqualTo(Date value) {
            addCriterion("data_fine_validita <=", value, "dataFineValidita");
            return (Criteria) this;
        }

        public Criteria andDataFineValiditaIn(List<Date> values) {
            addCriterion("data_fine_validita in", values, "dataFineValidita");
            return (Criteria) this;
        }

        public Criteria andDataFineValiditaNotIn(List<Date> values) {
            addCriterion("data_fine_validita not in", values, "dataFineValidita");
            return (Criteria) this;
        }

        public Criteria andDataFineValiditaBetween(Date value1, Date value2) {
            addCriterion("data_fine_validita between", value1, value2, "dataFineValidita");
            return (Criteria) this;
        }

        public Criteria andDataFineValiditaNotBetween(Date value1, Date value2) {
            addCriterion("data_fine_validita not between", value1, value2, "dataFineValidita");
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

        public Criteria andCodiceRepositoryLikeInsensitive(String value) {
            addCriterion("upper(codice_repository) like", value.toUpperCase(), "codiceRepository");
            return (Criteria) this;
        }

        public Criteria andCodiceFunzioneLikeInsensitive(String value) {
            addCriterion("upper(codice_funzione) like", value.toUpperCase(), "codiceFunzione");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table gatefire.gatefire_r_repository_funzione
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
     * This class corresponds to the database table gatefire.gatefire_r_repository_funzione
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