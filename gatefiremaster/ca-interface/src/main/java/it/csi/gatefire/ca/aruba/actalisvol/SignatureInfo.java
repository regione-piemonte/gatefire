/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for signatureInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="signatureInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sigValid" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="sigCorrupted" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="sigTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="sigCertV2" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="sigAlgorithm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sigValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sigMessageDigest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sigDelib45Valid" type="{http://actalisVol/}delib45Valid" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signatureInfo", propOrder = {
    "sigValid",
    "sigCorrupted",
    "sigTime",
    "sigCertV2",
    "sigAlgorithm",
    "sigValue",
    "sigMessageDigest",
    "sigDelib45Valid"
})
public class SignatureInfo {

    protected Boolean sigValid;
    protected Boolean sigCorrupted;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sigTime;
    protected Boolean sigCertV2;
    protected String sigAlgorithm;
    protected String sigValue;
    protected String sigMessageDigest;
    protected Delib45Valid sigDelib45Valid;

    /**
     * Gets the value of the sigValid property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSigValid() {
        return sigValid;
    }

    /**
     * Sets the value of the sigValid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSigValid(Boolean value) {
        this.sigValid = value;
    }

    /**
     * Gets the value of the sigCorrupted property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSigCorrupted() {
        return sigCorrupted;
    }

    /**
     * Sets the value of the sigCorrupted property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSigCorrupted(Boolean value) {
        this.sigCorrupted = value;
    }

    /**
     * Gets the value of the sigTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSigTime() {
        return sigTime;
    }

    /**
     * Sets the value of the sigTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSigTime(XMLGregorianCalendar value) {
        this.sigTime = value;
    }

    /**
     * Gets the value of the sigCertV2 property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSigCertV2() {
        return sigCertV2;
    }

    /**
     * Sets the value of the sigCertV2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSigCertV2(Boolean value) {
        this.sigCertV2 = value;
    }

    /**
     * Gets the value of the sigAlgorithm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigAlgorithm() {
        return sigAlgorithm;
    }

    /**
     * Sets the value of the sigAlgorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigAlgorithm(String value) {
        this.sigAlgorithm = value;
    }

    /**
     * Gets the value of the sigValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigValue() {
        return sigValue;
    }

    /**
     * Sets the value of the sigValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigValue(String value) {
        this.sigValue = value;
    }

    /**
     * Gets the value of the sigMessageDigest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigMessageDigest() {
        return sigMessageDigest;
    }

    /**
     * Sets the value of the sigMessageDigest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigMessageDigest(String value) {
        this.sigMessageDigest = value;
    }

    /**
     * Gets the value of the sigDelib45Valid property.
     * 
     * @return
     *     possible object is
     *     {@link Delib45Valid }
     *     
     */
    public Delib45Valid getSigDelib45Valid() {
        return sigDelib45Valid;
    }

    /**
     * Sets the value of the sigDelib45Valid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Delib45Valid }
     *     
     */
    public void setSigDelib45Valid(Delib45Valid value) {
        this.sigDelib45Valid = value;
    }

}
