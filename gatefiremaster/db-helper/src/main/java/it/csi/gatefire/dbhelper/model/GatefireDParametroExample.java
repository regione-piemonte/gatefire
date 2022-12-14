/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GatefireDParametroExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table gatefire.gatefire_d_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table gatefire.gatefire_d_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table gatefire.gatefire_d_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	public GatefireDParametroExample() {
		oredCriteria = new ArrayList<>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table gatefire.gatefire_d_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
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

		public Criteria andParametroIsNull() {
			addCriterion("parametro is null");
			return (Criteria) this;
		}

		public Criteria andParametroIsNotNull() {
			addCriterion("parametro is not null");
			return (Criteria) this;
		}

		public Criteria andParametroEqualTo(String value) {
			addCriterion("parametro =", value, "parametro");
			return (Criteria) this;
		}

		public Criteria andParametroNotEqualTo(String value) {
			addCriterion("parametro <>", value, "parametro");
			return (Criteria) this;
		}

		public Criteria andParametroGreaterThan(String value) {
			addCriterion("parametro >", value, "parametro");
			return (Criteria) this;
		}

		public Criteria andParametroGreaterThanOrEqualTo(String value) {
			addCriterion("parametro >=", value, "parametro");
			return (Criteria) this;
		}

		public Criteria andParametroLessThan(String value) {
			addCriterion("parametro <", value, "parametro");
			return (Criteria) this;
		}

		public Criteria andParametroLessThanOrEqualTo(String value) {
			addCriterion("parametro <=", value, "parametro");
			return (Criteria) this;
		}

		public Criteria andParametroLike(String value) {
			addCriterion("parametro like", value, "parametro");
			return (Criteria) this;
		}

		public Criteria andParametroNotLike(String value) {
			addCriterion("parametro not like", value, "parametro");
			return (Criteria) this;
		}

		public Criteria andParametroIn(List<String> values) {
			addCriterion("parametro in", values, "parametro");
			return (Criteria) this;
		}

		public Criteria andParametroNotIn(List<String> values) {
			addCriterion("parametro not in", values, "parametro");
			return (Criteria) this;
		}

		public Criteria andParametroBetween(String value1, String value2) {
			addCriterion("parametro between", value1, value2, "parametro");
			return (Criteria) this;
		}

		public Criteria andParametroNotBetween(String value1, String value2) {
			addCriterion("parametro not between", value1, value2, "parametro");
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

		public Criteria andTipoParametroIsNull() {
			addCriterion("tipo_parametro is null");
			return (Criteria) this;
		}

		public Criteria andTipoParametroIsNotNull() {
			addCriterion("tipo_parametro is not null");
			return (Criteria) this;
		}

		public Criteria andTipoParametroEqualTo(String value) {
			addCriterion("tipo_parametro =", value, "tipoParametro");
			return (Criteria) this;
		}

		public Criteria andTipoParametroNotEqualTo(String value) {
			addCriterion("tipo_parametro <>", value, "tipoParametro");
			return (Criteria) this;
		}

		public Criteria andTipoParametroGreaterThan(String value) {
			addCriterion("tipo_parametro >", value, "tipoParametro");
			return (Criteria) this;
		}

		public Criteria andTipoParametroGreaterThanOrEqualTo(String value) {
			addCriterion("tipo_parametro >=", value, "tipoParametro");
			return (Criteria) this;
		}

		public Criteria andTipoParametroLessThan(String value) {
			addCriterion("tipo_parametro <", value, "tipoParametro");
			return (Criteria) this;
		}

		public Criteria andTipoParametroLessThanOrEqualTo(String value) {
			addCriterion("tipo_parametro <=", value, "tipoParametro");
			return (Criteria) this;
		}

		public Criteria andTipoParametroLike(String value) {
			addCriterion("tipo_parametro like", value, "tipoParametro");
			return (Criteria) this;
		}

		public Criteria andTipoParametroNotLike(String value) {
			addCriterion("tipo_parametro not like", value, "tipoParametro");
			return (Criteria) this;
		}

		public Criteria andTipoParametroIn(List<String> values) {
			addCriterion("tipo_parametro in", values, "tipoParametro");
			return (Criteria) this;
		}

		public Criteria andTipoParametroNotIn(List<String> values) {
			addCriterion("tipo_parametro not in", values, "tipoParametro");
			return (Criteria) this;
		}

		public Criteria andTipoParametroBetween(String value1, String value2) {
			addCriterion("tipo_parametro between", value1, value2, "tipoParametro");
			return (Criteria) this;
		}

		public Criteria andTipoParametroNotBetween(String value1, String value2) {
			addCriterion("tipo_parametro not between", value1, value2, "tipoParametro");
			return (Criteria) this;
		}

		public Criteria andUsoParametroIsNull() {
			addCriterion("uso_parametro is null");
			return (Criteria) this;
		}

		public Criteria andUsoParametroIsNotNull() {
			addCriterion("uso_parametro is not null");
			return (Criteria) this;
		}

		public Criteria andUsoParametroEqualTo(String value) {
			addCriterion("uso_parametro =", value, "usoParametro");
			return (Criteria) this;
		}

		public Criteria andUsoParametroNotEqualTo(String value) {
			addCriterion("uso_parametro <>", value, "usoParametro");
			return (Criteria) this;
		}

		public Criteria andUsoParametroGreaterThan(String value) {
			addCriterion("uso_parametro >", value, "usoParametro");
			return (Criteria) this;
		}

		public Criteria andUsoParametroGreaterThanOrEqualTo(String value) {
			addCriterion("uso_parametro >=", value, "usoParametro");
			return (Criteria) this;
		}

		public Criteria andUsoParametroLessThan(String value) {
			addCriterion("uso_parametro <", value, "usoParametro");
			return (Criteria) this;
		}

		public Criteria andUsoParametroLessThanOrEqualTo(String value) {
			addCriterion("uso_parametro <=", value, "usoParametro");
			return (Criteria) this;
		}

		public Criteria andUsoParametroLike(String value) {
			addCriterion("uso_parametro like", value, "usoParametro");
			return (Criteria) this;
		}

		public Criteria andUsoParametroNotLike(String value) {
			addCriterion("uso_parametro not like", value, "usoParametro");
			return (Criteria) this;
		}

		public Criteria andUsoParametroIn(List<String> values) {
			addCriterion("uso_parametro in", values, "usoParametro");
			return (Criteria) this;
		}

		public Criteria andUsoParametroNotIn(List<String> values) {
			addCriterion("uso_parametro not in", values, "usoParametro");
			return (Criteria) this;
		}

		public Criteria andUsoParametroBetween(String value1, String value2) {
			addCriterion("uso_parametro between", value1, value2, "usoParametro");
			return (Criteria) this;
		}

		public Criteria andUsoParametroNotBetween(String value1, String value2) {
			addCriterion("uso_parametro not between", value1, value2, "usoParametro");
			return (Criteria) this;
		}

		public Criteria andTagCaIsNull() {
			addCriterion("tag_ca is null");
			return (Criteria) this;
		}

		public Criteria andTagCaIsNotNull() {
			addCriterion("tag_ca is not null");
			return (Criteria) this;
		}

		public Criteria andTagCaEqualTo(String value) {
			addCriterion("tag_ca =", value, "tagCa");
			return (Criteria) this;
		}

		public Criteria andTagCaNotEqualTo(String value) {
			addCriterion("tag_ca <>", value, "tagCa");
			return (Criteria) this;
		}

		public Criteria andTagCaGreaterThan(String value) {
			addCriterion("tag_ca >", value, "tagCa");
			return (Criteria) this;
		}

		public Criteria andTagCaGreaterThanOrEqualTo(String value) {
			addCriterion("tag_ca >=", value, "tagCa");
			return (Criteria) this;
		}

		public Criteria andTagCaLessThan(String value) {
			addCriterion("tag_ca <", value, "tagCa");
			return (Criteria) this;
		}

		public Criteria andTagCaLessThanOrEqualTo(String value) {
			addCriterion("tag_ca <=", value, "tagCa");
			return (Criteria) this;
		}

		public Criteria andTagCaLike(String value) {
			addCriterion("tag_ca like", value, "tagCa");
			return (Criteria) this;
		}

		public Criteria andTagCaNotLike(String value) {
			addCriterion("tag_ca not like", value, "tagCa");
			return (Criteria) this;
		}

		public Criteria andTagCaIn(List<String> values) {
			addCriterion("tag_ca in", values, "tagCa");
			return (Criteria) this;
		}

		public Criteria andTagCaNotIn(List<String> values) {
			addCriterion("tag_ca not in", values, "tagCa");
			return (Criteria) this;
		}

		public Criteria andTagCaBetween(String value1, String value2) {
			addCriterion("tag_ca between", value1, value2, "tagCa");
			return (Criteria) this;
		}

		public Criteria andTagCaNotBetween(String value1, String value2) {
			addCriterion("tag_ca not between", value1, value2, "tagCa");
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

		public Criteria andParametroLikeInsensitive(String value) {
			addCriterion("upper(parametro) like", value.toUpperCase(), "parametro");
			return (Criteria) this;
		}

		public Criteria andDescrizioneLikeInsensitive(String value) {
			addCriterion("upper(descrizione) like", value.toUpperCase(), "descrizione");
			return (Criteria) this;
		}

		public Criteria andTipoParametroLikeInsensitive(String value) {
			addCriterion("upper(tipo_parametro) like", value.toUpperCase(), "tipoParametro");
			return (Criteria) this;
		}

		public Criteria andUsoParametroLikeInsensitive(String value) {
			addCriterion("upper(uso_parametro) like", value.toUpperCase(), "usoParametro");
			return (Criteria) this;
		}

		public Criteria andTagCaLikeInsensitive(String value) {
			addCriterion("upper(tag_ca) like", value.toUpperCase(), "tagCa");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table gatefire.gatefire_d_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
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
     * This class corresponds to the database table gatefire.gatefire_d_parametro
     *
     * @mbg.generated do_not_delete_during_merge Wed Nov 25 11:01:29 CET 2020
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }
}