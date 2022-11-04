/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.ca.service;

import it.csi.gatefire.common.model.Attachment;
import it.csi.gatefire.common.model.MarkVerifyResult;
import it.csi.gatefire.common.model.SignVerifyResult;

public interface VolSrvc {
	SignVerifyResult verificaFirma(Attachment attachment, String codiceCa, Long idEvento);

	MarkVerifyResult verificaMarca(Attachment attachment, String timeStampToken, String codiceCa, Long idEvento);
}
