/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.arubasignservice;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for signRequestV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="signRequestV2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="binaryinput" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="certID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identity" type="{http://arubasignservice.arubapec.it/}auth"/&gt;
 *         &lt;element name="notify_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="notifymail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="profile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="requiredmark" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="session_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="signingTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="srcName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stream" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="transport" type="{http://arubasignservice.arubapec.it/}typeTransport"/&gt;
 *         &lt;element name="tsa_identity" type="{http://arubasignservice.arubapec.it/}tsaAuth" minOccurs="0"/&gt;
 *         &lt;element name="signatureLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signRequestV2", propOrder = {
    "binaryinput",
    "certID",
    "dstName",
    "identity",
    "notifyId",
    "notifymail",
    "profile",
    "requiredmark",
    "sessionId",
    "signingTime",
    "srcName",
    "stream",
    "transport",
    "tsaIdentity",
    "signatureLevel"
})
public class SignRequestV2 {

    protected byte[] binaryinput;
    @XmlElement(required = true)
    protected String certID;
    protected String dstName;
    @XmlElement(required = true)
    protected Auth identity;
    @XmlElement(name = "notify_id")
    protected String notifyId;
    protected String notifymail;
    protected String profile;
    protected boolean requiredmark;
    @XmlElement(name = "session_id")
    protected String sessionId;
    protected String signingTime;
    protected String srcName;
    @XmlMimeType("application/octet-stream")
    protected DataHandler stream;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TypeTransport transport;
    @XmlElement(name = "tsa_identity")
    protected TsaAuth tsaIdentity;
    protected String signatureLevel;

    /**
     * Gets the value of the binaryinput property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBinaryinput() {
        return binaryinput;
    }

    /**
     * Sets the value of the binaryinput property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBinaryinput(byte[] value) {
        this.binaryinput = value;
    }

    /**
     * Gets the value of the certID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertID() {
        return certID;
    }

    /**
     * Sets the value of the certID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertID(String value) {
        this.certID = value;
    }

    /**
     * Gets the value of the dstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDstName() {
        return dstName;
    }

    /**
     * Sets the value of the dstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDstName(String value) {
        this.dstName = value;
    }

    /**
     * Gets the value of the identity property.
     * 
     * @return
     *     possible object is
     *     {@link Auth }
     *     
     */
    public Auth getIdentity() {
        return identity;
    }

    /**
     * Sets the value of the identity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Auth }
     *     
     */
    public void setIdentity(Auth value) {
        this.identity = value;
    }

    /**
     * Gets the value of the notifyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotifyId() {
        return notifyId;
    }

    /**
     * Sets the value of the notifyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotifyId(String value) {
        this.notifyId = value;
    }

    /**
     * Gets the value of the notifymail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotifymail() {
        return notifymail;
    }

    /**
     * Sets the value of the notifymail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotifymail(String value) {
        this.notifymail = value;
    }

    /**
     * Gets the value of the profile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfile() {
        return profile;
    }

    /**
     * Sets the value of the profile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfile(String value) {
        this.profile = value;
    }

    /**
     * Gets the value of the requiredmark property.
     * 
     */
    public boolean isRequiredmark() {
        return requiredmark;
    }

    /**
     * Sets the value of the requiredmark property.
     * 
     */
    public void setRequiredmark(boolean value) {
        this.requiredmark = value;
    }

    /**
     * Gets the value of the sessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the value of the sessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionId(String value) {
        this.sessionId = value;
    }

    /**
     * Gets the value of the signingTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigningTime() {
        return signingTime;
    }

    /**
     * Sets the value of the signingTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigningTime(String value) {
        this.signingTime = value;
    }

    /**
     * Gets the value of the srcName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcName() {
        return srcName;
    }

    /**
     * Sets the value of the srcName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcName(String value) {
        this.srcName = value;
    }

    /**
     * Gets the value of the stream property.
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getStream() {
        return stream;
    }

    /**
     * Sets the value of the stream property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setStream(DataHandler value) {
        this.stream = value;
    }

    /**
     * Gets the value of the transport property.
     * 
     * @return
     *     possible object is
     *     {@link TypeTransport }
     *     
     */
    public TypeTransport getTransport() {
        return transport;
    }

    /**
     * Sets the value of the transport property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeTransport }
     *     
     */
    public void setTransport(TypeTransport value) {
        this.transport = value;
    }

    /**
     * Gets the value of the tsaIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link TsaAuth }
     *     
     */
    public TsaAuth getTsaIdentity() {
        return tsaIdentity;
    }

    /**
     * Sets the value of the tsaIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link TsaAuth }
     *     
     */
    public void setTsaIdentity(TsaAuth value) {
        this.tsaIdentity = value;
    }

    /**
     * Gets the value of the signatureLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureLevel() {
        return signatureLevel;
    }

    /**
     * Sets the value of the signatureLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureLevel(String value) {
        this.signatureLevel = value;
    }

}
