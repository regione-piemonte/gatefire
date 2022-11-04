/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.arubasignservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendCredential complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendCredential"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Identity" type="{http://arubasignservice.arubapec.it/}auth" minOccurs="0"/&gt;
 *         &lt;element name="type" type="{http://arubasignservice.arubapec.it/}credentialsType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendCredential", propOrder = {
    "identity",
    "type"
})
public class SendCredential {

    @XmlElement(name = "Identity")
    protected Auth identity;
    @XmlSchemaType(name = "string")
    protected CredentialsType type;

    /**
     * Gets the value of the identity property.
     * 
     * @return
     *     possible object is
     *     {@link Auth }
     *     
     */
    public Auth getIdentity() {
        return identity;
    }

    /**
     * Sets the value of the identity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Auth }
     *     
     */
    public void setIdentity(Auth value) {
        this.identity = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link CredentialsType }
     *     
     */
    public CredentialsType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link CredentialsType }
     *     
     */
    public void setType(CredentialsType value) {
        this.type = value;
    }

}
