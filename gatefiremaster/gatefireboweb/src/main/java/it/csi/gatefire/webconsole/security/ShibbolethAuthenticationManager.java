/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.webconsole.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;

import it.csi.gatefire.common.web.model.User;

public class ShibbolethAuthenticationManager implements AuthenticationManager {
	private UserDetailsService userDetailsService;

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		ShibbolethAuthToken principal = (ShibbolethAuthToken) authentication.getPrincipal();
		Object credentials = authentication.getCredentials();

		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());

		if (userDetails == null || userDetails.getAuthorities() == null || userDetails.getAuthorities().isEmpty()) {
			throw new BadCredentialsException("User rights cannot be retrieved for user " + principal.getName());
		}
		
		try {
			User user=(User)userDetails;
			if (StringUtils.hasLength(principal.getNome())) {
				user.setNome(principal.getNome());
			}
			if (StringUtils.hasLength(principal.getCognome())) {
				user.setCognome(principal.getCognome());
			}
		} catch (Exception e) {
		
			e.printStackTrace();
		}

		return new PreAuthenticatedAuthenticationToken(principal, credentials, userDetails.getAuthorities());
	}

}
