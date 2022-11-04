package it.csi.gatefire.dbhelper.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import it.csi.gatefire.common.web.model.User;

public interface LoginSrvc extends UserDetailsService {
	public User fillUserData(String username);
}
