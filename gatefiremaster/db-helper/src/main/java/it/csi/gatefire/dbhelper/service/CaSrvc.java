/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.service;

import java.util.List;

import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativo;

public interface CaSrvc {
	

	List<GatefireTParametroOperativo> getParametriCa(String codiceCa);
	
	

}