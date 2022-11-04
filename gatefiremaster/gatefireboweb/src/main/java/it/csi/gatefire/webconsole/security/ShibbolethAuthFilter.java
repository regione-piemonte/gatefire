/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.webconsole.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.util.Assert;

public class ShibbolethAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

	private static final String USERNAME_ATTRIBUTE_NAME = "Shib-Identita-CodiceFiscale";
	private static final String NOME_ATTRIBUTE_NAME = "Shib-Identita-nome";
	private static final String COGNOME_ATTRIBUTE_NAME = "Shib-Identita-cognome";
	private String credentialsRequestHeader;

	protected final Log log = LogFactory.getLog(this.getClass());

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		// Attribute received in AJP request

		log.debug("Ricerca param " + USERNAME_ATTRIBUTE_NAME);
		Object username = request.getHeader(USERNAME_ATTRIBUTE_NAME);
		log.debug("trovato " + username);
		if (username == null) {
			return null;
		}

		ShibbolethAuthToken authToken = new ShibbolethAuthToken(username.toString());
		String nome = request.getHeader(NOME_ATTRIBUTE_NAME);

		String cognome = request.getHeader(COGNOME_ATTRIBUTE_NAME);
		authToken.setNome(nome);
		authToken.setCognome(cognome);

		return authToken;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) throws IOException, ServletException {
		UserDetails ud = (UserDetails) authResult.getPrincipal();
		log.info("autenticato correttamente utente " + ud.getUsername());
		super.successfulAuthentication(request, response, authResult);
	}

	public void setCredentialsRequestHeader(String credentialsRequestHeader) {
		Assert.hasText(credentialsRequestHeader, "credentialsRequestHeader must not be empty or null");
		this.credentialsRequestHeader = credentialsRequestHeader;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		if (this.credentialsRequestHeader != null) {
			return request.getHeader(this.credentialsRequestHeader);
		}
		return "N/A";
	}
}
