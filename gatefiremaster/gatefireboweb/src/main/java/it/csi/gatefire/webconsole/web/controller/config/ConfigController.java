/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.webconsole.web.controller.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.csi.gatefire.common.web.model.ErrorMessage;
import it.csi.gatefire.common.web.model.ValidationResponse;
import it.csi.gatefire.dbhelper.model.GatefireDApplicazione;
import it.csi.gatefire.dbhelper.model.GatefireDCertificationAuthority;
import it.csi.gatefire.dbhelper.model.GatefireDFunzione;
import it.csi.gatefire.dbhelper.model.GatefireDParametro;
import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativo;
import it.csi.gatefire.dbhelper.service.CaSrvc;
import it.csi.gatefire.dbhelper.service.CachedListsSrvc;
import it.csi.gatefire.dbhelper.service.ParametriSrvc;
import it.csi.gatefire.webconsole.web.controller.BaseController;

@Controller
public class ConfigController extends BaseController {

	private static final String CA_CONF_VIEW = "config/configCA";

	private static final String PARAMS_VIEW = "config/params";

	@Autowired
	private CaSrvc caSrvc;

	@Autowired
	private CachedListsSrvc cacListsSrvc;

	@Autowired
	private ParametriSrvc parametriSrvc;

	@GetMapping("/conf/configCA")
	public String caConf(ModelMap map) {
		List<GatefireDCertificationAuthority> list = cacListsSrvc.getAllCa();
		map.addAttribute("caList", list);

		List<GatefireDParametro> parametriDisponibili = parametriSrvc.getAllParametersCA();
		map.addAttribute("parametriDisponibili", parametriDisponibili);

		List<GatefireDApplicazione> applicazioniDisponibili = parametriSrvc.getAllApplicazioni();
		map.addAttribute("applicazioniDisponibili", applicazioniDisponibili);

		List<GatefireDFunzione> funzioniDisponibili = parametriSrvc.getAllFunzioni();
		map.addAttribute("funzioniDisponibili", funzioniDisponibili);

		return CA_CONF_VIEW;
	}

	@GetMapping("/conf/configParam")
	public String confparams(ModelMap map) {

		List<GatefireDParametro> parametriDisponibili = parametriSrvc.getAllParametersGenerici();
		map.addAttribute("parametriDisponibili", parametriDisponibili);

		List<GatefireDApplicazione> applicazioniDisponibili = parametriSrvc.getAllApplicazioni();
		map.addAttribute("applicazioniDisponibili", applicazioniDisponibili);

		List<GatefireDFunzione> funzioniDisponibili = parametriSrvc.getAllFunzioni();
		map.addAttribute("funzioniDisponibili", funzioniDisponibili);

		return PARAMS_VIEW;
	}

	@RequestMapping("/conf/getCaParams.json")
	@ResponseBody
	public List<GatefireTParametroOperativo> getCaParams(@RequestParam("codiceCa") String codiceCa) {

		List<GatefireDCertificationAuthority> list = cacListsSrvc.getAllCa();
		boolean trovato = false;
		for (GatefireDCertificationAuthority gatefireDCertificationAuthority : list) {
			if (gatefireDCertificationAuthority.getCodice().equalsIgnoreCase(codiceCa)) {
				trovato = true;
				break;
			}
		}

		if (!trovato) {
			return new ArrayList<>();

		}
		return caSrvc.getParametriCa(codiceCa);

	}

	@RequestMapping("/conf/getParams.json")
	@ResponseBody
	public List<GatefireTParametroOperativo> getparams() {
		return parametriSrvc.getParametriGenerici();

	}

	@PostMapping("/conf/insertParam.json")
	@ResponseBody
	public ValidationResponse insert(@ModelAttribute("caParametri") GatefireTParametroOperativo parametro,
			@RequestParam(name = "params", required = false) String params) {
		boolean isCa = (params == null);
		ValidationResponse response = validaParametro(parametro, isCa);
		if (!response.getStatus().equalsIgnoreCase(ValidationResponse.STATUS_SUCCESS)) {

			return response;
		}

		if (!parametriSrvc.exist(parametro.getParametro(), parametro.getCodiceCa(), parametro.getCodiceApplicazione(),
				parametro.getCodiceFunzione()).isEmpty()) {
			response.setStatus(ValidationResponse.STATUS_ERROR);
			response.setMessage("Paramentro gia' esistente a sistema ");
			return response;
		}

		try {
			int ret = parametriSrvc.insertParam(parametro);
			if (ret > 0) {
				logInfo("inserito correttamente parametro " + parametro);
			} else {
				response.setStatus(ValidationResponse.STATUS_ERROR);
				response.setMessage("Nessun record inserito");
			}
		} catch (Exception e) {
			response.setStatus(ValidationResponse.STATUS_ERROR);
			response.setMessage(e.getMessage());
			logError(e);
		}

		return response;
	}

