/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.service;

import java.util.List;

import it.csi.gatefire.dbhelper.model.GatefireDApplicazione;
import it.csi.gatefire.dbhelper.model.GatefireDCaCollocazioneDominio;
import it.csi.gatefire.dbhelper.model.GatefireDCertificationAuthority;
import it.csi.gatefire.dbhelper.model.GatefireDRepository;
import it.csi.gatefire.dbhelper.model.GatefireDStatoEvento;
import it.csi.gatefire.dbhelper.model.GatefireDTipoEvento;
import it.csi.gatefire.dbhelper.model.GatefireDXdscode;
import it.csi.gatefire.dbhelper.model.GatefireRRepositoryAccount;
import it.csi.gatefire.dbhelper.model.GatefireRRepositoryUri;
import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativo;

public interface CachedListsSrvc {
	List<GatefireTParametroOperativo> getAllParametriCa();

	List<GatefireDTipoEvento> getTipiEvento();

	List<GatefireDStatoEvento> getStatiEvento();

	List<GatefireDCertificationAuthority> getAllCa();

	List<GatefireTParametroOperativo> getParametriGenerici();

	List<GatefireDApplicazione> getAllApplicazioni();

	List<GatefireDCaCollocazioneDominio> getAllDomini();

	List<GatefireDRepository> getAllRepository();

	List<GatefireDXdscode> getAllXdsCodes();

	List<GatefireRRepositoryUri> getAllRepositoryUri();

	List<GatefireRRepositoryAccount> getAllRepositoryAccount();

	int refreshCache();
}
