/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.webconsole.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends BaseController {

	@GetMapping(value = "/home")
	public String home() {

		return "home";
	}

	@GetMapping(value = "/homereload")
	public String homereload() {
		logInfo("RELOAD LOGS");
		reloadMessages();

		return "redirect:/home";
	}

}