	@PostMapping("/conf/deleteParam.json")
	@ResponseBody
	public ValidationResponse delete(@RequestParam("id") Integer id) {

		ValidationResponse response = new ValidationResponse();
		try {
			int ret = parametriSrvc.deleteParam(id);
			if (ret > 0) {
				logInfo("cancellato correttamente parametro " + id);
			} else {
				response.setStatus(ValidationResponse.STATUS_ERROR);
				response.setMessage("Nessun record cancellato");
			}
		} catch (Exception e) {
			response.setStatus(ValidationResponse.STATUS_ERROR);
			response.setMessage(e.getMessage());
			logError(e);
		}

		return response;
	}

	@PostMapping("/conf/updateParam.json")
	@ResponseBody
	public ValidationResponse update(@ModelAttribute("caParametri") GatefireTParametroOperativo parametro,
			@RequestParam(name = "params", required = false) String params) {
		boolean isCa = (params == null);
		ValidationResponse response = validaParametro(parametro, isCa);
		if (!response.getStatus().equalsIgnoreCase(ValidationResponse.STATUS_SUCCESS)) {

			return response;
		}

		List<GatefireTParametroOperativo> list = parametriSrvc.exist(parametro.getParametro(), parametro.getCodiceCa(),
				null, null);
		GatefireTParametroOperativo theParam = null;
		if (!list.isEmpty()) {

			for (GatefireTParametroOperativo gatefireTParametroOperativo : list) {
				if (gatefireTParametroOperativo.getId().equals(parametro.getId())) {

					theParam = gatefireTParametroOperativo;
					break;
				}
			}
			if (theParam == null) {
				response.setStatus(ValidationResponse.STATUS_ERROR);
				response.setMessage("Paramentro gia' esistente a sistema ");
				return response;
			}

			theParam.setCodiceApplicazione(parametro.getCodiceApplicazione());
			theParam.setCodiceFunzione(parametro.getCodiceFunzione());
			theParam.setValore(parametro.getValore());
		}
		try {
			int ret = parametriSrvc.updateParam(theParam);
			if (ret > 0) {
				logInfo("aggiornato correttamente parametro " + theParam);
			} else {
				response.setStatus(ValidationResponse.STATUS_ERROR);
				response.setMessage("Nessun record aggiornato");
			}
		} catch (Exception e) {
			response.setStatus(ValidationResponse.STATUS_ERROR);
			response.setMessage(e.getMessage());
			logError(e);
		}

		return response;
	}

	private ValidationResponse validaParametro(GatefireTParametroOperativo caParametri, boolean isCa) {
		ValidationResponse response = new ValidationResponse();
		boolean validError = false;

		if (isCa) {
			if (!StringUtils.hasLength(caParametri.getCodiceCa())) {

				response.addErrorMessage("codiceCa", "CA", "CA non selezionata", true);
				validError = true;
			}
		} else {
			caParametri.setCodiceCa(null);
		}

		if (!StringUtils.hasLength(caParametri.getParametro())) {

			response.addErrorMessage("parametro", "parametro", ErrorMessage.MSG_NON_SPECIFICATO, true);
			validError = true;
		}

		if (!StringUtils.hasLength(caParametri.getValore())) {

			response.addErrorMessage("valore", "valore", ErrorMessage.MSG_NON_SPECIFICATO, true);
			validError = true;
		}

		if ("".equalsIgnoreCase(caParametri.getCodiceApplicazione())) {
			caParametri.setCodiceApplicazione(null);
		}

		if ("".equalsIgnoreCase(caParametri.getCodiceFunzione())) {
			caParametri.setCodiceFunzione(null);
		}
		if (validError) {
			response.setStatus(ValidationResponse.STATUS_VALIDATION_ERROR);
			response.setMessage("Errore validazione dati");

		}

		return response;

	}



}
