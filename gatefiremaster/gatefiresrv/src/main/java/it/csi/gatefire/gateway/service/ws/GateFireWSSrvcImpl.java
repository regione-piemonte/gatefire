package it.csi.gatefire.gateway.service.ws;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.csi.gatefire.ca.service.ArssSrvc;
import it.csi.gatefire.ca.service.ProxySignSrvc;
import it.csi.gatefire.ca.service.ProxySignVerSrvc;
import it.csi.gatefire.ca.service.VolSrvc;
import it.csi.gatefire.common.consts.GatefireConsts;
import it.csi.gatefire.common.consts.ParamsConsts;
import it.csi.gatefire.common.model.AssertionIdentity;
import it.csi.gatefire.common.model.Attachment;
import it.csi.gatefire.common.model.AutoSignIdentity;
import it.csi.gatefire.common.model.CallInfo;
import it.csi.gatefire.common.model.ErrorField;
import it.csi.gatefire.common.model.FileResult;
import it.csi.gatefire.common.model.ITIMetadata;
import it.csi.gatefire.common.model.Identity;
import it.csi.gatefire.common.model.MarkInput;
import it.csi.gatefire.common.model.MarkVerifyResult;
import it.csi.gatefire.common.model.OtpReqInput;
import it.csi.gatefire.common.model.OtpResult;
import it.csi.gatefire.common.model.PadesInput;
import it.csi.gatefire.common.model.PingResult;
import it.csi.gatefire.common.model.RepositoryDocIdInput;
import it.csi.gatefire.common.model.RepositoryInput;
import it.csi.gatefire.common.model.RepositoryMetadataUpdateInput;
import it.csi.gatefire.common.model.RepositoryQueryResult;
import it.csi.gatefire.common.model.RepositoryUndoInput;
import it.csi.gatefire.common.model.Result;
import it.csi.gatefire.common.model.SessionIdResult;
import it.csi.gatefire.common.model.SessionInput;
import it.csi.gatefire.common.model.SignIdentity;
import it.csi.gatefire.common.model.SignVerifyResult;
import it.csi.gatefire.common.util.ErrorHelper;
import it.csi.gatefire.common.util.JsonUtils;
import it.csi.gatefire.common.validator.InputValidator;
import it.csi.gatefire.dbhelper.model.GatefireDApplicazione;
import it.csi.gatefire.dbhelper.model.GatefireDCertificationAuthority;
import it.csi.gatefire.dbhelper.model.GatefireDRepository;
import it.csi.gatefire.dbhelper.model.GatefireDXdscode;
import it.csi.gatefire.dbhelper.model.GatefireLEvento;
import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativo;
import it.csi.gatefire.dbhelper.service.BaseService;
import it.csi.gatefire.dbhelper.service.CachedListsSrvc;
import it.csi.gatefire.dbhelper.service.EventoSrvc;
import it.csi.gatefire.dbhelper.service.VerifySrvc;
import it.csi.gatefire.dbhelper.util.ParamUtils;
import it.csi.gatefire.repository.service.IheSrvc;
import it.csi.gatefire.repository.validator.RepoValidator;

@WebService(name = "gateFireSrvc", serviceName = "gateFireSrvc", targetNamespace = "http://www.csi.it/gatefire/")
@Service("gateFireWSSrvc")
public class GateFireWSSrvcImpl extends BaseService implements GateFireWSSrvc {

	private static final String REPO_NON_CONFIG = "Repository non configurato per ";

	@Autowired
	private ArssSrvc arssSrvc;

	@Autowired
	private VolSrvc volSrvc;

	@Autowired
	private EventoSrvc eventoSrvc;

	@Autowired
	private CachedListsSrvc cachedListsSrvc;

	@Autowired
	private VerifySrvc verifySrvc;

	@Autowired
	private ProxySignSrvc proxySignSrvc;

	@Autowired
	private ProxySignVerSrvc proxySignVerSrvc;

	@Autowired
	private IheSrvc iheSrvc;

	private static final String PADES_INPUT = "padesInput";

	private static final String CALL_INFO = "callInfo";

	private static final String REPOSITORY_INPUT = "repositoryInput";

