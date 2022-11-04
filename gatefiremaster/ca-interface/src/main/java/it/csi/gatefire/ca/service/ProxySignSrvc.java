/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.ca.service;

import java.util.List;

import it.csi.gatefire.common.model.Attachment;
import it.csi.gatefire.common.model.AutoSignIdentity;
import it.csi.gatefire.common.model.CallInfo;
import it.csi.gatefire.common.model.FileResult;
import it.csi.gatefire.common.model.MarkInput;
import it.csi.gatefire.common.model.OtpReqInput;
import it.csi.gatefire.common.model.OtpResult;
import it.csi.gatefire.common.model.PadesInput;
import it.csi.gatefire.common.model.PingResult;
import it.csi.gatefire.common.model.SignIdentity;

public interface ProxySignSrvc {
	PingResult ping(CallInfo callInfo);

	FileResult marcaTemporale(Attachment attachment, MarkInput markInput, Long idEvento);

	FileResult firmaPAdESRemota(Attachment attachment, PadesInput padesInput, SignIdentity signIdentity, Long idEvento,
			boolean chiudiEvento);

	FileResult firmaPAdESAutomatica(Attachment attachment, PadesInput padesInput, AutoSignIdentity signIdentity,
			Long idEvento, boolean chiudiEvento);

	FileResult firmaPAdESMassivaRemota(List<Attachment> attachmentList, PadesInput padesInput,
			SignIdentity signIdentity, Long idEvento);

	FileResult firmaPAdESMassivaAutomatica(List<Attachment> attachmentList, PadesInput padesInput,
			AutoSignIdentity signIdentity, Long idEvento);

	OtpResult requestOtp(OtpReqInput otpReqInput, Long idEvento);

}
