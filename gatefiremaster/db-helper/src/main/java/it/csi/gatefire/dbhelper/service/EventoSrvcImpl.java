/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import it.csi.gatefire.common.consts.GatefireConsts;
import it.csi.gatefire.common.util.DateUtils;
import it.csi.gatefire.dbhelper.dao.GatefireLEventoMapper;
import it.csi.gatefire.dbhelper.dao.GatefireLEventoStepMapper;
import it.csi.gatefire.dbhelper.model.GatefireLEvento;
import it.csi.gatefire.dbhelper.model.GatefireLEventoExample;
import it.csi.gatefire.dbhelper.model.GatefireLEventoStep;
import it.csi.gatefire.dbhelper.model.GatefireLEventoStepExample;
import it.csi.gatefire.dbhelper.model.GatefireLEventoExample.Criteria;
import it.csi.gatefire.dbhelper.model.fc.EventoFilterBean;

@Service("eventoSrvc")
public class EventoSrvcImpl extends BaseService implements EventoSrvc {

	@Autowired
	private GatefireLEventoMapper eventoMapper;

	@Autowired
	private GatefireLEventoStepMapper eventoLogMapper;

	@Override
	@Transactional
	public GatefireLEvento insertEvento(GatefireLEvento evento, String jsonInput) {
		eventoMapper.insert(evento);
		GatefireLEventoStep eventoLog = new GatefireLEventoStep();

		eventoLog.setDataCreazione(evento.getDataCreazione());
		eventoLog.setCodiceTipoPassoEventoLog(GatefireConsts.EVT_LOG_TIPO_PASSO_SERVIZIO_INIZIO);
		eventoLog.setCodiceStatoEventoLog(GatefireConsts.EVT_LOG_STATO_FINE_ELAB);
		eventoLog.setDescrizione(jsonInput);
		eventoLog.setIdEvento(evento.getId());

		eventoLogMapper.insert(eventoLog);
		return evento;
	}

	@Override
	public void closeEvento(GatefireLEvento evento, String stato, String descrizioneEvtlog) {
		Date data = new Date();

		evento.setDataFineElab(data);
		evento.setDataAggStato(data);
		evento.setCodiceStatoEvento(stato);

		eventoMapper.updateByPrimaryKey(evento);

		GatefireLEventoStep eventoLog = new GatefireLEventoStep();

		eventoLog.setDataCreazione(data);
		eventoLog.setCodiceTipoPassoEventoLog(GatefireConsts.EVT_LOG_TIPO_PASSO_SERVIZIO_FINE);
		eventoLog.setCodiceStatoEventoLog(GatefireConsts.EVT_LOG_STATO_FINE_ELAB);
		eventoLog.setDescrizione(descrizioneEvtlog);
		eventoLog.setIdEvento(evento.getId());

		eventoLogMapper.insert(eventoLog);

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public GatefireLEventoStep insertEventoLog(GatefireLEventoStep eventoLog) {
		eventoLogMapper.insert(eventoLog);
		return eventoLog;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public GatefireLEventoStep insertEventoLog(Long idEvento, String tipoPasso, String logState, String msg,
			String retCode, String retMessage) {

		Date data = new Date();
		GatefireLEventoStep eventoLog = new GatefireLEventoStep();

		eventoLog.setIdEvento(idEvento);

		eventoLog.setCodiceStatoEventoLog(logState);
		eventoLog.setCodiceTipoPassoEventoLog(tipoPasso);

		eventoLog.setDataCreazione(data);
		eventoLog.setDescrizione(msg);
		eventoLog.setReturnCode(retCode);
		eventoLog.setReturnCodeDescrizione(retMessage);
		eventoLogMapper.insert(eventoLog);

		return eventoLog;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateEvento(GatefireLEvento evento) {
		eventoMapper.updateByPrimaryKey(evento);

	}

	@Override
	public GatefireLEvento getEvento(Long id) {

		return eventoMapper.selectByPrimaryKey(id);
	}

	@Override
	public GatefireLEvento getEventoFullById(Long id) {
		GatefireLEvento evento = getEvento(id);

		GatefireLEventoStepExample ex = new GatefireLEventoStepExample();
		ex.or().andIdEventoEqualTo(id);
		ex.setOrderByClause("ID ASC");
		List<GatefireLEventoStep> listLog = eventoLogMapper.selectByExample(ex);

		evento.setEventoStepList(listLog);
		return evento;
	}

	@Override
	public List<GatefireLEvento> ricercaEventi(EventoFilterBean filterBean) {
		GatefireLEventoExample ex = new GatefireLEventoExample();
		Criteria criteria = ex.or();
		if (StringUtils.hasLength(filterBean.getStato())) {
			criteria.andCodiceStatoEventoEqualTo(filterBean.getStato());
		}

		if (StringUtils.hasLength(filterBean.getCodiceTipoEvento())) {
			criteria = criteria.andCodiceGatefireDTipoEventoEqualTo(filterBean.getCodiceTipoEvento());
		}

		if (filterBean.getDataDa() != null) {
			criteria.andDataCreazioneGreaterThanOrEqualTo(filterBean.getDataDa());
		}
		if (filterBean.getDataA() != null) {
			criteria.andDataCreazioneLessThanOrEqualTo(DateUtils.addDay(filterBean.getDataA(), 1));
		}
		if (StringUtils.hasLength(filterBean.getCodiceApplicazione())) {
			criteria.andCodiceApplicazioneEqualTo(filterBean.getCodiceApplicazione());
		}
		if (StringUtils.hasLength(filterBean.getCodiceCertificationAuthority())) {
			criteria.andCodiceCertificationAuthorityEqualTo(filterBean.getCodiceCertificationAuthority());
		}

		if (StringUtils.hasLength(filterBean.getCodiceFiscale())) {
			criteria.andCodiceFiscaleEqualTo(filterBean.getCodiceFiscale());
		}
		if (StringUtils.hasLength(filterBean.getNomeFile())) {
			criteria.andNomeFileLikeInsensitive("%" + filterBean.getNomeFile() + "%");
		}
		
		if (StringUtils.hasLength(filterBean.getCollocazione())) {
			criteria.andCollocazioneEqualTo(filterBean.getCollocazione());
		}

		return eventoMapper.selectByExample(ex);
	}

	public List<String> selectCodiceFiscaleLike(String term) {
		if (term != null) {
			String part = term.trim().toUpperCase() + "%";
			return eventoMapper.selectCodiciFiscali(part);
		}
		return new ArrayList<>();

	}
}
