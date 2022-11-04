/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for certIssuer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="certIssuer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="issuerDistinguishName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="issuerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="issuerPartitaIva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "certIssuer", propOrder = {
    "issuerDistinguishName",
    "issuerName",
    "issuerPartitaIva"
})
public class CertIssuer {

    protected String issuerDistinguishName;
    protected String issuerName;
    protected String issuerPartitaIva;

    /**
     * Gets the value of the issuerDistinguishName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuerDistinguishName() {
        return issuerDistinguishName;
    }

    /**
     * Sets the value of the issuerDistinguishName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuerDistinguishName(String value) {
        this.issuerDistinguishName = value;
    }

    /**
     * Gets the value of the issuerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuerName() {
        return issuerName;
    }

    /**
     * Sets the value of the issuerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuerName(String value) {
        this.issuerName = value;
    }

    /**
     * Gets the value of the issuerPartitaIva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuerPartitaIva() {
        return issuerPartitaIva;
    }

    /**
     * Sets the value of the issuerPartitaIva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuerPartitaIva(String value) {
        this.issuerPartitaIva = value;
    }

}
