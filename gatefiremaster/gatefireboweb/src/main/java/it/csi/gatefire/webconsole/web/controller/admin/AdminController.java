/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.webconsole.web.controller.admin;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.gatefire.common.consts.ParamsConsts;
import it.csi.gatefire.common.consts.security.PasswordUtils;
import it.csi.gatefire.common.model.PingResult;
import it.csi.gatefire.common.model.Result;
import it.csi.gatefire.common.util.ErrorHelper;
import it.csi.gatefire.common.util.FileUtils;
import it.csi.gatefire.common.web.model.ValidationResponse;
import it.csi.gatefire.dbhelper.model.GatefireDCaCollocazioneDominio;
import it.csi.gatefire.dbhelper.model.GatefireDCertificationAuthority;
import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativo;
import it.csi.gatefire.dbhelper.service.CachedListsSrvc;
import it.csi.gatefire.dbhelper.service.ParametriSrvc;
import it.csi.gatefire.webconsole.web.controller.BaseController;

@Controller
public class AdminController extends BaseController {

	@Autowired
	private CachedListsSrvc cachedListsSrvc;

	@Autowired
	private ParametriSrvc parametriSrvc;

	private static final String criptview = "admin/criptPsw";

	private static final String refreshCacheView = "admin/refreshCache";

	@GetMapping(value = "/admin/criptaPsw")
	String criptaPws() {

		return criptview;
	}

	@PostMapping(value = "/admin/criptaPsw.json")
	@ResponseBody
	Map<String, String> criptaPwsDo(@RequestParam("psw") String password) {
		Map<String, String> map = new HashMap<>();
		String salt = PasswordUtils.generateSalt();
		String encryptedPsw = PasswordUtils.encrypt(password, salt);
		map.put("salt", salt);
		map.put("encryptedPsw", encryptedPsw);
		return map;
	}

	@GetMapping(value = "/admin/refreshCache")
	public String refreshCache() {

		return refreshCacheView;
	}

	@PostMapping(value = "/admin/refreshCache.json")
	@ResponseBody
	public ValidationResponse refreshCacheDo(@RequestParam(name = "mode") String mode, HttpServletRequest request) {

		mode = sanitizeMode(mode);
		ValidationResponse response = new ValidationResponse();
		String msg = "";
		boolean ok = true;
		try {

			if ("A".equalsIgnoreCase(mode) || "P".equalsIgnoreCase(mode)) {
				cachedListsSrvc.refreshCache();
				msg += " - Parametri ricaricati con successo";
			}

		} catch (Exception e) {
			msg += "Errore refresh Cache " + e.getMessage();
			ok = false;

		}
		if (ok) {
			try {

				if ("A".equalsIgnoreCase(mode) || "M".equalsIgnoreCase(mode)) {
					reloadMessages();
					msg += " - Messaggi ricaricati con successo";
				}

			} catch (Exception e) {
				msg += "Errore reload messages " + e.getMessage();
				ok = false;

			}
		}

		if (!ok) {
			response.setStatus(ValidationResponse.STATUS_ERROR);
		}
		response.setMessage(msg);

		return response;
	}

	@PostMapping("/admin/refreshGatewayCache.json")
	@ResponseBody
	public ValidationResponse refreshGatewayCache(@RequestParam(name = "mode") String mode) {

		mode = sanitizeMode(mode);

		ValidationResponse response = new ValidationResponse();
		String reqUrl = null;
		List<GatefireTParametroOperativo> parametriDisponibili = parametriSrvc.getParametriGenerici();
		for (GatefireTParametroOperativo param : parametriDisponibili) {
			if (param.getParametro().equalsIgnoreCase(ParamsConsts.PARAM_GEN_ENDPOINT_GATEWAY)) {
				reqUrl = param.getValore();
				break;
			}
		}

		if (reqUrl != null) {
			String[] urls = reqUrl.split(";");

			try {
				boolean ok = true;
				StringBuilder message = new StringBuilder("");
				for (String theurl : urls) {
					theurl += "/WS/rest/params/refreshCache";
					message.append("<strong>URL:</strong> " + theurl);
					message.append("<br/>");
					if (StringUtils.hasLength(mode)) {
						theurl += "?mode=" + mode;
					}
					URL url = new URL(theurl.trim());

					HttpURLConnection conn = (HttpURLConnection) url.openConnection();

					conn.setDoOutput(true);

					conn.setRequestMethod("GET");

					conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
					conn.setRequestProperty("Accept", "application/json");

					int retCode = conn.getResponseCode();

					if (retCode == 200 || retCode == 201) {

						String str = FileUtils.convertStreamToString(conn.getInputStream());

						ObjectMapper map = new ObjectMapper();

						Result result = map.readValue(str, Result.class);
						message.append("<strong>Esito:</strong> " + result.getMessage() + "<br/><br/>");

						if (!ErrorHelper.OK.equalsIgnoreCase(result.getErrorCode())) {
							ok = false;
						}
					}

				}

				response.setMessage(message.toString());
				if (!ok) {
					response.setStatus(ValidationResponse.STATUS_ERROR);
				}
			} catch (Exception e) {
				response.setMessage(e.getMessage());

				response.setStatus(ValidationResponse.STATUS_ERROR);
			}
		} else {
			response.setMessage("Endpoint di refresh della cache per il gateway non configurato");

			response.setStatus(ValidationResponse.STATUS_ERROR);

		}

		return response;

	}

