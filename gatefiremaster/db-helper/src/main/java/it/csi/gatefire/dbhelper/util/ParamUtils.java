/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.util;

import java.util.List;

import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativo;

public class ParamUtils {

	private ParamUtils() {
		super();

	}

	public static String getParam(List<GatefireTParametroOperativo> list, String parametro, String codiceCa,
			String codiceApplicazione, String funzione, String defaultValue) {
		GatefireTParametroOperativo param = null;
		int currcount = 0;
		for (GatefireTParametroOperativo vCaParametri : list) {
			if (vCaParametri.getParametro().equalsIgnoreCase(parametro)
					&& vCaParametri.getCodiceCa().equalsIgnoreCase(codiceCa)) {
				int count = 0;
				if (vCaParametri.getCodiceApplicazione() == null && vCaParametri.getCodiceFunzione() == null) {
					count++;
				}
				if (codiceApplicazione != null
						&& codiceApplicazione.equalsIgnoreCase(vCaParametri.getCodiceApplicazione())) {
					count++;
				} else {
					if (codiceApplicazione == null && vCaParametri.getCodiceApplicazione() != null) {
						continue;
					}
				}
				if (funzione != null && funzione.equalsIgnoreCase(vCaParametri.getCodiceFunzione())) {
					count++;
				} else {
					if (funzione == null && vCaParametri.getCodiceFunzione() != null) {
						continue;
					}
				}
				if (count > currcount) {
					currcount = count;
					param = vCaParametri;
				}

			}
		}

		if (param == null) {
			return defaultValue;
		} else {
			return param.getValore();
		}
	}
}
