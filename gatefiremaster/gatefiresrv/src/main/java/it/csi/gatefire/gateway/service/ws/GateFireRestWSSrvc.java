/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.gateway.service.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import it.csi.gatefire.common.model.CallInfo;
import it.csi.gatefire.common.model.FileResult;
import it.csi.gatefire.common.model.MarkType;
import it.csi.gatefire.common.model.MarkVerifyResult;
import it.csi.gatefire.common.model.OtpReqInput;
import it.csi.gatefire.common.model.OtpResult;
import it.csi.gatefire.common.model.PingResult;
import it.csi.gatefire.common.model.Result;
import it.csi.gatefire.common.model.SessionIdResult;
import it.csi.gatefire.common.model.SessionInput;
import it.csi.gatefire.common.model.SignVerifyResult;

@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public interface GateFireRestWSSrvc {
	@GET
	@Path("/ping")
	PingResult ping(@QueryParam("user") String user, @QueryParam("") CallInfo callInfo);

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/firmaPAdES")
	FileResult firmaPAdES(
			@Multipart(value = "attachment", required = false) org.apache.cxf.jaxrs.ext.multipart.Attachment file,
			@Multipart(value = "padesInput.callInfo.applicationCode", required = false) String applicationCode,
			@Multipart(value = "padesInput.callInfo.caCode", required = false) String caCode,
			@Multipart(value = "padesInput.callInfo.codiceFiscale", required = false) String codiceFiscale,
			@Multipart(value = "padesInput.callInfo.collocazione", required = false) String collocazione,
			@Multipart(value = "padesInput.callInfo.dominio", required = false) String dominio,
			@Multipart(value = "padesInput.signLayout.page", required = false) Integer page,
			@Multipart(value = "padesInput.signLayout.lowLeftX", required = false) Integer lowLeftX,
			@Multipart(value = "padesInput.signLayout.lowLeftY", required = false) Integer lowLeftY,
			@Multipart(value = "padesInput.signLayout.upRightX", required = false) Integer upRightX,
			@Multipart(value = "padesInput.signLayout.upRightY", required = false) Integer upRightY,
			@Multipart(value = "padesInput.signLayout.reason", required = false) String reason,
			@Multipart(value = "padesInput.signLayout.text", required = false) String text,
			@Multipart(value = "padesInput.signLayout.showDateTime", required = false) Boolean showDateTime,
			@Multipart(value = "padesInput.signLayout.image", required = false) org.apache.cxf.jaxrs.ext.multipart.Attachment image,
			@Multipart(value = "padesInput.requiredMark", required = false) Boolean requiredMark,
			@Multipart(value = "padesInput.markIdentity.password", required = false) String markPassword,
			@Multipart(value = "padesInput.markIdentity.user", required = false) String markUser,
			@Multipart(value = "identity.otp", required = false) String otp,
			@Multipart(value = "identity.password", required = false) String password,
			@Multipart(value = "identity.user", required = false) String user);

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/firmaPAdESRemota")
	FileResult firmaPAdESRemota(
			@Multipart(value = "attachment", required = false) org.apache.cxf.jaxrs.ext.multipart.Attachment file,
			@Multipart(value = "padesInput.callInfo.applicationCode", required = false) String applicationCode,
			@Multipart(value = "padesInput.callInfo.caCode", required = false) String caCode,
			@Multipart(value = "padesInput.callInfo.codiceFiscale", required = false) String codiceFiscale,
			@Multipart(value = "padesInput.callInfo.collocazione", required = false) String collocazione,
			@Multipart(value = "padesInput.callInfo.dominio", required = false) String dominio,
			@Multipart(value = "padesInput.signLayout.page", required = false) Integer page,
			@Multipart(value = "padesInput.signLayout.lowLeftX", required = false) Integer lowLeftX,
			@Multipart(value = "padesInput.signLayout.lowLeftY", required = false) Integer lowLeftY,
			@Multipart(value = "padesInput.signLayout.upRightX", required = false) Integer upRightX,
			@Multipart(value = "padesInput.signLayout.upRightY", required = false) Integer upRightY,
			@Multipart(value = "padesInput.signLayout.reason", required = false) String reason,
			@Multipart(value = "padesInput.signLayout.text", required = false) String text,
			@Multipart(value = "padesInput.signLayout.showDateTime", required = false) Boolean showDateTime,
			@Multipart(value = "padesInput.signLayout.image", required = false) org.apache.cxf.jaxrs.ext.multipart.Attachment image,
			@Multipart(value = "padesInput.requiredMark", required = false) Boolean requiredMark,
			@Multipart(value = "padesInput.markIdentity.password", required = false) String markPassword,
			@Multipart(value = "padesInput.markIdentity.user", required = false) String markUser,
			@Multipart(value = "identity.otp", required = false) String otp,
			@Multipart(value = "identity.password", required = false) String password,
			@Multipart(value = "identity.user", required = false) String user,
			@Multipart(value = "identity.sessionId", required = false) String sessionId);

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/firmaPAdESAutomatica")
	FileResult firmaPAdESAutomatica(
			@Multipart(value = "attachment", required = false) org.apache.cxf.jaxrs.ext.multipart.Attachment file,
			@Multipart(value = "padesInput.callInfo.applicationCode", required = false) String applicationCode,
			@Multipart(value = "padesInput.callInfo.caCode", required = false) String caCode,
			@Multipart(value = "padesInput.callInfo.codiceFiscale", required = false) String codiceFiscale,
			@Multipart(value = "padesInput.callInfo.collocazione", required = false) String collocazione,
			@Multipart(value = "padesInput.callInfo.dominio", required = false) String dominio,
			@Multipart(value = "padesInput.signLayout.page", required = false) Integer page,
			@Multipart(value = "padesInput.signLayout.lowLeftX", required = false) Integer lowLeftX,
			@Multipart(value = "padesInput.signLayout.lowLeftY", required = false) Integer lowLeftY,
			@Multipart(value = "padesInput.signLayout.upRightX", required = false) Integer upRightX,
			@Multipart(value = "padesInput.signLayout.upRightY", required = false) Integer upRightY,
			@Multipart(value = "padesInput.signLayout.reason", required = false) String reason,
			@Multipart(value = "padesInput.signLayout.text", required = false) String text,
			@Multipart(value = "padesInput.signLayout.showDateTime", required = false) Boolean showDateTime,
			@Multipart(value = "padesInput.signLayout.image", required = false) org.apache.cxf.jaxrs.ext.multipart.Attachment image,
			@Multipart(value = "padesInput.requiredMark", required = false) Boolean requiredMark,
			@Multipart(value = "padesInput.markIdentity.password", required = false) String markPassword,
			@Multipart(value = "padesInput.markIdentity.user", required = false) String markUser,
			@Multipart(value = "identity.otp", required = false) String otp,
			@Multipart(value = "identity.password", required = false) String password,
			@Multipart(value = "identity.user", required = false) String user,
			@Multipart(value = "identity.delegatedDomain", required = false) String delegatedDomain,
			@Multipart(value = "identity.delegatedPassword", required = false) String delegatedPassword,
			@Multipart(value = "identity.delegatedUser", required = false) String delegatedUser);

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/firmaPAdESMassiva")
	FileResult firmaPAdESMassiva(
			@Multipart(value = "attachment", required = false) List<org.apache.cxf.jaxrs.ext.multipart.Attachment> attachment,
			@Multipart(value = "padesInput.callInfo.applicationCode", required = false) String applicationCode,
			@Multipart(value = "padesInput.callInfo.caCode", required = false) String caCode,
			@Multipart(value = "padesInput.callInfo.codiceFiscale", required = false) String codiceFiscale,
			@Multipart(value = "padesInput.callInfo.collocazione", required = false) String collocazione,
			@Multipart(value = "padesInput.callInfo.dominio", required = false) String dominio,
			@Multipart(value = "padesInput.signLayout.page", required = false) Integer page,
			@Multipart(value = "padesInput.signLayout.lowLeftX", required = false) Integer lowLeftX,
			@Multipart(value = "padesInput.signLayout.lowLeftY", required = false) Integer lowLeftY,
			@Multipart(value = "padesInput.signLayout.upRightX", required = false) Integer upRightX,
			@Multipart(value = "padesInput.signLayout.upRightY", required = false) Integer upRightY,
			@Multipart(value = "padesInput.signLayout.reason", required = false) String reason,
			@Multipart(value = "padesInput.signLayout.text", required = false) String text,
			@Multipart(value = "padesInput.signLayout.showDateTime", required = false) Boolean showDateTime,
			@Multipart(value = "padesInput.signLayout.image", required = false) org.apache.cxf.jaxrs.ext.multipart.Attachment image,
			@Multipart(value = "padesInput.requiredMark", required = false) Boolean requiredMark,
			@Multipart(value = "padesInput.markIdentity.password", required = false) String markPassword,
			@Multipart(value = "padesInput.markIdentity.user", required = false) String markUser,
			@Multipart(value = "identity.otp", required = false) String otp,
			@Multipart(value = "identity.password", required = false) String password,
			@Multipart(value = "identity.user", required = false) String user);

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/firmaPAdESMassivaRemota")
	FileResult firmaPAdESMassivaRemota(
			@Multipart(value = "attachment", required = false) List<org.apache.cxf.jaxrs.ext.multipart.Attachment> attachment,
			@Multipart(value = "padesInput.callInfo.applicationCode", required = false) String applicationCode,
			@Multipart(value = "padesInput.callInfo.caCode", required = false) String caCode,
			@Multipart(value = "padesInput.callInfo.codiceFiscale", required = false) String codiceFiscale,
			@Multipart(value = "padesInput.callInfo.collocazione", required = false) String collocazione,
			@Multipart(value = "padesInput.callInfo.dominio", required = false) String dominio,
			@Multipart(value = "padesInput.signLayout.page", required = false) Integer page,
			@Multipart(value = "padesInput.signLayout.lowLeftX", required = false) Integer lowLeftX,
			@Multipart(value = "padesInput.signLayout.lowLeftY", required = false) Integer lowLeftY,
			@Multipart(value = "padesInput.signLayout.upRightX", required = false) Integer upRightX,
			@Multipart(value = "padesInput.signLayout.upRightY", required = false) Integer upRightY,
			@Multipart(value = "padesInput.signLayout.reason", required = false) String reason,
			@Multipart(value = "padesInput.signLayout.text", required = false) String text,
			@Multipart(value = "padesInput.signLayout.showDateTime", required = false) Boolean showDateTime,
			@Multipart(value = "padesInput.signLayout.image", required = false) org.apache.cxf.jaxrs.ext.multipart.Attachment image,
			@Multipart(value = "padesInput.requiredMark", required = false) Boolean requiredMark,
			@Multipart(value = "padesInput.markIdentity.password", required = false) String markPassword,
			@Multipart(value = "padesInput.markIdentity.user", required = false) String markUser,
			@Multipart(value = "identity.otp", required = false) String otp,
			@Multipart(value = "identity.password", required = false) String password,
			@Multipart(value = "identity.user", required = false) String user,
			@Multipart(value = "identity.sessionId", required = false) String sessionId);

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/firmaPAdESMassivaAutomatica")
	FileResult firmaPAdESMassivaAutomatica(
			@Multipart(value = "attachment", required = false) List<org.apache.cxf.jaxrs.ext.multipart.Attachment> attachment,
			@Multipart(value = "padesInput.callInfo.applicationCode", required = false) String applicationCode,
			@Multipart(value = "padesInput.callInfo.caCode", required = false) String caCode,
			@Multipart(value = "padesInput.callInfo.codiceFiscale", required = false) String codiceFiscale,
			@Multipart(value = "padesInput.callInfo.collocazione", required = false) String collocazione,
			@Multipart(value = "padesInput.callInfo.dominio", required = false) String dominio,
			@Multipart(value = "padesInput.signLayout.page", required = false) Integer page,
			@Multipart(value = "padesInput.signLayout.lowLeftX", required = false) Integer lowLeftX,
			@Multipart(value = "padesInput.signLayout.lowLeftY", required = false) Integer lowLeftY,
			@Multipart(value = "padesInput.signLayout.upRightX", required = false) Integer upRightX,
			@Multipart(value = "padesInput.signLayout.upRightY", required = false) Integer upRightY,
			@Multipart(value = "padesInput.signLayout.reason", required = false) String reason,
			@Multipart(value = "padesInput.signLayout.text", required = false) String text,
			@Multipart(value = "padesInput.signLayout.showDateTime", required = false) Boolean showDateTime,
			@Multipart(value = "padesInput.signLayout.image", required = false) org.apache.cxf.jaxrs.ext.multipart.Attachment image,
			@Multipart(value = "padesInput.requiredMark", required = false) Boolean requiredMark,
			@Multipart(value = "padesInput.markIdentity.password", required = false) String markPassword,
			@Multipart(value = "padesInput.markIdentity.user", required = false) String markUser,
			@Multipart(value = "identity.otp", required = false) String otp,
			@Multipart(value = "identity.password", required = false) String password,
			@Multipart(value = "identity.user", required = false) String user,
			@Multipart(value = "identity.delegatedDomain", required = false) String delegatedDomain,
			@Multipart(value = "identity.delegatedPassword", required = false) String delegatedPassword,
			@Multipart(value = "identity.delegatedUser", required = false) String delegatedUser);

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/marcaTemporale")
	FileResult marcaTemporale(
			@Multipart(value = "attachment", required = false) org.apache.cxf.jaxrs.ext.multipart.Attachment file,
			@Multipart(value = "markInput.callInfo.applicationCode", required = false) String applicationCode,
			@Multipart(value = "markInput.callInfo.caCode", required = false) String caCode,
			@Multipart(value = "markInput.callInfo.codiceFiscale", required = false) String codiceFiscale,
			@Multipart(value = "markInput.callInfo.collocazione", required = false) String collocazione,
			@Multipart(value = "markInput.callInfo.dominio", required = false) String dominio,
			@Multipart(value = "markInput.markIdentity.password", required = false) String markPassword,
			@Multipart(value = "markInput.markIdentity.user", required = false) String markUser,
			@Multipart(value = "markType", required = false) MarkType markType);

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/verificaFirma")
	SignVerifyResult verificaFirma(
			@Multipart(value = "attachment", required = false) org.apache.cxf.jaxrs.ext.multipart.Attachment file,
			@Multipart(value = "callInfo.applicationCode", required = false) String applicationCode,
			@Multipart(value = "callInfo.caCode", required = false) String caCode,
			@Multipart(value = "callInfo.codiceFiscale", required = false) String codiceFiscale,
			@Multipart(value = "callInfo.collocazione", required = false) String collocazione,
			@Multipart(value = "callInfo.dominio", required = false) String dominio);

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/verificaMarca")
	MarkVerifyResult verificaMarca(
			@Multipart(value = "attachment", required = false) org.apache.cxf.jaxrs.ext.multipart.Attachment file,
			@Multipart(value = "callInfo.applicationCode", required = false) String applicationCode,
			@Multipart(value = "callInfo.caCode", required = false) String caCode,
			@Multipart(value = "callInfo.codiceFiscale", required = false) String codiceFiscale,
			@Multipart(value = "callInfo.collocazione", required = false) String collocazione,
			@Multipart(value = "callInfo.dominio", required = false) String dominio);

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/verificaMarcaDetached")
	MarkVerifyResult verificaMarcaDetached(
			@Multipart(value = "mark", required = false) org.apache.cxf.jaxrs.ext.multipart.Attachment mark,
			@Multipart(value = "attachment", required = false) org.apache.cxf.jaxrs.ext.multipart.Attachment file,
			@Multipart(value = "callInfo.applicationCode", required = false) String applicationCode,
			@Multipart(value = "callInfo.caCode", required = false) String caCode,
			@Multipart(value = "callInfo.codiceFiscale", required = false) String codiceFiscale,
			@Multipart(value = "callInfo.collocazione", required = false) String collocazione,
			@Multipart(value = "callInfo.dominio", required = false) String dominio);

	@POST
	@Path("/openSession")
	SessionIdResult openSession(@QueryParam("") SessionInput sessionInput);

	@POST
	@Path("/closeSession")
	Result closeSession(@QueryParam("sessionId") String sessionId, @QueryParam("") SessionInput sessionInput);

	@POST
	@Path("/richiestaOtp")
	OtpResult richiestaOtp(@QueryParam("") OtpReqInput otpReqInput);

	/*
	 * @POST
	 * 
	 * @Consumes(MediaType.MULTIPART_FORM_DATA)
	 * 
	 * @Path("/archivia") Result archivia(@Multipart("repositoryInput")
	 * RepositoryInput input, @Multipart("callInfo") CallInfo callInfo);
	 * 
	 * @POST
	 * 
	 * @Consumes(MediaType.MULTIPART_FORM_DATA)
	 * 
	 * @Path("/firmaPAdESAutomaticaConArchiviaz") FileResult
	 * firmaPAdESAutomaticaConArchiviaz(@Multipart("attachment") Attachment
	 * attachment,
	 * 
	 * @Multipart("padesInput") PadesInput padesInput, @Multipart("identity")
	 * AutoSignIdentity signIdentity,
	 * 
	 * @Multipart("metadata") ITIMetadata itiMetadata,
	 * 
	 * @Multipart("assertionIdentity") AssertionIdentity assertionIdentity);
	 * 
	 * @POST
	 * 
	 * @Consumes(MediaType.MULTIPART_FORM_DATA)
	 * 
	 * @Path("/firmaPAdESRemotaConArchiviaz") FileResult
	 * firmaPAdESRemotaConArchiviaz(@Multipart("attachment") Attachment attachment,
	 * 
	 * @Multipart("padesInput") PadesInput padesInput, @Multipart("identity")
	 * SignIdentity signIdentity,
	 * 
	 * @Multipart("metadata") ITIMetadata itiMetadata,
	 * 
	 * @Multipart("assertionIdentity") AssertionIdentity assertionIdentity);
	 * 
	 * @POST
	 * 
	 * @Consumes(MediaType.MULTIPART_FORM_DATA)
	 * 
	 * @Path("/firmaPAdESConArchiviaz") FileResult
	 * firmaPAdESConArchiviaz(@Multipart("attachment") Attachment attachment,
	 * 
	 * @Multipart("padesInput") PadesInput padesInput, @Multipart("identity")
	 * Identity signIdentity,
	 * 
	 * @Multipart("metadata") ITIMetadata itiMetadata,
	 * 
	 * @Multipart("assertionIdentity") AssertionIdentity assertionIdentity);
	 */
/*
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/archivia")
	Result archivia(
			@Multipart(value = "repositoryInput.attachment", required = false) org.apache.cxf.jaxrs.ext.multipart.Attachment file,
			@Multipart(value = "repositoryInput.identity.user", required = false) String user,
			@Multipart(value = "repositoryInput.identity.password", required = false) String password,
			@Multipart(value = "callInfo.codiceFiscale", required = false) String codiceFiscale,
			@Multipart(value = "callInfo.collocazione", required = false) String collocazione,

			@Multipart(value = "repositoryInput.metadata.documento.accessionNumber", required = false) List<String> accessionNumber,
			@Multipart(value = "repositoryInput.metadata.documento.assettoOrganizzativo", required = false) String assettoOrganizzativo,
			@Multipart(value = "repositoryInput.metadata.documento.codiceDocumentoScaricabile", required = false) String codiceDocumentoScaricabile,
			@Multipart(value = "repositoryInput.metadata.documento.medicoRedattore", required = false) List<Medico> medicoRedattore,

			@Multipart(value = "repositoryInput.metadata.documento.formatCode", required = false) String formatCode);*/
	/*
	 * : String importoTicketDaPagare : String importoTicketPagato : String invioCLS
	 * : String invioFSE : String livelloRiservatezza : String mimeType : String nre
	 * : List<String>
	 * 
	 * dataDisponibilitaReferto : Date dataOraFirmaDocumento : Date
	 * firmatoDigitalmente : Boolean
	 * 
	 * medicoRedattore : List<Medico> medicoValidatore : Medico
	 * 
	 * oscuramentoDocDSE : Boolean oscuraScaricoCittadino : String pagatoTicket :
	 * String privacyDocumento : String scaricabileDalCittadino : Boolean
	 * soggettoALeggiSpeciali : Boolean submissionSetSourceId : String
	 * submissionSetUniqueId : String tipoAttivitaClinica : String tipoFirma :
	 * TipoFirma tipologiaDocumentoAlto : String tipologiaDocumentoMedio : String
	 * tipologiaStrutturaProdDoc : String uniqueId : String
	 * 
	 * @Multipart(value = "repositoryInput.metadata.episodio.", required = false)
	 * String reason,
	 * 
	 * @Multipart(value = "repositoryInput.metadata.paziente.", required = false)
	 * String reason,
	 * 
	 * @Multipart(value = "repositoryInput.metadata.richiesta.", required = false)
	 * String reason,
	 */

}
