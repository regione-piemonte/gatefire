/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import java.util.Date;

public class RevocationInfo {
	protected Date thisUpdate;

	protected Date revocationDate;

	protected Date expired;

	public Date getThisUpdate() {
		return thisUpdate;
	}

	public void setThisUpdate(Date thisUpdate) {
		this.thisUpdate = thisUpdate;
	}

	public Date getRevocationDate() {
		return revocationDate;
	}

	public void setRevocationDate(Date revocationDate) {
		this.revocationDate = revocationDate;
	}

	public Date getExpired() {
		return expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}

	@Override
	public String toString() {
		return "RevocationInfo [thisUpdate=" + thisUpdate + ", revocationDate=" + revocationDate + ", expired="
				+ expired + "]";
	}

	/*
	 * protected String crlHoldDate;
	 * 
	 * protected String crlInvalidSince;
	 * 
	 * 
	 * protected Boolean certRevoked; protected String revocationReason;
	 * 
	 * 
	 * protected Boolean crlStatus; protected String clStatusInfo;
	 * 
	 * @XmlSchemaType(name = "dateTime") protected XMLGregorianCalendar
	 * crlNextUpdate; protected String serial;
	 */

}
