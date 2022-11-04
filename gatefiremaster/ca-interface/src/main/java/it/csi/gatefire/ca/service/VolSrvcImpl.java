package it.csi.gatefire.ca.service;

import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.message.Message;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.csi.gatefire.ca.aruba.actalisvol.FileInfo;
import it.csi.gatefire.ca.aruba.actalisvol.Return;
import it.csi.gatefire.ca.aruba.actalisvol.ReturnTimeStampDataValidation;
import it.csi.gatefire.ca.aruba.actalisvol.ReturnTimeStampValidation;
import it.csi.gatefire.ca.aruba.actalisvol.Signer;
import it.csi.gatefire.ca.aruba.actalisvol.VerificationService;
import it.csi.gatefire.ca.util.ArubaObjectFiller;
import it.csi.gatefire.common.consts.GatefireConsts;
import it.csi.gatefire.common.consts.ParamsConsts;
import it.csi.gatefire.common.exception.ValidationException;
import it.csi.gatefire.common.model.Attachment;
import it.csi.gatefire.common.model.ErrorField;
import it.csi.gatefire.common.model.MarkVerifyResult;
import it.csi.gatefire.common.model.Result;
import it.csi.gatefire.common.model.SignVerifyResult;
import it.csi.gatefire.common.util.ErrorHelper;
import it.csi.gatefire.common.util.FileUtils;
import it.csi.gatefire.common.util.JsonUtils;
import it.csi.gatefire.common.util.ProxyUtils;
import it.csi.gatefire.dbhelper.model.GatefireLEvento;
import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativo;
import it.csi.gatefire.dbhelper.service.BaseService;
import it.csi.gatefire.dbhelper.service.CachedListsSrvc;
import it.csi.gatefire.dbhelper.service.EventoSrvc;

@Service("volSrvc")
public class VolSrvcImpl extends BaseService implements VolSrvc {
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

			VerificationService verificationService = initService(endpoint);

			FileInfo fileInfo = new FileInfo();
			fileInfo.setAutoDetectType(true);
			String extension = FileUtils.getEstensioneFile(attachment.getFileName());
			fileInfo.setFileExtension(extension);

			fileInfo.setFileName(attachment.getFileName());

			String contentType = new Tika().detect(attachment.getFile());

			if (contentType == null && "p7m".equalsIgnoreCase(extension))
				contentType = "application/x-pkcs7-mime";
			DataSource source = new ByteArrayDataSource(attachment.getFile(), contentType);

			DataHandler fileContent = new DataHandler(source);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(fileInfo, attachment, contentType);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doVerificaFirma(verificationService, fileInfo, fileContent, idEvento);

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

