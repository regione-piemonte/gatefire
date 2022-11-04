/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.arubasignservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for testCredential complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="testCredential"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="appidentity" type="{http://arubasignservice.arubapec.it/}applicationAuth" minOccurs="0"/&gt;
 *         &lt;element name="domain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dummy_otp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "testCredential", propOrder = {
    "appidentity",
    "domain",
    "dummyOtp",
    "user"
})
public class TestCredential {

    protected ApplicationAuth appidentity;
    protected String domain;
    @XmlElement(name = "dummy_otp")
    protected String dummyOtp;
    protected String user;

    /**
     * Gets the value of the appidentity property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationAuth }
     *     
     */
    public ApplicationAuth getAppidentity() {
        return appidentity;
    }

    /**
     * Sets the value of the appidentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationAuth }
     *     
     */
    public void setAppidentity(ApplicationAuth value) {
        this.appidentity = value;
    }

    /**
     * Gets the value of the domain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets the value of the domain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomain(String value) {
        this.domain = value;
    }

    /**
     * Gets the value of the dummyOtp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDummyOtp() {
        return dummyOtp;
    }

    /**
     * Sets the value of the dummyOtp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDummyOtp(String value) {
        this.dummyOtp = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

}
