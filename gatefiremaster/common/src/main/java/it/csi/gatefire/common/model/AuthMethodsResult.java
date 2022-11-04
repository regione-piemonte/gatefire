/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "AuthMethodsResult")
public class AuthMethodsResult extends BaseResult {

	private List<String> authMethods;

	public List<String> getAuthMethods() {
		return authMethods;
	}

	public void setAuthMethods(List<String> authMethods) {
		this.authMethods = authMethods;
	}

}
