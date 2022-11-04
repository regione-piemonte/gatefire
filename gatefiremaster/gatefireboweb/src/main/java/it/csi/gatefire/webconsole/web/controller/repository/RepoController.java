/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.webconsole.web.controller.repository;

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

import it.csi.gatefire.common.consts.security.PasswordUtils;
import it.csi.gatefire.common.model.repository.consts.RepoConsts;
import it.csi.gatefire.common.web.model.ErrorMessage;
import it.csi.gatefire.common.web.model.ValidationResponse;
import it.csi.gatefire.dbhelper.model.GatefireDApplicazione;
import it.csi.gatefire.dbhelper.model.GatefireDRepository;
import it.csi.gatefire.dbhelper.model.GatefireRRepositoryAccount;
import it.csi.gatefire.dbhelper.model.GatefireRRepositoryUri;
import it.csi.gatefire.dbhelper.service.CachedListsSrvc;
import it.csi.gatefire.dbhelper.service.RepoSrvc;
import it.csi.gatefire.webconsole.web.controller.BaseController;

@Controller
public class RepoController extends BaseController {

	private static final String REPO_CONF_VIEW = "repo/repoConfig";

	@Autowired
	private RepoSrvc repoSrvc;

	@Autowired
	private CachedListsSrvc cachedListsSrvc;

	@GetMapping("/repo/repoConfig")
	public String repoConf(ModelMap map) {

		List<String> transazioniDisponibili = new ArrayList<>();
		transazioniDisponibili.add(RepoConsts.TRANSAZIONE_ASSERTION);
		transazioniDisponibili.add(RepoConsts.TRANSAZIONE_ITI18);
		transazioniDisponibili.add(RepoConsts.TRANSAZIONE_ITI41);
		transazioniDisponibili.add(RepoConsts.TRANSAZIONE_ITI41_UNDO);
		transazioniDisponibili.add(RepoConsts.TRANSAZIONE_ITI43);
		transazioniDisponibili.add(RepoConsts.TRANSAZIONE_ITI57);
		transazioniDisponibili.add(RepoConsts.TRANSAZIONE_WS_ADDRESS_TO_ITI18);
		transazioniDisponibili.add(RepoConsts.TRANSAZIONE_WS_ADDRESS_TO_ITI41);
		transazioniDisponibili.add(RepoConsts.TRANSAZIONE_WS_ADDRESS_TO_ITI41_UNDO);
		transazioniDisponibili.add(RepoConsts.TRANSAZIONE_WS_ADDRESS_TO_ITI43);
		transazioniDisponibili.add(RepoConsts.TRANSAZIONE_WS_ADDRESS_TO_ITI57);
		map.addAttribute("transazioniDisponibili", transazioniDisponibili);
		List<String> tipoRepoDisponibili = new ArrayList<>();
		tipoRepoDisponibili.add(RepoConsts.TIPO_REPOSITORY_DEDALUS);
		map.addAttribute("tipoRepoDisponibili", tipoRepoDisponibili);

		List<GatefireDApplicazione> appList = cachedListsSrvc.getAllApplicazioni();

		map.addAttribute("appDisponibili", appList);

		return REPO_CONF_VIEW;
	}

	@RequestMapping("/repo/getRepos.json")
	@ResponseBody
	public List<GatefireDRepository> getRepos() {

		return repoSrvc.getAllRepository();

	}

	@RequestMapping("/repo/getRepoUri.json")
	@ResponseBody
	public List<GatefireRRepositoryUri> getRepoUri(@RequestParam("codice") String codice) {

		List<GatefireDRepository> list = repoSrvc.getAllRepository();
		boolean trovato = false;
		for (GatefireDRepository uri : list) {
			if (uri.getCodice().equalsIgnoreCase(codice)) {
				trovato = true;
				break;
			} 
		}

		if (!trovato) {
			return new ArrayList<>();

		}
		return repoSrvc.getRepoUris(codice);
	}