	@GetMapping(value = "/admin/pingCA")
	public String ping(ModelMap map) {
		List<GatefireDCertificationAuthority> list = cachedListsSrvc.getAllCa();
		map.addAttribute("caList", list);
		return "admin/ping";
	}

	@PostMapping("/admin/pingCA.json")
	@ResponseBody
	public ValidationResponse ping(@RequestParam(name = "caCode") String caCode) {
		ValidationResponse response = new ValidationResponse();
		List<GatefireDCertificationAuthority> list = cachedListsSrvc.getAllCa();

		boolean trovato = false;
		for (GatefireDCertificationAuthority gatefireDCertificationAuthority : list) {
			if (gatefireDCertificationAuthority.getCodice().equalsIgnoreCase(caCode)) {
				trovato = true;
				break;
			}
		}

		if (!trovato) {
			response.setMessage("CA " + caCode + " non trovato");

			response.setStatus(ValidationResponse.STATUS_ERROR);
			return response;
		}

		String reqUrl = null;
		List<GatefireTParametroOperativo> parametriDisponibili = parametriSrvc.getParametriGenerici();
		for (GatefireTParametroOperativo param : parametriDisponibili) {
			if (param.getParametro().equalsIgnoreCase(ParamsConsts.PARAM_GEN_ENDPOINT_GATEWAY)) {
				reqUrl = param.getValore();
				break;
			}
		}

		if (reqUrl != null) {
			String[] urls = reqUrl.split(";");

			try {
				boolean ok = true;
				StringBuilder message = new StringBuilder("");
				String theurl = urls[0];
				theurl += "/WS/rest/gatefire/ping";

				theurl += "?user=" + getUserName() + "&caCode=" + caCode;

				List<GatefireDCaCollocazioneDominio> domini = cachedListsSrvc.getAllDomini();

				String collocazione = domini.get(0).getCollocazione();

				theurl += "&collocazione=" + collocazione;

				URL url = new URL(theurl.trim());

				HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				conn.setDoOutput(true);

				conn.setRequestMethod("GET");

				conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
				conn.setRequestProperty("Accept", "application/json");

				int retCode = conn.getResponseCode();

				if (retCode == 200 || retCode == 201) {

					String str = FileUtils.convertStreamToString(conn.getInputStream());

					ObjectMapper map = new ObjectMapper();

					PingResult result = map.readValue(str, PingResult.class);
					message.append("<strong>Esito:</strong> ");

					if (!ErrorHelper.OK.equalsIgnoreCase(result.getResult().getErrorCode())) {
						ok = false;
						message.append(result.getResult().getDescription() + "<br/><br/>");
					} else {
						message.append(result.getResult().getMessage() + "<br/><br/>");
					}
				} else {
					ok = false;
					message.append("Errore http invocazione servizio " + retCode);
				}

				response.setMessage(message.toString());
				if (!ok) {
					response.setStatus(ValidationResponse.STATUS_ERROR);
				}

			} catch (Exception e) {
				response.setMessage(e.getMessage());

				response.setStatus(ValidationResponse.STATUS_ERROR);
			}
		} else

		{
			response.setMessage("Endpoint dei  gateway non configurati");

			response.setStatus(ValidationResponse.STATUS_ERROR);

		}
		return response;
	}

	private String sanitizeMode(String mode) {
		String[] whiteList = { "P", "M", "A" };
		if (mode != null) {
			if (Arrays.asList(whiteList).indexOf(mode.toUpperCase()) == -1) {
				return null;
			} else {
				return mode;
			}
		}
		return null;
	}

	@GetMapping(value = "/admin/password")
	public String generateP(ModelMap map) {
		List<GatefireDCertificationAuthority> list = cachedListsSrvc.getAllCa();
		map.addAttribute("caList", list);
		return "admin/ping";
	}

}
