/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.gateway.service.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import it.csi.gatefire.common.model.AssertionIdentity;
import it.csi.gatefire.common.model.Attachment;
import it.csi.gatefire.common.model.AutoSignIdentity;
import it.csi.gatefire.common.model.CallInfo;
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

@WSDLDocumentation("Servizio di gatefire")
@WebService(name = "gateFireSrvc", serviceName = "gateFireSrvc", targetNamespace = "http://www.csi.it/gatefire/")
public interface GateFireWSSrvc {

	@WSDLDocumentation("Verifica disponibilita' servizio di firma")
	PingResult ping(@WebParam(name = "user") String user, @WebParam(name = "callInfo") CallInfo callInfo);

	@WSDLDocumentation("Marca temporale")
	FileResult marcaTemporale(@WebParam(name = "attachment") Attachment attachment,
			@WebParam(name = "markInput") MarkInput markInput);

	@WSDLDocumentation("firma PAdES - se otp specificato: firma remota. Se otp nullo: firma automatica.")
	FileResult firmaPAdES(@WebParam(name = "attachment") Attachment attachment,
			@WebParam(name = "padesInput") PadesInput padesInput, @WebParam(name = "identity") Identity identity);

	@WSDLDocumentation("firma PAdES remota")
	FileResult firmaPAdESRemota(@WebParam(name = "attachment") Attachment attachment,
			@WebParam(name = "padesInput") PadesInput padesInput,
			@WebParam(name = "identity") SignIdentity signIdentity);

	@WSDLDocumentation("firma PAdES Automatica")
	FileResult firmaPAdESAutomatica(@WebParam(name = "attachment") Attachment attachment,
			@WebParam(name = "padesInput") PadesInput padesInput,
			@WebParam(name = "identity") AutoSignIdentity signIdentity);

	@WSDLDocumentation("firma PAdES Massiva  - se otp specificato: firma remota. Se otp nullo: firma automatica.")
	FileResult firmaPAdESMassiva(@WebParam(name = "attachment") List<Attachment> attachment,
			@WebParam(name = "padesInput") PadesInput padesInput, @WebParam(name = "identity") Identity identity);

	@WSDLDocumentation("firma PAdES Massiva Remota")
	FileResult firmaPAdESMassivaRemota(@WebParam(name = "attachment") List<Attachment> attachment,
			@WebParam(name = "padesInput") PadesInput padesInput,
			@WebParam(name = "identity") SignIdentity signIdentity);

	@WSDLDocumentation("firma PAdES Massiva Automatica")
	FileResult firmaPAdESMassivaAutomatica(@WebParam(name = "attachment") List<Attachment> attachmentList,
			@WebParam(name = "padesInput") PadesInput padesInput,
			@WebParam(name = "identity") AutoSignIdentity signIdentity);

	@WSDLDocumentation("richiesta otp")
	OtpResult richiestaOtp(@WebParam(name = "otpReqInput") OtpReqInput otpReqInput);

	@WSDLDocumentation("verifica firma")
	SignVerifyResult verificaFirma(@WebParam(name = "attachment") Attachment attachment,
			@WebParam(name = "callInfo") CallInfo callInfo);

	@WSDLDocumentation("verifica marca")
	MarkVerifyResult verificaMarca(@WebParam(name = "attachment") Attachment attachment,
			@WebParam(name = "callInfo") CallInfo callInfo);

	@WSDLDocumentation("verifica marca detached")
	MarkVerifyResult verificaMarcaDetached(@WebParam(name = "mark") Attachment mark,
			@WebParam(name = "originalFile") Attachment originalFile, @WebParam(name = "callInfo") CallInfo callInfo);

	@WSDLDocumentation("Apertura sessione di firma multipla. Si ottiene il session id da utlizzare nelle chiamate successive")
	SessionIdResult openSession(@WebParam(name = "sessionInput") SessionInput sessionInput);

	@WSDLDocumentation("Chiusura sessione di firma multipla.")
	Result closeSession(@WebParam(name = "sessionId") String sessionId,
			@WebParam(name = "sessionInput") SessionInput sessionInput);

	@WSDLDocumentation("Archiviazione di un documento su repository.")
	Result archivia(@WebParam(name = "repositoryInput") RepositoryInput input,
			@WebParam(name = "callInfo") CallInfo callInfo);

	@WSDLDocumentation("Modifica dei metadati di un documento su repository.")
	Result modificaMetadati(@WebParam(name = "repositoryInput") RepositoryMetadataUpdateInput input,
			@WebParam(name = "callInfo") CallInfo callInfo);

	@WSDLDocumentation("Recupero di un documento su repository.")
	FileResult recuperaDocumento(@WebParam(name = "repositoryInput") RepositoryDocIdInput input,
			@WebParam(name = "callInfo") CallInfo callInfo);

	@WSDLDocumentation("Recupero metadati di un documento su repository.")
	RepositoryQueryResult recuperaMetadataDocumento(@WebParam(name = "repositoryInput") RepositoryDocIdInput input,
			@WebParam(name = "callInfo") CallInfo callInfo);

	@WSDLDocumentation("Annullamento di un documento su repository.")
	Result annullaDocumento(@WebParam(name = "repositoryInput") RepositoryUndoInput input,
			@WebParam(name = "callInfo") CallInfo callInfo);

	@WSDLDocumentation("firma PAdES Automatica con archiviazione del documento firmato su repository.")
	FileResult firmaPAdESAutomaticaConArchiviaz(@WebParam(name = "attachment") Attachment attachment,
			@WebParam(name = "padesInput") PadesInput padesInput,
			@WebParam(name = "identity") AutoSignIdentity signIdentity,
			@WebParam(name = "metadata") ITIMetadata itiMetadata,
			@WebParam(name = "assertionIdentity") AssertionIdentity assertionIdentity);

	@WSDLDocumentation("firma PAdES remota con archiviazione del documento firmato su repository.")
	FileResult firmaPAdESRemotaConArchiviaz(@WebParam(name = "attachment") Attachment attachment,
			@WebParam(name = "padesInput") PadesInput padesInput,
			@WebParam(name = "identity") SignIdentity signIdentity,
			@WebParam(name = "metadata") ITIMetadata itiMetadata,
			@WebParam(name = "assertionIdentity") AssertionIdentity assertionIdentity);

	@WSDLDocumentation("firma PAdES con archiviazione del documento firmato su repository - se otp specificato: firma remota. Se otp nullo: firma automatica.")
	FileResult firmaPAdESConArchiviaz(@WebParam(name = "attachment") Attachment attachment,
			@WebParam(name = "padesInput") PadesInput padesInput, @WebParam(name = "identity") Identity identity,
			@WebParam(name = "metadata") ITIMetadata itiMetadata,
			@WebParam(name = "assertionIdentity") AssertionIdentity assertionIdentity);

}