	@PostMapping("/repo/deleteUri.json")
	@ResponseBody
	public ValidationResponse delete(@RequestParam("id") Long id) {

		ValidationResponse response = new ValidationResponse();
		try {
			int ret = repoSrvc.deleteUri(id);
			if (ret > 0) {
				logInfo("cancellato correttamente uri " + id);
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

	@PostMapping("/repo/insertUri.json")
	@ResponseBody
	public ValidationResponse insert(@ModelAttribute("uri") GatefireRRepositoryUri uri) {

		ValidationResponse response = validaUri(uri, false);
		if (!response.getStatus().equalsIgnoreCase(ValidationResponse.STATUS_SUCCESS)) {

			return response;
		}

		if (repoSrvc.exist(uri)) {
			response.setStatus(ValidationResponse.STATUS_ERROR);
			response.setMessage("Uri gia' esistente a sistema ");
			return response;
		}

		try {
			int ret = repoSrvc.insertParam(uri);
			if (ret > 0) {
				logInfo("inserita correttamente uri " + uri);
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

	@PostMapping("/repo/updateUri.json")
	@ResponseBody
	public ValidationResponse update(@ModelAttribute("uri") GatefireRRepositoryUri uri) {

		ValidationResponse response = validaUri(uri, true);
		if (!response.getStatus().equalsIgnoreCase(ValidationResponse.STATUS_SUCCESS)) {

			return response;
		}

		GatefireRRepositoryUri origUri = repoSrvc.getUri(uri.getId());

		if (origUri != null) {

			origUri.setUri(uri.getUri());

			try {
				int ret = repoSrvc.updateUri(origUri);
				if (ret > 0) {
					logInfo("aggiornato correttamente uri " + origUri);
				} else {
					response.setStatus(ValidationResponse.STATUS_ERROR);
					response.setMessage("Nessun record aggiornato");
				}
			} catch (Exception e) {
				response.setStatus(ValidationResponse.STATUS_ERROR);
				response.setMessage(e.getMessage());
				logError(e);
			}
		} else {
			response.setStatus(ValidationResponse.STATUS_ERROR);
			response.setMessage("Uri non trovata a DB");
		}

		return response;

	}

	@PostMapping("/repo/updateRepo.json")
	@ResponseBody
	public ValidationResponse update(@ModelAttribute("repo") GatefireDRepository repo) {

		ValidationResponse response = validaRepo(repo);
		if (!response.getStatus().equalsIgnoreCase(ValidationResponse.STATUS_SUCCESS)) {

			return response;
		}

		GatefireDRepository origrepo = repoSrvc.getRepo(repo.getCodice());

		if (origrepo != null) {

			origrepo.setCollocazione(repo.getCollocazione());
			origrepo.setDescrizione(repo.getDescrizione());
			origrepo.setAuthenticationRequired(repo.getAuthenticationRequired());
			origrepo.setGestioneConsensi(repo.getGestioneConsensi());
			origrepo.setGestioneIdEpisodio(repo.getGestioneIdEpisodio());
			origrepo.setTipoRepository(repo.getTipoRepository());

			try {
				int ret = repoSrvc.updateRepo(origrepo);
				if (ret > 0) {
					logInfo("aggiornato correttamente uri " + origrepo);
				} else {
					response.setStatus(ValidationResponse.STATUS_ERROR);
					response.setMessage("Nessun record aggiornato");
				}
			} catch (Exception e) {
				response.setStatus(ValidationResponse.STATUS_ERROR);
				response.setMessage(e.getMessage());
				logError(e);
			}
		} else {
			response.setStatus(ValidationResponse.STATUS_ERROR);
			response.setMessage("Repository non trovata a DB");
		}

		return response;

	}

	@RequestMapping("/repo/getRepoAccounts.json")
	@ResponseBody
	public List<GatefireRRepositoryAccount> getRepoAccounts(@RequestParam("codice") String codice) {

		List<GatefireDRepository> list = repoSrvc.getAllRepository();
		boolean trovato = false;
		for (GatefireDRepository uri : list) {
			if (uri.getCodice().equalsIgnoreCase(codice)) {
				trovato = true;
				break;
			}
		}

		if (!trovato) {
			return new ArrayList<>();

		}
		return repoSrvc.getRepoAccounts(codice);
	}

	@RequestMapping("/repo/revealPsw.json")
	@ResponseBody
	public String revealPsw(@RequestParam("id") Long id) {
		GatefireRRepositoryAccount account = repoSrvc.getAccount(id);

		if (account != null) {
			return PasswordUtils.decrypt(account.getHashedPassword(), account.getSalt());
		}

		return "";
	}

	@PostMapping("/repo/deleteAccount.json")
	@ResponseBody
	public ValidationResponse deleteAccount(@RequestParam("id") Long id) {

		ValidationResponse response = new ValidationResponse();
		try {
			int ret = repoSrvc.deleteAccount(id);
			if (ret > 0) {
				logInfo("cancellato correttamente account " + id);
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

	@PostMapping("/repo/insertAccount.json")
	@ResponseBody
	public ValidationResponse insertAccount(@ModelAttribute("account") GatefireRRepositoryAccount account) {

		ValidationResponse response = validaAccount(account, false);
		if (!response.getStatus().equalsIgnoreCase(ValidationResponse.STATUS_SUCCESS)) {

			return response;
		}

		if (repoSrvc.exist(account)) {
			response.setStatus(ValidationResponse.STATUS_ERROR);
			response.setMessage("Account gia' esistente a sistema ");
			return response;
		}

		try {

			String salt = PasswordUtils.generateSalt();
			String encryptedPsw = PasswordUtils.encrypt(account.getHashedPassword(), salt);

			account.setSalt(salt);
			account.setHashedPassword(encryptedPsw);
			int ret = repoSrvc.insertAccount(account);
			if (ret > 0) {
				logInfo("inserita correttamente account " + account);
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

	@PostMapping("/repo/updateAccount.json")
	@ResponseBody
	public ValidationResponse updateAccount(@ModelAttribute("account") GatefireRRepositoryAccount account) {

		ValidationResponse response = validaAccount(account, true);
		if (!response.getStatus().equalsIgnoreCase(ValidationResponse.STATUS_SUCCESS)) {

			return response;
		}

		GatefireRRepositoryAccount origAccount = repoSrvc.getAccount(account.getId());

		if (origAccount != null) {
			String salt = PasswordUtils.generateSalt();
			String encryptedPsw = PasswordUtils.encrypt(account.getHashedPassword(), salt);

			account.setSalt(salt);
			account.setHashedPassword(encryptedPsw);

			origAccount.setUsername(account.getUsername());
			origAccount.setHashedPassword(encryptedPsw);
			origAccount.setSalt(salt);

			try {
				int ret = repoSrvc.updateAccount(origAccount);
				if (ret > 0) {
					logInfo("aggiornato correttamente account " + origAccount);
				} else {
					response.setStatus(ValidationResponse.STATUS_ERROR);
					response.setMessage("Nessun record aggiornato");
				}
			} catch (Exception e) {
				response.setStatus(ValidationResponse.STATUS_ERROR);
				response.setMessage(e.getMessage());
				logError(e);
			}
		} else {
			response.setStatus(ValidationResponse.STATUS_ERROR);
			response.setMessage("Uri non trovata a DB");
		}

		return response;

	}

	private ValidationResponse validaUri(GatefireRRepositoryUri uri, boolean edit) {
		ValidationResponse response = new ValidationResponse();
		boolean validError = false;

		if (!StringUtils.hasLength(uri.getCodiceRepository())) {

			response.addErrorMessage("codiceRepository", "codiceRepository", ErrorMessage.MSG_NON_SPECIFICATO, true);
			validError = true;
		}

		if (!StringUtils.hasLength(uri.getTransazione())) {

			response.addErrorMessage("transazione", "transazione", ErrorMessage.MSG_NON_SPECIFICATO, true);
			validError = true;
		}

		if (!StringUtils.hasLength(uri.getUri())) {

			response.addErrorMessage("uri", "uri", ErrorMessage.MSG_NON_SPECIFICATO, true);
			validError = true;
		}

		if (edit) {
			if (uri.getId() == null) {
				response.addErrorMessage("id", "id", ErrorMessage.MSG_NON_SPECIFICATO, true);
				validError = true;
			}
		}

		if (validError) {
			response.setStatus(ValidationResponse.STATUS_VALIDATION_ERROR);
			response.setMessage("Errore validazione dati");

		}

		return response;

	}

	private ValidationResponse validaRepo(GatefireDRepository repo) {
		ValidationResponse response = new ValidationResponse();
		boolean validError = false;

		if (!StringUtils.hasLength(repo.getCodice())) {

			response.addErrorMessage("codice", "codice", ErrorMessage.MSG_NON_SPECIFICATO, true);
			validError = true;
		}
		if (!StringUtils.hasLength(repo.getDescrizione())) {

			response.addErrorMessage("descrizione", "descrizione", ErrorMessage.MSG_NON_SPECIFICATO, true);
			validError = true;
		}
		if (!StringUtils.hasLength(repo.getCollocazione())) {

			response.addErrorMessage("collocazione", "collocazione", ErrorMessage.MSG_NON_SPECIFICATO, true);
			validError = true;
		}

		if (!StringUtils.hasLength(repo.getGestioneConsensi())) {

			response.addErrorMessage("gestioneConsensi", "gestione consensi", ErrorMessage.MSG_NON_SPECIFICATO, true);
			validError = true;
		}

		if (validError) {
			response.setStatus(ValidationResponse.STATUS_VALIDATION_ERROR);
			response.setMessage("Errore validazione dati");

		}

		return response;

	}

	private ValidationResponse validaAccount(GatefireRRepositoryAccount uri, boolean edit) {
		ValidationResponse response = new ValidationResponse();
		boolean validError = false;

		if (!StringUtils.hasLength(uri.getCodiceRepository())) {

			response.addErrorMessage("codiceRepository", "codiceRepository", ErrorMessage.MSG_NON_SPECIFICATO, true);
			validError = true;
		}

		if (!StringUtils.hasLength(uri.getCodiceApplicazione())) {

			response.addErrorMessage("codiceApplicazione", "codiceApplicazione", ErrorMessage.MSG_NON_SPECIFICATO,
					true);
			validError = true;
		}
		
		

		if (!StringUtils.hasLength(uri.getCodiceApplicazione())) {

			response.addErrorMessage("username", "username", ErrorMessage.MSG_NON_SPECIFICATO, true);
			validError = true;
		}

		if (!StringUtils.hasLength(uri.getHashedPassword())) {

			response.addErrorMessage("hashedPassword", "password", ErrorMessage.MSG_NON_SPECIFICATO, true);
			validError = true;
		}

		if (edit) {
			if (uri.getId() == null) {
				response.addErrorMessage("id", "id", ErrorMessage.MSG_NON_SPECIFICATO, true);
				validError = true;
			}
		}

		if (validError) {
			response.setStatus(ValidationResponse.STATUS_VALIDATION_ERROR);
			response.setMessage("Errore validazione dati");

		}

		return response;

	}

}
