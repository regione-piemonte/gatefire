package it.csi.gatefire.ca.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.csi.gatefire.ca.infocert.DeSign;
import it.csi.gatefire.ca.util.InfocertObjFiller;
import it.csi.gatefire.ca.util.MultipartHttpPost;
import it.csi.gatefire.common.consts.GatefireConsts;
import it.csi.gatefire.common.consts.ParamsConsts;
import it.csi.gatefire.common.exception.ValidationException;
import it.csi.gatefire.common.model.Attachment;
import it.csi.gatefire.common.model.ErrorField;
import it.csi.gatefire.common.model.MarkVerifyResult;
import it.csi.gatefire.common.model.Result;
import it.csi.gatefire.common.model.SignVerifyResult;
import it.csi.gatefire.common.model.Signer;
import it.csi.gatefire.common.util.ErrorHelper;
import it.csi.gatefire.common.util.JsonUtils;
import it.csi.gatefire.dbhelper.model.GatefireLEvento;
import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativo;
import it.csi.gatefire.dbhelper.service.BaseService;
import it.csi.gatefire.dbhelper.service.CachedListsSrvc;
import it.csi.gatefire.dbhelper.service.EventoSrvc;

@Service("poxySignVerSrvc")
public class ProxySignVerSrvcImpl extends BaseService implements ProxySignVerSrvc {
	@Value("${spring.profiles.active:Unknown}")
	private String activeProfile;

	@Autowired
	private CachedListsSrvc cachedListsSrvc;

	@Autowired
	EventoSrvc eventoSrvc;

