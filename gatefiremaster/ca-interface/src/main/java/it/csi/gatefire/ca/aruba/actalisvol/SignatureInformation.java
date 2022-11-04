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


/**
 * <p>Java class for signatureInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="signatureInformation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="containerFormat" type="{http://actalisVol/}containerFormat" minOccurs="0"/&gt;
 *         &lt;element name="containsGraphometricInfo" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="corrupted" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="counterSignature" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="counterSignaturesInformation" type="{http://actalisVol/}signatureInformation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="format" type="{http://actalisVol/}signatureFormat" minOccurs="0"/&gt;
 *         &lt;element name="padesSpecific" type="{http://actalisVol/}padesSpecific" minOccurs="0"/&gt;
 *         &lt;element name="refDocs" type="{http://actalisVol/}referenceDoc" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="refsCertId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="signatureDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="signatureInfo" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="signerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="timeStampTokens" type="{http://actalisVol/}signatureInformation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="tstSpecific" type="{http://actalisVol/}tstSpecific" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signatureInformation", propOrder = {
    "containerFormat",
    "containsGraphometricInfo",
    "corrupted",
    "counterSignature",
    "counterSignaturesInformation",
    "format",
    "padesSpecific",
    "refDocs",
    "refsCertId",
    "signatureDate",
    "signatureInfo",
    "signerName",
    "timeStampTokens",
    "tstSpecific"
})
public class SignatureInformation {

    @XmlSchemaType(name = "string")
    protected ContainerFormat containerFormat;
    protected boolean containsGraphometricInfo;
    protected boolean corrupted;
    protected boolean counterSignature;
    @XmlElement(nillable = true)
    protected List<SignatureInformation> counterSignaturesInformation;
    @XmlSchemaType(name = "string")
    protected SignatureFormat format;
    protected PadesSpecific padesSpecific;
    @XmlElement(nillable = true)
    protected List<ReferenceDoc> refDocs;
    protected String refsCertId;
    protected String signatureDate;
    protected byte[] signatureInfo;
    protected String signerName;
    @XmlElement(nillable = true)
    protected List<SignatureInformation> timeStampTokens;
    protected TstSpecific tstSpecific;

    /**
     * Gets the value of the containerFormat property.
     * 
     * @return
     *     possible object is
     *     {@link ContainerFormat }
     *     
     */
    public ContainerFormat getContainerFormat() {
        return containerFormat;
    }

    /**
     * Sets the value of the containerFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContainerFormat }
     *     
     */
    public void setContainerFormat(ContainerFormat value) {
        this.containerFormat = value;
    }

    /**
     * Gets the value of the containsGraphometricInfo property.
     * 
     */
    public boolean isContainsGraphometricInfo() {
        return containsGraphometricInfo;
    }

    /**
     * Sets the value of the containsGraphometricInfo property.
     * 
     */
    public void setContainsGraphometricInfo(boolean value) {
        this.containsGraphometricInfo = value;
    }

    /**
     * Gets the value of the corrupted property.
     * 
     */
    public boolean isCorrupted() {
        return corrupted;
    }

    /**
     * Sets the value of the corrupted property.
     * 
     */
    public void setCorrupted(boolean value) {
        this.corrupted = value;
    }

    /**
     * Gets the value of the counterSignature property.
     * 
     */
    public boolean isCounterSignature() {
        return counterSignature;
    }

    /**
     * Sets the value of the counterSignature property.
     * 
     */
    public void setCounterSignature(boolean value) {
        this.counterSignature = value;
    }

    /**
     * Gets the value of the counterSignaturesInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the counterSignaturesInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCounterSignaturesInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SignatureInformation }
     * 
     * 
     */
    public List<SignatureInformation> getCounterSignaturesInformation() {
        if (counterSignaturesInformation == null) {
            counterSignaturesInformation = new ArrayList<SignatureInformation>();
        }
        return this.counterSignaturesInformation;
    }

    /**
     * Gets the value of the format property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureFormat }
     *     
     */
    public SignatureFormat getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureFormat }
     *     
     */
    public void setFormat(SignatureFormat value) {
        this.format = value;
    }

    /**
     * Gets the value of the padesSpecific property.
     * 
     * @return
     *     possible object is
     *     {@link PadesSpecific }
     *     
     */
    public PadesSpecific getPadesSpecific() {
        return padesSpecific;
    }

    /**
     * Sets the value of the padesSpecific property.
     * 
     * @param value
     *     allowed object is
     *     {@link PadesSpecific }
     *     
     */
    public void setPadesSpecific(PadesSpecific value) {
        this.padesSpecific = value;
    }

    /**
     * Gets the value of the refDocs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refDocs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefDocs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceDoc }
     * 
     * 
     */
    public List<ReferenceDoc> getRefDocs() {
        if (refDocs == null) {
            refDocs = new ArrayList<ReferenceDoc>();
        }
        return this.refDocs;
    }

    /**
     * Gets the value of the refsCertId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefsCertId() {
        return refsCertId;
    }

    /**
     * Sets the value of the refsCertId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefsCertId(String value) {
        this.refsCertId = value;
    }

    /**
     * Gets the value of the signatureDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureDate() {
        return signatureDate;
    }

    /**
     * Sets the value of the signatureDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureDate(String value) {
        this.signatureDate = value;
    }

    /**
     * Gets the value of the signatureInfo property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getSignatureInfo() {
        return signatureInfo;
    }

    /**
     * Sets the value of the signatureInfo property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setSignatureInfo(byte[] value) {
        this.signatureInfo = value;
    }

    /**
     * Gets the value of the signerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignerName() {
        return signerName;
    }

    /**
     * Sets the value of the signerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignerName(String value) {
        this.signerName = value;
    }

    /**
     * Gets the value of the timeStampTokens property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timeStampTokens property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimeStampTokens().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SignatureInformation }
     * 
     * 
     */
    public List<SignatureInformation> getTimeStampTokens() {
        if (timeStampTokens == null) {
            timeStampTokens = new ArrayList<SignatureInformation>();
        }
        return this.timeStampTokens;
    }

    /**
     * Gets the value of the tstSpecific property.
     * 
     * @return
     *     possible object is
     *     {@link TstSpecific }
     *     
     */
    public TstSpecific getTstSpecific() {
        return tstSpecific;
    }

    /**
     * Sets the value of the tstSpecific property.
     * 
     * @param value
     *     allowed object is
     *     {@link TstSpecific }
     *     
     */
    public void setTstSpecific(TstSpecific value) {
        this.tstSpecific = value;
    }

}
