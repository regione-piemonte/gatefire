/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.arubasignservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.csi.gatefire.common.json.PasswordSerializer;

/**
 * <p>
 * Java class for tsaAuth complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="tsaAuth"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tsaurl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tsaAuth", propOrder = { "password", "user", "tsaurl" })
public class TsaAuth {

	@XmlElement(required = true)
	protected String password;
	@XmlElement(required = true)
	protected String user;
	protected String tsaurl;

	/**
	 * Gets the value of the password property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	@JsonSerialize(using = PasswordSerializer.class)
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the value of the password property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setPassword(String value) {
		this.password = value;
	}

	/**
	 * Gets the value of the user property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the value of the user property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setUser(String value) {
		this.user = value;
	}

	/**
	 * Gets the value of the tsaurl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTsaurl() {
		return tsaurl;
	}

	/**
	 * Sets the value of the tsaurl property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setTsaurl(String value) {
		this.tsaurl = value;
	}

}
