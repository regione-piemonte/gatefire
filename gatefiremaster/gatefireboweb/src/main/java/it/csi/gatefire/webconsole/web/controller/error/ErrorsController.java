package it.csi.gatefire.webconsole.web.controller.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorsController {

	@RequestMapping(value = "/error/404", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String show404() {
		// test
		return "errors/404";
	} 

	@RequestMapping(value = "/error/403", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String show403() {
		return "errors/403";
	}
	
	@RequestMapping(value = "/error/401", method = { RequestMethod.GET,
			RequestMethod.POST })
	public  String show401(HttpServletRequest request) {
	
		return "errors/401";
	}

	@RequestMapping(value = "/invalidToken", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String invalidToken() {
		return "errors/invalidToken";
	}

	@RequestMapping(value = "/error/error", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showError() {
		return "errors/error";
	}

	@RequestMapping(value = "/popup/error", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String popupError() {
		return "errors/popupError";
	}

	@RequestMapping(value = "/error/authenticationError", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String showAuthError() {
		return "errors/authenticationError";
	}

}
