/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.webconsole.security.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import it.csi.gatefire.dbhelper.service.LoginSrvc;
import it.csi.gatefire.webconsole.security.AuthSuccessHandler;
import it.csi.gatefire.webconsole.security.ShibbolethAuthFilter;
import it.csi.gatefire.webconsole.security.ShibbolethPreAuthProvider;
import it.csi.gatefire.webconsole.web.filter.LogoutListener;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LoginSrvc loginSrvc;

	@Qualifier("propertyConfigurer")
	@Autowired
	private Properties properties;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		String roleAmministratore = properties.getProperty("role.amministratore");
		String roleOperatore = properties.getProperty("role.operatore");
		String shibboletFilter = properties.getProperty("enableShibboleth");

		log.info("enableShibboleth:" + shibboletFilter);

		if ("true".equalsIgnoreCase(shibboletFilter.trim())) {
			log.info("Predispongo l'autenticazione tramite shibboleth");
			http.addFilterAfter(shibbolethFilter(), RequestHeaderAuthenticationFilter.class).authorizeRequests()
					.antMatchers("/perform_logout**").permitAll().antMatchers("/resources/**").permitAll()
					.antMatchers("/loginPage**").denyAll().antMatchers("/admin**").hasAuthority(roleAmministratore)
					.antMatchers("/conf**").hasAuthority(roleAmministratore).anyRequest()
					.hasAnyAuthority(roleAmministratore, roleOperatore).and().logout().logoutUrl("/perform_logout")
					.logoutSuccessHandler(shibLogutSuccessHandler());

			http.headers().frameOptions().sameOrigin().cacheControl().disable();
		} else {

			http.authorizeRequests().antMatchers("/loginPage**").permitAll().antMatchers("/perform_logout**")
					.permitAll().antMatchers("/resources/**").permitAll().antMatchers("/admin**")
					.hasAuthority(roleAmministratore).antMatchers("/conf**").hasAuthority(roleAmministratore)
					.anyRequest().hasAnyAuthority(roleAmministratore, roleOperatore).and().formLogin()
					.loginPage("/loginPage").loginProcessingUrl("/perform_login").failureUrl("/loginPage?error=true")
					.successHandler(authSuccessHandler()).and().logout().logoutUrl("/perform_logout")
					.deleteCookies("JSESSIONID");

			http.headers().frameOptions().sameOrigin().cacheControl().disable();
		}
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthSuccessHandler authSuccessHandler() {
		AuthSuccessHandler auth = new AuthSuccessHandler();

		auth.setAlwaysUseDefaultTargetUrl(false);
		return auth;
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		final List<AuthenticationProvider> providers = new ArrayList<>(2);
		providers.add(preauthAuthProvider());
		providers.add(daoAuthenticationProvider());
		return new ProviderManager(providers);
	}

	@Bean(name = "preAuthProvider")
	ShibbolethPreAuthProvider preauthAuthProvider() {
		ShibbolethPreAuthProvider provider = new ShibbolethPreAuthProvider();
		provider.setPreAuthenticatedUserDetailsService(userDetailsServiceWrapper());

		return provider;
	}

	@Bean(name = "daoAuthProvider")
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(loginSrvc);
		return daoAuthenticationProvider;
	}

	@Bean
	UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> userDetailsServiceWrapper() {
		UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> wrapper = new UserDetailsByNameServiceWrapper<>();
		wrapper.setUserDetailsService(loginSrvc);
		return wrapper;
	}

	@Bean(name = "shibbolethFilter")
	public ShibbolethAuthFilter shibbolethFilter() throws Exception {
		ShibbolethAuthFilter filter = new ShibbolethAuthFilter();
		filter.setAuthenticationManager(authenticationManager());

		return filter;
	}

	@Bean(name = "shibLogutSuccessHandler")
	public ShibLogutSuccessHandler shibLogutSuccessHandler() {
		return new ShibLogutSuccessHandler();
	}

	@Bean
	public LogoutListener initLogoutListener() {
		return new LogoutListener();
	}

}
