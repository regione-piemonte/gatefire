/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlType(name = "signLayout")
public class SignLayout {

	private Integer page ;
	private Integer lowLeftX;
	private Integer lowLeftY;

	private Integer upRightX;
	private Integer upRightY;

	private String reason;
	private boolean showDateTime;
	private byte[] image;

	

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLowLeftX() {
		return lowLeftX;
	}

	public void setLowLeftX(Integer lowLeftX) {
		this.lowLeftX = lowLeftX;
	}

	public Integer getLowLeftY() {
		return lowLeftY;
	}

	public void setLowLeftY(Integer lowLeftY) {
		this.lowLeftY = lowLeftY;
	}

	public Integer getUpRightX() {
		return upRightX;
	}

	public void setUpRightX(Integer upRightX) {
		this.upRightX = upRightX;
	}

	public Integer getUpRightY() {
		return upRightY;
	}

	public void setUpRightY(Integer upRightY) {
		this.upRightY = upRightY;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@JsonIgnore
	public byte[] getImage() {
		return image;
	}

	@JsonProperty
	public void setImage(byte[] image) {
		this.image = image;
	}

	public boolean isShowDateTime() {
		return showDateTime;
	}

	public void setShowDateTime(boolean showDateTime) {
		this.showDateTime = showDateTime;
	}

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "SignLayout [page=" + page + ", lowLeftX=" + lowLeftX + ", lowLeftY=" + lowLeftY + ", upRightX="
				+ upRightX + ", upRightY=" + upRightY + ", reason=" + reason + ", showDateTime=" + showDateTime
				+ ", image=" + Arrays.toString(image) + ", text=" + text + "]";
	}

}
