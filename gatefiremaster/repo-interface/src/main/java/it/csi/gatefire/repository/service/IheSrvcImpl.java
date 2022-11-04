/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.repository.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.message.Message;
import org.apache.cxf.rt.security.SecurityConstants;
import org.apache.cxf.rt.security.claims.Claim;
import org.apache.cxf.ws.addressing.WSAddressingFeature;
import org.apache.cxf.ws.security.tokenstore.SecurityToken;
import org.apache.cxf.ws.security.trust.STSClient;
import org.apache.cxf.ws.security.trust.STSUtils;
import org.apache.tika.Tika;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLProvideAndRegisterDocumentSetRequest30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLQueryResponse30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLSubmitObjectsRequest30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.RetrieveDocumentSetRequestType;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.RetrieveDocumentSetRequestType.DocumentRequest;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.RetrieveDocumentSetResponseType;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Association;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.AssociationLabel;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.AssociationType;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Document;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Identifiable;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.SubmissionSet;
import org.openehealth.ipf.commons.ihe.xds.core.requests.ProvideAndRegisterDocumentSet;
import org.openehealth.ipf.commons.ihe.xds.core.requests.QueryRegistry;
import org.openehealth.ipf.commons.ihe.xds.core.requests.RegisterDocumentSet;
import org.openehealth.ipf.commons.ihe.xds.core.requests.query.GetDocumentsQuery;
import org.openehealth.ipf.commons.ihe.xds.core.requests.query.QueryReturnType;
import org.openehealth.ipf.commons.ihe.xds.core.responses.QueryResponse;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.lcm.RemoveObjectsRequest;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.query.AdhocQueryResponse;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rim.ObjectRefListType;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rim.ObjectRefType;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rs.RegistryError;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rs.RegistryResponseType;
import org.openehealth.ipf.commons.ihe.xds.core.transform.requests.QueryRegistryTransformer;
import org.openehealth.ipf.commons.ihe.xds.iti18.Iti18PortType;
import org.openehealth.ipf.commons.ihe.xds.iti43.Iti43PortType;
import org.openehealth.ipf.commons.ihe.xds.iti62.Iti62PortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Element;

import it.csi.gatefire.common.consts.GatefireConsts;
import it.csi.gatefire.common.consts.security.PasswordUtils;
import it.csi.gatefire.common.exception.ValidationException;
import it.csi.gatefire.common.model.AssertionIdentity;
import it.csi.gatefire.common.model.Attachment;
import it.csi.gatefire.common.model.CallInfo;
import it.csi.gatefire.common.model.ErrorField;
import it.csi.gatefire.common.model.FileResult;
import it.csi.gatefire.common.model.ITIMetadata;
import it.csi.gatefire.common.model.RepositoryDocIdInput;
import it.csi.gatefire.common.model.RepositoryInput;
import it.csi.gatefire.common.model.RepositoryMetadataUpdateInput;
import it.csi.gatefire.common.model.RepositoryQueryResult;
import it.csi.gatefire.common.model.RepositoryUndoInput;
import it.csi.gatefire.common.model.Result;
import it.csi.gatefire.common.model.repository.DocumentEntry;
import it.csi.gatefire.common.model.repository.consts.RepoConsts;
import it.csi.gatefire.common.util.ErrorHelper;
import it.csi.gatefire.common.util.FileUtils;
import it.csi.gatefire.common.util.JsonUtils;
import it.csi.gatefire.dbhelper.model.GatefireDApplicazione;
import it.csi.gatefire.dbhelper.model.GatefireDRepository;
import it.csi.gatefire.dbhelper.model.GatefireDXdscode;
import it.csi.gatefire.dbhelper.model.GatefireLEvento;
import it.csi.gatefire.dbhelper.model.GatefireRRepositoryAccount;
import it.csi.gatefire.dbhelper.model.GatefireRRepositoryUri;
import it.csi.gatefire.dbhelper.service.BaseService;
import it.csi.gatefire.dbhelper.service.CachedListsSrvc;
import it.csi.gatefire.dbhelper.service.EventoSrvc;
import it.csi.gatefire.repository.assertion.AddStsTokenInterceptor;
import it.csi.gatefire.repository.assertion.GatefireClaimCollection;
import it.csi.gatefire.repository.filler.RepoFiller;
import it.csi.gatefire.repository.xds.iti41.Iti41PortType;
import it.csi.gatefire.repository.xds.iti57.Iti57PortType;
import it.csi.gatefire.repository.xds.transform.ebxml.GatefireEbXmlFactory;
import it.csi.gatefire.repository.xds.transform.ebxml.QueryResponseTransformer;

@Service("iheSrvc")
public class IheSrvcImpl extends BaseService implements IheSrvc {
	@Value("${spring.profiles.active:Unknown}")
	private String activeProfile;
	@Autowired
	private EventoSrvc eventoSrvc;

	@Autowired
	private CachedListsSrvc cachedListsSrvc;

	private static final String STATUS_SUCCESS = "urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Success";

	private static final String xmlParserProp = "org.xml.sax.driver";
	private static final String xmlParserValue = "com.sun.org.apache.xerces.internal.parsers.SAXParser";

	private static final String HAS_MEMBER_ASSOC = "HAS_MEMBER_ASOCIATION_1";

	@Transactional
	public Result archiviaDoc(RepositoryInput input, CallInfo callInfo, Long idEvento, Long idEventoPadre) {
		List<GatefireDRepository> list = cachedListsSrvc.getAllRepository();
		List<GatefireRRepositoryUri> uriList = cachedListsSrvc.getAllRepositoryUri();

		Result res;
		var data = new Date();

		GatefireLEvento evento;

		if (idEvento != null) {
			evento = eventoSrvc.getEvento(idEvento);
		} else {
			GatefireLEvento eventoPadre = eventoSrvc.getEvento(idEventoPadre);

			evento = new GatefireLEvento(data);

			evento.setCodiceApplicazione(eventoPadre.getCodiceApplicazione());
			evento.setCodiceCertificationAuthority(eventoPadre.getCodiceCertificationAuthority());

			evento.setCodiceFiscale(eventoPadre.getCodiceFiscale());
			evento.setCollocazione(eventoPadre.getCollocazione());

			evento.setIdUtente(eventoPadre.getIdUtente());

			evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
			evento.setCodiceGatefireDTipoEvento(GatefireConsts.EVT_TIPO_ARCHIVIA_FILE);
			evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_REPOSITORY);

			evento.setNomeFile(input.getAttachment().getFileName());
			evento.setIpApplicazione(eventoPadre.getIpApplicazione());
			evento.setIdEventoPadre(idEventoPadre);

			String jsonInput = JsonUtils.fillObjects(input, callInfo);

			evento = eventoSrvc.insertEvento(evento, jsonInput);
			eventoSrvc.insertEventoLog(evento.getId(), GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);

			eventoPadre.setIdEventoFiglio(evento.getId());
			eventoSrvc.updateEvento(eventoPadre);

		}

