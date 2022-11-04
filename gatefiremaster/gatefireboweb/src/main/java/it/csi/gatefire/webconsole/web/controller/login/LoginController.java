package it.csi.gatefire.webconsole.web.controller.login;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.csi.gatefire.webconsole.web.controller.BaseController;

@Controller
public class LoginController extends BaseController {

	private static final  String VIEW = "login/login";

	
	@RequestMapping(value = "/loginPage", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showLogin(HttpServletResponse response) {

		return VIEW;
	}

	
}
