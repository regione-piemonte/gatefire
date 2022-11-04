/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pingResult")
public class PingResult extends BaseResult {
	private Boolean success = true;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

}
