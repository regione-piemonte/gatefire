/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model.repository;

public class Prestazione {
	private String codiceBrancaRegionale;
	private String codiceCatalogoRegionalePrestazione;

	public String getCodiceBrancaRegionale() {
		return codiceBrancaRegionale;
	}

	public void setCodiceBrancaRegionale(String codiceBrancaRegionale) {
		this.codiceBrancaRegionale = codiceBrancaRegionale;
	}

	public String getCodiceCatalogoRegionalePrestazione() {
		return codiceCatalogoRegionalePrestazione;
	}

	public void setCodiceCatalogoRegionalePrestazione(String codiceCatalogoRegionalePrestazione) {
		this.codiceCatalogoRegionalePrestazione = codiceCatalogoRegionalePrestazione;
	}

}
