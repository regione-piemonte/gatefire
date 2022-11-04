/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import java.util.Date;

public class SignatureInfo {
	  private Boolean valid;
	    private Boolean  corrupted;
	 
	    private Date signTime;
	    private String signAlgorithm;
		private String signValue;

		public Boolean getValid() {
			return valid;
		}

		public void setValid(Boolean valid) {
			this.valid = valid;
		}

		public Boolean getCorrupted() {
			return corrupted;
		}

		public void setCorrupted(Boolean corrupted) {
			this.corrupted = corrupted;
		}

		public Date getSignTime() {
			return signTime;
		}

		public void setSignTime(Date signTime) {
			this.signTime = signTime;
		}

		public String getSignAlgorithm() {
			return signAlgorithm;
		}

		public void setSignAlgorithm(String signAlgorithm) {
			this.signAlgorithm = signAlgorithm;
		}

		public String getSignValue() {
			return signValue;
		}

		public void setSignValue(String signValue) {
			this.signValue = signValue;
		}

		@Override
		public String toString() {
			return "SignatureInfo [valid=" + valid + ", corrupted=" + corrupted + ", signTime=" + signTime
					+ ", signAlgorithm=" + signAlgorithm + ", signValue=" + signValue + "]";
		}
	    
	    /*
	    protected Boolean sigCertV2;
	  
	     protected Delib45Valid sigDelib45Valid;
	    protected String sigMessageDigest;*/
}
