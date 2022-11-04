package it.csi.gatefire.webconsole.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import it.csi.gatefire.common.web.model.User;

public class ShibbolethPreAuthProvider implements AuthenticationProvider, InitializingBean, Ordered {
	private static final Log logger = LogFactory.getLog(ShibbolethPreAuthProvider.class);

	private AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> preAuthenticatedUserDetailsService;

	private UserDetailsChecker userDetailsChecker = new AccountStatusUserDetailsChecker();

	private boolean throwExceptionWhenTokenRejected;

	private int order = -1; // default: same as non-ordered

	/**
	 * Check whether all required properties have been set.
	 */
	@Override
	public void afterPropertiesSet() {
		Assert.notNull(this.preAuthenticatedUserDetailsService, "An AuthenticationUserDetailsService must be set");
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!supports(authentication.getClass())) {
			return null;
		}
		logger.debug(LogMessage.format("PreAuthenticated authentication request: %s", authentication));
		if (authentication.getPrincipal() == null) {
			logger.debug("No pre-authenticated principal found in request.");
			if (throwExceptionWhenTokenRejected) {
				throw new BadCredentialsException("No pre-authenticated principal found in request.");
			}
			return null;
		}
		if (authentication.getCredentials() == null) {
			logger.debug("No pre-authenticated credentials found in request.");
			if (this.throwExceptionWhenTokenRejected) {
				throw new BadCredentialsException("No pre-authenticated credentials found in request.");
			}
			return null;
		}
		UserDetails userDetails = this.preAuthenticatedUserDetailsService
				.loadUserDetails((PreAuthenticatedAuthenticationToken) authentication);
		User user = (User) userDetails;
		ShibbolethAuthToken principal = (ShibbolethAuthToken) authentication.getPrincipal();
		if (StringUtils.hasLength(principal.getNome())) {
			user.setNome(principal.getNome());
		}
		if (StringUtils.hasLength(principal.getCognome())) {
			user.setCognome(principal.getCognome());
		}

		this.userDetailsChecker.check(user);
		PreAuthenticatedAuthenticationToken result = new PreAuthenticatedAuthenticationToken(userDetails,
				authentication.getCredentials(), userDetails.getAuthorities());
		result.setDetails(authentication.getDetails());
		return result;
	}

	/**
	 * Indicate that this provider only supports PreAuthenticatedAuthenticationToken
	 * (sub)classes.
	 */
	@Override
	public final boolean supports(Class<?> authentication) {
		return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
	}

	/**
	 * Set the AuthenticatedUserDetailsService to be used to load the
	 * {@code UserDetails} for the authenticated user.
	 * 
	 * @param uds
	 */
	public void setPreAuthenticatedUserDetailsService(
			AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> uds) {
		this.preAuthenticatedUserDetailsService = uds;
	}

	/**
	 * If true, causes the provider to throw a BadCredentialsException if the
	 * presented authentication request is invalid (contains a null principal or
	 * credentials). Otherwise it will just return null. Defaults to false.
	 */
	public void setThrowExceptionWhenTokenRejected(boolean throwExceptionWhenTokenRejected) {
		this.throwExceptionWhenTokenRejected = throwExceptionWhenTokenRejected;
	}

	/**
	 * Sets the strategy which will be used to validate the loaded
	 * <tt>UserDetails</tt> object for the user. Defaults to an
	 * {@link AccountStatusUserDetailsChecker}.
	 * 
	 * @param userDetailsChecker
	 */
	public void setUserDetailsChecker(UserDetailsChecker userDetailsChecker) {
		Assert.notNull(userDetailsChecker, "userDetailsChecker cannot be null");
		this.userDetailsChecker = userDetailsChecker;
	}

	@Override
	public int getOrder() {
		return this.order;
	}

	public void setOrder(int i) {
		this.order = i;
	}
}
