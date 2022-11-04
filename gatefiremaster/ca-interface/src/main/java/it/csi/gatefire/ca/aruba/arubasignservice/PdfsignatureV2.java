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
 * <p>Java class for pdfsignatureV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pdfsignatureV2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SignRequestV2" type="{http://arubasignservice.arubapec.it/}signRequestV2" minOccurs="0"/&gt;
 *         &lt;element name="Apparence" type="{http://arubasignservice.arubapec.it/}pdfSignApparence" minOccurs="0"/&gt;
 *         &lt;element name="pdfprofile" type="{http://arubasignservice.arubapec.it/}pdfProfile" minOccurs="0"/&gt;
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dict_signed_attributes" type="{http://arubasignservice.arubapec.it/}dictionarySignedAttributes" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pdfsignatureV2", propOrder = {
    "signRequestV2",
    "apparence",
    "pdfprofile",
    "password",
    "dictSignedAttributes"
})
public class PdfsignatureV2 {

    @XmlElement(name = "SignRequestV2")
    protected SignRequestV2 signRequestV2;
    @XmlElement(name = "Apparence")
    protected PdfSignApparence apparence;
    @XmlSchemaType(name = "string")
    protected PdfProfile pdfprofile;
    protected String password;
    @XmlElement(name = "dict_signed_attributes")
    protected DictionarySignedAttributes dictSignedAttributes;

    /**
     * Gets the value of the signRequestV2 property.
     * 
     * @return
     *     possible object is
     *     {@link SignRequestV2 }
     *     
     */
    public SignRequestV2 getSignRequestV2() {
        return signRequestV2;
    }

    /**
     * Sets the value of the signRequestV2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignRequestV2 }
     *     
     */
    public void setSignRequestV2(SignRequestV2 value) {
        this.signRequestV2 = value;
    }

    /**
     * Gets the value of the apparence property.
     * 
     * @return
     *     possible object is
     *     {@link PdfSignApparence }
     *     
     */
    public PdfSignApparence getApparence() {
        return apparence;
    }

    /**
     * Sets the value of the apparence property.
     * 
     * @param value
     *     allowed object is
     *     {@link PdfSignApparence }
     *     
     */
    public void setApparence(PdfSignApparence value) {
        this.apparence = value;
    }

    /**
     * Gets the value of the pdfprofile property.
     * 
     * @return
     *     possible object is
     *     {@link PdfProfile }
     *     
     */
    public PdfProfile getPdfprofile() {
        return pdfprofile;
    }

    /**
     * Sets the value of the pdfprofile property.
     * 
     * @param value
     *     allowed object is
     *     {@link PdfProfile }
     *     
     */
    public void setPdfprofile(PdfProfile value) {
        this.pdfprofile = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the dictSignedAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link DictionarySignedAttributes }
     *     
     */
    public DictionarySignedAttributes getDictSignedAttributes() {
        return dictSignedAttributes;
    }

    /**
     * Sets the value of the dictSignedAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DictionarySignedAttributes }
     *     
     */
    public void setDictSignedAttributes(DictionarySignedAttributes value) {
        this.dictSignedAttributes = value;
    }

}
