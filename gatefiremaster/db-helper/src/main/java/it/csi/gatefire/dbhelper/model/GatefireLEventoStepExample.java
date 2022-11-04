/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GatefireLEventoStepExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	public GatefireLEventoStepExample() {
		oredCriteria = new ArrayList<>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
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

		public Criteria andIdEventoIsNull() {
			addCriterion("id_evento is null");
			return (Criteria) this;
		}

		public Criteria andIdEventoIsNotNull() {
			addCriterion("id_evento is not null");
			return (Criteria) this;
		}

		public Criteria andIdEventoEqualTo(Long value) {
			addCriterion("id_evento =", value, "idEvento");
			return (Criteria) this;
		}

		public Criteria andIdEventoNotEqualTo(Long value) {
			addCriterion("id_evento <>", value, "idEvento");
			return (Criteria) this;
		}

		public Criteria andIdEventoGreaterThan(Long value) {
			addCriterion("id_evento >", value, "idEvento");
			return (Criteria) this;
		}

		public Criteria andIdEventoGreaterThanOrEqualTo(Long value) {
			addCriterion("id_evento >=", value, "idEvento");
			return (Criteria) this;
		}

		public Criteria andIdEventoLessThan(Long value) {
			addCriterion("id_evento <", value, "idEvento");
			return (Criteria) this;
		}

		public Criteria andIdEventoLessThanOrEqualTo(Long value) {
			addCriterion("id_evento <=", value, "idEvento");
			return (Criteria) this;
		}

		public Criteria andIdEventoIn(List<Long> values) {
			addCriterion("id_evento in", values, "idEvento");
			return (Criteria) this;
		}

		public Criteria andIdEventoNotIn(List<Long> values) {
			addCriterion("id_evento not in", values, "idEvento");
			return (Criteria) this;
		}

		public Criteria andIdEventoBetween(Long value1, Long value2) {
			addCriterion("id_evento between", value1, value2, "idEvento");
			return (Criteria) this;
		}

		public Criteria andIdEventoNotBetween(Long value1, Long value2) {
			addCriterion("id_evento not between", value1, value2, "idEvento");
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

		public Criteria andDescrizioneIsNull() {
			addCriterion("descrizione is null");
			return (Criteria) this;
		}

		public Criteria andDescrizioneIsNotNull() {
			addCriterion("descrizione is not null");
			return (Criteria) this;
		}

		public Criteria andDescrizioneEqualTo(String value) {
			addCriterion("descrizione =", value, "descrizione");
			return (Criteria) this;
		}

		public Criteria andDescrizioneNotEqualTo(String value) {
			addCriterion("descrizione <>", value, "descrizione");
			return (Criteria) this;
		}

		public Criteria andDescrizioneGreaterThan(String value) {
			addCriterion("descrizione >", value, "descrizione");
			return (Criteria) this;
		}

		public Criteria andDescrizioneGreaterThanOrEqualTo(String value) {
			addCriterion("descrizione >=", value, "descrizione");
			return (Criteria) this;
		}

		public Criteria andDescrizioneLessThan(String value) {
			addCriterion("descrizione <", value, "descrizione");
			return (Criteria) this;
		}

		public Criteria andDescrizioneLessThanOrEqualTo(String value) {
			addCriterion("descrizione <=", value, "descrizione");
			return (Criteria) this;
		}

		public Criteria andDescrizioneLike(String value) {
			addCriterion("descrizione like", value, "descrizione");
			return (Criteria) this;
		}

		public Criteria andDescrizioneNotLike(String value) {
			addCriterion("descrizione not like", value, "descrizione");
			return (Criteria) this;
		}

		public Criteria andDescrizioneIn(List<String> values) {
			addCriterion("descrizione in", values, "descrizione");
			return (Criteria) this;
		}

		public Criteria andDescrizioneNotIn(List<String> values) {
			addCriterion("descrizione not in", values, "descrizione");
			return (Criteria) this;
		}

		public Criteria andDescrizioneBetween(String value1, String value2) {
			addCriterion("descrizione between", value1, value2, "descrizione");
			return (Criteria) this;
		}

		public Criteria andDescrizioneNotBetween(String value1, String value2) {
			addCriterion("descrizione not between", value1, value2, "descrizione");
			return (Criteria) this;
		}

		public Criteria andReturnCodeIsNull() {
			addCriterion("return_code is null");
			return (Criteria) this;
		}

		public Criteria andReturnCodeIsNotNull() {
			addCriterion("return_code is not null");
			return (Criteria) this;
		}

		public Criteria andReturnCodeEqualTo(String value) {
			addCriterion("return_code =", value, "returnCode");
			return (Criteria) this;
		}

		public Criteria andReturnCodeNotEqualTo(String value) {
			addCriterion("return_code <>", value, "returnCode");
			return (Criteria) this;
		}

		public Criteria andReturnCodeGreaterThan(String value) {
			addCriterion("return_code >", value, "returnCode");
			return (Criteria) this;
		}

		public Criteria andReturnCodeGreaterThanOrEqualTo(String value) {
			addCriterion("return_code >=", value, "returnCode");
			return (Criteria) this;
		}

		public Criteria andReturnCodeLessThan(String value) {
			addCriterion("return_code <", value, "returnCode");
			return (Criteria) this;
		}

		public Criteria andReturnCodeLessThanOrEqualTo(String value) {
			addCriterion("return_code <=", value, "returnCode");
			return (Criteria) this;
		}

		public Criteria andReturnCodeLike(String value) {
			addCriterion("return_code like", value, "returnCode");
			return (Criteria) this;
		}

		public Criteria andReturnCodeNotLike(String value) {
			addCriterion("return_code not like", value, "returnCode");
			return (Criteria) this;
		}

		public Criteria andReturnCodeIn(List<String> values) {
			addCriterion("return_code in", values, "returnCode");
			return (Criteria) this;
		}

		public Criteria andReturnCodeNotIn(List<String> values) {
			addCriterion("return_code not in", values, "returnCode");
			return (Criteria) this;
		}

		public Criteria andReturnCodeBetween(String value1, String value2) {
			addCriterion("return_code between", value1, value2, "returnCode");
			return (Criteria) this;
		}

		public Criteria andReturnCodeNotBetween(String value1, String value2) {
			addCriterion("return_code not between", value1, value2, "returnCode");
			return (Criteria) this;
		}

		public Criteria andReturnCodeDescrizioneIsNull() {
			addCriterion("return_code_descrizione is null");
			return (Criteria) this;
		}

		public Criteria andReturnCodeDescrizioneIsNotNull() {
			addCriterion("return_code_descrizione is not null");
			return (Criteria) this;
		}

		public Criteria andReturnCodeDescrizioneEqualTo(String value) {
			addCriterion("return_code_descrizione =", value, "returnCodeDescrizione");
			return (Criteria) this;
		}

		public Criteria andReturnCodeDescrizioneNotEqualTo(String value) {
			addCriterion("return_code_descrizione <>", value, "returnCodeDescrizione");
			return (Criteria) this;
		}

		public Criteria andReturnCodeDescrizioneGreaterThan(String value) {
			addCriterion("return_code_descrizione >", value, "returnCodeDescrizione");
			return (Criteria) this;
		}

		public Criteria andReturnCodeDescrizioneGreaterThanOrEqualTo(String value) {
			addCriterion("return_code_descrizione >=", value, "returnCodeDescrizione");
			return (Criteria) this;
		}

		public Criteria andReturnCodeDescrizioneLessThan(String value) {
			addCriterion("return_code_descrizione <", value, "returnCodeDescrizione");
			return (Criteria) this;
		}

		public Criteria andReturnCodeDescrizioneLessThanOrEqualTo(String value) {
			addCriterion("return_code_descrizione <=", value, "returnCodeDescrizione");
			return (Criteria) this;
		}

		public Criteria andReturnCodeDescrizioneLike(String value) {
			addCriterion("return_code_descrizione like", value, "returnCodeDescrizione");
			return (Criteria) this;
		}

		public Criteria andReturnCodeDescrizioneNotLike(String value) {
			addCriterion("return_code_descrizione not like", value, "returnCodeDescrizione");
			return (Criteria) this;
		}

		public Criteria andReturnCodeDescrizioneIn(List<String> values) {
			addCriterion("return_code_descrizione in", values, "returnCodeDescrizione");
			return (Criteria) this;
		}

		public Criteria andReturnCodeDescrizioneNotIn(List<String> values) {
			addCriterion("return_code_descrizione not in", values, "returnCodeDescrizione");
			return (Criteria) this;
		}

		public Criteria andReturnCodeDescrizioneBetween(String value1, String value2) {
			addCriterion("return_code_descrizione between", value1, value2, "returnCodeDescrizione");
			return (Criteria) this;
		}

		public Criteria andReturnCodeDescrizioneNotBetween(String value1, String value2) {
			addCriterion("return_code_descrizione not between", value1, value2, "returnCodeDescrizione");
			return (Criteria) this;
		}

		public Criteria andCodiceStatoEventoLogIsNull() {
			addCriterion("codice_stato_evento_log is null");
			return (Criteria) this;
		}

		public Criteria andCodiceStatoEventoLogIsNotNull() {
			addCriterion("codice_stato_evento_log is not null");
			return (Criteria) this;
		}

		public Criteria andCodiceStatoEventoLogEqualTo(String value) {
			addCriterion("codice_stato_evento_log =", value, "codiceStatoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceStatoEventoLogNotEqualTo(String value) {
			addCriterion("codice_stato_evento_log <>", value, "codiceStatoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceStatoEventoLogGreaterThan(String value) {
			addCriterion("codice_stato_evento_log >", value, "codiceStatoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceStatoEventoLogGreaterThanOrEqualTo(String value) {
			addCriterion("codice_stato_evento_log >=", value, "codiceStatoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceStatoEventoLogLessThan(String value) {
			addCriterion("codice_stato_evento_log <", value, "codiceStatoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceStatoEventoLogLessThanOrEqualTo(String value) {
			addCriterion("codice_stato_evento_log <=", value, "codiceStatoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceStatoEventoLogLike(String value) {
			addCriterion("codice_stato_evento_log like", value, "codiceStatoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceStatoEventoLogNotLike(String value) {
			addCriterion("codice_stato_evento_log not like", value, "codiceStatoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceStatoEventoLogIn(List<String> values) {
			addCriterion("codice_stato_evento_log in", values, "codiceStatoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceStatoEventoLogNotIn(List<String> values) {
			addCriterion("codice_stato_evento_log not in", values, "codiceStatoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceStatoEventoLogBetween(String value1, String value2) {
			addCriterion("codice_stato_evento_log between", value1, value2, "codiceStatoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceStatoEventoLogNotBetween(String value1, String value2) {
			addCriterion("codice_stato_evento_log not between", value1, value2, "codiceStatoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceTipoPassoEventoLogIsNull() {
			addCriterion("codice_tipo_passo_evento_log is null");
			return (Criteria) this;
		}

		public Criteria andCodiceTipoPassoEventoLogIsNotNull() {
			addCriterion("codice_tipo_passo_evento_log is not null");
			return (Criteria) this;
		}

		public Criteria andCodiceTipoPassoEventoLogEqualTo(String value) {
			addCriterion("codice_tipo_passo_evento_log =", value, "codiceTipoPassoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceTipoPassoEventoLogNotEqualTo(String value) {
			addCriterion("codice_tipo_passo_evento_log <>", value, "codiceTipoPassoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceTipoPassoEventoLogGreaterThan(String value) {
			addCriterion("codice_tipo_passo_evento_log >", value, "codiceTipoPassoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceTipoPassoEventoLogGreaterThanOrEqualTo(String value) {
			addCriterion("codice_tipo_passo_evento_log >=", value, "codiceTipoPassoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceTipoPassoEventoLogLessThan(String value) {
			addCriterion("codice_tipo_passo_evento_log <", value, "codiceTipoPassoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceTipoPassoEventoLogLessThanOrEqualTo(String value) {
			addCriterion("codice_tipo_passo_evento_log <=", value, "codiceTipoPassoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceTipoPassoEventoLogLike(String value) {
			addCriterion("codice_tipo_passo_evento_log like", value, "codiceTipoPassoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceTipoPassoEventoLogNotLike(String value) {
			addCriterion("codice_tipo_passo_evento_log not like", value, "codiceTipoPassoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceTipoPassoEventoLogIn(List<String> values) {
			addCriterion("codice_tipo_passo_evento_log in", values, "codiceTipoPassoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceTipoPassoEventoLogNotIn(List<String> values) {
			addCriterion("codice_tipo_passo_evento_log not in", values, "codiceTipoPassoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceTipoPassoEventoLogBetween(String value1, String value2) {
			addCriterion("codice_tipo_passo_evento_log between", value1, value2, "codiceTipoPassoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceTipoPassoEventoLogNotBetween(String value1, String value2) {
			addCriterion("codice_tipo_passo_evento_log not between", value1, value2, "codiceTipoPassoEventoLog");
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

		public Criteria andDescrizioneLikeInsensitive(String value) {
			addCriterion("upper(descrizione) like", value.toUpperCase(), "descrizione");
			return (Criteria) this;
		}

		public Criteria andReturnCodeLikeInsensitive(String value) {
			addCriterion("upper(return_code) like", value.toUpperCase(), "returnCode");
			return (Criteria) this;
		}

		public Criteria andReturnCodeDescrizioneLikeInsensitive(String value) {
			addCriterion("upper(return_code_descrizione) like", value.toUpperCase(), "returnCodeDescrizione");
			return (Criteria) this;
		}

		public Criteria andCodiceStatoEventoLogLikeInsensitive(String value) {
			addCriterion("upper(codice_stato_evento_log) like", value.toUpperCase(), "codiceStatoEventoLog");
			return (Criteria) this;
		}

		public Criteria andCodiceTipoPassoEventoLogLikeInsensitive(String value) {
			addCriterion("upper(codice_tipo_passo_evento_log) like", value.toUpperCase(), "codiceTipoPassoEventoLog");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
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
     * This class corresponds to the database table gatefire.gatefire_l_evento_step
     *
     * @mbg.generated do_not_delete_during_merge Wed Nov 25 10:47:49 CET 2020
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }
}