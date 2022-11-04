/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.ca.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.csi.gatefire.ca.aruba.arubasignservice.ArssReturn;
import it.csi.gatefire.ca.aruba.arubasignservice.ArubaSignService;
import it.csi.gatefire.ca.aruba.arubasignservice.Auth;
import it.csi.gatefire.ca.aruba.arubasignservice.CredentialsType;
import it.csi.gatefire.ca.aruba.arubasignservice.DictionarySignedAttributes;
import it.csi.gatefire.ca.aruba.arubasignservice.MarkRequest;
import it.csi.gatefire.ca.aruba.arubasignservice.MarkReturn;
import it.csi.gatefire.ca.aruba.arubasignservice.PdfProfile;
import it.csi.gatefire.ca.aruba.arubasignservice.PdfSignApparence;
import it.csi.gatefire.ca.aruba.arubasignservice.SignRequestV2;
import it.csi.gatefire.ca.aruba.arubasignservice.SignReturnV2;
import it.csi.gatefire.ca.aruba.arubasignservice.SignReturnV2Multiple;
import it.csi.gatefire.ca.aruba.arubasignservice.TsaAuth;
import it.csi.gatefire.ca.aruba.arubasignservice.TypeTransport;
import it.csi.gatefire.common.consts.GatefireConsts;
import it.csi.gatefire.common.consts.ParamsConsts;
import it.csi.gatefire.common.exception.ValidationException;
import it.csi.gatefire.common.model.Attachment;
import it.csi.gatefire.common.model.AutoSignIdentity;
import it.csi.gatefire.common.model.CallInfo;
import it.csi.gatefire.common.model.ErrorField;
import it.csi.gatefire.common.model.FileResult;
import it.csi.gatefire.common.model.Identity;
import it.csi.gatefire.common.model.MarkIdentity;
import it.csi.gatefire.common.model.MarkInput;
import it.csi.gatefire.common.model.MarkType;
import it.csi.gatefire.common.model.OtpCredentialsType;
import it.csi.gatefire.common.model.OtpReqInput;
import it.csi.gatefire.common.model.OtpResult;
import it.csi.gatefire.common.model.PadesInput;
import it.csi.gatefire.common.model.PingResult;
import it.csi.gatefire.common.model.Result;
import it.csi.gatefire.common.model.SessionIdResult;
import it.csi.gatefire.common.model.SessionInput;
import it.csi.gatefire.common.model.SignIdentity;
import it.csi.gatefire.common.model.SignType;
import it.csi.gatefire.common.util.ErrorHelper;
import it.csi.gatefire.common.util.JsonUtils;
import it.csi.gatefire.common.util.ProxyUtils;
import it.csi.gatefire.dbhelper.model.GatefireDCaCollocazioneDominio;
import it.csi.gatefire.dbhelper.model.GatefireLEvento;
import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativo;
import it.csi.gatefire.dbhelper.service.BaseService;
import it.csi.gatefire.dbhelper.service.CachedListsSrvc;
import it.csi.gatefire.dbhelper.service.EventoSrvc;
import it.csi.gatefire.dbhelper.util.ParamUtils;

@Service("arssSrvc")
public class ArssSrvcImpl extends BaseService implements ArssSrvc {
	@Value("${spring.profiles.active:Unknown}")
	private String activeProfile;

	@Autowired
	private CachedListsSrvc cachedListsSrvc;

	@Autowired
	EventoSrvc eventoSrvc;

