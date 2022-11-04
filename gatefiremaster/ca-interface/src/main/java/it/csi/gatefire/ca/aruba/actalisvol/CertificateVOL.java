/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for certificateVOL complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="certificateVOL"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="certName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="certVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="certSerialNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="certQualified" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="certValFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="certValUntil" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="certKeyUsage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="certSubject" type="{http://actalisVol/}certSubject" minOccurs="0"/&gt;
 *         &lt;element name="certIssuer" type="{http://actalisVol/}certIssuer" minOccurs="0"/&gt;
 *         &lt;element name="certPublicKey" type="{http://actalisVol/}certPublicKey" minOccurs="0"/&gt;
 *         &lt;element name="certPolicy" type="{http://actalisVol/}certPolicy" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="certRevocation" type="{http://actalisVol/}certRevocationInfo" minOccurs="0"/&gt;
 *         &lt;element name="certFinger256" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="certValid" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="certTimeValid" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="certTrusted" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="certCert" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "certificateVOL", propOrder = {
    "certName",
    "certVersion",
    "certSerialNo",
    "certQualified",
    "certValFrom",
    "certValUntil",
    "certKeyUsage",
    "certSubject",
    "certIssuer",
    "certPublicKey",
    "certPolicy",
    "certRevocation",
    "certFinger256",
    "certValid",
    "certTimeValid",
    "certTrusted",
    "certCert"
})
public class CertificateVOL {

    protected String certName;
    protected String certVersion;
    protected String certSerialNo;
    protected Boolean certQualified;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar certValFrom;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar certValUntil;
    protected String certKeyUsage;
    protected CertSubject certSubject;
    protected CertIssuer certIssuer;
    protected CertPublicKey certPublicKey;
    @XmlElement(nillable = true)
    protected List<CertPolicy> certPolicy;
    protected CertRevocationInfo certRevocation;
    protected String certFinger256;
    protected Boolean certValid;
    protected Boolean certTimeValid;
    protected Boolean certTrusted;
    protected String certCert;

    /**
     * Gets the value of the certName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertName() {
        return certName;
    }

    /**
     * Sets the value of the certName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertName(String value) {
        this.certName = value;
    }

    /**
     * Gets the value of the certVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertVersion() {
        return certVersion;
    }

    /**
     * Sets the value of the certVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertVersion(String value) {
        this.certVersion = value;
    }

    /**
     * Gets the value of the certSerialNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertSerialNo() {
        return certSerialNo;
    }

    /**
     * Sets the value of the certSerialNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertSerialNo(String value) {
        this.certSerialNo = value;
    }

    /**
     * Gets the value of the certQualified property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCertQualified() {
        return certQualified;
    }

    /**
     * Sets the value of the certQualified property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCertQualified(Boolean value) {
        this.certQualified = value;
    }

    /**
     * Gets the value of the certValFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCertValFrom() {
        return certValFrom;
    }

    /**
     * Sets the value of the certValFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCertValFrom(XMLGregorianCalendar value) {
        this.certValFrom = value;
    }

    /**
     * Gets the value of the certValUntil property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCertValUntil() {
        return certValUntil;
    }

    /**
     * Sets the value of the certValUntil property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCertValUntil(XMLGregorianCalendar value) {
        this.certValUntil = value;
    }

    /**
     * Gets the value of the certKeyUsage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertKeyUsage() {
        return certKeyUsage;
    }

    /**
     * Sets the value of the certKeyUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertKeyUsage(String value) {
        this.certKeyUsage = value;
    }

    /**
     * Gets the value of the certSubject property.
     * 
     * @return
     *     possible object is
     *     {@link CertSubject }
     *     
     */
    public CertSubject getCertSubject() {
        return certSubject;
    }

    /**
     * Sets the value of the certSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertSubject }
     *     
     */
    public void setCertSubject(CertSubject value) {
        this.certSubject = value;
    }

    /**
     * Gets the value of the certIssuer property.
     * 
     * @return
     *     possible object is
     *     {@link CertIssuer }
     *     
     */
    public CertIssuer getCertIssuer() {
        return certIssuer;
    }

    /**
     * Sets the value of the certIssuer property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertIssuer }
     *     
     */
    public void setCertIssuer(CertIssuer value) {
        this.certIssuer = value;
    }

    /**
     * Gets the value of the certPublicKey property.
     * 
     * @return
     *     possible object is
     *     {@link CertPublicKey }
     *     
     */
    public CertPublicKey getCertPublicKey() {
        return certPublicKey;
    }

    /**
     * Sets the value of the certPublicKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertPublicKey }
     *     
     */
    public void setCertPublicKey(CertPublicKey value) {
        this.certPublicKey = value;
    }

    /**
     * Gets the value of the certPolicy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certPolicy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCertPolicy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CertPolicy }
     * 
     * 
     */
    public List<CertPolicy> getCertPolicy() {
        if (certPolicy == null) {
            certPolicy = new ArrayList<CertPolicy>();
        }
        return this.certPolicy;
    }

    /**
     * Gets the value of the certRevocation property.
     * 
     * @return
     *     possible object is
     *     {@link CertRevocationInfo }
     *     
     */
    public CertRevocationInfo getCertRevocation() {
        return certRevocation;
    }

    /**
     * Sets the value of the certRevocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertRevocationInfo }
     *     
     */
    public void setCertRevocation(CertRevocationInfo value) {
        this.certRevocation = value;
    }

    /**
     * Gets the value of the certFinger256 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertFinger256() {
        return certFinger256;
    }

    /**
     * Sets the value of the certFinger256 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertFinger256(String value) {
        this.certFinger256 = value;
    }

    /**
     * Gets the value of the certValid property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCertValid() {
        return certValid;
    }

    /**
     * Sets the value of the certValid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCertValid(Boolean value) {
        this.certValid = value;
    }

    /**
     * Gets the value of the certTimeValid property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCertTimeValid() {
        return certTimeValid;
    }

    /**
     * Sets the value of the certTimeValid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCertTimeValid(Boolean value) {
        this.certTimeValid = value;
    }

    /**
     * Gets the value of the certTrusted property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCertTrusted() {
        return certTrusted;
    }

    /**
     * Sets the value of the certTrusted property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCertTrusted(Boolean value) {
        this.certTrusted = value;
    }

    /**
     * Gets the value of the certCert property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertCert() {
        return certCert;
    }

    /**
     * Sets the value of the certCert property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertCert(String value) {
        this.certCert = value;
    }

}
