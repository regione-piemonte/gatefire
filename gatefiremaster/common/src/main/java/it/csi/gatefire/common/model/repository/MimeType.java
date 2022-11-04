/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model.repository;

public enum MimeType {

	HL7_CDA("text/x-cda-r2+xml"), XML("text/xml"), TEXT("text/plain"), PKCS7("application/x-pkcs7-mime"),
	RTF("application/rtf"), PDF("application/pdf"), MULTIPART("multipart/related"), DICOM("application/dicom"),
	JSON("application/json");

	private String value;

	MimeType(String value) {
		this.value = value;
	}

	public static MimeType fromValue(String value) {
		for (MimeType c : MimeType.values()) {
			if (c.value.equals(value)) {
				return c;
			}
		}
		throw new IllegalArgumentException(value);
	}

}