		try {
			GatefireDRepository repository = getRepository(list, uriList, callInfo);
			var repoSrvc = initIti41Service(repository, callInfo.getApplicationCode());

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(input, repository);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			res = doIti41Call(repoSrvc, input, repository, evento.getId(), callInfo.getApplicationCode());
		} catch (ValidationException e) {

			res = new Result();

			res.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			res.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			res.setMessage(e.getErrorFields().get(0).toString());

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);

		} catch (Exception e) {
			res = new Result();
			res.setErrorCode(ErrorHelper.ERRORE_GENERICO);
			res.setDescription(getText(ErrorHelper.ERRORE_GENERICO));
			res.setMessage(e.getMessage());

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);
		}

		if (idEventoPadre == null) {
			if (ErrorHelper.OK.equalsIgnoreCase(res.getErrorCode())) {
				eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null);
			} else {
				eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
			}
		}
		return res;
	}

	@Override
	@Transactional
	public Result annullaDoc(RepositoryUndoInput input, CallInfo callInfo, Long idEvento) {
		List<GatefireDRepository> list = cachedListsSrvc.getAllRepository();
		List<GatefireRRepositoryUri> uriList = cachedListsSrvc.getAllRepositoryUri();

		Result res = new Result();

		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);

		try {
			GatefireDRepository repository = getRepository(list, uriList, callInfo);
			var repoSrvc = initIti18Service(repository, callInfo.getApplicationCode());

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(input);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_INQUIRY_REPOSITORY,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			RepositoryDocIdInput inqInput = new RepositoryDocIdInput();
			inqInput.setDocUid(input.getOriginalDocUniqueId());

			RepositoryQueryResult result = doIti18Call(repoSrvc, inqInput, idEvento);

			if (result.getResult().getErrorCode().equalsIgnoreCase("0")) {

				Iti41PortType iti41PortType = initIti41UndoService(repository, callInfo.getApplicationCode());

				jsonString = JsonUtils.fillObjects(result);
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
						GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);
				res = doIti41UndoCall(iti41PortType, input, result, idEvento, callInfo.getApplicationCode());
			} else {
				res = result.getResult();
			}

		} catch (

		ValidationException e) {

			res.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);

			res.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			res.setMessage(e.getErrorFields().get(0).toString());

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);

		} catch (Exception e) {

			res.setErrorCode(ErrorHelper.ERRORE_GENERICO);
			res.setDescription(getText(ErrorHelper.ERRORE_GENERICO));
			res.setMessage(e.getMessage());

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);
		}

		if (ErrorHelper.OK.equalsIgnoreCase(res.getErrorCode())) {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null);
		} else {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
		}

		return res;
	}

	@Override
	@Transactional
	public Result modificaMetadati(RepositoryMetadataUpdateInput input, CallInfo callInfo, Long idEvento) {
		List<GatefireDRepository> list = cachedListsSrvc.getAllRepository();
		List<GatefireRRepositoryUri> uriList = cachedListsSrvc.getAllRepositoryUri();

		Result res;

		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);

		try {
			GatefireDRepository repository = getRepository(list, uriList, callInfo);

			var repoSrvc = initIti18Service(repository, callInfo.getApplicationCode());
			RepositoryDocIdInput repositoryDocIdInput = new RepositoryDocIdInput();
			repositoryDocIdInput.setDocUid(input.getDocUid());
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(repositoryDocIdInput);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_INQUIRY_REPOSITORY,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			RepositoryQueryResult result = doIti18Call(repoSrvc, repositoryDocIdInput, idEvento);

			var iti57Srvc = initIti57Service(repository, callInfo.getApplicationCode());

			jsonString = JsonUtils.fillObjects(input);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_MODIFICA_METADATI,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			ITIMetadata metadata = new ITIMetadata();
			metadata.setDocumento(input.getMetadata().getDocumento());
			metadata.setRichiesta(input.getMetadata().getRichiesta());

			res = doIti57Call(iti57Srvc, metadata, result, repository, evento.getId(), callInfo.getApplicationCode());
		} catch (ValidationException e) {

			res = new Result();

			res.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
			res.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			res.setMessage(e.getErrorFields().get(0).toString());

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);

		} catch (Exception e) {
			res = new Result();
			res.setErrorCode(ErrorHelper.ERRORE_GENERICO);
			res.setDescription(getText(ErrorHelper.ERRORE_GENERICO));
			res.setMessage(e.getMessage());

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);
		}

		if (ErrorHelper.OK.equalsIgnoreCase(res.getErrorCode())) {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null);
		} else {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
		}

		return res;
	}

	@Override
	@Transactional
	public FileResult recuperaDoc(RepositoryDocIdInput input, CallInfo callInfo, Long idEvento) {
		List<GatefireDRepository> list = cachedListsSrvc.getAllRepository();
		List<GatefireRRepositoryUri> uriList = cachedListsSrvc.getAllRepositoryUri();

		FileResult res = new FileResult();

		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);

		try {
			GatefireDRepository repository = getRepository(list, uriList, callInfo);
			var repoSrvc = initIti18Service(repository, callInfo.getApplicationCode());

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(input);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_INQUIRY_REPOSITORY,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			RepositoryQueryResult result = doIti18Call(repoSrvc, input, idEvento);

			if (result.getResult().getErrorCode().equalsIgnoreCase("0")) {

				Iti43PortType iti43PortType = initIti43Service(repository, callInfo.getApplicationCode());
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setDocumentUniqueId(input.getDocUid());
				documentRequest.setRepositoryUniqueId(result.getDocumentEntry().getRepositoryUniqueId());
				jsonString = JsonUtils.fillObjects(documentRequest);
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_RECUPERA_FILE,
						GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);
				res = doIti43Call(iti43PortType, documentRequest, idEvento);
			}

		} catch (

		ValidationException e) {

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);

			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(e.getErrorFields().get(0).toString());
			res.setResult(result);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);

		} catch (Exception e) {
			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERRORE_GENERICO);
			result.setDescription(getText(ErrorHelper.ERRORE_GENERICO));
			result.setMessage(e.getMessage());
			res.setResult(result);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);
		}

		if (ErrorHelper.OK.equalsIgnoreCase(res.getResult().getErrorCode())) {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null);
		} else {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
		}

		return res;
	}

	@Override
	@Transactional
	public RepositoryQueryResult recuperaMetadataDoc(RepositoryDocIdInput input, CallInfo callInfo, Long idEvento) {
		List<GatefireDRepository> list = cachedListsSrvc.getAllRepository();
		List<GatefireRRepositoryUri> uriList = cachedListsSrvc.getAllRepositoryUri();

		RepositoryQueryResult res = new RepositoryQueryResult();

		GatefireLEvento evento = eventoSrvc.getEvento(idEvento);

		try {
			GatefireDRepository repository = getRepository(list, uriList, callInfo);
			var repoSrvc = initIti18Service(repository, callInfo.getApplicationCode());

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null);
			String jsonString = JsonUtils.fillObjects(input);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_INQUIRY_REPOSITORY,
					GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);

			res = doIti18Call(repoSrvc, input, idEvento);

		} catch (

		ValidationException e) {

			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);

			result.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
			result.setMessage(e.getErrorFields().get(0).toString());
			res.setResult(result);

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);

		} catch (Exception e) {
			Result result = new Result();
			result.setErrorCode(ErrorHelper.ERRORE_GENERICO);
			result.setDescription(getText(ErrorHelper.ERRORE_GENERICO));
			result.setMessage(e.getMessage());
			res.setResult(result);
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
					GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
			logError(e);
		}

		if (ErrorHelper.OK.equalsIgnoreCase(res.getResult().getErrorCode())) {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null);
		} else {
			eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE, null);
		}

		return res;
	}

	/*
	 * @Transactional public Result cancellaDoc(RepositoryDeleteInput input,
	 * CallInfo callInfo, Long idEvento, Long idEventoPadre) { Result res = null;
	 * 
	 * List<GatefireDRepository> list = cachedListsSrvc.getAllRepository();
	 * GatefireLEvento evento; var data = new Date(); if (idEvento != null) { evento
	 * = eventoSrvc.getEvento(idEvento); } else { GatefireLEvento eventoPadre =
	 * eventoSrvc.getEvento(idEventoPadre);
	 * 
	 * evento = new GatefireLEvento(data);
	 * 
	 * evento.setCodiceApplicazione(eventoPadre.getCodiceApplicazione());
	 * evento.setCodiceCertificationAuthority(eventoPadre.
	 * getCodiceCertificationAuthority());
	 * 
	 * evento.setCodiceFiscale(eventoPadre.getCodiceFiscale());
	 * evento.setCollocazione(eventoPadre.getCollocazione());
	 * 
	 * evento.setIdUtente(eventoPadre.getIdUtente());
	 * 
	 * evento.setCodiceStatoEvento(GatefireConsts.EVT_STATO_INIZIO_ELAB);
	 * evento.setCodiceGatefireDTipoEvento(GatefireConsts.
	 * EVT_TIPO_CANCELLA_FILE_ARCH);
	 * evento.setCodiceOperazione(GatefireConsts.CODICE_OPERAZIONE_REPOSITORY);
	 * 
	 * evento.setNomeFile("");
	 * evento.setIpApplicazione(eventoPadre.getIpApplicazione());
	 * evento.setIdEventoPadre(idEventoPadre);
	 * 
	 * String jsonInput = JsonUtils.fillObjects(input.getDocUid(), callInfo);
	 * 
	 * evento = eventoSrvc.insertEvento(evento, jsonInput);
	 * eventoSrvc.insertEventoLog(evento.getId(),
	 * GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
	 * GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
	 * 
	 * eventoSrvc.insertEventoLog(idEvento,
	 * GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
	 * GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, null, null, null);
	 * 
	 * eventoPadre.setIdEventoFiglio(evento.getId());
	 * eventoSrvc.updateEvento(eventoPadre);
	 * 
	 * }
	 * 
	 * try { GatefireDRepository repository = getEndpoint(list, callInfo); var
	 * repoSrvc = initDeleteService(repository, input.getIdentity());
	 * 
	 * eventoSrvc.insertEventoLog(idEvento,
	 * GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
	 * GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, null, null); String jsonString
	 * = JsonUtils.fillObjects(); eventoSrvc.insertEventoLog(idEvento,
	 * GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
	 * GatefireConsts.EVT_LOG_STATO_INIZIO_ELAB, jsonString, null, null);
	 * 
	 * res = doDeleteCall(repoSrvc, input.getDocUid(), evento.getId()); } catch (
	 * 
	 * ValidationException e) {
	 * 
	 * res = new Result();
	 * 
	 * res.setErrorCode(ErrorHelper.ERROR_VALIDAZIONE);
	 * res.setDescription(getText(ErrorHelper.ERROR_VALIDAZIONE));
	 * res.setMessage(e.getErrorFields().get(0).toString());
	 * 
	 * eventoSrvc.insertEventoLog(idEvento,
	 * GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
	 * GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
	 * logError(e);
	 * 
	 * } catch (Exception e) { res = new Result();
	 * res.setErrorCode(ErrorHelper.ERRORE_GENERICO);
	 * res.setDescription(getText(ErrorHelper.ERRORE_GENERICO));
	 * res.setMessage(e.getMessage());
	 * 
	 * eventoSrvc.insertEventoLog(idEvento,
	 * GatefireConsts.EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI,
	 * GatefireConsts.EVT_LOG_STATO_ERRORE, e.getMessage(), null, null);
	 * logError(e); }
	 * 
	 * if (idEventoPadre != null) { if
	 * (ErrorHelper.OK.equalsIgnoreCase(res.getErrorCode())) {
	 * eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null);
	 * } else { eventoSrvc.closeEvento(evento, GatefireConsts.EVT_LOG_STATO_ERRORE,
	 * null); } } return res;
	 * 
	 * }
	 * 
	 * private Result doDeleteCall(Iti86PortType repoSrvc, String docUuid, long
	 * idEvento) { Result res = new Result();
	 * 
	 * RemoveDocumentsRequestType req = new RemoveDocumentsRequestType();
	 * req.getDocumentRequest(); DocumentRequest docReq = new DocumentRequest();
	 * docReq.setDocumentUniqueId(docUuid);
	 * 
	 * req.getDocumentRequest().add(docReq);
	 * 
	 * RegistryResponseType response =
	 * repoSrvc.documentRepositoryRemoveDocuments(req); if
	 * ("".equalsIgnoreCase(response.getStatus())) {
	 * eventoSrvc.insertEventoLog(idEvento,
	 * GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
	 * GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, response.getStatus(),
	 * "requestId " + response.getRequestId()); } else { String msg = ""; if
	 * (response.getRegistryErrorList() != null &&
	 * response.getRegistryErrorList().getHighestSeverity() != null) { msg =
	 * response.getRegistryErrorList().getHighestSeverity(); } res = new
	 * Result(ErrorHelper.ERRORE_REPOSITORY);
	 * res.setDescription(getText(ErrorHelper.ERRORE_REPOSITORY));
	 * res.setMessage(msg);
	 * 
	 * logError(msg); eventoSrvc.insertEventoLog(idEvento,
	 * GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
	 * GatefireConsts.EVT_LOG_STATO_ERRORE, null, response.getStatus(), msg); }
	 * 
	 * return res; }
	 */
	private Result doIti41Call(Iti41PortType repoSrvc, RepositoryInput input, GatefireDRepository repository,
			long idEvento, String codiceApplicazione) {
		Result res = new Result();

		try {
			Map<String, List<GatefireDXdscode>> map = fillXdsCodes();
			List<GatefireDApplicazione> appList = cachedListsSrvc.getAllApplicazioni();

			GatefireDApplicazione applicazione = getApplicazione(appList, codiceApplicazione);

			it.csi.gatefire.common.model.repository.DocumentEntry documentEntry = RepoFiller
					.fillDocumentEntry(input.getMetadata(), map, repository, applicazione);
			SubmissionSet submissionSet = RepoFiller.fillSubmissionSet(input.getMetadata(), map, applicazione);

			String contentType = new Tika().detect(input.getAttachment().getFile());
			DataSource fds = new ByteArrayDataSource(input.getAttachment().getFile(), contentType);
			DataHandler dataHandler = new DataHandler(fds);

			var document = new Document(documentEntry, dataHandler);

			var pnrSet = new ProvideAndRegisterDocumentSet();

			pnrSet.setSubmissionSet(submissionSet);
			// pnrSet.getFolders().addAll(metadata.getFolderList());
			pnrSet.getDocuments().add(document);

			Association association = new Association(AssociationType.HAS_MEMBER, HAS_MEMBER_ASSOC,
					submissionSet.getEntryUuid(), documentEntry.getEntryUuid());

			association.setLabel(AssociationLabel.ORIGINAL);

			pnrSet.getAssociations().add(association);
			// pnrSet.setTargetHomeCommunityId(targetHomeCommunityId);

			var doctransformer = new it.csi.gatefire.repository.xds.transform.ebxml.ProvideAndRegisterDocumentSetTransformer(
					new GatefireEbXmlFactory());
			EbXMLProvideAndRegisterDocumentSetRequest30 req = (EbXMLProvideAndRegisterDocumentSetRequest30) doctransformer
					.toEbXML(pnrSet);

			var innerReq = req.getInternal();

			var response = repoSrvc.documentRepositoryProvideAndRegisterDocumentSetB(innerReq);

			if ("".equalsIgnoreCase(response.getStatus()) || STATUS_SUCCESS.equalsIgnoreCase(response.getStatus())) {
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
						GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, response.getStatus(),
						"requestId " + response.getRequestId());
			} else {
				StringBuilder msg = new StringBuilder("");
				String retCode = "";
				if (response.getRegistryErrorList() != null) {

					if (response.getRegistryErrorList().getRegistryError() != null
							&& !response.getRegistryErrorList().getRegistryError().isEmpty()) {

						if (response.getRegistryErrorList().getHighestSeverity() != null) {
							msg.append(response.getRegistryErrorList().getHighestSeverity());

						}

						List<RegistryError> errList = response.getRegistryErrorList().getRegistryError();

						for (RegistryError registryError : errList) {
							msg.append(" - " + registryError.getCodeContext());
						}

						retCode = response.getRegistryErrorList().getRegistryError().get(0).getErrorCode();
					}
				}

				res = new Result(ErrorHelper.ERRORE_REPOSITORY);
				res.setDescription(getText(ErrorHelper.ERRORE_REPOSITORY));
				res.setMessage(msg.toString());
				res.setOriginalReturnCode(retCode);

				logError(msg.toString());
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
						GatefireConsts.EVT_LOG_STATO_ERRORE, null, response.getStatus(), msg.toString());
			}

		} catch (Exception e) {

			res = new Result();
			res.setErrorCode(ErrorHelper.ERRORE_REPOSITORY);
			res.setDescription(getText(ErrorHelper.ERRORE_REPOSITORY));
			res.setMessage(e.getMessage());

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
					GatefireConsts.EVT_LOG_STATO_ERRORE, "Errore chiamata servizio remoto: " + e.getMessage(), null,
					null);
			logError(e);
		}

		return res;
	}

	private Result doIti41UndoCall(Iti41PortType repoSrvc, RepositoryUndoInput input,
			RepositoryQueryResult repositoryQueryResult, long idEvento, String codiceApplicazione) {
		Result res = new Result();

		try {
			Map<String, List<GatefireDXdscode>> map = fillXdsCodes();
			List<GatefireDApplicazione> appList = cachedListsSrvc.getAllApplicazioni();
			GatefireDApplicazione applicazione = getApplicazione(appList, codiceApplicazione);

			String contentType = new Tika().detect(input.getAttachment().getFile());
			DataSource fds = new ByteArrayDataSource(input.getAttachment().getFile(), contentType);
			DataHandler dataHandler = new DataHandler(fds);

			it.csi.gatefire.common.model.repository.DocumentEntry documentEntry = repositoryQueryResult
					.getDocumentEntry();

			documentEntry.getExtraMetadata().put("documentoAnnullativo", Arrays.asList("true"));

			Identifiable patientId = documentEntry.getPatientId();
			String assettoOrganizzativo = documentEntry.getPracticeSettingCode().getCode();
			String tipoAttivitaClinica = documentEntry.getExtraMetadata().get(RepoConsts.METADATO_TIPO_ATT_CLINICA)
					.get(0);

			SubmissionSet submissionSet = RepoFiller.fillSubmissionSet(input.getRichiesta(), patientId,
					assettoOrganizzativo, tipoAttivitaClinica, map, applicazione);

			String originalUUID = documentEntry.getEntryUuid();
			documentEntry.setAvailabilityStatus(null);
			documentEntry.setUniqueId(input.getUndoDocUniqueId());
			documentEntry.setEntryUuid("Document01");
			documentEntry.setLogicalUuid(null);
			documentEntry.setVersion(null);

			/*
			 * PatientInfo extPatientInfo = documentEntry.getExtSourcePatientInfo();
			 * 
			 * ListIterator<Address> addresses = extPatientInfo.getAddresses();
			 * 
			 * while (addresses.hasNext()) { Address address = addresses.next();
			 * address.setCountyParishCode(address.getZipOrPostalCode());
			 * address.setZipOrPostalCode(null);
			 * 
			 * String tipoindindrizzo = address.getHapiObject().getAddressType() != null ?
			 * address.getHapiObject().getAddressType().getValue() : null;
			 * 
			 * if ("L".equalsIgnoreCase(tipoindindrizzo) &&
			 * !StringUtils.hasLength(address.getStreetAddress())) {
			 * address.setStreetAddress("."); } addresses.set(address);
			 * 
			 * }
			 */

			var document = new Document(documentEntry, dataHandler);

			var pnrSet = new ProvideAndRegisterDocumentSet();

			pnrSet.setSubmissionSet(submissionSet);
			// pnrSet.getFolders().addAll(metadata.getFolderList());
			pnrSet.getDocuments().add(document);

			Association association = new Association(AssociationType.HAS_MEMBER, HAS_MEMBER_ASSOC,
					submissionSet.getEntryUuid(), documentEntry.getEntryUuid());

			association.setLabel(AssociationLabel.ORIGINAL);

			pnrSet.getAssociations().add(association);
			// pnrSet.setTargetHomeCommunityId(targetHomeCommunityId);

			association = new Association(AssociationType.REPLACE, "REPLACE_ASSOCIATION_1",
					documentEntry.getEntryUuid(), originalUUID);
			pnrSet.getAssociations().add(association);
			var doctransformer = new it.csi.gatefire.repository.xds.transform.ebxml.ProvideAndRegisterDocumentSetTransformer(
					new GatefireEbXmlFactory());
			EbXMLProvideAndRegisterDocumentSetRequest30 req = (EbXMLProvideAndRegisterDocumentSetRequest30) doctransformer
					.toEbXML(pnrSet);

			var innerReq = req.getInternal();

			var response = repoSrvc.documentRepositoryProvideAndRegisterDocumentSetB(innerReq);

			if ("".equalsIgnoreCase(response.getStatus()) || STATUS_SUCCESS.equalsIgnoreCase(response.getStatus())) {
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
						GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, response.getStatus(),
						"requestId " + response.getRequestId());
			} else {
				StringBuilder msg = new StringBuilder("");
				String retCode = "";
				if (response.getRegistryErrorList() != null) {

					if (response.getRegistryErrorList().getRegistryError() != null
							&& !response.getRegistryErrorList().getRegistryError().isEmpty()) {

						if (response.getRegistryErrorList().getHighestSeverity() != null) {
							msg.append(response.getRegistryErrorList().getHighestSeverity());

						}

						List<RegistryError> errList = response.getRegistryErrorList().getRegistryError();

						for (RegistryError registryError : errList) {
							msg.append(" - " + registryError.getCodeContext());
						}

						retCode = response.getRegistryErrorList().getRegistryError().get(0).getErrorCode();
					}
				}

				res = new Result(ErrorHelper.ERRORE_REPOSITORY);
				res.setDescription(getText(ErrorHelper.ERRORE_REPOSITORY));
				res.setMessage(msg.toString());
				res.setOriginalReturnCode(retCode);

				logError(msg.toString());
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
						GatefireConsts.EVT_LOG_STATO_ERRORE, null, response.getStatus(), msg.toString());
			}

		} catch (Exception e) {

			res = new Result();
			res.setErrorCode(ErrorHelper.ERRORE_REPOSITORY);
			res.setDescription(getText(ErrorHelper.ERRORE_REPOSITORY));
			res.setMessage(e.getMessage());

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
					GatefireConsts.EVT_LOG_STATO_ERRORE, "Errore chiamata servizio remoto: " + e.getMessage(), null,
					null);
			logError(e);
		}

		return res;
	}

	private Result doIti57Call(Iti57PortType repoSrvc, ITIMetadata metadata,
			RepositoryQueryResult repositoryQueryResult, GatefireDRepository repository, long idEvento,
			String codiceApplicaizione) {
		Result res = new Result();

		try {
			Map<String, List<GatefireDXdscode>> map = fillXdsCodes();

			List<GatefireDApplicazione> appList = cachedListsSrvc.getAllApplicazioni();

			GatefireDApplicazione applicazione = getApplicazione(appList, codiceApplicaizione);
			/*
			 * it.csi.gatefire.common.model.repository.DocumentEntry documentEntry =
			 * RepoFiller.fillDocumentEntry(metadata, map,
			 * repository.getGestioneConsensi());
			 */

			it.csi.gatefire.common.model.repository.DocumentEntry documentEntry = repositoryQueryResult
					.getDocumentEntry();

			RepoFiller.updateDocumentEntry(documentEntry, metadata, map, repository, applicazione);

			Identifiable patientId = documentEntry.getPatientId();

			String assettoOrganizzativo = null;
			String tipoAttivitaClinica = null;

			if (documentEntry.getPracticeSettingCode() != null) {
				assettoOrganizzativo = documentEntry.getPracticeSettingCode().getCode();
			}
			if (documentEntry.getExtraMetadata() != null
					&& documentEntry.getExtraMetadata().get(RepoConsts.METADATO_TIPO_ATT_CLINICA) != null) {
				tipoAttivitaClinica = documentEntry.getExtraMetadata().get(RepoConsts.METADATO_TIPO_ATT_CLINICA).get(0);
			}
			SubmissionSet submissionSet = RepoFiller.fillSubmissionSet(metadata.getRichiesta(), patientId,
					assettoOrganizzativo, tipoAttivitaClinica, map, applicazione);

			var pnrSet = new RegisterDocumentSet();

			pnrSet.setSubmissionSet(submissionSet);

			pnrSet.getDocumentEntries().add(documentEntry);

			Association association = new Association(AssociationType.HAS_MEMBER, HAS_MEMBER_ASSOC,
					submissionSet.getEntryUuid(), documentEntry.getEntryUuid());

			association.setPreviousVersion(repositoryQueryResult.getDocumentEntry().getVersion().getVersionName());
			association.setLabel(AssociationLabel.ORIGINAL);
			/*
			 * var extraM = new HashMap<String, List<String>>();
			 * 
			 * association.setExtraMetadata(extraM);
			 * association.getExtraMetadata().put("SubmissionSetStatus",
			 * Arrays.asList("Original"));
			 */
			pnrSet.getAssociations().add(association);
			// pnrSet.setTargetHomeCommunityId(targetHomeCommunityId);

			var doctransformer = new it.csi.gatefire.repository.xds.transform.ebxml.RegisterDocumentSetTransformer(
					new GatefireEbXmlFactory());
			EbXMLSubmitObjectsRequest30 req = (EbXMLSubmitObjectsRequest30) doctransformer.toEbXML(pnrSet);

			var innerReq = req.getInternal();

			var response = repoSrvc.documentRegistryUpdateDocumentSet(innerReq);

			if ("".equalsIgnoreCase(response.getStatus()) || STATUS_SUCCESS.equalsIgnoreCase(response.getStatus())) {
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
						GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, response.getStatus(),
						"requestId " + response.getRequestId());
			} else {
				StringBuilder msg = new StringBuilder("");
				String retCode = "";
				if (response.getRegistryErrorList() != null) {

					if (response.getRegistryErrorList().getRegistryError() != null
							&& !response.getRegistryErrorList().getRegistryError().isEmpty()) {

						if (response.getRegistryErrorList().getHighestSeverity() != null) {
							msg.append(response.getRegistryErrorList().getHighestSeverity());

						}

						List<RegistryError> errList = response.getRegistryErrorList().getRegistryError();

						for (RegistryError registryError : errList) {
							msg.append(" - " + registryError.getCodeContext());
						}

						retCode = response.getRegistryErrorList().getRegistryError().get(0).getErrorCode();
					}
				}

				res = new Result(ErrorHelper.ERRORE_REPOSITORY);
				res.setDescription(getText(ErrorHelper.ERRORE_REPOSITORY));
				res.setMessage(msg.toString());
				res.setOriginalReturnCode(retCode);

				logError(msg.toString());
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
						GatefireConsts.EVT_LOG_STATO_ERRORE, null, response.getStatus(), msg.toString());
			}

		} catch (Exception e) {

			res = new Result();
			res.setErrorCode(ErrorHelper.ERRORE_REPOSITORY);
			res.setDescription(getText(ErrorHelper.ERRORE_REPOSITORY));
			res.setMessage(e.getMessage());

			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_ARCHIVIA,
					GatefireConsts.EVT_LOG_STATO_ERRORE, "Errore chiamata servizio remoto: " + e.getMessage(), null,
					null);
			logError(e);
		}

		return res;
	}

	private Result doIti62Call(Iti62PortType iti62PortType, String docId, long idEvento) {

		Result ret = new Result();

		RemoveObjectsRequest request = new RemoveObjectsRequest();

		ObjectRefType type = new ObjectRefType();
		type.setId(docId);

		ObjectRefListType list = new ObjectRefListType();
		list.getObjectRef().add(type);
		request.setObjectRefList(list);

		RegistryResponseType response = iti62PortType.documentRegistryDeleteDocumentSet(request);
		if ("".equalsIgnoreCase(response.getStatus()) || STATUS_SUCCESS.equalsIgnoreCase(response.getStatus())) {
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_RECUPERA_FILE,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, response.getStatus(), null);

		} else {
			StringBuilder msg = new StringBuilder("");
			String retCode = "";
			if (response.getRegistryErrorList() != null) {

				if (response.getRegistryErrorList().getRegistryError() != null
						&& !response.getRegistryErrorList().getRegistryError().isEmpty()) {

					if (response.getRegistryErrorList().getHighestSeverity() != null) {
						msg.append(response.getRegistryErrorList().getHighestSeverity());

					}

					List<RegistryError> errList = response.getRegistryErrorList().getRegistryError();

					for (RegistryError registryError : errList) {
						msg.append(" - " + registryError.getCodeContext());
					}

					retCode = response.getRegistryErrorList().getRegistryError().get(0).getErrorCode();
				}
			}

			ret = new Result(ErrorHelper.ERRORE_REPOSITORY);
			ret.setDescription(getText(ErrorHelper.ERRORE_REPOSITORY));
			ret.setMessage(msg.toString());
			ret.setOriginalReturnCode(retCode);

			logError(msg.toString());
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_RECUPERA_FILE,
					GatefireConsts.EVT_LOG_STATO_ERRORE, null, response.getStatus(), msg.toString());
		}

		return ret;

	}

	private Iti41PortType initIti41Service(GatefireDRepository repository, String codiceApplicazione)
			throws ValidationException {
		List<GatefireRRepositoryAccount> accList = cachedListsSrvc.getAllRepositoryAccount();
		System.setProperty(xmlParserProp, xmlParserValue);

		String endpoint = getEndpoint(repository, RepoConsts.TRANSAZIONE_ITI41);
		if (endpoint == null) {

			throwEndpointException(repository.getCodice(), RepoConsts.TRANSAZIONE_ITI41);
		}

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.setServiceClass(Iti41PortType.class);
		factory.setBindingId(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING);
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

		WSAddressingFeature wsa = new WSAddressingFeature();

		factory.setFeatures(Arrays.asList(wsa));

		// stsClient.setFeatures(Arrays.asList(wsa));

		/*
		 * final Soap11 soap11 = Soap11.getInstance();
		 * 
		 * BindingConfiguration config = new BindingConfiguration() {
		 * 
		 * @Override public String getBindingId() { return soap11.getBindingId(); } };
		 * factory.setBindingConfig(config);
		 * factory.setBindingId(soap11.getBindingId());
		 */

		Iti41PortType it41Service = (Iti41PortType) factory.create();
		Client client = ClientProxy.getClient(it41Service);

		client.getRequestContext().put(Message.ENDPOINT_ADDRESS, endpoint);

		boolean checkAuth = true;
		if (repository.getAuthenticationRequired() != null && !repository.getAuthenticationRequired().booleanValue()) {
			checkAuth = false;
		}
		if (checkAuth) {

			AssertionIdentity identity = getIdentity(accList, repository.getCodice(), codiceApplicazione);
			if (identity == null) {

				throwIdentityException(repository.getCodice(), codiceApplicazione);
			}

			if ("svil".equalsIgnoreCase(activeProfile)) {
				System.setProperty("javax.net.ssl.trustStore", "client-truststore.jks");
				System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
			}

			String stsServiceLocation = getEndpoint(repository, RepoConsts.TRANSAZIONE_ASSERTION);
			if (stsServiceLocation == null) {

				throwEndpointException(repository.getCodice(), RepoConsts.TRANSAZIONE_ASSERTION);
			}

			STSClient stsClient = initSTS(stsServiceLocation, endpoint, identity.getUser(), identity.getPassword(),
					SIMMETRIC_KEYTYPE);

			String wsAddressTo = getEndpoint(repository, RepoConsts.TRANSAZIONE_WS_ADDRESS_TO_ITI41);
			try {

				SecurityToken samlToken = stsClient.requestSecurityToken(endpoint);
				client.getEndpoint().getOutInterceptors().add(new AddStsTokenInterceptor(samlToken, wsAddressTo));

			} catch (Exception e) {

				logError(e);

				stsClient.setKeyType(BEARER_KEYTYPE);
				try {

					SecurityToken samlToken = stsClient.requestSecurityToken(endpoint);
					client.getEndpoint().getOutInterceptors().add(new AddStsTokenInterceptor(samlToken, wsAddressTo));

				} catch (Exception ex) {

					logError(e);

				}
			}

		}

		return it41Service;
	}

	private Iti41PortType initIti41UndoService(GatefireDRepository repository, String codiceApplicazione)
			throws ValidationException {
		List<GatefireRRepositoryAccount> accList = cachedListsSrvc.getAllRepositoryAccount();
		System.setProperty(xmlParserProp, xmlParserValue);

		String endpoint = getEndpoint(repository, RepoConsts.TRANSAZIONE_ITI41_UNDO);
		if (endpoint == null) {

			throwEndpointException(repository.getCodice(), RepoConsts.TRANSAZIONE_ITI41_UNDO);
		}

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.setServiceClass(Iti41PortType.class);
		factory.setBindingId(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING);
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

		WSAddressingFeature wsa = new WSAddressingFeature();

		factory.setFeatures(Arrays.asList(wsa));

		// stsClient.setFeatures(Arrays.asList(wsa));

		/*
		 * final Soap11 soap11 = Soap11.getInstance();
		 * 
		 * BindingConfiguration config = new BindingConfiguration() {
		 * 
		 * @Override public String getBindingId() { return soap11.getBindingId(); } };
		 * factory.setBindingConfig(config);
		 * factory.setBindingId(soap11.getBindingId());
		 */

		Iti41PortType it41Service = (Iti41PortType) factory.create();
		Client client = ClientProxy.getClient(it41Service);

		client.getRequestContext().put(Message.ENDPOINT_ADDRESS, endpoint);

		boolean checkAuth = true;
		if (repository.getAuthenticationRequired() != null && !repository.getAuthenticationRequired().booleanValue()) {
			checkAuth = false;
		}
		if (checkAuth) {

			AssertionIdentity identity = getIdentity(accList, repository.getCodice(), codiceApplicazione);
			if (identity == null) {

				throwIdentityException(repository.getCodice(), codiceApplicazione);
			}

			if ("svil".equalsIgnoreCase(activeProfile)) {
				System.setProperty("javax.net.ssl.trustStore", "client-truststore.jks");
				System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
			}

			String stsServiceLocation = getEndpoint(repository, RepoConsts.TRANSAZIONE_ASSERTION);
			if (stsServiceLocation == null) {

				throwEndpointException(repository.getCodice(), RepoConsts.TRANSAZIONE_ASSERTION);
			}

			STSClient stsClient = initSTS(stsServiceLocation, endpoint, identity.getUser(), identity.getPassword(),
					SIMMETRIC_KEYTYPE);

			String wsAddressTo = getEndpoint(repository, RepoConsts.TRANSAZIONE_WS_ADDRESS_TO_ITI41_UNDO);
			try {

				SecurityToken samlToken = stsClient.requestSecurityToken(endpoint);
				client.getEndpoint().getOutInterceptors().add(new AddStsTokenInterceptor(samlToken, wsAddressTo));

			} catch (Exception e) {

				logError(e);

				stsClient.setKeyType(BEARER_KEYTYPE);
				try {

					SecurityToken samlToken = stsClient.requestSecurityToken(endpoint);
					client.getEndpoint().getOutInterceptors().add(new AddStsTokenInterceptor(samlToken, wsAddressTo));

				} catch (Exception ex) {

					logError(e);

				}
			}

		}

		return it41Service;
	}

	private Iti43PortType initIti43Service(GatefireDRepository repository, String codiceApplicazione)
			throws ValidationException {
		List<GatefireRRepositoryAccount> accList = cachedListsSrvc.getAllRepositoryAccount();
		System.setProperty(xmlParserProp, xmlParserValue);

		String endpoint = getEndpoint(repository, RepoConsts.TRANSAZIONE_ITI43);
		if (endpoint == null) {

			throwEndpointException(repository.getCodice(), RepoConsts.TRANSAZIONE_ITI43);
		}

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.setServiceClass(Iti43PortType.class);
		factory.setBindingId(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING);
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

		WSAddressingFeature wsa = new WSAddressingFeature();

		factory.setFeatures(Arrays.asList(wsa));

		// stsClient.setFeatures(Arrays.asList(wsa));

		/*
		 * final Soap11 soap11 = Soap11.getInstance();
		 * 
		 * BindingConfiguration config = new BindingConfiguration() {
		 * 
		 * @Override public String getBindingId() { return soap11.getBindingId(); } };
		 * factory.setBindingConfig(config);
		 * factory.setBindingId(soap11.getBindingId());
		 */

		Iti43PortType it41Service = (Iti43PortType) factory.create();
		Client client = ClientProxy.getClient(it41Service);

		client.getRequestContext().put(Message.ENDPOINT_ADDRESS, endpoint);

		boolean checkAuth = true;
		if (repository.getAuthenticationRequired() != null && !repository.getAuthenticationRequired().booleanValue()) {
			checkAuth = false;
		}
		if (checkAuth) {

			AssertionIdentity identity = getIdentity(accList, repository.getCodice(), codiceApplicazione);
			if (identity == null) {

				throwIdentityException(repository.getCodice(), codiceApplicazione);
			}
			if ("svil".equalsIgnoreCase(activeProfile)) {
				System.setProperty("javax.net.ssl.trustStore", "client-truststore.jks");
				System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
			}
			String stsServiceLocation = getEndpoint(repository, RepoConsts.TRANSAZIONE_ASSERTION);
			if (stsServiceLocation == null) {

				throwEndpointException(repository.getCodice(), RepoConsts.TRANSAZIONE_ASSERTION);
			}
			STSClient stsClient = initSTS(stsServiceLocation, endpoint, identity.getUser(), identity.getPassword(),
					SIMMETRIC_KEYTYPE);

			String wsAddressTo = getEndpoint(repository, RepoConsts.TRANSAZIONE_WS_ADDRESS_TO_ITI43);
			try {

				SecurityToken samlToken = stsClient.requestSecurityToken(endpoint);
				client.getEndpoint().getOutInterceptors().add(new AddStsTokenInterceptor(samlToken, wsAddressTo));

			} catch (Exception e) {

				logError(e);

				stsClient.setKeyType(BEARER_KEYTYPE);
				try {
					logInfo("bearer TOKEN");

					SecurityToken samlToken = stsClient.requestSecurityToken(endpoint);
					client.getEndpoint().getOutInterceptors().add(new AddStsTokenInterceptor(samlToken, wsAddressTo));

				} catch (Exception ex) {

					logError(e);

				}
			}

		}

		return it41Service;
	}

	private Iti57PortType initIti57Service(GatefireDRepository repository, String codiceApplicazione)
			throws ValidationException {
		List<GatefireRRepositoryAccount> accList = cachedListsSrvc.getAllRepositoryAccount();
		System.setProperty(xmlParserProp, xmlParserValue);

		String endpoint = getEndpoint(repository, RepoConsts.TRANSAZIONE_ITI57);
		if (endpoint == null) {

			throwEndpointException(repository.getCodice(), RepoConsts.TRANSAZIONE_ITI57);
		}

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.setServiceClass(Iti57PortType.class);
		factory.setBindingId(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING);
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

		WSAddressingFeature wsa = new WSAddressingFeature();

		factory.setFeatures(Arrays.asList(wsa));

		// stsClient.setFeatures(Arrays.asList(wsa));

		/*
		 * final Soap11 soap11 = Soap11.getInstance();
		 * 
		 * BindingConfiguration config = new BindingConfiguration() {
		 * 
		 * @Override public String getBindingId() { return soap11.getBindingId(); } };
		 * factory.setBindingConfig(config);
		 * factory.setBindingId(soap11.getBindingId());
		 */

		Iti57PortType iti57Service = (Iti57PortType) factory.create();
		Client client = ClientProxy.getClient(iti57Service);

		client.getRequestContext().put(Message.ENDPOINT_ADDRESS, endpoint);

		boolean checkAuth = true;
		if (repository.getAuthenticationRequired() != null && !repository.getAuthenticationRequired().booleanValue()) {
			checkAuth = false;
		}
		if (checkAuth) {

			AssertionIdentity identity = getIdentity(accList, repository.getCodice(), codiceApplicazione);
			if (identity == null) {

				throwIdentityException(repository.getCodice(), codiceApplicazione);
			}
			if ("svil".equalsIgnoreCase(activeProfile)) {
				System.setProperty("javax.net.ssl.trustStore", "client-truststore.jks");
				System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
			}
			String stsServiceLocation = getEndpoint(repository, RepoConsts.TRANSAZIONE_ASSERTION);
			if (stsServiceLocation == null) {

				throwEndpointException(repository.getCodice(), RepoConsts.TRANSAZIONE_ASSERTION);
			}
			STSClient stsClient = initSTS(stsServiceLocation, endpoint, identity.getUser(), identity.getPassword(),
					SIMMETRIC_KEYTYPE);

			String wsAddressTo = getEndpoint(repository, RepoConsts.TRANSAZIONE_WS_ADDRESS_TO_ITI57);
			try {

				SecurityToken samlToken = stsClient.requestSecurityToken(endpoint);
				client.getEndpoint().getOutInterceptors().add(new AddStsTokenInterceptor(samlToken, wsAddressTo));

			} catch (Exception e) {

				logError(e);

				stsClient.setKeyType(BEARER_KEYTYPE);
				try {
					logInfo("bearer TOKEN");

					SecurityToken samlToken = stsClient.requestSecurityToken(endpoint);
					client.getEndpoint().getOutInterceptors().add(new AddStsTokenInterceptor(samlToken, wsAddressTo));

				} catch (Exception ex) {

					logError(e);

				}
			}

		}

		return iti57Service;
	}

	private Iti62PortType initIti62Service(GatefireDRepository repository, String codiceApplicazione)
			throws ValidationException {
		List<GatefireRRepositoryAccount> accList = cachedListsSrvc.getAllRepositoryAccount();
		System.setProperty(xmlParserProp, xmlParserValue);

		String endpoint = getEndpoint(repository, RepoConsts.TRANSAZIONE_ITI62);
		if (endpoint == null) {

			throwEndpointException(repository.getCodice(), RepoConsts.TRANSAZIONE_ITI62);
		}

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.setServiceClass(Iti62PortType.class);
		factory.setBindingId(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING);
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

		WSAddressingFeature wsa = new WSAddressingFeature();

		factory.setFeatures(Arrays.asList(wsa));

		// stsClient.setFeatures(Arrays.asList(wsa));

		/*
		 * final Soap11 soap11 = Soap11.getInstance();
		 * 
		 * BindingConfiguration config = new BindingConfiguration() {
		 * 
		 * @Override public String getBindingId() { return soap11.getBindingId(); } };
		 * factory.setBindingConfig(config);
		 * factory.setBindingId(soap11.getBindingId());
		 */

		Iti62PortType iti62Service = (Iti62PortType) factory.create();
		Client client = ClientProxy.getClient(iti62Service);

		client.getRequestContext().put(Message.ENDPOINT_ADDRESS, endpoint);

		boolean checkAuth = true;
		if (repository.getAuthenticationRequired() != null && !repository.getAuthenticationRequired().booleanValue()) {
			checkAuth = false;
		}
		if (checkAuth) {

			AssertionIdentity identity = getIdentity(accList, repository.getCodice(), codiceApplicazione);
			if (identity == null) {

				throwIdentityException(repository.getCodice(), codiceApplicazione);
			}

			if ("svil".equalsIgnoreCase(activeProfile)) {
				System.setProperty("javax.net.ssl.trustStore", "client-truststore.jks");
				System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
			}
			String stsServiceLocation = getEndpoint(repository, RepoConsts.TRANSAZIONE_ASSERTION);
			if (stsServiceLocation == null) {

				throwEndpointException(repository.getCodice(), RepoConsts.TRANSAZIONE_ASSERTION);
			}
			STSClient stsClient = initSTS(stsServiceLocation, endpoint, identity.getUser(), identity.getPassword(),
					SIMMETRIC_KEYTYPE);

			String wsAddressTo = getEndpoint(repository, RepoConsts.TRANSAZIONE_WS_ADDRESS_TO_ITI62);
			try {

				SecurityToken samlToken = stsClient.requestSecurityToken(endpoint);
				client.getEndpoint().getOutInterceptors().add(new AddStsTokenInterceptor(samlToken, wsAddressTo));

			} catch (Exception e) {

				logError(e);

				stsClient.setKeyType(BEARER_KEYTYPE);
				try {
					logInfo("bearer TOKEN");

					SecurityToken samlToken = stsClient.requestSecurityToken(endpoint);
					client.getEndpoint().getOutInterceptors().add(new AddStsTokenInterceptor(samlToken, wsAddressTo));

				} catch (Exception ex) {

					logError(e);

				}
			}

		}

		return iti62Service;
	}

	/*
	 * private Iti86PortType initDeleteService(GatefireDRepository repository,
	 * AssertionIdentity identity) { String endpoint =
	 * repository.getRepositoryUrl(); String stsServiceLocation =
	 * repository.getAssertionUrl(); JaxWsProxyFactoryBean factory = new
	 * JaxWsProxyFactoryBean();
	 * 
	 * factory.setServiceClass(Iti41PortType.class); factory.setAddress(endpoint);
	 * 
	 * LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
	 * loggingInInterceptor.setPrettyLogging(true); LoggingOutInterceptor
	 * loggingOutInterceptor = new LoggingOutInterceptor();
	 * loggingOutInterceptor.setPrettyLogging(true);
	 * loggingOutInterceptor.setLogMultipart(true);
	 * loggingOutInterceptor.setLogBinary(true);
	 * factory.getInInterceptors().add(loggingInInterceptor);
	 * factory.getOutInterceptors().add(loggingOutInterceptor);
	 * 
	 * Iti86PortType Iti86PortType = (Iti86PortType) factory.create(); Client client
	 * = ClientProxy.getClient(Iti86PortType);
	 * 
	 * client.getRequestContext().put(Message.ENDPOINT_ADDRESS, endpoint);
	 * 
	 * boolean checkAuth = true; if (repository.getAuthenticationRequired() != null
	 * && repository.getAuthenticationRequired()) { checkAuth = false; } if
	 * (checkAuth) { client.getRequestContext().put("ws-security.sts.client",
	 * initSTS(stsServiceLocation, endpoint, identity.getUser(),
	 * identity.getPassword(), BEARER_KEYTYPE)); } if
	 * ("dev".equalsIgnoreCase(System.getProperty("spring.profiles.active"))) {
	 * ProxyUtils.authenticate(client); } return Iti86PortType; }
	 */
	private GatefireDRepository getRepository(List<GatefireDRepository> list, List<GatefireRRepositoryUri> uriList,
			CallInfo callInfo) throws ValidationException {

		for (GatefireDRepository repo : list) {
			if (callInfo.getCollocazione().equalsIgnoreCase(repo.getCollocazione())) {

				List<GatefireRRepositoryUri> lista = new ArrayList<>();
				for (GatefireRRepositoryUri uri : uriList) {
					if (uri.getCodiceRepository().equalsIgnoreCase(repo.getCodice())) {
						lista.add(uri);
					}
				}
				repo.setRepositoryUriList(lista);

				return repo;
			}

		}

		ErrorField errorfield = new ErrorField("callInfo.collocazione",
				"nessun repository configurato per la collocazione [" + callInfo.getCollocazione() + "]");

		throw new ValidationException("Collocazione [" + callInfo.getCollocazione() + "] non abilitata", errorfield);

	}

	private static final String SAML2_TOKEN_TYPE = "http://docs.oasis-open.org/wss/oasis-wss-saml-token-profile-1.1#SAMLV2.0";

	// private static final String PUBLIC_KEY_KEYTYPE =
	// "http://docs.oasis-open.org/ws-sx/ws-trust/200512/PublicKey";
	private static final String BEARER_KEYTYPE = "http://docs.oasis-open.org/ws-sx/ws-trust/200512/Bearer";
	private static final String SIMMETRIC_KEYTYPE = "http://docs.oasis-open.org/ws-sx/ws-trust/200512/SymmetricKey";

	private STSClient initSTS(String serviceLocation, String endpointAddress, String username, String password,
			String keyType) {

		// String endpointName =
		// "{http://ws.apache.org/axis2}wso2carbon-stsHttpsSoap12Endpoint";
		String serviceName = "{http://ws.apache.org/axis2}wso2carbon-sts";

		String tokenType = SAML2_TOKEN_TYPE;

		Bus bus = BusFactory.getThreadDefaultBus();

		STSClient stsClient = new STSClient(bus);

		Map<String, Object> properties = new HashMap<>();
		properties.put(SecurityConstants.USERNAME, username);
		properties.put(SecurityConstants.PASSWORD, password);
		properties.put(SecurityConstants.STS_APPLIES_TO, endpointAddress);

		InputStream is = IheSrvcImpl.class.getResourceAsStream("sts.policy.xml");

		/*
		 * String emptyWspPolicyXml =
		 * "<wsp:Policy xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"><wsp:ExactlyOne>"
		 * + "<wsp:All>" +
		 * "<sp:TransportBinding xmlns:sp=\"http://schemas.xmlsoap.org/ws/2005/07/securitypolicy\">"
		 * + "<wsp:Policy>" + "<sp:AlgorithmSuite>" + "<wsp:Policy>" + "<sp:Basic256/>"
		 * + "</wsp:Policy>" + "</sp:AlgorithmSuite>" + "<sp:Layout>" + "<wsp:Policy>" +
		 * "<sp:Lax/></wsp:Policy></sp:Layout><sp:IncludeTimestamp/>" + "</wsp:Policy>"
		 * + "</sp:TransportBinding>" +
		 * "<sp:SignedSupportingTokens xmlns:sp=\"http://schemas.xmlsoap.org/ws/2005/07/securitypolicy\">"
		 * + "<wsp:Policy>" +
		 * "<sp:UsernameToken sp:IncludeToken=\"http://schemas.xmlsoap.org/ws/2005/07/securitypolicy/IncludeToken/AlwaysToRecipient\"/>                    </wsp:Policy>                </sp:SignedSupportingTokens>"
		 * + "</wsp:All>" + "</wsp:ExactlyOne>" + "</wsp:Policy>";
		 */

		// InputStream is = new
		// ByteArrayInputStream(emptyWspPolicyXml.getBytes(StandardCharsets.UTF_8));
		Element wsSecurityPolicy = loadPolicy(is);
		stsClient.setPolicy(wsSecurityPolicy);

		stsClient.setProperties(properties);
		stsClient.setTokenType(tokenType);
		stsClient.setKeyType(keyType);

		stsClient.setLocation(serviceLocation);
		// stsClient.setEndpointName(endpointName);

		stsClient.setServiceName(serviceName);

		stsClient.setEnableAppliesTo(true);

		stsClient.setNamespace(STSUtils.WST_NS_05_02);

		LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
		loggingInInterceptor.setPrettyLogging(true);
		loggingInInterceptor.setLimit(-1);
		loggingInInterceptor.setLogBinary(true);

		LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
		loggingOutInterceptor.setPrettyLogging(true);
		loggingOutInterceptor.setLogMultipart(true);
		loggingOutInterceptor.setLogBinary(true);
		stsClient.getOutInterceptors().add(loggingOutInterceptor);
		stsClient.getInInterceptors().add(loggingInInterceptor);

		GatefireClaimCollection claims = new GatefireClaimCollection();
		URI dialect = URI.create("http://schemas.xmlsoap.org/ws/2005/05/identity");
		claims.setDialect(dialect);
		claims.setDialectPrefix("wsid");
		Claim claim = new Claim();
		claim.setClaimType(RepoConsts.CLAIM_CODICE_FISCALE);
		claim.setOptional(true);

		claims.add(claim);
		claim = new Claim();
		claim.setClaimType(RepoConsts.CLAIM_COGNOME);
		claim.setOptional(true);
		claims.add(claim);
		claim = new Claim();
		claim.setClaimType(RepoConsts.CLAIM_NOME);
		claim.setOptional(true);
		claims.add(claim);
		claim.setClaimType(RepoConsts.CLAIM_RUOLO);
		claim.setOptional(true);
		claims.add(claim);

		stsClient.setClaims(claims);
		return stsClient;
	}

	private Element loadPolicy(InputStream is) {
		try {
			// DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// to be compliant, completely disable DOCTYPE declaration:
			factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			// or completely disable external entities declarations:
			factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
			factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
			// or prohibit the use of all protocols by external entities:
			// factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
			// factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
			factory.setNamespaceAware(true);
			DocumentBuilder db = factory.newDocumentBuilder();
			org.w3c.dom.Document d = db.parse(is);
			return d.getDocumentElement();

		} catch (Exception e) {
			e.printStackTrace();
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

	private RepositoryQueryResult doIti18Call(Iti18PortType iti18PortType, RepositoryDocIdInput input, long idEvento) {
		RepositoryQueryResult ret = new RepositoryQueryResult();
		GetDocumentsQuery query = new GetDocumentsQuery();

		query.setUniqueIds(Arrays.asList(input.getDocUid()));

		QueryRegistry queryRegistry = new QueryRegistry(query);
		QueryReturnType qrt = QueryReturnType.LEAF_CLASS;

		if (qrt != null) {
			queryRegistry.setReturnType(qrt);
		}
		var doctransformer = new QueryRegistryTransformer();
		EbXMLAdhocQueryRequest30 req = (EbXMLAdhocQueryRequest30) doctransformer.toEbXML(queryRegistry);

		var innerReq = req.getInternal();

		AdhocQueryResponse response = iti18PortType.documentRegistryRegistryStoredQuery(innerReq);

		if (response.getStatus() == null || "".equalsIgnoreCase(response.getStatus())
				|| STATUS_SUCCESS.equalsIgnoreCase(response.getStatus())) {
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_INQUIRY_REPOSITORY,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, response.getStatus(), null);

			QueryResponseTransformer trasformer = new QueryResponseTransformer(new GatefireEbXmlFactory());

			QueryResponse queryResponse = trasformer.fromEbXML(new EbXMLQueryResponse30(response));

			if (queryResponse.getDocumentEntries() != null && !queryResponse.getDocumentEntries().isEmpty()) {

				ret.setDocumentEntry((DocumentEntry) queryResponse.getDocumentEntries().get(0));
				if (queryResponse.getSubmissionSets() != null && !queryResponse.getSubmissionSets().isEmpty()) {
					ret.setSubmissionSet(queryResponse.getSubmissionSets().get(0));

				}
			} else {
				String msg = "documento " + input.getDocUid() + " non trovato";
				Result res = new Result(ErrorHelper.ERRORE_REPOSITORY);
				res.setDescription(getText(ErrorHelper.ERRORE_REPOSITORY));
				res.setMessage(msg);
				res.setOriginalReturnCode(ErrorHelper.ERRORE_REPOSITORY);
				ret.setResult(res);
				logError(msg);
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_INQUIRY_REPOSITORY,
						GatefireConsts.EVT_LOG_STATO_ERRORE, null, response.getStatus(), msg);
			}

		} else {
			StringBuilder msg = new StringBuilder("");
			String retCode = "";
			if (response.getRegistryErrorList() != null) {

				if (response.getRegistryErrorList().getRegistryError() != null
						&& !response.getRegistryErrorList().getRegistryError().isEmpty()) {

					if (response.getRegistryErrorList().getHighestSeverity() != null) {
						msg.append(response.getRegistryErrorList().getHighestSeverity());

					}

					List<RegistryError> errList = response.getRegistryErrorList().getRegistryError();

					for (RegistryError registryError : errList) {
						msg.append(" - " + registryError.getCodeContext());
					}

					retCode = response.getRegistryErrorList().getRegistryError().get(0).getErrorCode();
				}
			}

			Result res = new Result(ErrorHelper.ERRORE_REPOSITORY);
			res.setDescription(getText(ErrorHelper.ERRORE_REPOSITORY));
			res.setMessage(msg.toString());
			res.setOriginalReturnCode(retCode);
			ret.setResult(res);
			logError(msg.toString());
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_INQUIRY_REPOSITORY,
					GatefireConsts.EVT_LOG_STATO_ERRORE, null, response.getStatus(), msg.toString());
		}

		return ret;

	}

	private FileResult doIti43Call(Iti43PortType iti43PortType, DocumentRequest documentRequest, long idEvento) {

		FileResult ret = new FileResult();

		RetrieveDocumentSetRequestType request = new RetrieveDocumentSetRequestType();

		request.getDocumentRequest().add(documentRequest);

		RetrieveDocumentSetResponseType response = iti43PortType.documentRepositoryRetrieveDocumentSet(request);

		if ("".equalsIgnoreCase(response.getRegistryResponse().getStatus())
				|| STATUS_SUCCESS.equalsIgnoreCase(response.getRegistryResponse().getStatus())) {
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_RECUPERA_FILE,
					GatefireConsts.EVT_LOG_STATO_FINE_ELAB, null, response.getRegistryResponse().getStatus(), null);

			if (response.getDocumentResponse() != null && !response.getDocumentResponse().isEmpty()) {
				DataHandler dataHandler = response.getDocumentResponse().get(0).getDocument();

				try {
					byte[] file = FileUtils.dataHandlerToBytes(dataHandler);
					MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
					String ext = "pdf";
					try {
						MimeType mime = allTypes.forName(new Tika().detect(file));
						ext = mime.getExtension();
					} catch (MimeTypeException e) {

						e.printStackTrace();
					}

					Attachment attachment = new Attachment(documentRequest.getDocumentUniqueId() + ext, file);
					ret.setAttachment(attachment);
				} catch (IOException e) {
					String msg = "errore recupero documento " + documentRequest.getDocumentUniqueId() + " "
							+ e.getMessage();
					Result res = new Result(ErrorHelper.ERRORE_REPOSITORY);
					res.setDescription(getText(ErrorHelper.ERRORE_REPOSITORY));
					res.setMessage(msg);
					res.setOriginalReturnCode(ErrorHelper.ERRORE_REPOSITORY);
					ret.setResult(res);
					logError(msg);
					eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_RECUPERA_FILE,
							GatefireConsts.EVT_LOG_STATO_ERRORE, null, ErrorHelper.ERRORE_REPOSITORY, msg);
					e.printStackTrace();
				}
			} else {
				String msg = "errore recupero documento " + documentRequest.getDocumentUniqueId();
				Result res = new Result(ErrorHelper.ERRORE_REPOSITORY);
				res.setDescription(getText(ErrorHelper.ERRORE_REPOSITORY));
				res.setMessage(msg);
				res.setOriginalReturnCode(ErrorHelper.ERRORE_REPOSITORY);
				ret.setResult(res);
				logError(msg);
				eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_RECUPERA_FILE,
						GatefireConsts.EVT_LOG_STATO_ERRORE, null, ErrorHelper.ERRORE_REPOSITORY, msg);

			}

		} else {
			StringBuilder msg = new StringBuilder("");
			String retCode = "";
			if (response.getRegistryResponse().getRegistryErrorList() != null) {

				if (response.getRegistryResponse().getRegistryErrorList().getRegistryError() != null
						&& !response.getRegistryResponse().getRegistryErrorList().getRegistryError().isEmpty()) {

					if (response.getRegistryResponse().getRegistryErrorList().getHighestSeverity() != null) {
						msg.append(response.getRegistryResponse().getRegistryErrorList().getHighestSeverity());

					}

					List<RegistryError> errList = response.getRegistryResponse().getRegistryErrorList()
							.getRegistryError();

					for (RegistryError registryError : errList) {
						msg.append(" - " + registryError.getCodeContext());
					}

					retCode = response.getRegistryResponse().getRegistryErrorList().getRegistryError().get(0)
							.getErrorCode();
				}
			}

			Result res = new Result(ErrorHelper.ERRORE_REPOSITORY);
			res.setDescription(getText(ErrorHelper.ERRORE_REPOSITORY));
			res.setMessage(msg.toString());
			res.setOriginalReturnCode(retCode);
			ret.setResult(res);
			logError(msg.toString());
			eventoSrvc.insertEventoLog(idEvento, GatefireConsts.EVT_LOG_TIPO_PASSO_RECUPERA_FILE,
					GatefireConsts.EVT_LOG_STATO_ERRORE, null, response.getRegistryResponse().getStatus(),
					msg.toString());
		}

		return ret;

	}

	private Iti18PortType initIti18Service(GatefireDRepository repository, String codiceApplicazione)
			throws ValidationException {
		List<GatefireRRepositoryAccount> accList = cachedListsSrvc.getAllRepositoryAccount();
		System.setProperty(xmlParserProp, xmlParserValue);

		String endpoint = getEndpoint(repository, RepoConsts.TRANSAZIONE_ITI18);
		if (endpoint == null) {

			throwEndpointException(repository.getCodice(), RepoConsts.TRANSAZIONE_ITI18);
		}

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.setServiceClass(Iti18PortType.class);
		factory.setBindingId(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING);
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

		WSAddressingFeature wsa = new WSAddressingFeature();

		factory.setFeatures(Arrays.asList(wsa));

		// stsClient.setFeatures(Arrays.asList(wsa));

		/*
		 * final Soap11 soap11 = Soap11.getInstance();
		 * 
		 * BindingConfiguration config = new BindingConfiguration() {
		 * 
		 * @Override public String getBindingId() { return soap11.getBindingId(); } };
		 * factory.setBindingConfig(config);
		 * factory.setBindingId(soap11.getBindingId());
		 */

		Iti18PortType iti18PortType = (Iti18PortType) factory.create();
		Client client = ClientProxy.getClient(iti18PortType);

		client.getRequestContext().put(Message.ENDPOINT_ADDRESS, endpoint);

		boolean checkAuth = true;
		if (repository.getAuthenticationRequired() != null && !repository.getAuthenticationRequired().booleanValue()) {
			checkAuth = false;
		}
		if (checkAuth) {

			AssertionIdentity identity = getIdentity(accList, repository.getCodice(), codiceApplicazione);
			if (identity == null) {

				throwIdentityException(repository.getCodice(), codiceApplicazione);
			}

			if ("svil".equalsIgnoreCase(activeProfile)) {
				System.setProperty("javax.net.ssl.trustStore", "client-truststore.jks");
				System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
			}
			String stsServiceLocation = getEndpoint(repository, RepoConsts.TRANSAZIONE_ASSERTION);
			if (stsServiceLocation == null) {

				throwEndpointException(repository.getCodice(), RepoConsts.TRANSAZIONE_ASSERTION);
			}

			STSClient stsClient = initSTS(stsServiceLocation, endpoint, identity.getUser(), identity.getPassword(),
					SIMMETRIC_KEYTYPE);

			String wsAddressTo = getEndpoint(repository, RepoConsts.TRANSAZIONE_WS_ADDRESS_TO_ITI18);
			try {
				logInfo("simmetric TOKEN");

				SecurityToken samlToken = stsClient.requestSecurityToken(endpoint);
				client.getEndpoint().getOutInterceptors().add(new AddStsTokenInterceptor(samlToken, wsAddressTo));

			} catch (Exception e) {

				logError(e);

				stsClient.setKeyType(BEARER_KEYTYPE);
				try {
					logInfo("bearer TOKEN");

					SecurityToken samlToken = stsClient.requestSecurityToken(endpoint);
					client.getEndpoint().getOutInterceptors().add(new AddStsTokenInterceptor(samlToken, wsAddressTo));

				} catch (Exception ex) {

					logError(e);

				}
			}

		}

		return iti18PortType;
	}

	private String getEndpoint(GatefireDRepository repo, String transazione) {

		if (repo.getRepositoryUriList() != null) {
			for (GatefireRRepositoryUri uri : repo.getRepositoryUriList()) {
				if (uri.getTransazione().equalsIgnoreCase(transazione)) {
					return uri.getUri();
				}
			}
		}

		return null;

	}

	private GatefireDApplicazione getApplicazione(List<GatefireDApplicazione> list, String codiceApplicazione) {

		if (list != null) {
			for (GatefireDApplicazione app : list) {
				if (app.getCodice().equalsIgnoreCase(codiceApplicazione)) {
					return app;
				}
			}
		}

		return null;

	}

	private AssertionIdentity getIdentity(List<GatefireRRepositoryAccount> list, String codiceRepository,
			String codiceApplicazione) {
		AssertionIdentity assertionIdentity = null;
		for (GatefireRRepositoryAccount acc : list) {
			if (acc.getCodiceApplicazione().equalsIgnoreCase(codiceApplicazione)
					&& acc.getCodiceRepository().equalsIgnoreCase(codiceRepository)) {
				assertionIdentity = new AssertionIdentity();
				assertionIdentity.setUser(acc.getUsername());
				assertionIdentity.setPassword(PasswordUtils.decrypt(acc.getHashedPassword(), acc.getSalt()));
				break;
			}
		}
		return assertionIdentity;
	}

	private void throwEndpointException(String codiceRepo, String transazione) throws ValidationException {
		ErrorField errorfield = new ErrorField("callInfo.collocazione",
				"nessun endpoint configurato per il repository [" + codiceRepo + "] e transazione [" + transazione
						+ "]");

		throw new ValidationException("nessun endpoint configurato per il repository [" + codiceRepo
				+ "] e transazione [" + transazione + "]", errorfield);
	}

	private void throwIdentityException(String codiceRepo, String codiceApplicazione) throws ValidationException {
		ErrorField errorfield = new ErrorField("callInfo.collocazione",
				"nessun endpoint configurato per il repository [" + codiceRepo + "] e codiceApplicazione ["
						+ codiceApplicazione + "]");

		throw new ValidationException("nessun endpoint configurato per il repository [" + codiceRepo
				+ "] e codiceApplicazione [" + codiceApplicazione + "]", errorfield);
	}
}
