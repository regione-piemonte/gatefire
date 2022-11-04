/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.webconsole.security.config;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.StringUtils;

public class ShibLogutSuccessHandler implements LogoutSuccessHandler {
	@Qualifier("propertyConfigurer")
	@Autowired
	private Properties properties;

	private static final  String SHIB_PARAMETER = "Shib-Handler";

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String logoutUrl = properties.getProperty("logoutUrl");

		if (request.getHeader(SHIB_PARAMETER) != null) {
			logoutUrl = request.getHeader(SHIB_PARAMETER) + "/Logout";
		}

		if (!StringUtils.hasLength(logoutUrl)) {
			logoutUrl = "/";
		}
		response.sendRedirect(logoutUrl);

	}

}
