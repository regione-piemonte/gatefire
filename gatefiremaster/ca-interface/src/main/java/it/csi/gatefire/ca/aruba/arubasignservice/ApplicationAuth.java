/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.arubasignservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for applicationAuth complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="applicationAuth"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="applicationpassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="applicationuser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "applicationAuth", propOrder = {
    "applicationpassword",
    "applicationuser"
})
public class ApplicationAuth {

    protected String applicationpassword;
    protected String applicationuser;

    /**
     * Gets the value of the applicationpassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationpassword() {
        return applicationpassword;
    }

    /**
     * Sets the value of the applicationpassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationpassword(String value) {
        this.applicationpassword = value;
    }

    /**
     * Gets the value of the applicationuser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationuser() {
        return applicationuser;
    }

    /**
     * Sets the value of the applicationuser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationuser(String value) {
        this.applicationuser = value;
    }

}
