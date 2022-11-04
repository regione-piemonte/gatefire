package it.csi.gatefire.webconsole.web.filter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;

public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {

		List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
		UserDetails ud;
		for (SecurityContext securityContext : lstSecurityContext) {
			if (securityContext.getAuthentication() != null
					&& securityContext.getAuthentication().getPrincipal() != null) {
				try {
					ud = (UserDetails) securityContext.getAuthentication().getPrincipal();
					String msg = "utente " + ud.getUsername() + " sessione terminata";
					log.info(msg);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}
	}

}
