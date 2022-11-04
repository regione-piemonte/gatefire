/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Certificate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="verifAllaData" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "certificate",
    "verifAllaData"
})
@XmlRootElement(name = "VerifyCertificate")
public class VerifyCertificate {

    @XmlElement(name = "Certificate", required = true)
    protected String certificate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar verifAllaData;

    /**
     * Gets the value of the certificate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificate() {
        return certificate;
    }

    /**
     * Sets the value of the certificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificate(String value) {
        this.certificate = value;
    }

    /**
     * Gets the value of the verifAllaData property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVerifAllaData() {
        return verifAllaData;
    }

    /**
     * Sets the value of the verifAllaData property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVerifAllaData(XMLGregorianCalendar value) {
        this.verifAllaData = value;
    }

}
