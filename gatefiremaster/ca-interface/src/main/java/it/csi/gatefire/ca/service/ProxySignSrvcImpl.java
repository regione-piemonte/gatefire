/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.ca.service;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.csi.gatefire.ca.util.MultipartHttpPost;
import it.csi.gatefire.common.consts.GatefireConsts;
import it.csi.gatefire.common.consts.ParamsConsts;
import it.csi.gatefire.common.exception.HttpStreamException;
import it.csi.gatefire.common.exception.ValidationException;
import it.csi.gatefire.common.model.Attachment;
import it.csi.gatefire.common.model.AutoSignIdentity;
import it.csi.gatefire.common.model.CallInfo;
import it.csi.gatefire.common.model.ErrorField;
import it.csi.gatefire.common.model.FileResult;
import it.csi.gatefire.common.model.Identity;
import it.csi.gatefire.common.model.MarkInput;
import it.csi.gatefire.common.model.MarkType;
import it.csi.gatefire.common.model.OtpReqInput;
import it.csi.gatefire.common.model.OtpResult;
import it.csi.gatefire.common.model.PadesInput;
import it.csi.gatefire.common.model.PingResult;
import it.csi.gatefire.common.model.Result;
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

@Service("proxySignSrvc")
public class ProxySignSrvcImpl extends BaseService implements ProxySignSrvc {

	@Value("${spring.profiles.active:Unknown}")
	private String activeProfile;

	@Autowired
	private EventoSrvc eventoSrvc;
	@Autowired
	private CachedListsSrvc cachedListsSrvc;

	private static final String BOX_SIGN_IMG = "box_signature_image";
	private static final String TIMESTAMP_USERNAME = "timestamp_username";
	private static final String TIMESTAMP_PASSWORD = "timestamp_password";
	private static final String ERRORE_CHIAMATA = "Errore chiamata servizio remoto: ";

