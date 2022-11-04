//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.03.08 at 01:14:07 PM CET 
//


package it.csi.gatefire.ca.infocert;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "serial",
    "subject",
    "issuer",
    "directoryAttributes",
    "policyInformationList",
    "qcStatements",
    "certNotBefore",
    "certNotAfter",
    "certificate",
    "signedAttributes",
    "signingTime",
    "signatureTimeStamp",
    "digestAlgorithm",
    "crlThisUpdateOrOcspThisUpdate",
    "crlExpiredOrOcspExpired",
    "caCertRevoked",
    "crlRevocationDateOrOcspRevocationDate",
    "crlHoldDateOrOcspHoldDate",
    "crlInvalidSinceOrOcspInvalidSince",
    "caCertExpired",
    "expiredCertsOnCRL",
    "verificationTime",
    "certExpired",
    "errorCode",
    "errorMessage",
    "status",
    "countersigner"
})
@XmlRootElement(name = "countersigner")
public class Countersigner {

    @XmlAttribute(name = "cadesCompliant")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String cadesCompliant;
    protected String serial;
    protected Subject subject;
    protected Issuer issuer;
    protected DirectoryAttributes directoryAttributes;
    protected PolicyInformationList policyInformationList;
    protected QcStatements qcStatements;
    protected String certNotBefore;
    protected String certNotAfter;
    protected String certificate;
    protected SignedAttributes signedAttributes;
    protected SigningTime signingTime;
    protected SignatureTimeStamp signatureTimeStamp;
    protected String digestAlgorithm;
    @XmlElements({
        @XmlElement(name = "crlThisUpdate", type = CrlThisUpdate.class),
        @XmlElement(name = "ocspThisUpdate", type = OcspThisUpdate.class)
    })
    protected List<Object> crlThisUpdateOrOcspThisUpdate;
    @XmlElements({
        @XmlElement(name = "crlExpired", type = CrlExpired.class),
        @XmlElement(name = "ocspExpired", type = OcspExpired.class)
    })
    protected List<Object> crlExpiredOrOcspExpired;
    protected String caCertRevoked;
    @XmlElements({
        @XmlElement(name = "crlRevocationDate", type = CrlRevocationDate.class),
        @XmlElement(name = "ocspRevocationDate", type = OcspRevocationDate.class)
    })
    protected List<Object> crlRevocationDateOrOcspRevocationDate;
    @XmlElements({
        @XmlElement(name = "crlHoldDate", type = CrlHoldDate.class),
        @XmlElement(name = "ocspHoldDate", type = OcspHoldDate.class)
    })
    protected List<Object> crlHoldDateOrOcspHoldDate;
    @XmlElements({
        @XmlElement(name = "crlInvalidSince", type = CrlInvalidSince.class),
        @XmlElement(name = "ocspInvalidSince", type = OcspInvalidSince.class)
    })
    protected List<Object> crlInvalidSinceOrOcspInvalidSince;
    protected String caCertExpired;
    protected String expiredCertsOnCRL;
    protected String verificationTime;
    protected String certExpired;
    protected ErrorCode errorCode;
    protected ErrorMessage errorMessage;
    @XmlElement(required = true)
    protected Status status;
    protected List<Countersigner> countersigner;

    /**
     * Gets the value of the cadesCompliant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCadesCompliant() {
        return cadesCompliant;
    }

    /**
     * Sets the value of the cadesCompliant property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCadesCompliant(String value) {
        this.cadesCompliant = value;
    }

    /**
     * Gets the value of the serial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerial() {
        return serial;
    }

    /**
     * Sets the value of the serial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerial(String value) {
        this.serial = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link Subject }
     *     
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link Subject }
     *     
     */
    public void setSubject(Subject value) {
        this.subject = value;
    }

    /**
     * Gets the value of the issuer property.
     * 
     * @return
     *     possible object is
     *     {@link Issuer }
     *     
     */
    public Issuer getIssuer() {
        return issuer;
    }