	public MarkVerifyResult verificaMarca(Attachment attachment, String timeStampToken, String codiceCa,
			Long idEvento) {
		List<GatefireTParametroOperativo> list = cachedListsSrvc.getAllParametriCa();

		MarkVerifyResult ret = new MarkVerifyResult();

		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);
		try {
			String endpoint = getEndpoint(list, codiceCa);

			VerificationService verificationService = initService(endpoint);

			FileInfo fileInfo = new FileInfo();
			fileInfo.setAutoDetectType(true);
			String extension = FileUtils.getEstensioneFile(attachment.getFileName());
			fileInfo.setFileExtension(extension);

			fileInfo.setFileName(attachment.getFileName());

			String contentType = new Tika().detect(attachment.getFile());

			if (contentType == null && "p7m".equalsIgnoreCase(extension))
				contentType = "application/x-pkcs7-mime";
			DataSource source = new ByteArrayDataSource(attachment.getFile(), contentType);

			DataHandler fileContent = new DataHandler(source);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(attachment, contentType);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ret = doVerificaMarca(verificationService, fileContent,  timeStampToken, idEvento);

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

	private SignVerifyResult doVerificaFirma(VerificationService verificationService, FileInfo fileInfo,
			DataHandler fileContent, Long idEvento) {
		SignVerifyResult ret = new SignVerifyResult();
		try {

			Return rets = verificationService.verification(fileInfo, fileContent, null);

			String error = rets.getError();

			if (error == null) {
				List<Signer> signers = rets.getSigners();
				ret.setSigner(ArubaObjectFiller.fillSignerList(signers));

				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA,
						GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			} else {

				Result result = new Result(ErrorHelper.ERRORE_VERIFICA_FIRMA);
				result.setDescription(getText(ErrorHelper.ERRORE_VERIFICA_FIRMA));
				result.setMessage(error);
				ret.setResult(result);
				logError(error);
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA,
						GatefireConsts.EVT_LOG_STATO_ERRORE, null, null, error);
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

	private MarkVerifyResult doVerificaMarca(VerificationService verificationService, DataHandler fileContent,
			String timeStampToken, Long idEvento) {
		MarkVerifyResult ret = new MarkVerifyResult();
		try {
			String error = null;
			if (timeStampToken != null) {
				ReturnTimeStampValidation retSt = verificationService.verifyTimeStampToken(timeStampToken, fileContent);
				ret.setValid("OK".equalsIgnoreCase(retSt.getError()));
				ret.setValidation(retSt.getSigValidation());
				ret.setValidationMessage(retSt.getSigValidationMessage());
				ret.setTimeStamp(ArubaObjectFiller.fillTimeStamp(retSt.getTimeStamp()));
				ret.setCertificate(ArubaObjectFiller.fillCertificate(retSt.getCertificate()));

				// ret.setOriginalFile(new Attachment(fileName,
				// FileUtils.dataHandlerToBytes(fileContent)));
				error = retSt.getError();
			} else {
				ReturnTimeStampDataValidation retSt = verificationService.verifyTimeStampData(fileContent);
				ret.setValidation(retSt.getSigValidation());
				ret.setValidationMessage(retSt.getSigValidationMessage());
				ret.setTimeStamp(ArubaObjectFiller.fillTimeStamp(retSt.getTimeStamp()));
				ret.setCertificate(ArubaObjectFiller.fillCertificate(retSt.getCertificate()));

				// ret.setOriginalFile(new Attachment(fileName,
				// FileUtils.dataHandlerToBytes(retSt.getOriginalFile())));
				error = retSt.getError();
			}

			if (!"OK".equalsIgnoreCase(error) && error != null) {
				Result res = new Result();
				res.setErrorCode(ErrorHelper.ERRORE_GENERICO);
				res.setMessage(error);
				ret.setResult(res);
			}

			if ("OK".equalsIgnoreCase(error) || error == null) {

				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA,
						GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			} else {

				Result result = new Result(ErrorHelper.ERRORE_VERIFICA_MARCA);
				result.setDescription(getText(ErrorHelper.ERRORE_VERIFICA_MARCA));
				result.setMessage(error);
				ret.setResult(result);
				logError(error);
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA,
						GatefireConsts.EVT_LOG_STATO_ERRORE, null, null, error);
			}

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

	private VerificationService initService(String endpoint) {

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.setServiceClass(VerificationService.class);
		factory.setAddress(endpoint);

		LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
		loggingInInterceptor.setPrettyLogging(true);
		LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
		loggingOutInterceptor.setPrettyLogging(true);
		loggingOutInterceptor.setLogMultipart(true);
		loggingOutInterceptor.setLogBinary(true);
		factory.getInInterceptors().add(loggingInInterceptor);
		factory.getOutInterceptors().add(loggingOutInterceptor);

		VerificationService verificationService = (VerificationService) factory.create();
		Client client = ClientProxy.getClient(verificationService);
		client.getRequestContext().put(Message.ENDPOINT_ADDRESS, endpoint);

		if ("dev".equalsIgnoreCase(activeProfile)) {
			ProxyUtils.authenticate(client);
		}
		return verificationService;
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

		String endpoint = getValoreParametroCA(list, ParamsConsts.PARAM_CA_ENDPOINT_VOL, codiceCa);
		if (endpoint == null) {
			ErrorField errorfield = new ErrorField(ParamsConsts.PARAM_CA_ENDPOINT_VOL, "parametro mancante a db");

			throw new ValidationException("endpoint non trovato per " + codiceCa, errorfield);
		}
		return endpoint;
	}
}
