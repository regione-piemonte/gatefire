/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import java.util.Date;
import java.util.List;

public class Certificate {
	private String name;
	private String sn;

	private Date validFrom;

	private Date validTo;

	private Boolean valid;
	private Boolean timeValid;

	private Subject subject;
	private Issuer issuer;

	private RevocationInfo revocationInfo;

	private List<CertPolicy> certPolicyList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Boolean getTimeValid() {
		return timeValid;
	}

	public void setTimeValid(Boolean timeValid) {
		this.timeValid = timeValid;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Issuer getIssuer() {
		return issuer;
	}

	public void setIssuer(Issuer issuer) {
		this.issuer = issuer;
	}

	public RevocationInfo getRevocationInfo() {
		return revocationInfo;
	}

	public void setRevocationInfo(RevocationInfo revocationInfo) {
		this.revocationInfo = revocationInfo;
	}

	public List<CertPolicy> getCertPolicyList() {
		return certPolicyList;
	}

	public void setCertPolicyList(List<CertPolicy> certPolicyList) {
		this.certPolicyList = certPolicyList;
	}

	@Override
	public String toString() {
		return "Certificate [name=" + name + ", sn=" + sn + ", validFrom=" + validFrom + ", validTo=" + validTo
				+ ", valid=" + valid + ", timeValid=" + timeValid + ", subject=" + subject + ", issuer=" + issuer
				+ ", revocationInfo=" + revocationInfo + ", certPolicyList=" + certPolicyList + "]";
	}

	// protected String certVersion;

	// protected Boolean certQualified;
	// protected String certKeyUsage;

	// protected CertPublicKey certPublicKey;

	// protected String certFinger256;

	// protected String certCert;

}
