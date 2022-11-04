/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fileResult")
public class FileResult extends BaseResult {

	private Attachment attachment;

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}


}