	@Override
	public PingResult ping(String user, CallInfo callInfo) {

		PingResult pingResult = new PingResult();

		String codiceCa = callInfo.getCaCode();
		logInfo("utente " + user + " chiamata PING per " + callInfo);

		if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(getTagCa(codiceCa))) {
			return arssSrvc.ping(callInfo);
		} else if (ParamsConsts.TAG_CA_INFOCERT.equalsIgnoreCase(getTagCa(codiceCa))) {
			return proxySignSrvc.ping(callInfo);
		}
		return pingResult;
	}

	public FileResult marcaTemporale(Attachment attachment, MarkInput markInput) {

		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();

		FileResult ret = new FileResult();

		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		String codiceCa = markInput.getCallInfo() != null ? markInput.getCallInfo().getCaCode() : null;
		String applicazione = markInput.getCallInfo() != null ? markInput.getCallInfo().getApplicationCode() : null;
		String codiceFiscale = markInput.getCallInfo() != null ? markInput.getCallInfo().getCodiceFiscale() : null;
		String idUtente = markInput.getMarkIdentity() != null ? markInput.getMarkIdentity().getUser() : null;
		String collocazione = markInput.getCallInfo() != null ? markInput.getCallInfo().getCollocazione() : null;

		String tagCa = getTagCa(codiceCa);
		if (tagCa != null) {
			evento.setCodiceCertificationAuthority(codiceCa);
		}
		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}

		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setIdUtente(idUtente);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_MARCA_TEMPORALE);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_CA);

		evento.setNomeFile(attachment.getFileName());
		evento.setIpApplicazione(getIp());

		String jsonInput = JsonUtils.fillObjects(attachment, markInput);
		evento = eventoSrvc.insertEvento(evento, jsonInput);

		boolean ok = true;
		eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
				GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
		String errorMessage = checkFunzCa(codiceCa, GatefireConsts.FUNZ_MARCA_TEMPORALE);
		if (errorMessage != null) {
			ok = false;
		}
		if (ok) {
			List<ErrorField> errorList = InputValidator.validate(markInput, getTagCa(codiceCa), getAppList(),
					"markInput");
			errorList.addAll(validateAttachment(attachment, list, codiceCa, applicazione,
					GatefireConsts.FUNZ_MARCA_TEMPORALE, null));

			if (!errorList.isEmpty()) {
				ok = false;
				errorMessage = JsonUtils.fillObject(errorList);
			}
		}
		if (ok) {
			if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(getTagCa(codiceCa))) {
				return arssSrvc.marcaTemporale(attachment, markInput, evento.getId());
			} else if (ParamsConsts.TAG_CA_INFOCERT.equalsIgnoreCase(getTagCa(codiceCa))) {
				return proxySignSrvc.marcaTemporale(attachment, markInput, evento.getId());
			}

		} else {

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);

			ret.setResult(result);
			return ret;
		}
		return null;

	}

	@Override
	public FileResult firmaPAdESRemota(Attachment attachment, PadesInput padesInput, SignIdentity signIdentity) {

		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();

		FileResult ret = new FileResult();
		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		String codiceCa = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCaCode() : null;

		String applicazione = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getApplicationCode() : null;
		String codiceFiscale = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCodiceFiscale() : null;
		String collocazione = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCollocazione() : null;

		String idUtente = signIdentity.getUser();
		String idSessione = signIdentity.getSessionId();
		evento.setIdSessione(idSessione);

		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}

		if (getTagCa(codiceCa) != null) {
			evento.setCodiceCertificationAuthority(codiceCa);
		}
		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setIdUtente(idUtente);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_FIRMA_PADES_REMOTA);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_CA);

		evento.setNomeFile(attachment.getFileName());
		evento.setIpApplicazione(getIp());

		String jsonInput = JsonUtils.fillObjects(attachment, padesInput, signIdentity);

		evento = eventoSrvc.insertEvento(evento, jsonInput);

		boolean ok = true;
		eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
				GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
		String errorMessage = null;
		errorMessage = checkFunzCa(codiceCa, GatefireConsts.FUNZ_FIRMA_PADES_REMOTA);
		if (errorMessage != null) {
			ok = false;
		}
		if (ok) {
			List<ErrorField> errorList = InputValidator.validate(padesInput, getTagCa(codiceCa), getAppList(),
					PADES_INPUT);
			errorList.addAll(
					InputValidator.validate(signIdentity, getTagCa(codiceCa), false, InputValidator.IDENTITY_PATH));

			errorList.addAll(validateAttachment(attachment, list, codiceCa, applicazione,
					GatefireConsts.FUNZ_FIRMA_PADES_REMOTA, null));

			if (!errorList.isEmpty()) {
				ok = false;
				errorMessage = JsonUtils.fillObject(errorList);
			}
		}
		if (ok) {
			if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(getTagCa(codiceCa))) {
				return arssSrvc.firmaPAdESRemota(attachment, padesInput, signIdentity, evento.getId(), true);
			} else if (ParamsConsts.TAG_CA_INFOCERT.equalsIgnoreCase(getTagCa(codiceCa))) {
				return proxySignSrvc.firmaPAdESRemota(attachment, padesInput, signIdentity, evento.getId(), true);
			}

		} else {

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);

			ret.setResult(result);
			return ret;
		}
		return null;
	}

	public FileResult firmaPAdESRemotaConArchiviaz(Attachment attachment, PadesInput padesInput,
			SignIdentity signIdentity, ITIMetadata itiMetadata, AssertionIdentity assertionIdentity) {

		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();

		FileResult ret = new FileResult();
		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		String codiceCa = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCaCode() : null;

		String applicazione = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getApplicationCode() : null;
		String codiceFiscale = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCodiceFiscale() : null;
		String collocazione = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCollocazione() : null;

		String idUtente = signIdentity.getUser();
		String idSessione = signIdentity.getSessionId();
		evento.setIdSessione(idSessione);

		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}

		if (getTagCa(codiceCa) != null) {
			evento.setCodiceCertificationAuthority(codiceCa);
		}
		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setIdUtente(idUtente);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_FIRMA_PADES_REMOTA);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_CA);

		evento.setNomeFile(attachment.getFileName());
		evento.setIpApplicazione(getIp());

		String jsonInput = JsonUtils.fillObjects(attachment, padesInput, signIdentity);

		evento = eventoSrvc.insertEvento(evento, jsonInput);

		boolean ok = true;
		eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
				GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
		String errorMessage = null;
		errorMessage = checkFunzCa(codiceCa, GatefireConsts.FUNZ_FIRMA_PADES_REMOTA);
		if (errorMessage != null) {
			ok = false;
		}
		if (ok) {
			List<ErrorField> errorList = InputValidator.validate(padesInput, getTagCa(codiceCa), getAppList(),
					PADES_INPUT);
			errorList.addAll(
					InputValidator.validate(signIdentity, getTagCa(codiceCa), false, InputValidator.IDENTITY_PATH));

			errorList.addAll(validateAttachment(attachment, list, codiceCa, applicazione,
					GatefireConsts.FUNZ_FIRMA_PADES_REMOTA, null));

			if (!errorList.isEmpty()) {
				ok = false;
				errorMessage = JsonUtils.fillObject(errorList);
			}
		}
		if (ok) {
			if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(getTagCa(codiceCa))) {
				ret = arssSrvc.firmaPAdESRemota(attachment, padesInput, signIdentity, evento.getId(), false);
			} else if (ParamsConsts.TAG_CA_INFOCERT.equalsIgnoreCase(getTagCa(codiceCa))) {
				ret = proxySignSrvc.firmaPAdESRemota(attachment, padesInput, signIdentity, evento.getId(), false);
			}

			if (ErrorHelper.OK.equalsIgnoreCase(ret.getResult().getErrorCode())) {
				RepositoryInput repoIn = new RepositoryInput();
				repoIn.setAttachment(ret.getAttachment());

				repoIn.setMetadata(itiMetadata);

				eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
						GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
				Result repoResult;
				try {
					repoResult = iheSrvc.archiviaDoc(repoIn, padesInput.getCallInfo(), null, evento.getId());
					if (ErrorHelper.OK.equalsIgnoreCase(repoResult.getErrorCode())) {
						eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
								GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, repoResult.getOriginalReturnCode(),
								repoResult.getMessage());

					} else {
						eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
								GatefireConsts.EVT_LOG_STATO_ERRORE, null, repoResult.getOriginalReturnCode(),
								repoResult.getMessage());
					}

					ret.setResult(repoResult);
				} catch (Exception e) {
					repoResult = new Result();
					repoResult.setErrorCode(ErrorHelper.ERRORE_REPOSITORY);
					repoResult.setDescription(getText(ErrorHelper.ERRORE_REPOSITORY));
					repoResult.setMessage(e.getMessage());
					eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
							GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
					logError(e);
				}
				if (ErrorHelper.OK.equalsIgnoreCase(repoResult.getErrorCode())) {

					eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null);

				} else {
					eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
				}

			} else {
				eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
			}

			return ret;

		} else {

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);

			ret.setResult(result);
			return ret;
		}

	}

	@Override
	public FileResult firmaPAdES(Attachment attachment, PadesInput padesInput, Identity identity) {

		if ((identity != null && StringUtils.hasLength(identity.getOtp())) || identity == null) {
			SignIdentity signIdentity = new SignIdentity();
			if (identity != null) {
				signIdentity.setOtp(identity.getOtp());
				signIdentity.setUser(identity.getUser());
				signIdentity.setPassword(identity.getPassword());

			}
			return firmaPAdESRemota(attachment, padesInput, signIdentity);
		} else {
			AutoSignIdentity signIdentity = new AutoSignIdentity();

			signIdentity.setOtp(identity.getOtp());
			signIdentity.setUser(identity.getUser());
			signIdentity.setPassword(identity.getPassword());

			return firmaPAdESAutomatica(attachment, padesInput, signIdentity);

		}

	}

	@Override
	public FileResult firmaPAdESConArchiviaz(Attachment attachment, PadesInput padesInput, Identity identity,
			ITIMetadata itiMetadata, AssertionIdentity assertionIdentity) {

		if ((identity != null && StringUtils.hasLength(identity.getOtp())) || identity == null) {
			SignIdentity signIdentity = new SignIdentity();
			if (identity != null) {
				signIdentity.setOtp(identity.getOtp());
				signIdentity.setUser(identity.getUser());
				signIdentity.setPassword(identity.getPassword());

			}
			return firmaPAdESRemotaConArchiviaz(attachment, padesInput, signIdentity, itiMetadata, assertionIdentity);
		} else {
			AutoSignIdentity signIdentity = new AutoSignIdentity();

			signIdentity.setOtp(identity.getOtp());
			signIdentity.setUser(identity.getUser());
			signIdentity.setPassword(identity.getPassword());

			return firmaPAdESAutomaticaConArchiviaz(attachment, padesInput, signIdentity, itiMetadata,
					assertionIdentity);

		}

	}

	@Override
	public FileResult firmaPAdESAutomatica(Attachment attachment, PadesInput padesInput,
			AutoSignIdentity signIdentity) {

		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();

		FileResult ret = new FileResult();
		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		String codiceCa = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCaCode() : null;

		String applicazione = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getApplicationCode() : null;
		String codiceFiscale = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCodiceFiscale() : null;
		String collocazione = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCollocazione() : null;

		String idUtente = signIdentity.getUser();

		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}

		if (getTagCa(codiceCa) != null) {
			evento.setCodiceCertificationAuthority(codiceCa);
		}
		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setIdUtente(idUtente);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_FIRMA_PADES_AUTOMATICA);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_CA);

		evento.setNomeFile(attachment.getFileName());
		evento.setIpApplicazione(getIp());

		String jsonInput = JsonUtils.fillObjects(attachment, padesInput, signIdentity);

		evento = eventoSrvc.insertEvento(evento, jsonInput);

		boolean ok = true;
		eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
				GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
		String errorMessage = checkFunzCa(codiceCa, GatefireConsts.FUNZ_FIRMA_PADES_AUTOMATICA);
		if (errorMessage != null) {
			ok = false;
		}
		if (ok) {
			List<ErrorField> errorList = InputValidator.validate(padesInput, getTagCa(codiceCa), getAppList(),
					PADES_INPUT);
			errorList.addAll(InputValidator.validate(signIdentity, getTagCa(codiceCa), InputValidator.IDENTITY_PATH));
			errorList.addAll(validateAttachment(attachment, list, codiceCa, applicazione,
					GatefireConsts.FUNZ_FIRMA_PADES_AUTOMATICA, null));

			if (!errorList.isEmpty()) {
				ok = false;
				errorMessage = JsonUtils.fillObject(errorList);
			}
		}
		if (ok) {
			if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(getTagCa(codiceCa))) {
				return arssSrvc.firmaPAdESAutomatica(attachment, padesInput, signIdentity, evento.getId(), true);
			} else if (ParamsConsts.TAG_CA_INFOCERT.equalsIgnoreCase(getTagCa(codiceCa))) {
				return proxySignSrvc.firmaPAdESAutomatica(attachment, padesInput, signIdentity, evento.getId(), true);
			}

		} else {

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);

			ret.setResult(result);
			return ret;
		}
		return null;
	}

	@Override
	public FileResult firmaPAdESAutomaticaConArchiviaz(Attachment attachment, PadesInput padesInput,
			AutoSignIdentity signIdentity, ITIMetadata itiMetadata, AssertionIdentity assertionIdentity) {

		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();

		FileResult ret = new FileResult();
		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		String codiceCa = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCaCode() : null;

		String applicazione = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getApplicationCode() : null;
		String codiceFiscale = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCodiceFiscale() : null;
		String collocazione = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCollocazione() : null;

		String idUtente = signIdentity.getUser();

		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}

		if (getTagCa(codiceCa) != null) {
			evento.setCodiceCertificationAuthority(codiceCa);
		}
		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setIdUtente(idUtente);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_FIRMA_PADES_AUTOMATICA);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_CA);

		evento.setNomeFile(attachment.getFileName());
		evento.setIpApplicazione(getIp());

		String jsonInput = JsonUtils.fillObjects(attachment, padesInput, signIdentity);

		evento = eventoSrvc.insertEvento(evento, jsonInput);

		boolean ok = true;
		eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
				GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
		String errorMessage = checkFunzCa(codiceCa, GatefireConsts.FUNZ_FIRMA_PADES_AUTOMATICA);
		if (errorMessage != null) {
			ok = false;
		}
		if (ok) {
			List<ErrorField> errorList = InputValidator.validate(padesInput, getTagCa(codiceCa), getAppList(),
					PADES_INPUT);
			errorList.addAll(InputValidator.validate(signIdentity, getTagCa(codiceCa), InputValidator.IDENTITY_PATH));
			errorList.addAll(validateAttachment(attachment, list, codiceCa, applicazione,
					GatefireConsts.FUNZ_FIRMA_PADES_AUTOMATICA, null));

			if (!errorList.isEmpty()) {
				ok = false;
				errorMessage = JsonUtils.fillObject(errorList);
			}
		}
		if (ok) {
			if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(getTagCa(codiceCa))) {
				ret = arssSrvc.firmaPAdESAutomatica(attachment, padesInput, signIdentity, evento.getId(), false);
			} else if (ParamsConsts.TAG_CA_INFOCERT.equalsIgnoreCase(getTagCa(codiceCa))) {
				ret = proxySignSrvc.firmaPAdESAutomatica(attachment, padesInput, signIdentity, evento.getId(), false);
			}
			if (ErrorHelper.OK.equalsIgnoreCase(ret.getResult().getErrorCode())) {
				RepositoryInput repoIn = new RepositoryInput();
				repoIn.setAttachment(ret.getAttachment());

				repoIn.setMetadata(itiMetadata);

				eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
						GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
				Result repoResult;
				try {
					repoResult = iheSrvc.archiviaDoc(repoIn, padesInput.getCallInfo(), null, evento.getId());
					if (ErrorHelper.OK.equalsIgnoreCase(repoResult.getErrorCode())) {
						eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
								GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, repoResult.getOriginalReturnCode(),
								repoResult.getMessage());

					} else {
						eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
								GatefireConsts.EVT_LOG_STATO_ERRORE, null, repoResult.getOriginalReturnCode(),
								repoResult.getMessage());
					}

					ret.setResult(repoResult);
				} catch (Exception e) {
					repoResult = new Result();
					repoResult.setErrorCode(ErrorHelper.ERRORE_REPOSITORY);
					repoResult.setDescription(getText(ErrorHelper.ERRORE_REPOSITORY));
					repoResult.setMessage(e.getMessage());
					eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
							GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
					logError(e);
				}
				if (ErrorHelper.OK.equalsIgnoreCase(repoResult.getErrorCode())) {

					eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null);

				} else {
					eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
				}

			} else {
				eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
			}

			return ret;
		} else {

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);

			ret.setResult(result);
			return ret;
		}

	}

	private String getIp() {
		Message message = PhaseInterceptorChain.getCurrentMessage();
		HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
		return request.getRemoteAddr();
	}

	@Override
	public FileResult firmaPAdESMassivaRemota(List<Attachment> attachmentList, PadesInput padesInput,
			SignIdentity signIdentity) {
		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();

		FileResult ret = new FileResult();
		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		String codiceCa = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCaCode() : null;
		String applicazione = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getApplicationCode() : null;
		String codiceFiscale = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCodiceFiscale() : null;
		String collocazione = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCollocazione() : null;

		String idUtente = signIdentity.getUser();

		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}
		if (getTagCa(codiceCa) != null) {
			evento.setCodiceCertificationAuthority(codiceCa);
		}
		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setIdUtente(idUtente);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_FIRMA_PADES_MASSIVA_REMOTA);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_CA);
		StringBuilder nomefile = new StringBuilder("");
		boolean first = true;
		for (Attachment att : attachmentList) {
			if (first) {
				first = false;
			} else {
				nomefile.append(" | ");

			}
			nomefile.append(att.getFileName());
		}
		evento.setNomeFile(nomefile.toString());

		evento.setIpApplicazione(getIp());

		String jsonInput = JsonUtils.fillObjects(attachmentList, padesInput, signIdentity);

		evento = eventoSrvc.insertEvento(evento, jsonInput);
		boolean ok = true;
		eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
				GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
		String errorMessage = checkFunzCa(codiceCa, GatefireConsts.FUNZ_FIRMA_PADES_MASSIVA_REMOTA);
		if (errorMessage != null) {
			ok = false;
		}
		if (ok) {
			List<ErrorField> errorList = InputValidator.validate(padesInput, getTagCa(codiceCa), getAppList(),
					PADES_INPUT);
			errorList.addAll(
					InputValidator.validate(signIdentity, getTagCa(codiceCa), false, InputValidator.IDENTITY_PATH));
			for (Attachment attachment : attachmentList) {
				errorList.addAll(validateAttachment(attachment, list, codiceCa, applicazione,
						GatefireConsts.FUNZ_FIRMA_PADES_AUTOMATICA, null));

			}
			errorList.addAll(validateAttachmentMassiva(attachmentList, list, codiceCa, applicazione,
					GatefireConsts.FUNZ_FIRMA_PADES_AUTOMATICA, null));
			if (!errorList.isEmpty()) {
				ok = false;
				errorMessage = JsonUtils.fillObject(errorList);
			}
		}
		if (ok) {
			if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(getTagCa(codiceCa))) {
				return arssSrvc.firmaPAdESMassivaRemota(attachmentList, padesInput, signIdentity, evento.getId());
			} else if (ParamsConsts.TAG_CA_INFOCERT.equalsIgnoreCase(getTagCa(codiceCa))) {
				return proxySignSrvc.firmaPAdESMassivaRemota(attachmentList, padesInput, signIdentity, evento.getId());
			}

		} else {

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);

			ret.setResult(result);
			return ret;
		}
		return null;

	}

	@Override
	public FileResult firmaPAdESMassivaAutomatica(List<Attachment> attachmentList, PadesInput padesInput,
			AutoSignIdentity signIdentity) {
		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();

		FileResult ret = new FileResult();
		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		String codiceCa = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCaCode() : null;
		String applicazione = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getApplicationCode() : null;
		String codiceFiscale = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCodiceFiscale() : null;
		String collocazione = padesInput.getCallInfo() != null ? padesInput.getCallInfo().getCollocazione() : null;

		String idUtente = signIdentity.getUser();

		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}
		if (getTagCa(codiceCa) != null) {
			evento.setCodiceCertificationAuthority(codiceCa);
		}
		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setIdUtente(idUtente);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_FIRMA_PADES_MASSIVA_AUTOMATICA);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_CA);
		StringBuilder nomefile = new StringBuilder("");
		boolean first = true;
		for (Attachment att : attachmentList) {
			if (first) {
				first = false;
			} else {
				nomefile.append(" | ");

			}
			nomefile.append(att.getFileName());
		}
		evento.setNomeFile(nomefile.toString());

		evento.setIpApplicazione(getIp());

		String jsonInput = JsonUtils.fillObjects(attachmentList, padesInput, signIdentity);

		evento = eventoSrvc.insertEvento(evento, jsonInput);
		boolean ok = true;
		eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
				GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
		String errorMessage = checkFunzCa(codiceCa, GatefireConsts.FUNZ_FIRMA_PADES_MASSIVA_AUTOMATICA);
		if (errorMessage != null) {
			ok = false;
		}
		if (ok) {
			List<ErrorField> errorList = InputValidator.validate(padesInput, getTagCa(codiceCa), getAppList(),
					PADES_INPUT);
			errorList.addAll(InputValidator.validate(signIdentity, getTagCa(codiceCa), InputValidator.IDENTITY_PATH));
			for (Attachment attachment : attachmentList) {
				errorList.addAll(validateAttachment(attachment, list, codiceCa, applicazione,
						GatefireConsts.FUNZ_FIRMA_PADES_AUTOMATICA, null));

			}
			errorList.addAll(validateAttachmentMassiva(attachmentList, list, codiceCa, applicazione,
					GatefireConsts.FUNZ_FIRMA_PADES_AUTOMATICA, null));

			if (!errorList.isEmpty()) {
				ok = false;
				errorMessage = JsonUtils.fillObject(errorList);
			}
		}
		if (ok) {
			if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(getTagCa(codiceCa))) {
				return arssSrvc.firmaPAdESMassivaAutomatica(attachmentList, padesInput, signIdentity, evento.getId());
			} else if (ParamsConsts.TAG_CA_INFOCERT.equalsIgnoreCase(getTagCa(codiceCa))) {
				return proxySignSrvc.firmaPAdESMassivaAutomatica(attachmentList, padesInput, signIdentity,
						evento.getId());
			}

		} else {

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);

			ret.setResult(result);
			return ret;
		}
		return null;

	}

	public SignVerifyResult verificaFirma(Attachment attachment, CallInfo callInfo) {
		List<GatefireTParametroOperativo> list = cachedListsSrvc.getParametriGenerici();
		String codiceCa = ParamsConsts.TAG_CA_ARUBA;

		for (GatefireTParametroOperativo param : list) {
			if (param.getParametro().equalsIgnoreCase(ParamsConsts.PARAM_GEN_VERIFICATION_SERVICE)) {
				codiceCa = param.getValore();
				break;
			}
		}
		callInfo.setCaCode(codiceCa);
		String applicazione = callInfo != null ? callInfo.getApplicationCode() : null;
		String codiceFiscale = callInfo != null ? callInfo.getCodiceFiscale() : null;
		String collocazione = callInfo != null ? callInfo.getCollocazione() : null;

		SignVerifyResult ret = new SignVerifyResult();

		Date data = new Date();

		GatefireLEvento evento = new GatefireLEvento(data);
		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}
		evento.setIdUtente(null);
		if (getTagCa(codiceCa) != null) {
			evento.setCodiceCertificationAuthority(codiceCa);
		}
		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_FIRMA_VERIFICA);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_CA);
		evento.setNomeFile(attachment.getFileName());
		evento.setIpApplicazione(getIp());

		String jsonInput = JsonUtils.fillObjects(attachment, callInfo);
		evento = eventoSrvc.insertEvento(evento, jsonInput);

		eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
				GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
		if (callInfo != null) {
			if (callInfo.getCaCode() == null) {
				callInfo.setCaCode(codiceCa);
			}
			if (!StringUtils.hasLength(callInfo.getCollocazione())) {
				callInfo.setCollocazione(".");
			}
		}
		List<ErrorField> errorList = InputValidator.validate(callInfo, getTagCa(codiceCa), getAppList(), null);

		errorList.addAll(
				validateAttachment(attachment, list, codiceCa, applicazione, GatefireConsts.FUNZ_FIRMA_VERIFICA, null));
		boolean ok = true;
		String errorMessage = null;
		if (!errorList.isEmpty()) {
			ok = false;
			errorMessage = JsonUtils.fillObject(errorList);
		}
		if (ok) {
			if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(codiceCa)) {
				ret = volSrvc.verificaFirma(attachment, ParamsConsts.TAG_CA_ARUBA, evento.getId());
			} else if (ParamsConsts.TAG_CA_INFOCERT.equalsIgnoreCase(codiceCa)) {
				ret = proxySignVerSrvc.verificaFirma(attachment, ParamsConsts.TAG_CA_INFOCERT, evento.getId());
			}

		} else {

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);

			ret.setResult(result);
			return ret;
		}
		return ret;

	}

	public MarkVerifyResult verificaMarca(Attachment attachment, CallInfo callInfo) {
		List<GatefireTParametroOperativo> listGen = cachedListsSrvc.getParametriGenerici();
		String codiceCa = ParamsConsts.TAG_CA_ARUBA;

		for (GatefireTParametroOperativo param : listGen) {
			if (param.getParametro().equalsIgnoreCase(ParamsConsts.PARAM_GEN_VERIFICATION_SERVICE)) {
				codiceCa = param.getValore();
				break;
			}
		}
		callInfo.setCaCode(codiceCa);
		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();
		String applicazione = callInfo != null ? callInfo.getApplicationCode() : null;
		String codiceFiscale = callInfo != null ? callInfo.getCodiceFiscale() : null;
		String collocazione = callInfo != null ? callInfo.getCollocazione() : null;

		MarkVerifyResult ret = new MarkVerifyResult();
		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}
		evento.setIdUtente(null);
		if (getTagCa(codiceCa) != null) {
			evento.setCodiceCertificationAuthority(codiceCa);
		}
		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_MARCA_VERIFICA);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_CA);
		evento.setIpApplicazione(getIp());
		evento.setNomeFile(attachment.getFileName());

		String jsonInput = JsonUtils.fillObjects(attachment, callInfo);
		evento = eventoSrvc.insertEvento(evento, jsonInput);
		if (callInfo != null) {
			if (callInfo.getCaCode() == null) {
				callInfo.setCaCode(codiceCa);
			}
			if (!StringUtils.hasLength(callInfo.getCollocazione())) {
				callInfo.setCollocazione(".");
			}
		}
		List<ErrorField> errorList = InputValidator.validate(callInfo, getTagCa(codiceCa), getAppList(), null);

		errorList.addAll(
				validateAttachment(attachment, list, codiceCa, applicazione, GatefireConsts.FUNZ_MARCA_VERIFICA, null));
		boolean ok = true;
		String errorMessage = null;
		if (!errorList.isEmpty()) {
			ok = false;
			errorMessage = JsonUtils.fillObject(errorList);
		}
		if (ok) {
			if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(codiceCa)) {
				ret = volSrvc.verificaMarca(attachment, null, ParamsConsts.TAG_CA_ARUBA, evento.getId());
			} else if (ParamsConsts.TAG_CA_INFOCERT.equalsIgnoreCase(codiceCa)) {

				ret = proxySignVerSrvc.verificaMarca(attachment, null, ParamsConsts.TAG_CA_INFOCERT, evento.getId());
			}

		} else {

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);

			ret.setResult(result);
			return ret;
		}
		return ret;

	}

	private String getTagCa(String codiceCa) {
		List<GatefireDCertificationAuthority> list = cachedListsSrvc.getAllCa();

		for (GatefireDCertificationAuthority ca : list) {
			if (ca.getCodice().equalsIgnoreCase(codiceCa)) {
				return ca.getTagCa();
			}
		}
		return null;

	}

	private List<String> getAppList() {

		List<String> retList = new ArrayList<>();
		List<GatefireDApplicazione> list = cachedListsSrvc.getAllApplicazioni();
		for (GatefireDApplicazione gatefireDApplicazione : list) {
			retList.add(gatefireDApplicazione.getCodice());
		}
		return retList;
	}

	private boolean isApplicazioneEsistente(String applicazione) {
		if (applicazione == null) {
			return false;
		}
		return getAppList().contains(applicazione);

	}

	private List<ErrorField> validateAttachment(Attachment input, List<GatefireTParametroOperativo> list,
			String codiceCa, String applicazione, String funzione, String path) {
		String maxsize = ParamUtils.getParam(list, ParamsConsts.PARAM_MAX_FILE_SIZE, codiceCa, applicazione, funzione,
				null);
		Long lMaxSize = null;

		try {
			lMaxSize = Long.parseLong(maxsize);
		} catch (Exception e) {
			logError(e);
		}

		if (path == null) {
			path = InputValidator.ATTACHMENT_PATH;
		}
		return InputValidator.validate(input, path, lMaxSize);
	}

	private List<ErrorField> validateAttachmentMassiva(List<Attachment> input, List<GatefireTParametroOperativo> list,
			String codiceCa, String applicazione, String funzione, String path) {
		String maxsize = ParamUtils.getParam(list, ParamsConsts.PARAM_MAX_FILE_SIZE_MASSIVE, codiceCa, applicazione,
				funzione, null);
		Long lMaxSize = null;

		try {
			lMaxSize = Long.parseLong(maxsize);
		} catch (Exception e) {
			logError(e);
		}

		if (path == null) {
			path = InputValidator.ATTACHMENT_PATH;
		}

		String numMaxfiles = ParamUtils.getParam(list, ParamsConsts.PARAM_MAX_N_FILE_MASSIVE, codiceCa, applicazione,
				funzione, null);
		Long lNumMaxfiles = null;

		try {
			lNumMaxfiles = Long.parseLong(numMaxfiles);
		} catch (Exception e) {
			logError(e);
		}

		return InputValidator.validate(input, path, lMaxSize, lNumMaxfiles);
	}

	@Override
	public MarkVerifyResult verificaMarcaDetached(Attachment mark, Attachment originalFile, CallInfo callInfo) {
		List<GatefireTParametroOperativo> listGen = cachedListsSrvc.getParametriGenerici();
		String codiceCa = ParamsConsts.TAG_CA_ARUBA;

		for (GatefireTParametroOperativo param : listGen) {
			if (param.getParametro().equalsIgnoreCase(ParamsConsts.PARAM_GEN_VERIFICATION_SERVICE)) {
				codiceCa = param.getValore();
				break;
			}
		}
		callInfo.setCaCode(codiceCa);
		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();
		String applicazione = callInfo != null ? callInfo.getApplicationCode() : null;
		String codiceFiscale = callInfo != null ? callInfo.getCodiceFiscale() : null;
		String collocazione = callInfo != null ? callInfo.getCollocazione() : null;

		MarkVerifyResult ret = new MarkVerifyResult();
		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}

		evento.setIdUtente(null);
		if (getTagCa(codiceCa) != null) {
			evento.setCodiceCertificationAuthority(codiceCa);
		}
		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_MARCA_VERIFICA);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_CA);
		evento.setIpApplicazione(getIp());
		evento.setNomeFile(mark.getFileName());

		String jsonInput = JsonUtils.fillObjects(mark, originalFile, callInfo);
		evento = eventoSrvc.insertEvento(evento, jsonInput);
		if (callInfo != null) {
			if (callInfo.getCaCode() == null) {
				callInfo.setCaCode(codiceCa);
			}
			if (!StringUtils.hasLength(callInfo.getCollocazione())) {
				callInfo.setCollocazione(".");
			}
		}
		List<ErrorField> errorList = InputValidator.validate(callInfo, getTagCa(codiceCa), getAppList(), null);

		errorList.addAll(
				validateAttachment(mark, list, codiceCa, applicazione, GatefireConsts.FUNZ_MARCA_VERIFICA, null));

		errorList.addAll(validateAttachment(originalFile, list, codiceCa, applicazione,
				GatefireConsts.FUNZ_MARCA_VERIFICA, null));
		boolean ok = true;
		String errorMessage = null;
		if (!errorList.isEmpty()) {
			ok = false;
			errorMessage = JsonUtils.fillObject(errorList);
		}
		if (ok) {
			if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(codiceCa)) {
				String timestampToken = Base64.getEncoder().encodeToString(mark.getFile());
				ret = volSrvc.verificaMarca(originalFile, timestampToken, ParamsConsts.TAG_CA_ARUBA, evento.getId());
			} else if (ParamsConsts.TAG_CA_INFOCERT.equalsIgnoreCase(codiceCa)) {
				ret = proxySignVerSrvc.verificaMarca(mark, originalFile, ParamsConsts.TAG_CA_INFOCERT, evento.getId());

			}

		} else {

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);

			ret.setResult(result);
			return ret;
		}
		return ret;
	}

	@Override
	public SessionIdResult openSession(SessionInput sessionInput) {

		SessionIdResult ret = new SessionIdResult();
		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		String codiceCa = sessionInput.getCallInfo() != null ? sessionInput.getCallInfo().getCaCode() : null;

		String applicazione = sessionInput.getCallInfo() != null ? sessionInput.getCallInfo().getApplicationCode()
				: null;
		String codiceFiscale = sessionInput.getCallInfo() != null ? sessionInput.getCallInfo().getCodiceFiscale()
				: null;
		String collocazione = sessionInput.getCallInfo() != null ? sessionInput.getCallInfo().getCollocazione() : null;

		String idUtente = sessionInput.getSignIdentity() != null ? sessionInput.getSignIdentity().getUser() : null;

		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}
		if (getTagCa(codiceCa) != null) {
			evento.setCodiceCertificationAuthority(codiceCa);
		}
		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setIdUtente(idUtente);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_SESSIONE_APERTURA);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_CA);

		evento.setIpApplicazione(getIp());

		String jsonInput = JsonUtils.fillObjects(sessionInput);

		evento = eventoSrvc.insertEvento(evento, jsonInput);

		boolean ok = true;
		eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
				GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
		String errorMessage = checkFunzCa(codiceCa, GatefireConsts.FUNZ_SESSIONE_APERTURA);
		if (errorMessage != null) {
			ok = false;
		}
		if (ok) {
			List<ErrorField> errorList = InputValidator.validate(sessionInput, getTagCa(codiceCa), getAppList(), false,
					"sessionInput");

			if (!errorList.isEmpty()) {
				ok = false;
				errorMessage = JsonUtils.fillObject(errorList);
			}
		}
		if (ok) {
			if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(getTagCa(codiceCa))) {
				return arssSrvc.openSession(sessionInput, evento.getId());
			}

		} else {

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);

			ret.setResult(result);
			return ret;
		}
		return null;
	}

	@Override
	public Result closeSession(String sessionId, SessionInput sessionInput) {

		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		String codiceCa = sessionInput.getCallInfo() != null ? sessionInput.getCallInfo().getCaCode() : null;
		String applicazione = sessionInput.getCallInfo() != null ? sessionInput.getCallInfo().getApplicationCode()
				: null;
		String codiceFiscale = sessionInput.getCallInfo() != null ? sessionInput.getCallInfo().getCodiceFiscale()
				: null;
		String collocazione = sessionInput.getCallInfo() != null ? sessionInput.getCallInfo().getCollocazione() : null;

		String idUtente = sessionInput.getSignIdentity() != null ? sessionInput.getSignIdentity().getUser() : null;

		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}
		if (getTagCa(codiceCa) != null) {
			evento.setCodiceCertificationAuthority(codiceCa);
		}
		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setIdUtente(idUtente);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_SESSIONE_CHIUSURA);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_CA);

		evento.setIpApplicazione(getIp());

		String jsonInput = JsonUtils.fillObjects(sessionInput);

		evento = eventoSrvc.insertEvento(evento, jsonInput);

		boolean ok = true;
		eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
				GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);

		String errorMessage = checkFunzCa(codiceCa, GatefireConsts.FUNZ_SESSIONE_CHIUSURA);
		if (errorMessage != null) {
			ok = false;
		}
		if (ok) {
			List<ErrorField> errorList = new ArrayList<>();
			if (!StringUtils.hasLength(sessionId)) {
				errorList.add(new ErrorField("sessionId", ErrorField.CAMPO_OBBLIGATORIO));
			}

			errorList.addAll(
					InputValidator.validate(sessionInput, getTagCa(codiceCa), getAppList(), true, "sessionInput"));

			if (!errorList.isEmpty()) {
				ok = false;
				errorMessage = JsonUtils.fillObject(errorList);
			}
		}

		if (ok) {
			if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(getTagCa(codiceCa))) {
				return arssSrvc.closeSession(sessionId, sessionInput, evento.getId());
			}

		} else {

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);

			return result;
		}

		return null;
	}

	private String checkFunzCa(String codiceCa, String codiceFunzione) {
		boolean ok = false;
		String ret = null;
		if (codiceCa != null) {
			ok = verifySrvc.checkfunzioneCa(codiceCa, codiceFunzione);
		}
		if (!ok) {
			ret = "Funzione [" + codiceFunzione + "] non disponibile per CA [" + codiceCa + "]";
		}
		return ret;

	}

	@Override
	public OtpResult richiestaOtp(OtpReqInput otpReqInput) {
		OtpResult ret = new OtpResult();
		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		String codiceCa = otpReqInput.getCallInfo() != null ? otpReqInput.getCallInfo().getCaCode() : null;

		String applicazione = otpReqInput.getCallInfo() != null ? otpReqInput.getCallInfo().getApplicationCode() : null;
		String codiceFiscale = otpReqInput.getCallInfo() != null ? otpReqInput.getCallInfo().getCodiceFiscale() : null;
		String collocazione = otpReqInput.getCallInfo() != null ? otpReqInput.getCallInfo().getCollocazione() : null;

		String idUtente = otpReqInput.getIdentity() != null ? otpReqInput.getIdentity().getUser() : null;

		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}
		if (getTagCa(codiceCa) != null) {
			evento.setCodiceCertificationAuthority(codiceCa);
		}
		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setIdUtente(idUtente);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_RICHIESTA_OTP);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_CA);

		evento.setIpApplicazione(getIp());

		String jsonInput = JsonUtils.fillObjects(otpReqInput);

		evento = eventoSrvc.insertEvento(evento, jsonInput);

		boolean ok = true;
		eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
				GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
		String errorMessage = checkFunzCa(codiceCa, GatefireConsts.FUNZ_RICHIESTA_OTP);
		if (errorMessage != null) {
			ok = false;
		}
		if (ok) {
			List<ErrorField> errorList = InputValidator.validate(otpReqInput, getTagCa(codiceCa), getAppList(), null);

			if (!errorList.isEmpty()) {
				ok = false;
				errorMessage = JsonUtils.fillObject(errorList);
			}
		}
		if (ok) {
			if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(getTagCa(codiceCa))) {
				return arssSrvc.requestOtp(otpReqInput, evento.getId());
			} else if (ParamsConsts.TAG_CA_INFOCERT.equalsIgnoreCase(getTagCa(codiceCa))) {
				return proxySignSrvc.requestOtp(otpReqInput, evento.getId());
			}

		} else {

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);

			ret.setResult(result);
			return ret;
		}
		return null;
	}

	@Override
	public FileResult firmaPAdESMassiva(List<Attachment> attachment, PadesInput padesInput, Identity identity) {
		if ((identity != null && StringUtils.hasLength(identity.getOtp())) || identity == null) {
			SignIdentity signIdentity = new SignIdentity();
			if (identity != null) {
				signIdentity.setOtp(identity.getOtp());
				signIdentity.setUser(identity.getUser());
				signIdentity.setPassword(identity.getPassword());

			}
			return firmaPAdESMassivaRemota(attachment, padesInput, signIdentity);
		} else {
			AutoSignIdentity signIdentity = new AutoSignIdentity();

			signIdentity.setOtp(identity.getOtp());
			signIdentity.setUser(identity.getUser());
			signIdentity.setPassword(identity.getPassword());

			return firmaPAdESMassivaAutomatica(attachment, padesInput, signIdentity);

		}

	}

	@Override
	public Result archivia(RepositoryInput input, CallInfo callInfo) {

		if (callInfo == null) {
			callInfo = new CallInfo();
		}
		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();
		List<GatefireDRepository> repoList = cachedListsSrvc.getAllRepository();

		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		String codiceCa = callInfo != null ? callInfo.getCaCode() : null;
		String applicazione = callInfo != null ? callInfo.getApplicationCode() : null;
		String codiceFiscale = callInfo != null ? callInfo.getCodiceFiscale() : null;
		String idUtente = null;
		String collocazione = callInfo != null ? callInfo.getCollocazione() : null;

		if (codiceCa == null) {
			codiceCa = ParamsConsts.TAG_CA_ARUBA;
			callInfo.setCaCode(codiceCa);
		}

		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}

		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setIdUtente(idUtente);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_ARCHIVIA_FILE);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_REPOSITORY);

		evento.setNomeFile(input.getAttachment().getFileName());
		evento.setIpApplicazione(getIp());

		String jsonInput = JsonUtils.fillObjects(input, callInfo);
		evento = eventoSrvc.insertEvento(evento, jsonInput);

		boolean ok = true;
		eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
				GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
		String errorMessage = null;

		GatefireDRepository repo = getRepository(repoList, callInfo);
		if (repo == null) {
			Result ret = new Result();
			ret.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			ret.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			ret.setMessage(REPO_NON_CONFIG + callInfo);

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, REPO_NON_CONFIG + callInfo, null, null);
			logError(REPO_NON_CONFIG + callInfo);
			return ret;
		}
		boolean checkAuth = true;
		if (repo.getAuthenticationRequired() != null && !repo.getAuthenticationRequired().booleanValue()) {
			checkAuth = false;
		}

		Map<String, List<GatefireDXdscode>> map = fillXdsCodes();
		List<ErrorField> errorList = RepoValidator.validate(input, map, checkAuth, REPOSITORY_INPUT);

		errorList.addAll(InputValidator.validate(callInfo, getTagCa(codiceCa), getAppList(), CALL_INFO));
		errorList.addAll(validateAttachment(input.getAttachment(), list, codiceCa, applicazione,
				GatefireConsts.FUNZ_ARCHIVIA_FILE, "repositoryInput.attachment"));

		if (!errorList.isEmpty()) {
			ok = false;
			errorMessage = JsonUtils.fillObject(errorList);
		}

		if (ok) {
			return iheSrvc.archiviaDoc(input, callInfo, evento.getId(), null);

		} else {

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);

			return result;
		}

	}

	@Override
	public Result modificaMetadati(RepositoryMetadataUpdateInput input, CallInfo callInfo) {

		if (callInfo == null) {
			callInfo = new CallInfo();
		}
		// List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();
		List<GatefireDRepository> repoList = cachedListsSrvc.getAllRepository();

		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		String codiceCa = callInfo != null ? callInfo.getCaCode() : null;
		String applicazione = callInfo != null ? callInfo.getApplicationCode() : null;
		String codiceFiscale = callInfo != null ? callInfo.getCodiceFiscale() : null;
		String idUtente = null;
		String collocazione = callInfo != null ? callInfo.getCollocazione() : null;

		if (codiceCa == null) {
			codiceCa = ParamsConsts.TAG_CA_ARUBA;
			callInfo.setCaCode(codiceCa);
		}

		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}

		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setIdUtente(idUtente);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_MODIFICA_METADATI);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_REPOSITORY);

		evento.setIpApplicazione(getIp());

		String jsonInput = JsonUtils.fillObjects(input, callInfo);
		evento = eventoSrvc.insertEvento(evento, jsonInput);

		boolean ok = true;
		eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
				GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
		String errorMessage = null;

		GatefireDRepository repo = getRepository(repoList, callInfo);
		if (repo == null) {
			Result ret = new Result();
			ret.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			ret.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			ret.setMessage(REPO_NON_CONFIG + callInfo);

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, REPO_NON_CONFIG + callInfo, null, null);
			logError(REPO_NON_CONFIG + callInfo);
			return ret;
		}
		boolean checkAuth = true;
		if (repo.getAuthenticationRequired() != null && !repo.getAuthenticationRequired().booleanValue()) {
			checkAuth = false;
		}

		// Map<String, List<GatefireDXdscode>> map = fillXdsCodes();
		List<ErrorField> errorList = RepoValidator.validate(input, checkAuth, REPOSITORY_INPUT);

		errorList.addAll(InputValidator.validate(callInfo, getTagCa(codiceCa), getAppList(), CALL_INFO));

		if (!errorList.isEmpty()) {
			ok = false;
			errorMessage = JsonUtils.fillObject(errorList);
		}

		if (ok) {
			return iheSrvc.modificaMetadati(input, callInfo, evento.getId());

		} else {

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);

			return result;
		}

	}

	private GatefireDRepository getRepository(List<GatefireDRepository> list, CallInfo callInfo) {
		if (list != null && callInfo != null) {
			for (GatefireDRepository repo : list) {
				if (repo.getCollocazione().equalsIgnoreCase(callInfo.getCollocazione())) {

					return repo;
				}

			}
		}

		return null;
	}

	private Map<String, List<GatefireDXdscode>> fillXdsCodes() {

		Map<String, List<GatefireDXdscode>> map = new HashMap<>();

		List<GatefireDXdscode> list = cachedListsSrvc.getAllXdsCodes();

		for (GatefireDXdscode xcode : list) {
			String codeType = xcode.getCodeType();
			if (map.get(codeType) != null) {
				map.get(codeType).add(xcode);

			} else {
				List<GatefireDXdscode> temp = new ArrayList<>();
				temp.add(xcode);
				map.put(codeType, temp);
			}

		}
		return map;
	}

	@Override
	public FileResult recuperaDocumento(RepositoryDocIdInput input, CallInfo callInfo) {

		if (callInfo == null) {
			callInfo = new CallInfo();
		}

		List<GatefireDRepository> repoList = cachedListsSrvc.getAllRepository();

		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		String codiceCa = callInfo != null ? callInfo.getCaCode() : null;
		String applicazione = callInfo != null ? callInfo.getApplicationCode() : null;
		String codiceFiscale = callInfo != null ? callInfo.getCodiceFiscale() : null;
		String idUtente = null;
		String collocazione = callInfo != null ? callInfo.getCollocazione() : null;

		if (codiceCa == null) {
			codiceCa = ParamsConsts.TAG_CA_ARUBA;
			callInfo.setCaCode(codiceCa);
		}

		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}

		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setIdUtente(idUtente);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_RECUPERA_FILE);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_REPOSITORY);

		evento.setIpApplicazione(getIp());

		String jsonInput = JsonUtils.fillObjects(input, callInfo);
		evento = eventoSrvc.insertEvento(evento, jsonInput);

		boolean ok = true;
		eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
				GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
		String errorMessage = null;

		GatefireDRepository repo = getRepository(repoList, callInfo);
		if (repo == null) {
			FileResult result = new FileResult();
			Result ret = new Result();
			ret.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			ret.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			ret.setMessage(REPO_NON_CONFIG + callInfo);

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, REPO_NON_CONFIG + callInfo, null, null);
			logError(REPO_NON_CONFIG + callInfo);

			result.setResult(ret);
			return result;
		}
		boolean checkAuth = true;
		if (repo.getAuthenticationRequired() != null && !repo.getAuthenticationRequired().booleanValue()) {
			checkAuth = false;
		}

		List<ErrorField> errorList = InputValidator.validate(callInfo, getTagCa(codiceCa), getAppList(), CALL_INFO);

		errorList.addAll(RepoValidator.validate(input, checkAuth, REPOSITORY_INPUT));

		if (!errorList.isEmpty()) {
			ok = false;
			errorMessage = JsonUtils.fillObject(errorList);
		}

		if (ok) {
			return iheSrvc.recuperaDoc(input, callInfo, evento.getId());

		} else {
			FileResult ret = new FileResult();
			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);
			ret.setResult(result);
			return ret;
		}
	}

	@Override
	public RepositoryQueryResult recuperaMetadataDocumento(RepositoryDocIdInput input, CallInfo callInfo) {
		if (callInfo == null) {
			callInfo = new CallInfo();
		}

		List<GatefireDRepository> repoList = cachedListsSrvc.getAllRepository();

		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		String codiceCa = callInfo != null ? callInfo.getCaCode() : null;
		String applicazione = callInfo != null ? callInfo.getApplicationCode() : null;
		String codiceFiscale = callInfo != null ? callInfo.getCodiceFiscale() : null;
		String idUtente = null;
		String collocazione = callInfo != null ? callInfo.getCollocazione() : null;

		if (codiceCa == null) {
			codiceCa = ParamsConsts.TAG_CA_ARUBA;
			callInfo.setCaCode(codiceCa);
		}

		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}

		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setIdUtente(idUtente);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_RECUPERA_METADATI);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_REPOSITORY);

		evento.setIpApplicazione(getIp());

		String jsonInput = JsonUtils.fillObjects(input, callInfo);
		evento = eventoSrvc.insertEvento(evento, jsonInput);

		boolean ok = true;
		eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
				GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
		String errorMessage = null;

		GatefireDRepository repo = getRepository(repoList, callInfo);
		if (repo == null) {
			RepositoryQueryResult result = new RepositoryQueryResult();
			Result ret = new Result();
			ret.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			ret.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			ret.setMessage(REPO_NON_CONFIG + callInfo);

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, REPO_NON_CONFIG + callInfo, null, null);
			logError(REPO_NON_CONFIG + callInfo);

			result.setResult(ret);
			return result;
		}
		boolean checkAuth = true;
		if (repo.getAuthenticationRequired() != null && !repo.getAuthenticationRequired().booleanValue()) {
			checkAuth = false;
		}

		List<ErrorField> errorList = InputValidator.validate(callInfo, getTagCa(codiceCa), getAppList(), CALL_INFO);

		errorList.addAll(RepoValidator.validate(input, checkAuth, REPOSITORY_INPUT));

		if (!errorList.isEmpty()) {
			ok = false;
			errorMessage = JsonUtils.fillObject(errorList);
		}

		if (ok) {
			return iheSrvc.recuperaMetadataDoc(input, callInfo, evento.getId());

		} else {
			RepositoryQueryResult ret = new RepositoryQueryResult();
			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);
			ret.setResult(result);
			return ret;
		}
	}

	@Override
	public Result annullaDocumento(RepositoryUndoInput input, CallInfo callInfo) {
		if (callInfo == null) {
			callInfo = new CallInfo();
		}

		List<GatefireDRepository> repoList = cachedListsSrvc.getAllRepository();

		Date data = new Date();
		GatefireLEvento evento = new GatefireLEvento(data);

		String codiceCa = callInfo != null ? callInfo.getCaCode() : null;
		String applicazione = callInfo != null ? callInfo.getApplicationCode() : null;
		String codiceFiscale = callInfo != null ? callInfo.getCodiceFiscale() : null;
		String idUtente = null;
		String collocazione = callInfo != null ? callInfo.getCollocazione() : null;

		if (codiceCa == null) {
			codiceCa = ParamsConsts.TAG_CA_ARUBA;
			callInfo.setCaCode(codiceCa);
		}

		if (isApplicazioneEsistente(applicazione)) {
			evento.setCodiceApplicazione(applicazione);
		}

		evento.setCodiceFiscale(codiceFiscale);
		evento.setCollocazione(collocazione);

		evento.setIdUtente(idUtente);

		evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
		evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_ANNULLA_FILE_ARCH);
		evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_REPOSITORY);

		evento.setIpApplicazione(getIp());

		String jsonInput = JsonUtils.fillObjects(input, callInfo);
		evento = eventoSrvc.insertEvento(evento, jsonInput);

		boolean ok = true;
		eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
				GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
		String errorMessage = null;

		GatefireDRepository repo = getRepository(repoList, callInfo);
		if (repo == null) {

			Result ret = new Result();
			ret.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			ret.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			ret.setMessage(REPO_NON_CONFIG + callInfo);

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, REPO_NON_CONFIG + callInfo, null, null);
			logError(REPO_NON_CONFIG + callInfo);

			return ret;
		}
		boolean checkAuth = true;
		if (repo.getAuthenticationRequired() != null && !repo.getAuthenticationRequired().booleanValue()) {
			checkAuth = false;
		}

		List<ErrorField> errorList = InputValidator.validate(callInfo, getTagCa(codiceCa), getAppList(), CALL_INFO);

		errorList.addAll(RepoValidator.validate(input, checkAuth, REPOSITORY_INPUT));

		if (!errorList.isEmpty()) {
			ok = false;
			errorMessage = JsonUtils.fillObject(errorList);
		}

		if (ok) {
			return iheSrvc.annullaDoc(input, callInfo, evento.getId());

		} else {

			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, errorMessage, null, null);
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(errorMessage);

			return result;
		}
	}

}
