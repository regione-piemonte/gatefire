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
 * <p>Java class for credentials_query complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="credentials_query"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="credential_query" type="{http://arubasignservice.arubapec.it/}credentialListQuery" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "credentials_query", propOrder = {
    "credentialQuery"
})
public class CredentialsQuery {

    @XmlElement(name = "credential_query")
    protected CredentialListQuery credentialQuery;

    /**
     * Gets the value of the credentialQuery property.
     * 
     * @return
     *     possible object is
     *     {@link CredentialListQuery }
     *     
     */
    public CredentialListQuery getCredentialQuery() {
        return credentialQuery;
    }

    /**
     * Sets the value of the credentialQuery property.
     * 
     * @param value
     *     allowed object is
     *     {@link CredentialListQuery }
     *     
     */
    public void setCredentialQuery(CredentialListQuery value) {
        this.credentialQuery = value;
    }

}
