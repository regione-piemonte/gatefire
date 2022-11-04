package it.csi.gatefire.dbhelper.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GatefireDRepositoryExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table gatefire.gatefire_d_repository
	 * @mbg.generated  Mon May 30 12:48:36 CEST 2022
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table gatefire.gatefire_d_repository
	 * @mbg.generated  Mon May 30 12:48:36 CEST 2022
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table gatefire.gatefire_d_repository
	 * @mbg.generated  Mon May 30 12:48:36 CEST 2022
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_repository
	 * @mbg.generated  Mon May 30 12:48:36 CEST 2022
	 */
	public GatefireDRepositoryExample() {
		oredCriteria = new ArrayList<>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_repository
	 * @mbg.generated  Mon May 30 12:48:36 CEST 2022
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_repository
	 * @mbg.generated  Mon May 30 12:48:36 CEST 2022
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_repository
	 * @mbg.generated  Mon May 30 12:48:36 CEST 2022
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_repository
	 * @mbg.generated  Mon May 30 12:48:36 CEST 2022
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_repository
	 * @mbg.generated  Mon May 30 12:48:36 CEST 2022
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_repository
	 * @mbg.generated  Mon May 30 12:48:36 CEST 2022
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_repository
	 * @mbg.generated  Mon May 30 12:48:36 CEST 2022
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_repository
	 * @mbg.generated  Mon May 30 12:48:36 CEST 2022
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_repository
	 * @mbg.generated  Mon May 30 12:48:36 CEST 2022
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_repository
	 * @mbg.generated  Mon May 30 12:48:36 CEST 2022
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table gatefire.gatefire_d_repository
	 * @mbg.generated  Mon May 30 12:48:36 CEST 2022
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

		public Criteria andCodiceIsNull() {
			addCriterion("codice is null");
			return (Criteria) this;
		}

		public Criteria andCodiceIsNotNull() {
			addCriterion("codice is not null");
			return (Criteria) this;
		}

		public Criteria andCodiceEqualTo(String value) {
			addCriterion("codice =", value, "codice");
			return (Criteria) this;
		}

		public Criteria andCodiceNotEqualTo(String value) {
			addCriterion("codice <>", value, "codice");
			return (Criteria) this;
		}

		public Criteria andCodiceGreaterThan(String value) {
			addCriterion("codice >", value, "codice");
			return (Criteria) this;
		}

		public Criteria andCodiceGreaterThanOrEqualTo(String value) {
			addCriterion("codice >=", value, "codice");
			return (Criteria) this;
		}

		public Criteria andCodiceLessThan(String value) {
			addCriterion("codice <", value, "codice");
			return (Criteria) this;
		}

		public Criteria andCodiceLessThanOrEqualTo(String value) {
			addCriterion("codice <=", value, "codice");
			return (Criteria) this;
		}

		public Criteria andCodiceLike(String value) {
			addCriterion("codice like", value, "codice");
			return (Criteria) this;
		}

		public Criteria andCodiceNotLike(String value) {
			addCriterion("codice not like", value, "codice");
			return (Criteria) this;
		}

		public Criteria andCodiceIn(List<String> values) {
			addCriterion("codice in", values, "codice");
			return (Criteria) this;
		}

		public Criteria andCodiceNotIn(List<String> values) {
			addCriterion("codice not in", values, "codice");
			return (Criteria) this;
		}

		public Criteria andCodiceBetween(String value1, String value2) {
			addCriterion("codice between", value1, value2, "codice");
			return (Criteria) this;
		}

		public Criteria andCodiceNotBetween(String value1, String value2) {
			addCriterion("codice not between", value1, value2, "codice");
			return (Criteria) this;
		}

		public Criteria andNomeIsNull() {
			addCriterion("nome is null");
			return (Criteria) this;
		}

		public Criteria andNomeIsNotNull() {
			addCriterion("nome is not null");
			return (Criteria) this;
		}

		public Criteria andNomeEqualTo(String value) {
			addCriterion("nome =", value, "nome");
			return (Criteria) this;
		}

		public Criteria andNomeNotEqualTo(String value) {
			addCriterion("nome <>", value, "nome");
			return (Criteria) this;
		}

		public Criteria andNomeGreaterThan(String value) {
			addCriterion("nome >", value, "nome");
			return (Criteria) this;
		}

		public Criteria andNomeGreaterThanOrEqualTo(String value) {
			addCriterion("nome >=", value, "nome");
			return (Criteria) this;
		}

		public Criteria andNomeLessThan(String value) {
			addCriterion("nome <", value, "nome");
			return (Criteria) this;
		}

		public Criteria andNomeLessThanOrEqualTo(String value) {
			addCriterion("nome <=", value, "nome");
			return (Criteria) this;
		}

		public Criteria andNomeLike(String value) {
			addCriterion("nome like", value, "nome");
			return (Criteria) this;
		}

		public Criteria andNomeNotLike(String value) {
			addCriterion("nome not like", value, "nome");
			return (Criteria) this;
		}

		public Criteria andNomeIn(List<String> values) {
			addCriterion("nome in", values, "nome");
			return (Criteria) this;
		}

		public Criteria andNomeNotIn(List<String> values) {
			addCriterion("nome not in", values, "nome");
			return (Criteria) this;
		}

		public Criteria andNomeBetween(String value1, String value2) {
			addCriterion("nome between", value1, value2, "nome");
			return (Criteria) this;
		}

		public Criteria andNomeNotBetween(String value1, String value2) {
			addCriterion("nome not between", value1, value2, "nome");
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

		public Criteria andCollocazioneIsNull() {
			addCriterion("collocazione is null");
			return (Criteria) this;
		}

		public Criteria andCollocazioneIsNotNull() {
			addCriterion("collocazione is not null");
			return (Criteria) this;
		}

		public Criteria andCollocazioneEqualTo(String value) {
			addCriterion("collocazione =", value, "collocazione");
			return (Criteria) this;
		}

		public Criteria andCollocazioneNotEqualTo(String value) {
			addCriterion("collocazione <>", value, "collocazione");
			return (Criteria) this;
		}

		public Criteria andCollocazioneGreaterThan(String value) {
			addCriterion("collocazione >", value, "collocazione");
			return (Criteria) this;
		}

		public Criteria andCollocazioneGreaterThanOrEqualTo(String value) {
			addCriterion("collocazione >=", value, "collocazione");
			return (Criteria) this;
		}

		public Criteria andCollocazioneLessThan(String value) {
			addCriterion("collocazione <", value, "collocazione");
			return (Criteria) this;
		}

		public Criteria andCollocazioneLessThanOrEqualTo(String value) {
			addCriterion("collocazione <=", value, "collocazione");
			return (Criteria) this;
		}

		public Criteria andCollocazioneLike(String value) {
			addCriterion("collocazione like", value, "collocazione");
			return (Criteria) this;
		}

		public Criteria andCollocazioneNotLike(String value) {
			addCriterion("collocazione not like", value, "collocazione");
			return (Criteria) this;
		}

		public Criteria andCollocazioneIn(List<String> values) {
			addCriterion("collocazione in", values, "collocazione");
			return (Criteria) this;
		}

		public Criteria andCollocazioneNotIn(List<String> values) {
			addCriterion("collocazione not in", values, "collocazione");
			return (Criteria) this;
		}

		public Criteria andCollocazioneBetween(String value1, String value2) {
			addCriterion("collocazione between", value1, value2, "collocazione");
			return (Criteria) this;
		}

		public Criteria andCollocazioneNotBetween(String value1, String value2) {
			addCriterion("collocazione not between", value1, value2, "collocazione");
			return (Criteria) this;
		}

		public Criteria andAuthenticationRequiredIsNull() {
			addCriterion("authentication_required is null");
			return (Criteria) this;
		}

		public Criteria andAuthenticationRequiredIsNotNull() {
			addCriterion("authentication_required is not null");
			return (Criteria) this;
		}

		public Criteria andAuthenticationRequiredEqualTo(Boolean value) {
			addCriterion("authentication_required =", value, "authenticationRequired");
			return (Criteria) this;
		}

		public Criteria andAuthenticationRequiredNotEqualTo(Boolean value) {
			addCriterion("authentication_required <>", value, "authenticationRequired");
			return (Criteria) this;
		}

		public Criteria andAuthenticationRequiredGreaterThan(Boolean value) {
			addCriterion("authentication_required >", value, "authenticationRequired");
			return (Criteria) this;
		}

		public Criteria andAuthenticationRequiredGreaterThanOrEqualTo(Boolean value) {
			addCriterion("authentication_required >=", value, "authenticationRequired");
			return (Criteria) this;
		}

		public Criteria andAuthenticationRequiredLessThan(Boolean value) {
			addCriterion("authentication_required <", value, "authenticationRequired");
			return (Criteria) this;
		}

		public Criteria andAuthenticationRequiredLessThanOrEqualTo(Boolean value) {
			addCriterion("authentication_required <=", value, "authenticationRequired");
			return (Criteria) this;
		}

		public Criteria andAuthenticationRequiredIn(List<Boolean> values) {
			addCriterion("authentication_required in", values, "authenticationRequired");
			return (Criteria) this;
		}

		public Criteria andAuthenticationRequiredNotIn(List<Boolean> values) {
			addCriterion("authentication_required not in", values, "authenticationRequired");
			return (Criteria) this;
		}

		public Criteria andAuthenticationRequiredBetween(Boolean value1, Boolean value2) {
			addCriterion("authentication_required between", value1, value2, "authenticationRequired");
			return (Criteria) this;
		}

		public Criteria andAuthenticationRequiredNotBetween(Boolean value1, Boolean value2) {
			addCriterion("authentication_required not between", value1, value2, "authenticationRequired");
			return (Criteria) this;
		}

		public Criteria andGestioneConsensiIsNull() {
			addCriterion("gestione_consensi is null");
			return (Criteria) this;
		}

		public Criteria andGestioneConsensiIsNotNull() {
			addCriterion("gestione_consensi is not null");
			return (Criteria) this;
		}

		public Criteria andGestioneConsensiEqualTo(String value) {
			addCriterion("gestione_consensi =", value, "gestioneConsensi");
			return (Criteria) this;
		}

		public Criteria andGestioneConsensiNotEqualTo(String value) {
			addCriterion("gestione_consensi <>", value, "gestioneConsensi");
			return (Criteria) this;
		}

		public Criteria andGestioneConsensiGreaterThan(String value) {
			addCriterion("gestione_consensi >", value, "gestioneConsensi");
			return (Criteria) this;
		}

		public Criteria andGestioneConsensiGreaterThanOrEqualTo(String value) {
			addCriterion("gestione_consensi >=", value, "gestioneConsensi");
			return (Criteria) this;
		}

		public Criteria andGestioneConsensiLessThan(String value) {
			addCriterion("gestione_consensi <", value, "gestioneConsensi");
			return (Criteria) this;
		}

		public Criteria andGestioneConsensiLessThanOrEqualTo(String value) {
			addCriterion("gestione_consensi <=", value, "gestioneConsensi");
			return (Criteria) this;
		}

		public Criteria andGestioneConsensiLike(String value) {
			addCriterion("gestione_consensi like", value, "gestioneConsensi");
			return (Criteria) this;
		}

		public Criteria andGestioneConsensiNotLike(String value) {
			addCriterion("gestione_consensi not like", value, "gestioneConsensi");
			return (Criteria) this;
		}

		public Criteria andGestioneConsensiIn(List<String> values) {
			addCriterion("gestione_consensi in", values, "gestioneConsensi");
			return (Criteria) this;
		}

		public Criteria andGestioneConsensiNotIn(List<String> values) {
			addCriterion("gestione_consensi not in", values, "gestioneConsensi");
			return (Criteria) this;
		}

		public Criteria andGestioneConsensiBetween(String value1, String value2) {
			addCriterion("gestione_consensi between", value1, value2, "gestioneConsensi");
			return (Criteria) this;
		}

		public Criteria andGestioneConsensiNotBetween(String value1, String value2) {
			addCriterion("gestione_consensi not between", value1, value2, "gestioneConsensi");
			return (Criteria) this;
		}

		public Criteria andGestioneIdEpisodioIsNull() {
			addCriterion("gestione_id_episodio is null");
			return (Criteria) this;
		}

		public Criteria andGestioneIdEpisodioIsNotNull() {
			addCriterion("gestione_id_episodio is not null");
			return (Criteria) this;
		}

		public Criteria andGestioneIdEpisodioEqualTo(Boolean value) {
			addCriterion("gestione_id_episodio =", value, "gestioneIdEpisodio");
			return (Criteria) this;
		}

		public Criteria andGestioneIdEpisodioNotEqualTo(Boolean value) {
			addCriterion("gestione_id_episodio <>", value, "gestioneIdEpisodio");
			return (Criteria) this;
		}

		public Criteria andGestioneIdEpisodioGreaterThan(Boolean value) {
			addCriterion("gestione_id_episodio >", value, "gestioneIdEpisodio");
			return (Criteria) this;
		}

		public Criteria andGestioneIdEpisodioGreaterThanOrEqualTo(Boolean value) {
			addCriterion("gestione_id_episodio >=", value, "gestioneIdEpisodio");
			return (Criteria) this;
		}

		public Criteria andGestioneIdEpisodioLessThan(Boolean value) {
			addCriterion("gestione_id_episodio <", value, "gestioneIdEpisodio");
			return (Criteria) this;
		}

		public Criteria andGestioneIdEpisodioLessThanOrEqualTo(Boolean value) {
			addCriterion("gestione_id_episodio <=", value, "gestioneIdEpisodio");
			return (Criteria) this;
		}

		public Criteria andGestioneIdEpisodioIn(List<Boolean> values) {
			addCriterion("gestione_id_episodio in", values, "gestioneIdEpisodio");
			return (Criteria) this;
		}

		public Criteria andGestioneIdEpisodioNotIn(List<Boolean> values) {
			addCriterion("gestione_id_episodio not in", values, "gestioneIdEpisodio");
			return (Criteria) this;
		}

		public Criteria andGestioneIdEpisodioBetween(Boolean value1, Boolean value2) {
			addCriterion("gestione_id_episodio between", value1, value2, "gestioneIdEpisodio");
			return (Criteria) this;
		}

		public Criteria andGestioneIdEpisodioNotBetween(Boolean value1, Boolean value2) {
			addCriterion("gestione_id_episodio not between", value1, value2, "gestioneIdEpisodio");
			return (Criteria) this;
		}

		public Criteria andTipoRepositoryIsNull() {
			addCriterion("tipo_repository is null");
			return (Criteria) this;
		}

		public Criteria andTipoRepositoryIsNotNull() {
			addCriterion("tipo_repository is not null");
			return (Criteria) this;
		}

		public Criteria andTipoRepositoryEqualTo(String value) {
			addCriterion("tipo_repository =", value, "tipoRepository");
			return (Criteria) this;
		}

		public Criteria andTipoRepositoryNotEqualTo(String value) {
			addCriterion("tipo_repository <>", value, "tipoRepository");
			return (Criteria) this;
		}

		public Criteria andTipoRepositoryGreaterThan(String value) {
			addCriterion("tipo_repository >", value, "tipoRepository");
			return (Criteria) this;
		}

		public Criteria andTipoRepositoryGreaterThanOrEqualTo(String value) {
			addCriterion("tipo_repository >=", value, "tipoRepository");
			return (Criteria) this;
		}

		public Criteria andTipoRepositoryLessThan(String value) {
			addCriterion("tipo_repository <", value, "tipoRepository");
			return (Criteria) this;
		}

		public Criteria andTipoRepositoryLessThanOrEqualTo(String value) {
			addCriterion("tipo_repository <=", value, "tipoRepository");
			return (Criteria) this;
		}

		public Criteria andTipoRepositoryLike(String value) {
			addCriterion("tipo_repository like", value, "tipoRepository");
			return (Criteria) this;
		}

		public Criteria andTipoRepositoryNotLike(String value) {
			addCriterion("tipo_repository not like", value, "tipoRepository");
			return (Criteria) this;
		}

		public Criteria andTipoRepositoryIn(List<String> values) {
			addCriterion("tipo_repository in", values, "tipoRepository");
			return (Criteria) this;
		}

		public Criteria andTipoRepositoryNotIn(List<String> values) {
			addCriterion("tipo_repository not in", values, "tipoRepository");
			return (Criteria) this;
		}

		public Criteria andTipoRepositoryBetween(String value1, String value2) {
			addCriterion("tipo_repository between", value1, value2, "tipoRepository");
			return (Criteria) this;
		}

		public Criteria andTipoRepositoryNotBetween(String value1, String value2) {
			addCriterion("tipo_repository not between", value1, value2, "tipoRepository");
			return (Criteria) this;
		}

		public Criteria andCodiceLikeInsensitive(String value) {
			addCriterion("upper(codice) like", value.toUpperCase(), "codice");
			return (Criteria) this;
		}

		public Criteria andNomeLikeInsensitive(String value) {
			addCriterion("upper(nome) like", value.toUpperCase(), "nome");
			return (Criteria) this;
		}

		public Criteria andDescrizioneLikeInsensitive(String value) {
			addCriterion("upper(descrizione) like", value.toUpperCase(), "descrizione");
			return (Criteria) this;
		}

		public Criteria andCollocazioneLikeInsensitive(String value) {
			addCriterion("upper(collocazione) like", value.toUpperCase(), "collocazione");
			return (Criteria) this;
		}

		public Criteria andGestioneConsensiLikeInsensitive(String value) {
			addCriterion("upper(gestione_consensi) like", value.toUpperCase(), "gestioneConsensi");
			return (Criteria) this;
		}

		public Criteria andTipoRepositoryLikeInsensitive(String value) {
			addCriterion("upper(tipo_repository) like", value.toUpperCase(), "tipoRepository");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table gatefire.gatefire_d_repository
	 * @mbg.generated  Mon May 30 12:48:36 CEST 2022
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
     * This class corresponds to the database table gatefire.gatefire_d_repository
     *
     * @mbg.generated do_not_delete_during_merge Wed Nov 25 11:01:29 CET 2020
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }
}