	@Override
	public PingResult ping(CallInfo callInfo) {

		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();
		PingResult pingResult = new PingResult();
		pingResult.setSuccess(false);
		String theurl = null;
		try {
			String endpoint = getEndpointGenerico(list, ParamsConsts.TAG_CA_INFOCERT);
			theurl = endpoint + "/remote/version";
			HttpGet request = new HttpGet(theurl);
			if ("dev".equalsIgnoreCase(activeProfile)) {

				request.setConfig(ProxyUtils.authenticateHttpClient());
			}

			try (CloseableHttpClient httpClient = HttpClients.createDefault();
					CloseableHttpResponse response = httpClient.execute(request)) {
				int retCode = response.getStatusLine().getStatusCode();
				logInfo("retCode: " + retCode);

				String str = "";
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					// return it as a String
					str = EntityUtils.toString(entity);

				}
				if (retCode == HttpURLConnection.HTTP_OK || retCode == HttpURLConnection.HTTP_CREATED) {

					Result result = new Result();
					result.setErrorCode(ErrorHelper.OK);
					result.setMessage("I servizi della CA " + callInfo.getCaCode() + " all'endpoint: " + theurl
							+ " sono correttamente raggiungibili in versione " + str);
					pingResult.setResult(result);
					pingResult.setSuccess(true);
				} else {

					Result result = new Result();
					result.setErrorCode(ErrorHelper.ERRORE_GENERICO);
					result.setMessage(getText(ErrorHelper.ERRORE_GENERICO));
					String descr = "Errore chiamta endpoint " + theurl + " retCode: " + retCode + ": " + str;
					result.setDescription(descr);
					pingResult.setResult(result);
					logError(descr);
				}

			}

		} catch (Exception e) {
			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERRORE_GENERICO);
			result.setMessage(getText(ErrorHelper.ERRORE_GENERICO));
			result.setDescription("Errore chiamata endpoint " + theurl + " -err: " + e.getMessage());
			pingResult.setResult(result);
			logError("Errore chiamata endpoint " + theurl + " -err: " + e.getMessage());

		}
		return pingResult;

	}

	@Override
	public OtpResult requestOtp(OtpReqInput otpReqInput, Long idEvento) {
		List<GatefireDCaCollocazioneDominio> list = cachedListsSrvc.getAllDomini();
		OtpResult ret = new OtpResult();

		Identity identity = otpReqInput.getIdentity();
		CallInfo callInfo = otpReqInput.getCallInfo();

		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);
		try {

			String endpoint = getEndpoint(list, callInfo, GatefireConsts.FUNZ_FIRMA_REMOTA);
			String theurl = endpoint + "/remote/request-otp/" + identity.getUser();

			if ("dev".equalsIgnoreCase(activeProfile)) {
				ProxyUtils.authenticate();
			}

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);

			String jsonString = JsonUtils.fillObject(otpReqInput);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_RICHIESTA_OTP,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doOtpCall(theurl, idEvento);

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
	public FileResult marcaTemporale(Attachment attachment, MarkInput markInput, Long idEvento) {
		List<GatefireDCaCollocazioneDominio> list = cachedListsSrvc.getAllDomini();
		FileResult ret = new FileResult();
		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);
		try {

			String endpoint = getEndpoint(list, markInput.getCallInfo(), GatefireConsts.FUNZ_FIRMA_REMOTA);
			String fileName = null;
			String url = endpoint + "/timestamp/";
			if (MarkType.TSD == markInput.getMarkType()) {
				url += "tsd";
				fileName = attachment.getFileName() + ".tsd";
			} else if (MarkType.TSR == markInput.getMarkType()) {
				url += "tsr";
				fileName = attachment.getFileName() + ".tsr";
			}

			Map<String, String> formFields = new HashMap<>();

			formFields.put(TIMESTAMP_USERNAME, markInput.getMarkIdentity().getUser());
			formFields.put(TIMESTAMP_PASSWORD, markInput.getMarkIdentity().getPassword());

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);

			String jsonString = jsonMap(formFields);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_MARCA,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doMarkCall(url, formFields, attachment, fileName, idEvento);

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
	public FileResult firmaPAdESRemota(Attachment attachment, PadesInput padesInput, SignIdentity signIdentity,
			Long idEvento, boolean chiudiEvento) {
		List<GatefireDCaCollocazioneDominio> list = cachedListsSrvc.getAllDomini();
		FileResult ret = new FileResult();
		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);
		try {

			String endpoint = getEndpoint(list, padesInput.getCallInfo(), GatefireConsts.FUNZ_FIRMA_REMOTA);

			String url = endpoint + "/remote/sign/pades/" + signIdentity.getUser();
			if (padesInput.isRequiredMark()) {
				url = endpoint + "/remote/sign/pades-t/" + signIdentity.getUser();
			}
			String otp = signIdentity.getOtp();
			List<GatefireTParametroOperativo> listParam = cachedListsSrvc.getAllParametriCa();
			Map<String, String> formFields = fillApparence(padesInput, listParam, SignType.REMOTA);

			formFields.put("pin", signIdentity.getPassword());
			formFields.put("otp", otp);
			// box_signature_image

			if (padesInput.isRequiredMark()) {
				formFields.put(TIMESTAMP_USERNAME, padesInput.getMarkIdentity().getUser());
				formFields.put(TIMESTAMP_PASSWORD, padesInput.getMarkIdentity().getPassword());
			}

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);

			String jsonString = jsonMap(formFields, signIdentity.getUser());
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doPadesCall(url, formFields, attachment, padesInput.getSignLayout().getImage(), idEvento);

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
	public FileResult firmaPAdESAutomatica(Attachment attachment, PadesInput padesInput, AutoSignIdentity signIdentity,
			Long idEvento, boolean chiudiEvento) {
		List<GatefireDCaCollocazioneDominio> list = cachedListsSrvc.getAllDomini();
		FileResult ret = new FileResult();
		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);
		try {

			String endpoint = getEndpoint(list, padesInput.getCallInfo(), GatefireConsts.FUNZ_FIRMA_AUTOMATICA);

			String url = endpoint + "/auto/sign/pades/" + signIdentity.getUser();
			if (padesInput.isRequiredMark()) {
				url = endpoint + "/auto/sign/pades-t/" + signIdentity.getUser();
			}

			List<GatefireTParametroOperativo> listParam = cachedListsSrvc.getAllParametriCa();
			Map<String, String> formFields = fillApparence(padesInput, listParam, SignType.AUTOMATICA);

			formFields.put("pin", signIdentity.getPassword());

			if (padesInput.isRequiredMark()) {
				formFields.put(TIMESTAMP_USERNAME, padesInput.getMarkIdentity().getUser());
				formFields.put(TIMESTAMP_PASSWORD, padesInput.getMarkIdentity().getPassword());
			}

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);

			String jsonString = jsonMap(formFields, signIdentity.getUser());
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doPadesCall(url, formFields, attachment, padesInput.getSignLayout().getImage(), idEvento);

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
	public FileResult firmaPAdESMassivaRemota(List<Attachment> attachmentList, PadesInput padesInput,
			SignIdentity signIdentity, Long idEvento) {
		List<GatefireDCaCollocazioneDominio> list = cachedListsSrvc.getAllDomini();

		FileResult ret = new FileResult();
		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);
		try {

			String endpoint = getEndpoint(list, padesInput.getCallInfo(), GatefireConsts.FUNZ_FIRMA_AUTOMATICA);

			String url = endpoint + "/remote/sign/pades/" + signIdentity.getUser();
			if (padesInput.isRequiredMark()) {
				url = endpoint + "/remote/sign/pades-t/" + signIdentity.getUser();
			}

			List<GatefireTParametroOperativo> listParam = cachedListsSrvc.getAllParametriCa();
			Map<String, String> formFields = fillApparence(padesInput, listParam, SignType.AUTOMATICA);

			String otp = signIdentity.getOtp();

			formFields.put("pin", signIdentity.getPassword());
			formFields.put("otp", otp);
			// box_signature_image

			if (padesInput.isRequiredMark()) {
				formFields.put(TIMESTAMP_USERNAME, padesInput.getMarkIdentity().getUser());
				formFields.put(TIMESTAMP_PASSWORD, padesInput.getMarkIdentity().getPassword());
			}
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);

			String jsonString = jsonMap(formFields, signIdentity.getUser());
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doPadesMassivaCall(url, formFields, attachmentList, padesInput.getSignLayout().getImage(), idEvento);

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
	public FileResult firmaPAdESMassivaAutomatica(List<Attachment> attachmentList, PadesInput padesInput,
			AutoSignIdentity signIdentity, Long idEvento) {
		List<GatefireDCaCollocazioneDominio> list = cachedListsSrvc.getAllDomini();

		FileResult ret = new FileResult();
		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);
		try {

			String endpoint = getEndpoint(list, padesInput.getCallInfo(), GatefireConsts.FUNZ_FIRMA_AUTOMATICA);

			String url = endpoint + "/auto/sign/pades/" + signIdentity.getUser();
			if (padesInput.isRequiredMark()) {
				url = endpoint + "/auto/sign/pades-t/" + signIdentity.getUser();
			}

			List<GatefireTParametroOperativo> listParam = cachedListsSrvc.getAllParametriCa();
			Map<String, String> formFields = fillApparence(padesInput, listParam, SignType.AUTOMATICA);

			// String otp = signIdentity.getOtp();

			formFields.put("pin", signIdentity.getPassword());
			// formFields.put("otp", otp);
			// box_signature_image

			if (padesInput.isRequiredMark()) {
				formFields.put(TIMESTAMP_USERNAME, padesInput.getMarkIdentity().getUser());
				formFields.put(TIMESTAMP_PASSWORD, padesInput.getMarkIdentity().getPassword());
			}
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);

			String jsonString = jsonMap(formFields, signIdentity.getUser());
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doPadesMassivaCall(url, formFields, attachmentList, padesInput.getSignLayout().getImage(), idEvento);

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

	private FileResult doPadesCall(String url, Map<String, String> formFields, Attachment attachment, byte[] signImage,
			Long idEvento) {
		logDebug("url chiamata: " + url);
		FileResult ret = new FileResult();
		String fileName = attachment.getFileName();
		try {

			MultipartHttpPost multipart = new MultipartHttpPost(url, activeProfile);
			for (Map.Entry<String, String> entry : formFields.entrySet()) {

				// Add form field
				multipart.addFormField(entry.getKey(), entry.getValue());
			}

			multipart.addFilePartAttachment("contentToSign-0", attachment);

			if (signImage != null) {
				multipart.addFilePartAttachment(BOX_SIGN_IMG, new Attachment(BOX_SIGN_IMG, signImage));

			}

			byte[] retfile = multipart.finishBytes();
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			Attachment retAtt = new Attachment(fileName, retfile);
			ret.setAttachment(retAtt);

		} catch (HttpStreamException e) {
			Result result = parseRetXml(e.getMessage());
			result.setErrorCode(ErrorHelper.ERRORE_FIRMA);
			result.setDescription(getText(ErrorHelper.ERRORE_FIRMA));
			String erroriCredenziali[] = { "PRS-0001", "PRS-0002", "PRS-0003", "PRS-0016" };

			if (Arrays.asList(erroriCredenziali).contains(result.getOriginalReturnCode())
					|| (result.getMessage() != null && result.getMessage().contains("OTP"))) {
				result.setErrorCode(ErrorHelper.ERRORE_CREDENZIALI);
				result.setDescription(getText(ErrorHelper.ERRORE_CREDENZIALI));
			}

			ret.setResult(result);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
					GatefireConsts.EVT_LOG_STATO_ERRORE, ERRORE_CHIAMATA + e.getMessage(),
					result.getOriginalReturnCode(), result.getMessage());
			logError(e);
		} catch (Exception e) {

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERRORE_FIRMA);
			result.setDescription(getText(ErrorHelper.ERRORE_FIRMA));
			result.setMessage(e.getMessage());
			ret.setResult(result);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
					GatefireConsts.EVT_LOG_STATO_ERRORE, ERRORE_CHIAMATA + e.getMessage(), null, null);
			logError(e);
		}
		return ret;

	}

	private FileResult doPadesMassivaCall(String url, Map<String, String> formFields, List<Attachment> attachmentList,
			byte[] signImage, Long idEvento) {

		logDebug("url chiamata: " + url);
		FileResult ret = new FileResult();

		try {

			MultipartHttpPost multipart = new MultipartHttpPost(url, activeProfile);
			for (Map.Entry<String, String> entry : formFields.entrySet()) {

				// Add form field
				multipart.addFormField(entry.getKey(), entry.getValue());
			}
			int count = 0;

			for (Attachment attachment : attachmentList) {
				multipart.addFilePartAttachment("contentToSign-" + count, attachment);
				count++;

			}

			if (signImage != null) {
				multipart.addFilePartAttachment(BOX_SIGN_IMG, new Attachment(BOX_SIGN_IMG, signImage));

			}

			byte[] retfile = multipart.finishBytes();
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			Attachment retAtt = new Attachment(new Date().getTime() + ".zip", retfile);
			ret.setAttachment(retAtt);

		} catch (HttpStreamException e) {
			Result result = parseRetXml(e.getMessage());
			result.setErrorCode(ErrorHelper.ERRORE_FIRMA);
			result.setDescription(getText(ErrorHelper.ERRORE_FIRMA));

			ret.setResult(result);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
					GatefireConsts.EVT_LOG_STATO_ERRORE, ERRORE_CHIAMATA + e.getMessage(),
					result.getOriginalReturnCode(), result.getMessage());
			logError(e);
		} catch (Exception e) {

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERRORE_FIRMA);
			result.setDescription(getText(ErrorHelper.ERRORE_FIRMA));
			result.setMessage(e.getMessage());
			ret.setResult(result);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE,
					GatefireConsts.EVT_LOG_STATO_ERRORE, ERRORE_CHIAMATA + e.getMessage(), null, null);
			logError(e);
		}
		return ret;

	}

	private FileResult doMarkCall(String url, Map<String, String> formFields, Attachment attachment, String fileName,
			Long idEvento) {
		logDebug("url chiamata: " + url);
		FileResult ret = new FileResult();

		try {

			MultipartHttpPost multipart = new MultipartHttpPost(url, activeProfile);
			for (Map.Entry<String, String> entry : formFields.entrySet()) {

				multipart.addFormField(entry.getKey(), entry.getValue());
			}

			multipart.addFilePartAttachment("contentToTimestamp-0", attachment);

			byte[] retfile = multipart.finishBytes();
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_MARCA,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			Attachment retAtt = new Attachment(fileName, retfile);
			ret.setAttachment(retAtt);

		} catch (HttpStreamException e) {
			Result result = parseRetXml(e.getMessage());
			result.setErrorCode(ErrorHelper.ERRORE_MARCA);
			result.setDescription(getText(ErrorHelper.ERRORE_MARCA));

			ret.setResult(result);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_MARCA,
					GatefireConsts.EVT_LOG_STATO_ERRORE, ERRORE_CHIAMATA + e.getMessage(),
					result.getOriginalReturnCode(), result.getMessage());
			logError(e);
		} catch (Exception e) {

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERRORE_MARCA);
			result.setDescription(getText(ErrorHelper.ERRORE_MARCA));
			result.setMessage(e.getMessage());
			ret.setResult(result);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_MARCA,
					GatefireConsts.EVT_LOG_STATO_ERRORE, ERRORE_CHIAMATA + e.getMessage(), null, null);
			logError(e);
		}
		return ret;

	}

	private OtpResult doOtpCall(String theurl, Long idEvento) {

		logDebug("url chiamata: " + theurl);
		OtpResult ret = new OtpResult();

		try {

			HttpGet request = new HttpGet(theurl.trim());
			if ("dev".equalsIgnoreCase(activeProfile)) {

				request.setConfig(ProxyUtils.authenticateHttpClient());
			}
			String msg = "";
			try (CloseableHttpClient httpClient = HttpClients.createDefault();
					CloseableHttpResponse response = httpClient.execute(request)) {
				int retCode = response.getStatusLine().getStatusCode();
				msg += "retCode: " + retCode;

				String str = "";
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					// return it as a String
					str = EntityUtils.toString(entity);
					ret = parseXml(str);
					msg += " - " + str;

				}

			}

			if (!ret.getSuccess().booleanValue()) {
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_RICHIESTA_OTP,
						GatefireConsts.EVT_LOG_STATO_ERRORE, ERRORE_CHIAMATA + msg,
						ret.getResult().getOriginalReturnCode(), ret.getResult().getMessage());
				logError(msg);
			} else {
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_RICHIESTA_OTP,
						GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
				logInfo(msg);
			}

		} catch (Exception e) {

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERRORE_FIRMA);
			result.setDescription(getText(ErrorHelper.ERRORE_FIRMA));
			result.setMessage(e.getMessage());
			ret.setResult(result);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_RICHIESTA_OTP,
					GatefireConsts.EVT_LOG_STATO_ERRORE, ERRORE_CHIAMATA + e.getMessage(), null, null);
			logError(e);
		}
		return ret;

	}

	private Map<String, String> fillApparence(PadesInput padesInput, List<GatefireTParametroOperativo> list,
			SignType signType) throws ValidationException {

		String tipoFirma = GatefireConsts.FUNZ_FIRMA_PADES_REMOTA;

		if (signType == SignType.AUTOMATICA) {
			tipoFirma = GatefireConsts.FUNZ_FIRMA_PADES_AUTOMATICA;
		}
		Map<String, String> apparence = new HashMap<>();
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

		if (StringUtils.hasLength(padesInput.getSignLayout().getReason())) {

			apparence.put("box_signature_reason", padesInput.getSignLayout().getReason());
		}
		if (leftX != null) {
			apparence.put("box_signature_llx", leftX.toString());
		}
		if (leftY != null) {
			apparence.put("box_signature_lly", leftY.toString());
		}
		if (rightX != null) {
			apparence.put("box_signature_urx", rightX.toString());
		}
		if (rightY != null) {
			apparence.put("box_signature_ury", rightY.toString());
		}

		if (page != null) {
			apparence.put("box_signature_page", page.toString());

		}

		if (!padesInput.getSignLayout().isShowDateTime()) {
			apparence.put("box_signature_lbl_date", "");
			apparence.put("box_signature_format_date", "");

		}

		return apparence;
	}

	private String getEndpoint(List<GatefireDCaCollocazioneDominio> list, CallInfo callInfo, String funzione)
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

	private String jsonMap(Map<String, String> map, String alias) {

		Map<String, String> jsonM = new HashMap<>();

		for (Map.Entry<String, String> entry : map.entrySet()) {

			if (entry.getKey().equalsIgnoreCase("pin") || entry.getKey().equalsIgnoreCase("otp")
					|| entry.getKey().equalsIgnoreCase(TIMESTAMP_PASSWORD)) {
				jsonM.put(entry.getKey(), "*****");
			} else {
				jsonM.put(entry.getKey(), entry.getValue());
			}
		}
		jsonM.put("alias", alias);
		return JsonUtils.fillObject(jsonM);
	}

	private String jsonMap(Map<String, String> map) {

		Map<String, String> jsonM = new HashMap<>();

		for (Map.Entry<String, String> entry : map.entrySet()) {

			if (entry.getKey().equalsIgnoreCase("pin") || entry.getKey().equalsIgnoreCase("otp")
					|| entry.getKey().equalsIgnoreCase(TIMESTAMP_PASSWORD)) {
				jsonM.put(entry.getKey(), "*****");
			} else {
				jsonM.put(entry.getKey(), entry.getValue());
			}
		}

		return JsonUtils.fillObject(jsonM);
	}

	private static OtpResult parseXml(String xml) {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		// factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
		// factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

		OtpResult ret = new OtpResult();
		boolean ok = true;
		String errorCode = null;
		String errorDescription = null;
		Result result = new Result();

		try {
			byte[] byteArray = xml.getBytes(StandardCharsets.UTF_8.name());
			ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);

			// Initializing the handler to access the tags in the XML file
			XMLEventReader eventReader = factory.createXMLEventReader(inputStream);

			// Checking the availability of the next tag
			while (eventReader.hasNext()) {
				XMLEvent xmlEvent = eventReader.nextEvent();

				if (xmlEvent.isStartElement()) {
					StartElement startElement = xmlEvent.asStartElement();

					// As soo as employee tag is opened, create new Employee object
					if ("status".equalsIgnoreCase(startElement.getName().getLocalPart())) {

						xmlEvent = (XMLEvent) eventReader.next();

						if (xmlEvent.isCharacters()) {
							String status = xmlEvent.asCharacters().getData();
							if (!"OK".equalsIgnoreCase(status)) {
								ok = false;
							}
						}

					}

					if ("error-code".equalsIgnoreCase(startElement.getName().getLocalPart())) {

						xmlEvent = (XMLEvent) eventReader.next();

						if (xmlEvent.isCharacters()) {
							errorCode = xmlEvent.asCharacters().getData();
						}

					}

					if ("error-description".equalsIgnoreCase(startElement.getName().getLocalPart())) {

						xmlEvent = (XMLEvent) eventReader.next();

						if (xmlEvent.isCharacters()) {
							errorDescription = xmlEvent.asCharacters().getData();
						}

					}

				}

			}

		} catch (UnsupportedEncodingException | XMLStreamException e) {

			e.printStackTrace();
		}
		result.setDescription(errorDescription);
		result.setMessage(errorDescription);
		if (!ok) {
			result.setErrorCode(errorCode);
		}
		result.setOriginalReturnCode(errorCode);

		ret.setSuccess(ok);
		ret.setResult(result);

		return ret;
	}

	private static Result parseRetXml(String xml) {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		// factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
		// factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

		boolean ok = true;
		String errorCode = null;
		String errorDescription = null;

		String proxyErrorCode = null;
		String proxyErrorDescr = null;
		Result result = new Result();

		try {
			byte[] byteArray = xml.getBytes(StandardCharsets.UTF_8.name());
			ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);

			// Initializing the handler to access the tags in the XML file
			XMLEventReader eventReader = factory.createXMLEventReader(inputStream);

			// Checking the availability of the next tag
			while (eventReader.hasNext()) {
				XMLEvent xmlEvent = eventReader.nextEvent();

				if (xmlEvent.isStartElement()) {
					StartElement startElement = xmlEvent.asStartElement();

					// As soo as employee tag is opened, create new Employee object
					if ("status".equalsIgnoreCase(startElement.getName().getLocalPart())) {

						xmlEvent = (XMLEvent) eventReader.next();

						if (xmlEvent.isCharacters()) {
							String status = xmlEvent.asCharacters().getData();
							if (!"OK".equalsIgnoreCase(status)) {
								ok = false;
							}
						}

					}

					if ("error-code".equalsIgnoreCase(startElement.getName().getLocalPart())) {

						xmlEvent = (XMLEvent) eventReader.next();

						if (xmlEvent.isCharacters()) {
							errorCode = xmlEvent.asCharacters().getData();
						}

					}

					if ("error-description".equalsIgnoreCase(startElement.getName().getLocalPart())) {

						xmlEvent = (XMLEvent) eventReader.next();

						if (xmlEvent.isCharacters()) {
							errorDescription = xmlEvent.asCharacters().getData();
						}

					}

					if ("proxysign-error-code".equalsIgnoreCase(startElement.getName().getLocalPart())) {

						xmlEvent = (XMLEvent) eventReader.next();

						if (xmlEvent.isCharacters()) {
							proxyErrorCode = xmlEvent.asCharacters().getData();
						}

					}

					if ("proxysign-error-description".equalsIgnoreCase(startElement.getName().getLocalPart())) {

						xmlEvent = (XMLEvent) eventReader.next();

						if (xmlEvent.isCharacters()) {
							proxyErrorDescr = xmlEvent.asCharacters().getData();
						}

					}

				}

			}

		} catch (UnsupportedEncodingException | XMLStreamException e) {

			e.printStackTrace();
		}

		if (!ok) {
			result.setErrorCode(ErrorHelper.ERRORE_GENERICO);
		}
		if ("ERROR-SIGNATURE".equalsIgnoreCase(errorCode)) {
			if (StringUtils.hasLength(proxyErrorDescr)) {
				errorCode = proxyErrorCode;
			}
			if (StringUtils.hasLength(proxyErrorDescr)) {
				errorDescription = proxyErrorDescr;
			}
		}
		result.setDescription(errorDescription);
		result.setMessage(errorDescription);
		if (!ok) {
			result.setErrorCode(errorCode);
		}
		result.setOriginalReturnCode(errorCode);

		return result;
	}

	private String getEndpointGenerico(List<GatefireTParametroOperativo> list, String codiceCa)
			throws ValidationException {

		String endpoint = getValoreParametroCA(list, ParamsConsts.PARAM_CA_ENDPOINT_PROXY_SIGN, codiceCa);
		if (endpoint == null) {
			ErrorField errorfield = new ErrorField(ParamsConsts.PARAM_CA_ENDPOINT_PROXY_SIGN,
					"parametro mancante a db");

			throw new ValidationException("endpoint non trovato per " + codiceCa, errorfield);
		}
		return endpoint;
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
}