    /**
     * Sets the value of the issuer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Issuer }
     *     
     */
    public void setIssuer(Issuer value) {
        this.issuer = value;
    }

    /**
     * Gets the value of the directoryAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link DirectoryAttributes }
     *     
     */
    public DirectoryAttributes getDirectoryAttributes() {
        return directoryAttributes;
    }

    /**
     * Sets the value of the directoryAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectoryAttributes }
     *     
     */
    public void setDirectoryAttributes(DirectoryAttributes value) {
        this.directoryAttributes = value;
    }

    /**
     * Gets the value of the policyInformationList property.
     * 
     * @return
     *     possible object is
     *     {@link PolicyInformationList }
     *     
     */
    public PolicyInformationList getPolicyInformationList() {
        return policyInformationList;
    }

    /**
     * Sets the value of the policyInformationList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PolicyInformationList }
     *     
     */
    public void setPolicyInformationList(PolicyInformationList value) {
        this.policyInformationList = value;
    }

    /**
     * Gets the value of the qcStatements property.
     * 
     * @return
     *     possible object is
     *     {@link QcStatements }
     *     
     */
    public QcStatements getQcStatements() {
        return qcStatements;
    }

    /**
     * Sets the value of the qcStatements property.
     * 
     * @param value
     *     allowed object is
     *     {@link QcStatements }
     *     
     */
    public void setQcStatements(QcStatements value) {
        this.qcStatements = value;
    }

    /**
     * Gets the value of the certNotBefore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertNotBefore() {
        return certNotBefore;
    }

    /**
     * Sets the value of the certNotBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertNotBefore(String value) {
        this.certNotBefore = value;
    }

    /**
     * Gets the value of the certNotAfter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertNotAfter() {
        return certNotAfter;
    }

    /**
     * Sets the value of the certNotAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertNotAfter(String value) {
        this.certNotAfter = value;
    }

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
     * Gets the value of the signedAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link SignedAttributes }
     *     
     */
    public SignedAttributes getSignedAttributes() {
        return signedAttributes;
    }

    /**
     * Sets the value of the signedAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignedAttributes }
     *     
     */
    public void setSignedAttributes(SignedAttributes value) {
        this.signedAttributes = value;
    }

    /**
     * Gets the value of the signingTime property.
     * 
     * @return
     *     possible object is
     *     {@link SigningTime }
     *     
     */
    public SigningTime getSigningTime() {
        return signingTime;
    }

    /**
     * Sets the value of the signingTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link SigningTime }
     *     
     */
    public void setSigningTime(SigningTime value) {
        this.signingTime = value;
    }

    /**
     * Gets the value of the signatureTimeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureTimeStamp }
     *     
     */
    public SignatureTimeStamp getSignatureTimeStamp() {
        return signatureTimeStamp;
    }

    /**
     * Sets the value of the signatureTimeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureTimeStamp }
     *     
     */
    public void setSignatureTimeStamp(SignatureTimeStamp value) {
        this.signatureTimeStamp = value;
    }

    /**
     * Gets the value of the digestAlgorithm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDigestAlgorithm() {
        return digestAlgorithm;
    }

    /**
     * Sets the value of the digestAlgorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDigestAlgorithm(String value) {
        this.digestAlgorithm = value;
    }

    /**
     * Gets the value of the crlThisUpdateOrOcspThisUpdate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crlThisUpdateOrOcspThisUpdate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrlThisUpdateOrOcspThisUpdate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrlThisUpdate }
     * {@link OcspThisUpdate }
     * 
     * 
     */
    public List<Object> getCrlThisUpdateOrOcspThisUpdate() {
        if (crlThisUpdateOrOcspThisUpdate == null) {
            crlThisUpdateOrOcspThisUpdate = new ArrayList<Object>();
        }
        return this.crlThisUpdateOrOcspThisUpdate;
    }

