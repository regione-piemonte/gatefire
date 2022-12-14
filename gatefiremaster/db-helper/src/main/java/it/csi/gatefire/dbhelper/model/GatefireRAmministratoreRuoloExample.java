/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GatefireRAmministratoreRuoloExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
	 * @mbg.generated  Tue Sep 29 15:05:27 CEST 2020
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
	 * @mbg.generated  Tue Sep 29 15:05:27 CEST 2020
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
	 * @mbg.generated  Tue Sep 29 15:05:27 CEST 2020
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
	 * @mbg.generated  Tue Sep 29 15:05:27 CEST 2020
	 */
	public GatefireRAmministratoreRuoloExample() {
		oredCriteria = new ArrayList<>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
	 * @mbg.generated  Tue Sep 29 15:05:27 CEST 2020
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
	 * @mbg.generated  Tue Sep 29 15:05:27 CEST 2020
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
	 * @mbg.generated  Tue Sep 29 15:05:27 CEST 2020
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
	 * @mbg.generated  Tue Sep 29 15:05:27 CEST 2020
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
	 * @mbg.generated  Tue Sep 29 15:05:27 CEST 2020
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
	 * @mbg.generated  Tue Sep 29 15:05:27 CEST 2020
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
	 * @mbg.generated  Tue Sep 29 15:05:27 CEST 2020
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
	 * @mbg.generated  Tue Sep 29 15:05:27 CEST 2020
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
	 * @mbg.generated  Tue Sep 29 15:05:27 CEST 2020
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
	 * @mbg.generated  Tue Sep 29 15:05:27 CEST 2020
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
	 * @mbg.generated  Tue Sep 29 15:05:27 CEST 2020
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

		public Criteria andIdAmministratoreIsNull() {
			addCriterion("id_amministratore is null");
			return (Criteria) this;
		}

		public Criteria andIdAmministratoreIsNotNull() {
			addCriterion("id_amministratore is not null");
			return (Criteria) this;
		}

		public Criteria andIdAmministratoreEqualTo(Integer value) {
			addCriterion("id_amministratore =", value, "idAmministratore");
			return (Criteria) this;
		}

		public Criteria andIdAmministratoreNotEqualTo(Integer value) {
			addCriterion("id_amministratore <>", value, "idAmministratore");
			return (Criteria) this;
		}

		public Criteria andIdAmministratoreGreaterThan(Integer value) {
			addCriterion("id_amministratore >", value, "idAmministratore");
			return (Criteria) this;
		}

		public Criteria andIdAmministratoreGreaterThanOrEqualTo(Integer value) {
			addCriterion("id_amministratore >=", value, "idAmministratore");
			return (Criteria) this;
		}

		public Criteria andIdAmministratoreLessThan(Integer value) {
			addCriterion("id_amministratore <", value, "idAmministratore");
			return (Criteria) this;
		}

		public Criteria andIdAmministratoreLessThanOrEqualTo(Integer value) {
			addCriterion("id_amministratore <=", value, "idAmministratore");
			return (Criteria) this;
		}

		public Criteria andIdAmministratoreIn(List<Integer> values) {
			addCriterion("id_amministratore in", values, "idAmministratore");
			return (Criteria) this;
		}

		public Criteria andIdAmministratoreNotIn(List<Integer> values) {
			addCriterion("id_amministratore not in", values, "idAmministratore");
			return (Criteria) this;
		}

		public Criteria andIdAmministratoreBetween(Integer value1, Integer value2) {
			addCriterion("id_amministratore between", value1, value2, "idAmministratore");
			return (Criteria) this;
		}

		public Criteria andIdAmministratoreNotBetween(Integer value1, Integer value2) {
			addCriterion("id_amministratore not between", value1, value2, "idAmministratore");
			return (Criteria) this;
		}

		public Criteria andCodiceRuoloIsNull() {
			addCriterion("codice_ruolo is null");
			return (Criteria) this;
		}

		public Criteria andCodiceRuoloIsNotNull() {
			addCriterion("codice_ruolo is not null");
			return (Criteria) this;
		}

		public Criteria andCodiceRuoloEqualTo(String value) {
			addCriterion("codice_ruolo =", value, "codiceRuolo");
			return (Criteria) this;
		}

		public Criteria andCodiceRuoloNotEqualTo(String value) {
			addCriterion("codice_ruolo <>", value, "codiceRuolo");
			return (Criteria) this;
		}

		public Criteria andCodiceRuoloGreaterThan(String value) {
			addCriterion("codice_ruolo >", value, "codiceRuolo");
			return (Criteria) this;
		}

		public Criteria andCodiceRuoloGreaterThanOrEqualTo(String value) {
			addCriterion("codice_ruolo >=", value, "codiceRuolo");
			return (Criteria) this;
		}

		public Criteria andCodiceRuoloLessThan(String value) {
			addCriterion("codice_ruolo <", value, "codiceRuolo");
			return (Criteria) this;
		}

		public Criteria andCodiceRuoloLessThanOrEqualTo(String value) {
			addCriterion("codice_ruolo <=", value, "codiceRuolo");
			return (Criteria) this;
		}

		public Criteria andCodiceRuoloLike(String value) {
			addCriterion("codice_ruolo like", value, "codiceRuolo");
			return (Criteria) this;
		}

		public Criteria andCodiceRuoloNotLike(String value) {
			addCriterion("codice_ruolo not like", value, "codiceRuolo");
			return (Criteria) this;
		}

		public Criteria andCodiceRuoloIn(List<String> values) {
			addCriterion("codice_ruolo in", values, "codiceRuolo");
			return (Criteria) this;
		}

		public Criteria andCodiceRuoloNotIn(List<String> values) {
			addCriterion("codice_ruolo not in", values, "codiceRuolo");
			return (Criteria) this;
		}

		public Criteria andCodiceRuoloBetween(String value1, String value2) {
			addCriterion("codice_ruolo between", value1, value2, "codiceRuolo");
			return (Criteria) this;
		}

		public Criteria andCodiceRuoloNotBetween(String value1, String value2) {
			addCriterion("codice_ruolo not between", value1, value2, "codiceRuolo");
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

		public Criteria andCodiceRuoloLikeInsensitive(String value) {
			addCriterion("upper(codice_ruolo) like", value.toUpperCase(), "codiceRuolo");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
	 * @mbg.generated  Tue Sep 29 15:05:27 CEST 2020
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

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table gatefire.gatefire_r_amministratore_ruolo
     *
     * @mbg.generated do_not_delete_during_merge Tue Sep 29 15:05:27 CEST 2020
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }
}