	public SignVerifyResult verificaFirma(Attachment attachment, String codiceCa, Long idEvento) {
		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();

		SignVerifyResult ret = new SignVerifyResult();

		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);
		try {

			String endpoint = getEndpoint(list, codiceCa);

			String url = endpoint + "/verify/";

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(attachment);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doVerificaFirma(url, attachment, idEvento);

		} catch (ValidationException e) {

			Result res = new Result();

			res.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			res.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			res.setMessage(e.getErrorFields().get(0).toString());
			ret.setResult(res);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);

		} catch (Exception e) {
			Result res = new Result();
			res.setErrorCode(ErrorHelper.ERRORE_GENERICO);
			res.setDescription(getText(ErrorHelper.ERRORE_GENERICO));
			res.setMessage(e.getMessage());
			ret.setResult(res);
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

	public MarkVerifyResult verificaMarca(Attachment attachment, Attachment originalFile, String codiceCa,
			Long idEvento) {
		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();

		MarkVerifyResult ret = new MarkVerifyResult();

		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);
		try {

			String endpoint = getEndpoint(list, codiceCa);

			String url = endpoint + "/verify/";

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(attachment);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doVerificaMarca(url, attachment, originalFile, idEvento);

		} catch (ValidationException e) {

			Result res = new Result();

			res.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			res.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			res.setMessage(e.getErrorFields().get(0).toString());
			ret.setResult(res);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);

		} catch (Exception e) {
			Result res = new Result();
			res.setErrorCode(ErrorHelper.ERRORE_GENERICO);
			res.setDescription(getText(ErrorHelper.ERRORE_GENERICO));
			res.setMessage(e.getMessage());
			ret.setResult(res);
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

	private SignVerifyResult doVerificaFirma(String url, Attachment attachment, Long idEvento) {
		SignVerifyResult ret = new SignVerifyResult();
		try {

			MultipartHttpPost multipart = new MultipartHttpPost(url, activeProfile);

			multipart.addFormField("language", "it");
			multipart.addFilePartAttachment("contentToVerify", attachment);
			multipart.addFormField("originalFileToVerify", "");

			String response = multipart.finish();

			JAXBContext jaxbContext = JAXBContext.newInstance(DeSign.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			DeSign design = (DeSign) jaxbUnmarshaller.unmarshal(new StringReader(response));

			List<Signer> signerList = new ArrayList<>();
			List<Object> list = design.getSignedDataOrTimeStamp();
			String error = null;
			String errorCode = null;

			for (Object object : list) {
				if (object instanceof it.csi.gatefire.ca.infocert.SignedData) {
					it.csi.gatefire.ca.infocert.SignedData signedData = (it.csi.gatefire.ca.infocert.SignedData) object;
					List<Object> objlist = signedData.getSignerOrSignedDataOrErrorCodeOrErrorMessageOrStatus();
					for (Object obj : objlist) {
						if (obj instanceof it.csi.gatefire.ca.infocert.Signer) {

							it.csi.gatefire.ca.infocert.Signer temp = (it.csi.gatefire.ca.infocert.Signer) obj;
							Signer signer = InfocertObjFiller.fillSigner(temp);
							signerList.add(signer);

						} else if (obj instanceof it.csi.gatefire.ca.infocert.SignedData) {
							it.csi.gatefire.ca.infocert.SignedData temp = (it.csi.gatefire.ca.infocert.SignedData) obj;
							List<Object> innerList = temp.getSignerOrSignedDataOrErrorCodeOrErrorMessageOrStatus();
							for (Object innerObj : innerList) {
								if (innerObj instanceof it.csi.gatefire.ca.infocert.Signer) {

									it.csi.gatefire.ca.infocert.Signer tempSigner = (it.csi.gatefire.ca.infocert.Signer) innerObj;
									Signer signer = InfocertObjFiller.fillSigner(tempSigner);
									signerList.add(signer);
								}
							}
						} else if (obj instanceof it.csi.gatefire.ca.infocert.ErrorCode) {
							errorCode = ((it.csi.gatefire.ca.infocert.ErrorCode) obj).getvalue();
						} else if (obj instanceof it.csi.gatefire.ca.infocert.ErrorMessage) {
							error = ((it.csi.gatefire.ca.infocert.ErrorMessage) obj).getvalue();
						}
					}

				}
			}

			if (error == null) {
				ret.setSigner(signerList);

				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA,
						GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			} else {

				Result result = new Result(ErrorHelper.ERRORE_VERIFICA_FIRMA);
				result.setDescription(getText(ErrorHelper.ERRORE_VERIFICA_FIRMA));
				result.setMessage(error);
				ret.setResult(result);
				logError(error);
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA,
						GatefireConsts.EVT_LOG_STATO_ERRORE, errorCode, error, error);
			}

		} catch (Exception e) {
			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERRORE_VERIFICA_FIRMA);
			result.setDescription(getText(ErrorHelper.ERRORE_VERIFICA_FIRMA));
			result.setMessage(e.getMessage());
			ret.setResult(result);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA,
					GatefireConsts.EVT_LOG_STATO_ERRORE, "Errore chiamata servizio remoto: " + e.getMessage(), null,
					null);
			logError(e);
		}

		return ret;
	}

	private MarkVerifyResult doVerificaMarca(String url, Attachment attachment, Attachment originalFile,
			Long idEvento) {
		MarkVerifyResult ret = new MarkVerifyResult();

		try {

			MultipartHttpPost multipart = new MultipartHttpPost(url, activeProfile);

			multipart.addFormField("language", "it");
			multipart.addFilePartAttachment("contentToVerify", attachment);
			if (originalFile == null) {
				multipart.addFormField("originalFileToVerify", "");
			} else {
				multipart.addFilePartAttachment("originalFileToVerify", originalFile);
			}

			String response = multipart.finish();

			JAXBContext jaxbContext = JAXBContext.newInstance(DeSign.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			DeSign design = (DeSign) jaxbUnmarshaller.unmarshal(new StringReader(response));

			List<Object> list = design.getSignedDataOrTimeStamp();

			for (Object object : list) {

				if (object instanceof it.csi.gatefire.ca.infocert.TimeStamp) {

					it.csi.gatefire.ca.infocert.TimeStamp timestamp = (it.csi.gatefire.ca.infocert.TimeStamp) object;

					ret = InfocertObjFiller.fillMarkVerifyResult(timestamp);

				} else if (object instanceof it.csi.gatefire.ca.infocert.SignedData) {
					it.csi.gatefire.ca.infocert.SignedData temp = (it.csi.gatefire.ca.infocert.SignedData) object;
					List<Object> innerList = temp.getSignerOrSignedDataOrErrorCodeOrErrorMessageOrStatus();
					for (Object innerObj : innerList) {
						if (innerObj instanceof it.csi.gatefire.ca.infocert.ErrorCode) {

							it.csi.gatefire.ca.infocert.ErrorCode errorCode = (it.csi.gatefire.ca.infocert.ErrorCode) innerObj;
							ret.getResult().setOriginalReturnCode(errorCode.getvalue());

						}
						if (innerObj instanceof it.csi.gatefire.ca.infocert.ErrorMessage) {

							it.csi.gatefire.ca.infocert.ErrorMessage errorCode = (it.csi.gatefire.ca.infocert.ErrorMessage) innerObj;
							ret.getResult().setMessage(errorCode.getvalue());

						}
						if (innerObj instanceof it.csi.gatefire.ca.infocert.Status) {

							it.csi.gatefire.ca.infocert.Status errorCode = (it.csi.gatefire.ca.infocert.Status) innerObj;
							if ("KO".equalsIgnoreCase(errorCode.getvalue())) {
								ret.getResult().setErrorCode(ErrorHelper.ERRORE_VERIFICA_MARCA);
								ret.getResult().setDescription(getText(ErrorHelper.ERRORE_VERIFICA_MARCA));
							}
						}
					}
				}

			}

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);

		} catch (Exception e) {
			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERRORE_VERIFICA_MARCA);
			result.setDescription(getText(ErrorHelper.ERRORE_VERIFICA_MARCA));
			result.setMessage(e.getMessage());
			ret.setResult(result);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA,
					GatefireConsts.EVT_LOG_STATO_ERRORE, "Errore chiamata servizio remoto: " + e.getMessage(), null,
					null);
			logError(e);
		}

		return ret;
	}

	private String getEndpoint(List<GatefireTParametroOperativo> list, String codiceCa) throws ValidationException {

		String endpoint = getValoreParametroCA(list, ParamsConsts.PARAM_CA_ENDPOINT_PROXY_SIGN_VERIFY, codiceCa);
		if (endpoint == null) {
			ErrorField errorfield = new ErrorField(ParamsConsts.PARAM_CA_ENDPOINT_PROXY_SIGN_VERIFY,
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
