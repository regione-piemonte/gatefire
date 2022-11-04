package it.csi.gatefire.ca.service;

import it.csi.gatefire.common.model.Attachment;
import it.csi.gatefire.common.model.MarkVerifyResult;
import it.csi.gatefire.common.model.SignVerifyResult;

public interface ProxySignVerSrvc {
	SignVerifyResult verificaFirma(Attachment attachment, String codiceCa, Long idEvento);

	MarkVerifyResult verificaMarca(Attachment attachment, Attachment originalFile, String codiceCa, Long idEvento);
}
