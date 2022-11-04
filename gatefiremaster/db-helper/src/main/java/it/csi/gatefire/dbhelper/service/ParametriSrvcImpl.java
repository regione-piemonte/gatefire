/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.csi.gatefire.common.consts.GatefireConsts;
import it.csi.gatefire.dbhelper.dao.GatefireDApplicazioneMapper;
import it.csi.gatefire.dbhelper.dao.GatefireDCaCollocazioneDominioMapper;
import it.csi.gatefire.dbhelper.dao.GatefireDFunzioneMapper;
import it.csi.gatefire.dbhelper.dao.GatefireDParametroMapper;
import it.csi.gatefire.dbhelper.dao.GatefireTParametroOperativoMapper;
import it.csi.gatefire.dbhelper.model.GatefireDApplicazione;
import it.csi.gatefire.dbhelper.model.GatefireDApplicazioneExample;
import it.csi.gatefire.dbhelper.model.GatefireDFunzione;
import it.csi.gatefire.dbhelper.model.GatefireDFunzioneExample;
import it.csi.gatefire.dbhelper.model.GatefireDParametro;
import it.csi.gatefire.dbhelper.model.GatefireDParametroExample;
import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativo;
import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativoExample;
import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativoExample.Criteria;

@Service("parametriSrvc")
public class ParametriSrvcImpl extends BaseService implements ParametriSrvc {

	@Autowired
	private GatefireTParametroOperativoMapper parametroOperativoMapper;
	@Autowired
	private GatefireDParametroMapper parametroMapper;
	@Autowired
	private GatefireDApplicazioneMapper applicazioneMapper;
	@Autowired
	private GatefireDFunzioneMapper funzioneMapper;
	@Autowired
	private GatefireDCaCollocazioneDominioMapper gatefireDCaCollocazioneDominioMapper;

	@Override
	public int updateParam(GatefireTParametroOperativo parametro) {
		parametro.setDataAggiornamento(new Date());

		if (parametro.getDescrizione() == null) {
			GatefireDParametro param = parametroMapper.selectByPrimaryKey(parametro.getParametro());
			if (param != null) {
				parametro.setDescrizione(param.getDescrizione());
			} else {
				parametro.setDescrizione(parametro.getParametro());
			}
		}
		return parametroOperativoMapper.updateByPrimaryKey(parametro);
	}

	@Override
	public int insertParam(GatefireTParametroOperativo parametro) {
		Date data = new Date();
		parametro.setDataCreazione(data);
		parametro.setDataAggiornamento(data);
		parametro.setDataInizioValidita(data);

		if (parametro.getDescrizione() == null) {
			GatefireDParametro param = parametroMapper.selectByPrimaryKey(parametro.getParametro());
			if (param != null) {
				parametro.setDescrizione(param.getDescrizione());
			} else {
				parametro.setDescrizione(parametro.getParametro());
			}
		}
		return parametroOperativoMapper.insert(parametro);
	}

	@Override
	public List<GatefireTParametroOperativo> exist(String parametro, String codiceCa, String codiceApplicazione,
			String codicefunzione) {
		GatefireTParametroOperativoExample ex = new GatefireTParametroOperativoExample();

		Criteria criteria = ex.or().andParametroEqualTo(parametro).andDataCancellazioneIsNull();

		if (StringUtils.hasLength(codiceCa)) {

			criteria.andCodiceCaEqualTo(codiceCa);
		}
		if (StringUtils.hasLength(codiceApplicazione)) {

			criteria.andCodiceApplicazioneEqualTo(codiceApplicazione);
		}
		if (StringUtils.hasLength(codicefunzione)) {

			criteria.andCodiceFunzioneEqualTo(codicefunzione);
		}

		return parametroOperativoMapper.selectByExample(ex);
	}

	@Override
	public List<GatefireDParametro> getAllParametersCA() {

		GatefireDParametroExample ex = new GatefireDParametroExample();
		ex.or().andUsoParametroIsNull().andDataCancellazioneIsNull();
		ex.setOrderByClause("parametro");

		return parametroMapper.selectByExample(ex);
	}

	@Override
	public List<GatefireDParametro> getAllParametersGenerici() {

		GatefireDParametroExample ex = new GatefireDParametroExample();
		ex.or().andUsoParametroEqualTo(GatefireConsts.TIPO_USO_PARAM_STRUTTURALE).andDataCancellazioneIsNull();
		ex.setOrderByClause("parametro");

		return parametroMapper.selectByExample(ex);
	}

	@Override
	public List<GatefireDApplicazione> getAllApplicazioni() {
		GatefireDApplicazioneExample ex = new GatefireDApplicazioneExample();
		ex.or().andDataCancellazioneIsNull();

		ex.setOrderByClause("codice");

		return applicazioneMapper.selectByExample(ex);
	}

	@Override
	public List<GatefireDFunzione> getAllFunzioni() {
		GatefireDFunzioneExample ex = new GatefireDFunzioneExample();
		ex.or().andDataCancellazioneIsNull();
		ex.setOrderByClause("codice");

		return funzioneMapper.selectByExample(ex);
	}

	@Override
	public int deleteParam(Integer id) {
		GatefireTParametroOperativo param = parametroOperativoMapper.selectByPrimaryKey(id);
		param.setDataCancellazione(new Date());
		return parametroOperativoMapper.updateByPrimaryKey(param);
	}

	@Override
	public List<GatefireTParametroOperativo> getParametriGenerici() {
		GatefireTParametroOperativoExample ex = new GatefireTParametroOperativoExample();
		ex.or().andCodiceCaIsNull().andDataCancellazioneIsNull();
		ex.setOrderByClause("PARAMETRO");
		return parametroOperativoMapper.selectByExample(ex);
	}

	@Override
	public List<String> getAllCollocazioni() {
		return gatefireDCaCollocazioneDominioMapper.getAllCollocazioni();
	}

	@Override
	public long countParams() {
		GatefireTParametroOperativoExample ex = new GatefireTParametroOperativoExample();
		ex.or();
		return parametroOperativoMapper.countByExample(ex);
	}

}
