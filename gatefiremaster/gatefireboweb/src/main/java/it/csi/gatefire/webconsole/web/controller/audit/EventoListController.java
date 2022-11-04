/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.webconsole.web.controller.audit;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.csi.gatefire.common.util.DateUtils;
import it.csi.gatefire.dbhelper.model.GatefireDApplicazione;
import it.csi.gatefire.dbhelper.model.GatefireDCertificationAuthority;
import it.csi.gatefire.dbhelper.model.GatefireDStatoEvento;
import it.csi.gatefire.dbhelper.model.GatefireDTipoEvento;
import it.csi.gatefire.dbhelper.model.GatefireLEvento;
import it.csi.gatefire.dbhelper.model.fc.EventoFilterBean;
import it.csi.gatefire.dbhelper.service.CachedListsSrvc;
import it.csi.gatefire.dbhelper.service.EventoSrvc;
import it.csi.gatefire.dbhelper.service.ParametriSrvc;
import it.csi.gatefire.webconsole.web.controller.BaseController;

@Controller
public class EventoListController extends BaseController {
	private static final String LIST_VIEW = "audit/listaEventi";
	private static final String DETT_VIEW = "audit/dettEvento";

	@Autowired
	private CachedListsSrvc cachedListsSrvc;
	@Autowired
	private ParametriSrvc parametriSrvc;
	@Autowired
	private EventoSrvc eventoSrvc;

	@ModelAttribute("filterBean")
	EventoFilterBean initfilter(@RequestParam(name = "search", required = false) String search) {

		EventoFilterBean filterBean = new EventoFilterBean();
		filterBean.setDataDa(DateUtils.getDataAMezzanotte(new Date()));
		filterBean.setDataA(DateUtils.getDataAMezzanotte(new Date()));

		return filterBean;

	}

	@RequestMapping("/audit/listaEventi")
	public String showlist(@RequestParam(name = "search", required = false) String search,
			@ModelAttribute("filterBean") EventoFilterBean filterBean, ModelMap map) {

		List<GatefireDStatoEvento> statoList = cachedListsSrvc.getStatiEvento();

		List<GatefireDTipoEvento> tipiEvento = cachedListsSrvc.getTipiEvento();

		List<GatefireDApplicazione> applicazioni = parametriSrvc.getAllApplicazioni();

		List<GatefireDCertificationAuthority> cas = cachedListsSrvc.getAllCa();

		List<String> collocazioni = parametriSrvc.getAllCollocazioni();

		map.addAttribute("search", search);

		map.addAttribute("statiFiltro", statoList);
		map.addAttribute("tipiEvento", tipiEvento);
		map.addAttribute("applicazioni", applicazioni);
		map.addAttribute("cas", cas);
		map.addAttribute("collocazioni", collocazioni);

		return LIST_VIEW;
	}

	@RequestMapping("/audit/listaEventi.json")
	@ResponseBody
	public List<GatefireLEvento> ricercaEventi(@ModelAttribute("filterBean") EventoFilterBean filterBean) {
		return eventoSrvc.ricercaEventi(filterBean);
	}

	@PostMapping("/audit/dettEvento")
	public String showlist(ModelMap map, @ModelAttribute("filterBean") EventoFilterBean filterBean,
			@RequestParam("id") Long id) {

		GatefireLEvento evento = eventoSrvc.getEventoFullById(id);
		map.addAttribute("evento", evento);
		return DETT_VIEW;
	}

	@GetMapping("/audit/dettEvento.json")
	@ResponseBody
	public GatefireLEvento dettEvento(@RequestParam("id") Long id) {
		return eventoSrvc.getEventoFullById(id);

	}

	@GetMapping("/audit/getCodiceFiscaleLike.json")
	@ResponseBody
	public List<String> getCodiceFiscaleLike(@RequestParam("term") String term) {
		return eventoSrvc.selectCodiceFiscaleLike(term);

	}

}
