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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for signer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="signer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sigType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sigValidation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sigValidationMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="signatureInfo" type="{http://actalisVol/}signatureInfo" minOccurs="0"/&gt;
 *         &lt;element name="certificate" type="{http://actalisVol/}certificateVOL" minOccurs="0"/&gt;
 *         &lt;element name="timeStamp" type="{http://actalisVol/}signerTimeStamp" minOccurs="0"/&gt;
 *         &lt;element name="counterSignature" type="{http://actalisVol/}signer" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signer", propOrder = {
    "sigType",
    "sigValidation",
    "sigValidationMessage",
    "signatureInfo",
    "certificate",
    "timeStamp",
    "counterSignature"
})
@XmlSeeAlso({
    SignerV2 .class
})
public class Signer {

    protected String sigType;
    protected String sigValidation;
    protected String sigValidationMessage;
    protected SignatureInfo signatureInfo;
    protected CertificateVOL certificate;
    protected SignerTimeStamp timeStamp;
    @XmlElement(nillable = true)
    protected List<Signer> counterSignature;

    /**
     * Gets the value of the sigType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigType() {
        return sigType;
    }

    /**
     * Sets the value of the sigType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigType(String value) {
        this.sigType = value;
    }

    /**
     * Gets the value of the sigValidation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigValidation() {
        return sigValidation;
    }

    /**
     * Sets the value of the sigValidation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigValidation(String value) {
        this.sigValidation = value;
    }

    /**
     * Gets the value of the sigValidationMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigValidationMessage() {
        return sigValidationMessage;
    }

    /**
     * Sets the value of the sigValidationMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigValidationMessage(String value) {
        this.sigValidationMessage = value;
    }

    /**
     * Gets the value of the signatureInfo property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureInfo }
     *     
     */
    public SignatureInfo getSignatureInfo() {
        return signatureInfo;
    }

    /**
     * Sets the value of the signatureInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureInfo }
     *     
     */
    public void setSignatureInfo(SignatureInfo value) {
        this.signatureInfo = value;
    }

    /**
     * Gets the value of the certificate property.
     * 
     * @return
     *     possible object is
     *     {@link CertificateVOL }
     *     
     */
    public CertificateVOL getCertificate() {
        return certificate;
    }

    /**
     * Sets the value of the certificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateVOL }
     *     
     */
    public void setCertificate(CertificateVOL value) {
        this.certificate = value;
    }

    /**
     * Gets the value of the timeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link SignerTimeStamp }
     *     
     */
    public SignerTimeStamp getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the value of the timeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignerTimeStamp }
     *     
     */
    public void setTimeStamp(SignerTimeStamp value) {
        this.timeStamp = value;
    }

    /**
     * Gets the value of the counterSignature property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the counterSignature property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCounterSignature().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Signer }
     * 
     * 
     */
    public List<Signer> getCounterSignature() {
        if (counterSignature == null) {
            counterSignature = new ArrayList<Signer>();
        }
        return this.counterSignature;
    }

}
