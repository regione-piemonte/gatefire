/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.service;

import java.util.List;

import it.csi.gatefire.dbhelper.model.GatefireDApplicazione;
import it.csi.gatefire.dbhelper.model.GatefireDFunzione;
import it.csi.gatefire.dbhelper.model.GatefireDParametro;
import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativo;

public interface ParametriSrvc {

	int updateParam(GatefireTParametroOperativo parametro);

	int insertParam(GatefireTParametroOperativo parametro);

	int deleteParam(Integer id);

	List<GatefireTParametroOperativo> exist(String parametro, String codiceCa, String codiceApplicazione,
			String codicefunzione);

	List<GatefireDParametro> getAllParametersCA();

	List<GatefireDParametro> getAllParametersGenerici();

	List<GatefireDApplicazione> getAllApplicazioni();

	List<GatefireDFunzione> getAllFunzioni();

	List<GatefireTParametroOperativo> getParametriGenerici();

	List<String> getAllCollocazioni();

	long countParams();
}