    /**
     * Gets the value of the crlExpiredOrOcspExpired property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crlExpiredOrOcspExpired property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrlExpiredOrOcspExpired().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrlExpired }
     * {@link OcspExpired }
     * 
     * 
     */
    public List<Object> getCrlExpiredOrOcspExpired() {
        if (crlExpiredOrOcspExpired == null) {
            crlExpiredOrOcspExpired = new ArrayList<Object>();
        }
        return this.crlExpiredOrOcspExpired;
    }

    /**
     * Gets the value of the caCertRevoked property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaCertRevoked() {
        return caCertRevoked;
    }

    /**
     * Sets the value of the caCertRevoked property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaCertRevoked(String value) {
        this.caCertRevoked = value;
    }

    /**
     * Gets the value of the crlRevocationDateOrOcspRevocationDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crlRevocationDateOrOcspRevocationDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrlRevocationDateOrOcspRevocationDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrlRevocationDate }
     * {@link OcspRevocationDate }
     * 
     * 
     */
    public List<Object> getCrlRevocationDateOrOcspRevocationDate() {
        if (crlRevocationDateOrOcspRevocationDate == null) {
            crlRevocationDateOrOcspRevocationDate = new ArrayList<Object>();
        }
        return this.crlRevocationDateOrOcspRevocationDate;
    }

    /**
     * Gets the value of the crlHoldDateOrOcspHoldDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crlHoldDateOrOcspHoldDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrlHoldDateOrOcspHoldDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrlHoldDate }
     * {@link OcspHoldDate }
     * 
     * 
     */
    public List<Object> getCrlHoldDateOrOcspHoldDate() {
        if (crlHoldDateOrOcspHoldDate == null) {
            crlHoldDateOrOcspHoldDate = new ArrayList<Object>();
        }
        return this.crlHoldDateOrOcspHoldDate;
    }

    /**
     * Gets the value of the crlInvalidSinceOrOcspInvalidSince property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crlInvalidSinceOrOcspInvalidSince property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrlInvalidSinceOrOcspInvalidSince().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrlInvalidSince }
     * {@link OcspInvalidSince }
     * 
     * 
     */
    public List<Object> getCrlInvalidSinceOrOcspInvalidSince() {
        if (crlInvalidSinceOrOcspInvalidSince == null) {
            crlInvalidSinceOrOcspInvalidSince = new ArrayList<Object>();
        }
        return this.crlInvalidSinceOrOcspInvalidSince;
    }

    /**
     * Gets the value of the caCertExpired property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaCertExpired() {
        return caCertExpired;
    }

    /**
     * Sets the value of the caCertExpired property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaCertExpired(String value) {
        this.caCertExpired = value;
    }

    /**
     * Gets the value of the expiredCertsOnCRL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpiredCertsOnCRL() {
        return expiredCertsOnCRL;
    }

    /**
     * Sets the value of the expiredCertsOnCRL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpiredCertsOnCRL(String value) {
        this.expiredCertsOnCRL = value;
    }

    /**
     * Gets the value of the verificationTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerificationTime() {
        return verificationTime;
    }

    /**
     * Sets the value of the verificationTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerificationTime(String value) {
        this.verificationTime = value;
    }

    /**
     * Gets the value of the certExpired property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertExpired() {
        return certExpired;
    }

    /**
     * Sets the value of the certExpired property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertExpired(String value) {
        this.certExpired = value;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorCode }
     *     
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorCode }
     *     
     */
    public void setErrorCode(ErrorCode value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the errorMessage property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorMessage }
     *     
     */
    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorMessage }
     *     
     */
    public void setErrorMessage(ErrorMessage value) {
        this.errorMessage = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Status }
     *     
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Status }
     *     
     */
    public void setStatus(Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the countersigner property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the countersigner property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCountersigner().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Countersigner }
     * 
     * 
     */
    public List<Countersigner> getCountersigner() {
        if (countersigner == null) {
            countersigner = new ArrayList<Countersigner>();
        }
        return this.countersigner;
    }

}