	@Override
	public PingResult ping(CallInfo callInfo) {
		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();
		PingResult pingResult = new PingResult();
		pingResult.setSuccess(false);
		try {

			String endpoint = getEndpoint(list, callInfo.getCaCode());

			ArubaSignService arubaSignService = initService(endpoint);
			arubaSignService.ping();
			Result result = new Result();
			result.setErrorCode(ErrorHelper.OK);
			result.setMessage("I servizi della CA " + callInfo.getCaCode() + " all'endpoint: " + endpoint
					+ " sono correttamente raggiungibili");
			pingResult.setResult(result);
			pingResult.setSuccess(true);

		} catch (Exception e) {
			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERRORE_GENERICO);
			result.setMessage(e.getMessage());
			pingResult.setResult(result);
			logError(e);

		}
		return pingResult;
	}

	@Override
	public OtpResult requestOtp(OtpReqInput otpReqInput, Long idEvento) {
		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();
		List<GatefireDCaCollocazioneDominio> domainList = cachedListsSrvc.getAllDomini();

		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);

		OtpResult ret = new OtpResult();

		Identity identity = otpReqInput.getIdentity();

		CallInfo callInfo = otpReqInput.getCallInfo();
		OtpCredentialsType otpCredentialsType = otpReqInput.getOtpCredentialsType();

		try {

			String endpoint = getEndpoint(list, callInfo.getCaCode());

			ArubaSignService arubaSignService = initService(endpoint);

			Auth auth = fillAuth(identity, callInfo, list, domainList, GatefireConsts.FUNZ_FIRMA_REMOTA);
			CredentialsType ct = CredentialsType.SMS;

			if (otpCredentialsType != null && OtpCredentialsType.ARUBACALL == otpCredentialsType) {
				ct = CredentialsType.ARUBACALL;
			}

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(auth, ct);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_SESSIONE,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doOtpCall(arubaSignService, auth, ct, idEvento);

		} catch (ValidationException e) {

			Result result = new Result();
			String erroMessage = JsonUtils.fillObject(e.getErrorFields());
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(erroMessage);
			ret.setResult(result);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, erroMessage, null, null);
			logError(e);
		} catch (Exception e) {

			Result result = new Result();

			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(e.getMessage());
			ret.setResult(result);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);

		}
		if (ErrorHelper.OK.equalsIgnoreCase(ret.getResult().getErrorCode())) {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null);
		} else {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
		}
		return ret;

	}

	public FileResult firmaPAdESRemota(Attachment attachment, PadesInput padesInput, SignIdentity signIdentity,
			Long idEvento, boolean chiudiEvento) {

		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();
		List<GatefireDCaCollocazioneDominio> domainList = cachedListsSrvc.getAllDomini();
		FileResult ret = new FileResult();
		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);
		try {
			SignRequestV2 signRequestV2 = initSignRequest(attachment, padesInput.getCallInfo(),
					padesInput.getMarkIdentity(), padesInput.isRequiredMark(), list,
					GatefireConsts.FUNZ_FIRMA_PADES_REMOTA);

			Auth auth = fillAuth(signIdentity, padesInput.getCallInfo(), list, domainList, false);
			if (signIdentity.getSessionId() != null) {
				signRequestV2.setSessionId(signIdentity.getSessionId());
			}
			signRequestV2.setIdentity(auth);

			PdfSignApparence apparence = fillApparence(padesInput, list, SignType.REMOTA);
			DictionarySignedAttributes dicAttributes = new DictionarySignedAttributes();
			// dicAttributes.setT(value);

			String endpoint = getEndpoint(list, padesInput.getCallInfo().getCaCode());
			ArubaSignService arubaSignService = initService(endpoint);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(signRequestV2, apparence, PdfProfile.PADESBES, dicAttributes);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doPadesCall(arubaSignService, signRequestV2, apparence, dicAttributes, attachment.getFileName(),
					idEvento);

		} catch (ValidationException e) {

			Result result = new Result();
			String erroMessage = JsonUtils.fillObject(e.getErrorFields());
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(erroMessage);
			ret.setResult(result);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, erroMessage, null, null);
			logError(e);

		} catch (Exception e) {

			Result result = new Result();

			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(e.getMessage());
			ret.setResult(result);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);

		}
		if (ErrorHelper.OK.equalsIgnoreCase(ret.getResult().getErrorCode())) {
			if (chiudiEvento) {
				eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null);
			}
		} else {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
		}
		return ret;
	}

	public FileResult firmaPAdESAutomatica(Attachment attachment, PadesInput padesInput, AutoSignIdentity signIdentity,
			Long idEvento, boolean chiudiEvento) {

		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();
		List<GatefireDCaCollocazioneDominio> domainList = cachedListsSrvc.getAllDomini();

		FileResult ret = new FileResult();
		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);
		try {
			SignRequestV2 signRequestV2 = initSignRequest(attachment, padesInput.getCallInfo(),
					padesInput.getMarkIdentity(), padesInput.isRequiredMark(), list,
					GatefireConsts.FUNZ_FIRMA_PADES_AUTOMATICA);

			Auth auth = fillAuthAutomatica(signIdentity, padesInput.getCallInfo(), list, domainList);

			signRequestV2.setIdentity(auth);

			PdfSignApparence apparence = fillApparence(padesInput, list, SignType.AUTOMATICA);
			DictionarySignedAttributes dicAttributes = new DictionarySignedAttributes();
			// dicAttributes.setT(value);

			String endpoint = getEndpoint(list, padesInput.getCallInfo().getCaCode());
			ArubaSignService arubaSignService = initService(endpoint);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(signRequestV2, apparence, PdfProfile.PADESBES, dicAttributes);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doPadesCall(arubaSignService, signRequestV2, apparence, dicAttributes, attachment.getFileName(),
					idEvento);

		} catch (ValidationException e) {

			Result result = new Result();
			String erroMessage = JsonUtils.fillObject(e.getErrorFields());
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(erroMessage);
			ret.setResult(result);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, erroMessage, null, null);
			logError(e);

		} catch (Exception e) {

			Result result = new Result();

			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(e.getMessage());
			ret.setResult(result);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);

		}
		if (ErrorHelper.OK.equalsIgnoreCase(ret.getResult().getErrorCode())) {
			if (chiudiEvento) {
				eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null);
			}
		} else {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
		}
		return ret;
	}

	private FileResult doPadesCall(ArubaSignService arubaSignService, SignRequestV2 signRequestV2,
			PdfSignApparence apparence, DictionarySignedAttributes dicAttributes, String fileName, Long idEvento) {
		FileResult ret = new FileResult();
		try {

			SignReturnV2 signReturnV2 = arubaSignService.pdfsignatureV2(signRequestV2, apparence, PdfProfile.PADESBES,
					null, dicAttributes);

			if ("OK".equalsIgnoreCase(signReturnV2.getStatus())) {
				if (TypeTransport.BYNARYNET.equals(signRequestV2.getTransport())) {
					Attachment retAtt = new Attachment(fileName, signReturnV2.getBinaryoutput());
					ret.setAttachment(retAtt);

				}
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
						GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, signReturnV2.getReturnCode(),
						signReturnV2.getDescription());
			} else {

				Result result = new Result(ErrorHelper.ERRORE_FIRMA);
				result.setDescription(getText(ErrorHelper.ERRORE_FIRMA));
				result.setMessage(signReturnV2.getDescription());
				result.setOriginalReturnCode(signReturnV2.getReturnCode());
				ret.setResult(result);

				logError(signReturnV2.getDescription());
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
						GatefireConsts.EVT_LOG_STATO_ERRORE, null, signReturnV2.getReturnCode(),
						signReturnV2.getDescription());
			}
		} catch (Exception e) {

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERRORE_FIRMA);
			result.setDescription(getText(ErrorHelper.ERRORE_FIRMA));
			result.setMessage(e.getMessage());
			ret.setResult(result);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
					GatefireConsts.EVT_LOG_STATO_ERRORE, "Errore chiamata servizio remoto: " + e.getMessage(), null,
					null);
			logError(e);
		}
		return ret;

	}

	private FileResult doPadesMassivaCall(ArubaSignService arubaSignService, List<Attachment> attachmentList,
			List<SignRequestV2> reqList, PdfSignApparence apparence, DictionarySignedAttributes dicAttributes,
			Long idEvento) {
		FileResult ret = new FileResult();
		try {

			SignReturnV2Multiple signReturnV2Multiple = arubaSignService.pdfsignatureV2Multiple(
					reqList.get(0).getIdentity(), reqList, apparence, PdfProfile.PADESBES, dicAttributes);
			if ("OK".equalsIgnoreCase(signReturnV2Multiple.getStatus())) {
				boolean binary = TypeTransport.BYNARYNET.equals(reqList.get(0).getTransport());
				Attachment attachment = new Attachment(new Date().getTime() + ".zip",
						zipFiles(signReturnV2Multiple.getReturnSigns(), attachmentList, binary, false));
				ret.setAttachment(attachment);
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
						GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, signReturnV2Multiple.getReturnCode(),
						signReturnV2Multiple.getDescription());

			} else {

				Result result = new Result(ErrorHelper.ERRORE_FIRMA);
				result.setDescription(getText(ErrorHelper.ERRORE_FIRMA));
				result.setMessage(signReturnV2Multiple.getDescription());
				result.setOriginalReturnCode(signReturnV2Multiple.getReturnCode());
				ret.setResult(result);
				logError(signReturnV2Multiple.getDescription());
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
						GatefireConsts.EVT_LOG_STATO_ERRORE, null, signReturnV2Multiple.getReturnCode(),
						signReturnV2Multiple.getDescription());
			}
		} catch (Exception e) {

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERRORE_FIRMA);
			result.setDescription(getText(ErrorHelper.ERRORE_FIRMA));
			result.setMessage(e.getMessage());
			ret.setResult(result);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
					GatefireConsts.EVT_LOG_STATO_ERRORE, "Errore chiamata servizio remoto: " + e.getMessage(), null,
					null);
			logError(e);
		}
		return ret;

	}

	private SignRequestV2 initSignRequest(Attachment attachment, CallInfo callInfo, MarkIdentity markIdentity,
			boolean requiredMark, List<GatefireTParametroOperativo> list, String funzione) throws ValidationException {
		SignRequestV2 signRequestV2 = new SignRequestV2();

		signRequestV2.setBinaryinput(attachment.getFile());
		signRequestV2.setTransport(TypeTransport.BYNARYNET);

		String signatureLevel = null;

		String certID = ParamUtils.getParam(list, ParamsConsts.PARAM_CA_ARUBA_CERT_ID, callInfo.getCaCode(),
				callInfo.getApplicationCode(), funzione, ParamsConsts.PARAM_CA_ARUBA_CERT_ID_DEFAULT);

		String profile = ParamUtils.getParam(list, ParamsConsts.PARAM_CA_ARUBA_CERT_ID, callInfo.getCaCode(),
				callInfo.getApplicationCode(), funzione, ParamsConsts.PARAM_CA_ARUBA_CERT_ID_DEFAULT);

		signRequestV2.setSignatureLevel(signatureLevel);
		signRequestV2.setCertID(certID);
		signRequestV2.setProfile(profile);

		signRequestV2.setRequiredmark(requiredMark);

		if (requiredMark) {
			signRequestV2.setSignatureLevel(ParamsConsts.PARAM_CA_ARUBA_SIGNATURE_LEVEL_DEFAULT);
			TsaAuth tsaAuth = new TsaAuth();
			tsaAuth.setPassword(markIdentity.getPassword());
			tsaAuth.setUser(markIdentity.getUser());
			signRequestV2.setTsaIdentity(tsaAuth);
		}

		return signRequestV2;

	}

	private PdfSignApparence fillApparence(PadesInput padesInput, List<GatefireTParametroOperativo> list,
			SignType signType) throws ValidationException {

		String tipoFirma = GatefireConsts.FUNZ_FIRMA_PADES_REMOTA;

		if (signType == SignType.AUTOMATICA) {
			tipoFirma = GatefireConsts.FUNZ_FIRMA_PADES_AUTOMATICA;
		}
		PdfSignApparence apparence = new PdfSignApparence();
		List<ErrorField> fieldsInError = new ArrayList<>();
		Integer leftX = padesInput.getSignLayout().getLowLeftX();
		if (leftX == null) {
			String temp = ParamUtils.getParam(list, ParamsConsts.PARAM_CA_LAYOUT_LOW_LEFT_X,
					padesInput.getCallInfo().getCaCode(), padesInput.getCallInfo().getApplicationCode(), tipoFirma,
					null);
			if (temp == null) {
				// fieldsInError
				// .add(new ErrorField(ParamsConsts.PARAM_CA_LAYOUT_LOW_LEFT_X,
				// ErrorField.CAMPO_OBBLIGATORIO));
			} else {
				leftX = Integer.parseInt(temp);
			}

		}
		Integer leftY = padesInput.getSignLayout().getLowLeftY();
		if (leftY == null) {
			String temp = ParamUtils.getParam(list, ParamsConsts.PARAM_CA_LAYOUT_LOW_LEFT_Y,
					padesInput.getCallInfo().getCaCode(), padesInput.getCallInfo().getApplicationCode(), tipoFirma,
					null);
			if (temp == null) {
				// fieldsInError
				// .add(new ErrorField(ParamsConsts.PARAM_CA_LAYOUT_LOW_LEFT_Y,
				// ErrorField.CAMPO_OBBLIGATORIO));

			} else {
				leftY = Integer.parseInt(temp);
			}

		}
		Integer rightX = padesInput.getSignLayout().getUpRightX();
		if (rightX == null) {
			String temp = ParamUtils.getParam(list, ParamsConsts.PARAM_CA_LAYOUT_UP_RIGHT_X,
					padesInput.getCallInfo().getCaCode(), padesInput.getCallInfo().getApplicationCode(), tipoFirma,
					null);
			if (temp == null) {
				// fieldsInError
				// .add(new ErrorField(ParamsConsts.PARAM_CA_LAYOUT_UP_RIGHT_X,
				// ErrorField.CAMPO_OBBLIGATORIO));
			} else {
				rightX = Integer.parseInt(temp);
			}

		}
		Integer rightY = padesInput.getSignLayout().getUpRightY();
		if (rightY == null) {
			String temp = ParamUtils.getParam(list, ParamsConsts.PARAM_CA_LAYOUT_UP_RIGHT_Y,
					padesInput.getCallInfo().getCaCode(), padesInput.getCallInfo().getApplicationCode(), tipoFirma,
					null);
			if (temp == null) {
				// fieldsInError
				// .add(new ErrorField(ParamsConsts.PARAM_CA_LAYOUT_UP_RIGHT_Y,
				// ErrorField.CAMPO_OBBLIGATORIO));
			} else {
				rightY = Integer.parseInt(temp);
			}

		}

		Integer page = padesInput.getSignLayout().getPage();
		if (page == null) {
			String temp = ParamUtils.getParam(list, ParamsConsts.PARAM_CA_LAYOUT_PAGE,
					padesInput.getCallInfo().getCaCode(), padesInput.getCallInfo().getApplicationCode(), tipoFirma,
					"1");
			if (temp == null) {
				// fieldsInError.add(new ErrorField(ParamsConsts.PARAM_CA_LAYOUT_PAGE,
				// ErrorField.CAMPO_OBBLIGATORIO));
			} else {
				page = Integer.parseInt(temp);
			}

		}
		if (!fieldsInError.isEmpty()) {
			throw new ValidationException("Errore validazione", fieldsInError);
		}
		if (leftX != null) {
			apparence.setLeftx(leftX);
		}
		if (leftY != null) {
			apparence.setLefty(leftY);
		}

		if (rightX != null) {
			apparence.setRightx(rightX);
		}
		if (rightY != null) {
			apparence.setRighty(rightY);
		}
		if (page != null) {
			apparence.setPage(page);
		}
		apparence.setReason(padesInput.getSignLayout().getReason());
		apparence.setImageBin(padesInput.getSignLayout().getImage());
		apparence.setImageOnly(false);

		apparence.setBScaleFont(false);
		apparence.setBShowDateTime(padesInput.getSignLayout().isShowDateTime());
		apparence.setResizeMode(5);
		apparence.setPreservePDFA(true);
		apparence.setTesto(padesInput.getSignLayout().getText());

		return apparence;
	}

	private Auth fillAuth(SignIdentity signIdentity, CallInfo callInfo, List<GatefireTParametroOperativo> list,
			List<GatefireDCaCollocazioneDominio> domainList, boolean closeSession) throws ValidationException {
		List<ErrorField> fieldsInError = new ArrayList<>();

		// TODO da db
		String otpDomain = null;
		try {
			otpDomain = getOtpDomain(domainList, callInfo, GatefireConsts.FUNZ_FIRMA_REMOTA);
		} catch (ValidationException e) {
			fieldsInError.addAll(e.getErrorFields());
		}

		String cosign = ParamUtils.getParam(list, ParamsConsts.PARAM_CA_ARUBA_TYPE_HSM, callInfo.getCaCode(),
				callInfo.getApplicationCode(), GatefireConsts.FUNZ_FIRMA_PADES_REMOTA,
				ParamsConsts.PARAM_CA_ARUBA_TYPE_HSM_DEFAULT);

		Auth auth = new Auth();
		if (signIdentity.getSessionId() == null) {
			String otp = signIdentity.getOtp();

			if (!StringUtils.hasLength(otp) && !closeSession) {
				fieldsInError.add(new ErrorField("signIdentity.otp", ErrorField.CAMPO_OBBLIGATORIO));
			}
			auth.setOtpPwd(otp);

		}
		if (!fieldsInError.isEmpty()) {
			throw new ValidationException("Errore validazione", fieldsInError);
		}
		auth.setTypeOtpAuth(otpDomain);
		auth.setUser(signIdentity.getUser());

		auth.setUserPWD(signIdentity.getPassword());

		auth.setTypeHSM(cosign);
		return auth;
	}

	private Auth fillAuthAutomatica(AutoSignIdentity signIdentity, CallInfo callInfo,
			List<GatefireTParametroOperativo> list, List<GatefireDCaCollocazioneDominio> domainList)
			throws ValidationException {
		List<ErrorField> fieldsInError = new ArrayList<>();

		String otpDomain = null;

		try {
			otpDomain = getOtpDomain(domainList, callInfo, GatefireConsts.FUNZ_FIRMA_AUTOMATICA);
		} catch (ValidationException e) {
			fieldsInError.addAll(e.getErrorFields());
		}
		String delegatedDomain = signIdentity.getDelegatedDomain();

		if (StringUtils.hasLength(signIdentity.getDelegatedUser()) && !StringUtils.hasLength(delegatedDomain)) {

			fieldsInError
					.add(new ErrorField(ParamsConsts.PARAM_CA_ARUBA_DELEGATED_DOMAIN, ErrorField.CAMPO_OBBLIGATORIO));

		}
		String cosign = ParamUtils.getParam(list, ParamsConsts.PARAM_CA_ARUBA_TYPE_HSM, callInfo.getCaCode(),
				callInfo.getApplicationCode(), GatefireConsts.FUNZ_FIRMA_PADES_REMOTA,
				ParamsConsts.PARAM_CA_ARUBA_TYPE_HSM_DEFAULT);

		Auth auth = new Auth();

		String otp = signIdentity.getOtp();
		if (!StringUtils.hasLength(otp)) {
			fieldsInError.add(new ErrorField(ParamsConsts.PARAM_CA_ARUBA_OTP, ErrorField.CAMPO_OBBLIGATORIO));

		}

		auth.setOtpPwd(otp);

		if (!fieldsInError.isEmpty()) {
			throw new ValidationException("Errore validazione", fieldsInError);
		}
		auth.setTypeOtpAuth(otpDomain);
		auth.setUser(signIdentity.getUser());

		auth.setDelegatedDomain(delegatedDomain);
		auth.setDelegatedUser(signIdentity.getDelegatedUser());
		auth.setDelegatedPassword(signIdentity.getDelegatedPassword());

		auth.setTypeHSM(cosign);
		return auth;
	}

	private Auth fillAuth(Identity identity, CallInfo callInfo, List<GatefireTParametroOperativo> list,
			List<GatefireDCaCollocazioneDominio> domainList, String funzione) throws ValidationException {
		List<ErrorField> fieldsInError = new ArrayList<>();

		String otpDomain = null;

		try {
			otpDomain = getOtpDomain(domainList, callInfo, funzione);
		} catch (ValidationException e) {
			fieldsInError.addAll(e.getErrorFields());
		}
		String cosign = ParamUtils.getParam(list, ParamsConsts.PARAM_CA_ARUBA_TYPE_HSM, callInfo.getCaCode(),
				callInfo.getApplicationCode(), GatefireConsts.FUNZ_FIRMA_PADES_REMOTA,
				ParamsConsts.PARAM_CA_ARUBA_TYPE_HSM_DEFAULT);

		if (!fieldsInError.isEmpty()) {
			throw new ValidationException("Errore validazione", fieldsInError);
		}
		Auth auth = new Auth();

		auth.setTypeOtpAuth(otpDomain);
		auth.setUser(identity.getUser());

		auth.setUserPWD(identity.getPassword());

		auth.setTypeHSM(cosign);
		return auth;
	}

	public FileResult firmaPAdESMassivaRemota(List<Attachment> attachmentList, PadesInput padesInput,
			SignIdentity signIdentity, Long idEvento) {

		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();
		List<GatefireDCaCollocazioneDominio> domainList = cachedListsSrvc.getAllDomini();

		FileResult ret = new FileResult();
		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);
		try {
			List<SignRequestV2> reqList = new ArrayList<>();

			for (Attachment attachment : attachmentList) {

				SignRequestV2 signRequestV2 = initSignRequest(attachment, padesInput.getCallInfo(),
						padesInput.getMarkIdentity(), padesInput.isRequiredMark(), list,
						GatefireConsts.FUNZ_FIRMA_PADES_REMOTA);

				Auth auth = fillAuth(signIdentity, padesInput.getCallInfo(), list, domainList, false);
				if (signIdentity.getSessionId() != null) {
					signRequestV2.setSessionId(signIdentity.getSessionId());
				}
				signRequestV2.setIdentity(auth);
				reqList.add(signRequestV2);
			}

			PdfSignApparence apparence = fillApparence(padesInput, list, SignType.REMOTA);
			DictionarySignedAttributes dicAttributes = new DictionarySignedAttributes();
			// dicAttributes.setT(value);

			String endpoint = getEndpoint(list, padesInput.getCallInfo().getCaCode());
			ArubaSignService arubaSignService = initService(endpoint);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(reqList, apparence, PdfProfile.PADESBES, dicAttributes);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doPadesMassivaCall(arubaSignService, attachmentList, reqList, apparence, dicAttributes, idEvento);

		} catch (ValidationException e) {

			Result result = new Result();
			String erroMessage = JsonUtils.fillObject(e.getErrorFields());
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(erroMessage);
			ret.setResult(result);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, erroMessage, null, null);
			logError(e);

		} catch (Exception e) {

			Result result = new Result();

			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(e.getMessage());
			ret.setResult(result);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);

		}
		if (ErrorHelper.OK.equalsIgnoreCase(ret.getResult().getErrorCode())) {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null);
		} else {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
		}
		return ret;

	}

	@Override
	public SessionIdResult openSession(SessionInput sessionInput, Long idEvento) {
		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();
		List<GatefireDCaCollocazioneDominio> domainList = cachedListsSrvc.getAllDomini();
		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);

		SessionIdResult ret = new SessionIdResult();

		try {
			String endpoint = getEndpoint(list, sessionInput.getCallInfo().getCaCode());
			ArubaSignService arubaSignService = initService(endpoint);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(sessionInput);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_SESSIONE,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doOpenSession(arubaSignService, sessionInput, list, domainList, idEvento);

		} catch (ValidationException e) {

			Result result = new Result();
			String erroMessage = JsonUtils.fillObject(e.getErrorFields());
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(erroMessage);
			ret.setResult(result);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, erroMessage, null, null);
			logError(e);
		} catch (Exception e) {

			Result result = new Result();

			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(e.getMessage());
			ret.setResult(result);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);

		}
		if (ErrorHelper.OK.equalsIgnoreCase(ret.getResult().getErrorCode())) {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null);
		} else {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
		}
		return ret;

	}

	@Override
	public Result closeSession(String sessionId, SessionInput sessionInput, Long idEvento) {
		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();
		List<GatefireDCaCollocazioneDominio> domainList = cachedListsSrvc.getAllDomini();
		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);

		Result ret = new Result();

		try {
			String endpoint = getEndpoint(list, sessionInput.getCallInfo().getCaCode());
			ArubaSignService arubaSignService = initService(endpoint);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(sessionInput);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_SESSIONE,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doCloseSession(arubaSignService, sessionId, sessionInput, list, domainList, idEvento);

		} catch (ValidationException e) {

			String erroMessage = JsonUtils.fillObject(e.getErrorFields());

			ret.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			ret.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			ret.setMessage(erroMessage);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, erroMessage, null, null);
			logError(e);

		} catch (Exception e) {

			ret.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			ret.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			ret.setMessage(e.getMessage());

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);

		}
		if (ErrorHelper.OK.equalsIgnoreCase(ret.getErrorCode())) {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null);
		} else {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
		}
		return ret;

	}

	public FileResult marcaTemporale(Attachment attachment, MarkInput markInput, Long idEvento) {
		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();

		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);
		FileResult ret = new FileResult();

		try {
			String endpoint = getEndpoint(list, markInput.getCallInfo().getCaCode());
			ArubaSignService arubaSignService = initService(endpoint);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(markInput, attachment);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_MARCA,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			MarkRequest markRequest = new MarkRequest();
			markRequest.setBinaryinput(attachment.getFile());
			markRequest.setTransport(TypeTransport.BYNARYNET);
			markRequest.setUser(markInput.getMarkIdentity().getUser());
			markRequest.setPassword(markInput.getMarkIdentity().getPassword());
			ret = doMarkCall(arubaSignService, markRequest, markInput.getMarkType(), attachment.getFileName(),
					idEvento);

		} catch (ValidationException e) {

			Result result = new Result();
			String erroMessage = JsonUtils.fillObject(e.getErrorFields());
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(erroMessage);
			ret.setResult(result);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, erroMessage, null, null);
			logError(e);

		} catch (Exception e) {

			Result result = new Result();

			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(e.getMessage());
			ret.setResult(result);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);

		}
		if (ErrorHelper.OK.equalsIgnoreCase(ret.getResult().getErrorCode())) {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null);
		} else {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
		}
		return ret;
	}

	private FileResult doMarkCall(ArubaSignService arubaSignService, MarkRequest markRequest, MarkType markType,
			String fileName, Long idEvento) {
		FileResult ret = new FileResult();
		try {

			MarkReturn markReturn;
			switch (markType) {

			case TSR:
				markReturn = arubaSignService.tsr(markRequest);
				break;
			case TSD:
				markReturn = arubaSignService.tsd(markRequest);
				break;
			default:
				markReturn = arubaSignService.tsr(markRequest);
				break;
			}

			if ("OK".equalsIgnoreCase(markReturn.getStatus())) {

				if (markType == MarkType.TSD) {
					fileName += ".tsd";
				} else {
					fileName += ".tsr";
				}
				Attachment retAtt = new Attachment(fileName, markReturn.getBinaryoutput());
				ret.setAttachment(retAtt);

				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_MARCA,
						GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, markReturn.getReturnCode(),
						markReturn.getDescription());

			} else {

				Result result = new Result(ErrorHelper.ERRORE_MARCA);
				result.setDescription(getText(ErrorHelper.ERRORE_MARCA));
				result.setMessage(markReturn.getDescription());
				result.setOriginalReturnCode(markReturn.getReturnCode());
				ret.setResult(result);
				logError(markReturn.getDescription());
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_MARCA,
						GatefireConsts.EVT_LOG_STATO_ERRORE, null, markReturn.getReturnCode(),
						markReturn.getDescription());
			}
		} catch (Exception e) {

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERRORE_MARCA);
			result.setDescription(getText(ErrorHelper.ERRORE_MARCA));
			result.setMessage(e.getMessage());
			ret.setResult(result);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_MARCA,
					GatefireConsts.EVT_LOG_STATO_ERRORE, "Errore chiamata servizio remoto: " + e.getMessage(), null,
					null);
			logError(e);
		}
		return ret;

	}

	private SessionIdResult doOpenSession(ArubaSignService arubaSignService, SessionInput sessionInput,
			List<GatefireTParametroOperativo> list, List<GatefireDCaCollocazioneDominio> domainList, Long idEvento) {
		SessionIdResult ret = new SessionIdResult();
		try {

			Auth auth = fillAuth(sessionInput.getSignIdentity(), sessionInput.getCallInfo(), list, domainList, false);

			String sessionid = arubaSignService.opensession(auth);

			if (sessionid.startsWith("KO-")) {
				Result result = new Result();
				result.setErrorCode(ErrorHelper.ERRORE_APERTURA_SESSIONE);
				result.setDescription(getText(ErrorHelper.ERRORE_APERTURA_SESSIONE));
				result.setMessage(sessionid);
				result.setOriginalReturnCode(sessionid);
				ret.setResult(result);
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_SESSIONE,
						GatefireConsts.EVT_LOG_STATO_ERRORE, null, sessionid, sessionid);

			} else {
				ret.setSessionId(sessionid);
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_SESSIONE,
						GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, "sessionId " + sessionid);

			}

		} catch (Exception e) {

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERRORE_GENERICO);
			result.setDescription(getText(ErrorHelper.ERRORE_GENERICO));
			result.setMessage(e.getMessage());
			ret.setResult(result);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_SESSIONE,
					GatefireConsts.EVT_LOG_STATO_ERRORE, "Errore chiamata servizio remoto: " + e.getMessage(), null,
					null);
			logError(e);
		}
		return ret;

	}

	private OtpResult doOtpCall(ArubaSignService arubaSignService, Auth auth, CredentialsType ct, Long idEvento) {
		OtpResult ret = new OtpResult();
		try {

			ArssReturn arssReturn = arubaSignService.sendCredential(auth, ct);

			if ("OK".equalsIgnoreCase(arssReturn.getStatus())) {
				ret.setSuccess(true);
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_RICHIESTA_OTP,
						GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, arssReturn.getReturnCode(),
						arssReturn.getDescription());
			} else {

				Result result = new Result(ErrorHelper.ERRORE_OTP);
				result.setDescription(getText(ErrorHelper.ERRORE_OTP));
				result.setMessage(arssReturn.getDescription());
				result.setOriginalReturnCode(arssReturn.getReturnCode());
				ret.setResult(result);
				logError(arssReturn.getDescription());
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_RICHIESTA_OTP,
						GatefireConsts.EVT_LOG_STATO_ERRORE, null, arssReturn.getReturnCode(),
						arssReturn.getDescription());
			}

		} catch (Exception e) {

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERRORE_GENERICO);
			result.setDescription(getText(ErrorHelper.ERRORE_GENERICO));
			result.setMessage(e.getMessage());
			ret.setResult(result);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_RICHIESTA_OTP,
					GatefireConsts.EVT_LOG_STATO_ERRORE, "Errore chiamata servizio remoto: " + e.getMessage(), null,
					null);
			logError(e);
		}
		return ret;

	}

	private Result doCloseSession(ArubaSignService arubaSignService, String sessionId, SessionInput sessionInput,
			List<GatefireTParametroOperativo> list, List<GatefireDCaCollocazioneDominio> domainList, Long idEvento) {
		Result ret = new Result();
		try {
			Auth auth = fillAuth(sessionInput.getSignIdentity(), sessionInput.getCallInfo(), list, domainList, true);

			String sessionid = arubaSignService.closesession(auth, sessionId);

			if ("OK".equalsIgnoreCase(sessionid)) {

				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_SESSIONE,
						GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, sessionid, null);

			} else {

				ret.setErrorCode(ErrorHelper.ERRORE_CHIUSURA_SESSIONE);
				ret.setDescription(getText(ErrorHelper.ERRORE_CHIUSURA_SESSIONE));
				ret.setOriginalReturnCode(sessionid);
				ret.setMessage(sessionid);

				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_MARCA,
						GatefireConsts.EVT_LOG_STATO_ERRORE, null, sessionId, sessionid);
			}

		} catch (Exception e) {

			ret.setErrorCode(ErrorHelper.ERRORE_GENERICO);
			ret.setDescription(getText(ErrorHelper.ERRORE_GENERICO));
			ret.setMessage(e.getMessage());

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_SESSIONE,
					GatefireConsts.EVT_LOG_STATO_ERRORE, "Errore chiamata servizio remoto: " + e.getMessage(), null,
					null);
			logError(e);
		}
		return ret;

	}

	private ArubaSignService initService(String endpoint) {

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.setServiceClass(ArubaSignService.class);
		factory.setAddress(endpoint);

		LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
		loggingInInterceptor.setPrettyLogging(true);
		LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
		loggingOutInterceptor.setPrettyLogging(true);
		loggingOutInterceptor.setLogMultipart(true);
		loggingOutInterceptor.setLogBinary(true);
		loggingOutInterceptor.setLimit(-1);
		factory.getInInterceptors().add(loggingInInterceptor);
		factory.getOutInterceptors().add(loggingOutInterceptor);

		ArubaSignService arubaSignService = (ArubaSignService) factory.create();
		Client client = ClientProxy.getClient(arubaSignService);
		client.getRequestContext().put(Message.ENDPOINT_ADDRESS, endpoint);

		if ("dev".equalsIgnoreCase(activeProfile)) {
			ProxyUtils.authenticate(client);
		}
		return arubaSignService;
	}

	private String getValoreParametroCA(List<GatefireTParametroOperativo> list, String parametro, String codiceCa) {

		for (GatefireTParametroOperativo vCaParametri : list) {
			if (vCaParametri.getCodiceCa().equalsIgnoreCase(codiceCa)
					&& vCaParametri.getParametro().equalsIgnoreCase(parametro)) {
				return vCaParametri.getValore();
			}
		}

		return null;
	}

	private String getEndpoint(List<GatefireTParametroOperativo> list, String codiceCa) throws ValidationException {

		String endpoint = getValoreParametroCA(list, ParamsConsts.PARAM_CA_ENDPOINT_ARSS, codiceCa);
		if (endpoint == null) {
			ErrorField errorfield = new ErrorField(ParamsConsts.PARAM_CA_ENDPOINT_ARSS, "parametro mancante a db");

			throw new ValidationException("endpoint non trovato per " + codiceCa, errorfield);
		}
		return endpoint;
	}

	private byte[] zipFiles(List<SignReturnV2> list, List<Attachment> attList, boolean binary, boolean pades)
			throws IOException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ZipOutputStream zos = new ZipOutputStream(baos);) {
				for (int i = 0; i < list.size(); i++) {
					SignReturnV2 signReturnV2 = list.get(i);
					String filename = attList.get(i).getFileName();
					if (pades) {
						filename += ".p7m";
					}
					zos.putNextEntry(new ZipEntry(filename));
					if (binary) {
						zos.write(signReturnV2.getBinaryoutput());
					}
					zos.closeEntry();

				}
			}
			return baos.toByteArray();
		}

	}

	@Override
	public FileResult firmaPAdESMassivaAutomatica(List<Attachment> attachmentList, PadesInput padesInput,
			AutoSignIdentity signIdentity, Long idEvento) {
		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();
		List<GatefireDCaCollocazioneDominio> domainList = cachedListsSrvc.getAllDomini();
		FileResult ret = new FileResult();
		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);
		try {
			List<SignRequestV2> reqList = new ArrayList<>();

			for (Attachment attachment : attachmentList) {

				SignRequestV2 signRequestV2 = initSignRequest(attachment, padesInput.getCallInfo(),
						padesInput.getMarkIdentity(), padesInput.isRequiredMark(), list,
						GatefireConsts.FUNZ_FIRMA_PADES_AUTOMATICA);

				Auth auth = fillAuthAutomatica(signIdentity, padesInput.getCallInfo(), list, domainList);

				signRequestV2.setIdentity(auth);
				reqList.add(signRequestV2);
			}

			PdfSignApparence apparence = fillApparence(padesInput, list, SignType.AUTOMATICA);
			DictionarySignedAttributes dicAttributes = new DictionarySignedAttributes();
			// dicAttributes.setT(value);

			String endpoint = getEndpoint(list, padesInput.getCallInfo().getCaCode());
			ArubaSignService arubaSignService = initService(endpoint);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(reqList, apparence, PdfProfile.PADESBES, dicAttributes);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doPadesMassivaCall(arubaSignService, attachmentList, reqList, apparence, dicAttributes, idEvento);

		} catch (ValidationException e) {

			Result result = new Result();
			String erroMessage = JsonUtils.fillObject(e.getErrorFields());
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(erroMessage);
			ret.setResult(result);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, erroMessage, null, null);
			logError(e);

		} catch (Exception e) {

			Result result = new Result();

			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(e.getMessage());
			ret.setResult(result);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);

		}
		if (ErrorHelper.OK.equalsIgnoreCase(ret.getResult().getErrorCode())) {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null);
		} else {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
		}
		return ret;
	}

	private String getOtpDomain(List<GatefireDCaCollocazioneDominio> list, CallInfo callInfo, String funzione)
			throws ValidationException {

		List<GatefireDCaCollocazioneDominio> retList = new ArrayList<>();
		for (GatefireDCaCollocazioneDominio dominio : list) {
			if (callInfo.getCaCode().equalsIgnoreCase(dominio.getCodiceCa())
					&& callInfo.getCollocazione().equalsIgnoreCase(dominio.getCollocazione())
					&& funzione.equalsIgnoreCase(dominio.getTipoFunzione())
					&& (!StringUtils.hasLength(callInfo.getDominio())
							|| (dominio.getDominio().equalsIgnoreCase(callInfo.getDominio())))) {
				retList.add(dominio);
			}

		}

		if (retList.isEmpty())

		{
			ErrorField errorfield = new ErrorField("callInfo.collocazione",
					"collocazione [" + callInfo.getCollocazione() + "] non abilitata");

			throw new ValidationException("Collocazione [" + callInfo.getCollocazione() + "] non abilitata",
					errorfield);
		} else if (retList.size() > 1) {
			ErrorField errorfield = new ErrorField("callInfo.collocazione", "piu' domini presenti per la collocazione ["
					+ callInfo.getCollocazione() + "] specificare il dominio");
			throw new ValidationException("piu' domini presenti per la collocazione [" + callInfo.getCollocazione()
					+ "] specificare il dominio", errorfield);
		}
		return retList.get(0).getConfigurazione();
	}

}
