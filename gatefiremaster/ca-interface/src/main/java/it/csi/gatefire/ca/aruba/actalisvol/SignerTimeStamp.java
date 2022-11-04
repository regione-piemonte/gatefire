/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for signerTimeStamp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="signerTimeStamp"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tsSignVaild" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="tsSignCertV2" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="tsSignDigestAlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tsSignDelib45Valid" type="{http://actalisVol/}delib45Valid" minOccurs="0"/&gt;
 *         &lt;element name="tsDelib45Valid" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="tsDigestMessageImprint" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tsGenTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="tsSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tsPolicyOid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tsTsaName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signerTimeStamp", propOrder = {
    "tsSignVaild",
    "tsSignCertV2",
    "tsSignDigestAlg",
    "tsSignDelib45Valid",
    "tsDelib45Valid",
    "tsDigestMessageImprint",
    "tsGenTime",
    "tsSerialNumber",
    "tsPolicyOid",
    "tsTsaName"
})
@XmlSeeAlso({
    SignerTimeStampV2 .class
})
public class SignerTimeStamp {

    protected Boolean tsSignVaild;
    protected Boolean tsSignCertV2;
    protected String tsSignDigestAlg;
    protected Delib45Valid tsSignDelib45Valid;
    protected Boolean tsDelib45Valid;
    protected String tsDigestMessageImprint;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar tsGenTime;
    protected String tsSerialNumber;
    protected String tsPolicyOid;
    protected String tsTsaName;

    /**
     * Gets the value of the tsSignVaild property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTsSignVaild() {
        return tsSignVaild;
    }

    /**
     * Sets the value of the tsSignVaild property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTsSignVaild(Boolean value) {
        this.tsSignVaild = value;
    }

    /**
     * Gets the value of the tsSignCertV2 property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTsSignCertV2() {
        return tsSignCertV2;
    }

    /**
     * Sets the value of the tsSignCertV2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTsSignCertV2(Boolean value) {
        this.tsSignCertV2 = value;
    }

    /**
     * Gets the value of the tsSignDigestAlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsSignDigestAlg() {
        return tsSignDigestAlg;
    }

    /**
     * Sets the value of the tsSignDigestAlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsSignDigestAlg(String value) {
        this.tsSignDigestAlg = value;
    }

    /**
     * Gets the value of the tsSignDelib45Valid property.
     * 
     * @return
     *     possible object is
     *     {@link Delib45Valid }
     *     
     */
    public Delib45Valid getTsSignDelib45Valid() {
        return tsSignDelib45Valid;
    }

    /**
     * Sets the value of the tsSignDelib45Valid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Delib45Valid }
     *     
     */
    public void setTsSignDelib45Valid(Delib45Valid value) {
        this.tsSignDelib45Valid = value;
    }

    /**
     * Gets the value of the tsDelib45Valid property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTsDelib45Valid() {
        return tsDelib45Valid;
    }

    /**
     * Sets the value of the tsDelib45Valid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTsDelib45Valid(Boolean value) {
        this.tsDelib45Valid = value;
    }

    /**
     * Gets the value of the tsDigestMessageImprint property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsDigestMessageImprint() {
        return tsDigestMessageImprint;
    }

    /**
     * Sets the value of the tsDigestMessageImprint property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsDigestMessageImprint(String value) {
        this.tsDigestMessageImprint = value;
    }

    /**
     * Gets the value of the tsGenTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTsGenTime() {
        return tsGenTime;
    }

    /**
     * Sets the value of the tsGenTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTsGenTime(XMLGregorianCalendar value) {
        this.tsGenTime = value;
    }

    /**
     * Gets the value of the tsSerialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsSerialNumber() {
        return tsSerialNumber;
    }

    /**
     * Sets the value of the tsSerialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsSerialNumber(String value) {
        this.tsSerialNumber = value;
    }

    /**
     * Gets the value of the tsPolicyOid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsPolicyOid() {
        return tsPolicyOid;
    }

    /**
     * Sets the value of the tsPolicyOid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsPolicyOid(String value) {
        this.tsPolicyOid = value;
    }

    /**
     * Gets the value of the tsTsaName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsTsaName() {
        return tsTsaName;
    }

    /**
     * Sets the value of the tsTsaName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsTsaName(String value) {
        this.tsTsaName = value;
    }

}
