/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for certPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="certPolicy"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="certCpsUri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="certPolText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "certPolicy", propOrder = {
    "certCpsUri",
    "certPolText"
})
public class CertPolicy {

    protected String certCpsUri;
    protected String certPolText;

    /**
     * Gets the value of the certCpsUri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertCpsUri() {
        return certCpsUri;
    }

    /**
     * Sets the value of the certCpsUri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertCpsUri(String value) {
        this.certCpsUri = value;
    }

    /**
     * Gets the value of the certPolText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertPolText() {
        return certPolText;
    }

    /**
     * Sets the value of the certPolText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertPolText(String value) {
        this.certPolText = value;
    }

}
