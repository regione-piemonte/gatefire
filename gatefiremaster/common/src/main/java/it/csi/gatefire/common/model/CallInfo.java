/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "callInfo")
public class CallInfo {
	@NotEmpty(message = "Campo obbligatorio")
	private String applicationCode;
	@NotEmpty(message = "Campo obbligatorio")
	private String caCode;
	@NotEmpty(message = "Campo obbligatorio")
	private String codiceFiscale;
	@NotEmpty(message = "Campo obbligatorio")
	private String collocazione;

	private String dominio;

	public String getCollocazione() {
		return collocazione;
	}

	public void setCollocazione(String collocazione) {
		this.collocazione = collocazione;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getCaCode() {
		return caCode;
	}

	public void setCaCode(String caCode) {
		if (caCode != null) {
			this.caCode = caCode.trim().toUpperCase();
		} else {
			this.caCode = caCode;
		}
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	@Override
	public String toString() {
		return "CallInfo [applicationCode=" + applicationCode + ", caCode=" + caCode + ", codiceFiscale="
				+ codiceFiscale + ", collocazione=" + collocazione + ", dominio=" + dominio + "]";
	}

}
