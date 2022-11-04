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
 * <p>Java class for ReturnTimeStampValidationSL complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReturnTimeStampValidationSL"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sigValidation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sigValidationMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="answerCurrentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="certificate" type="{http://actalisVol/}certificateVOL" minOccurs="0"/&gt;
 *         &lt;element name="timeStamp" type="{http://actalisVol/}signerTimeStampV2" minOccurs="0"/&gt;
 *         &lt;element name="pdfReport" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="pdfReportPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReturnTimeStampValidationSL", propOrder = {
    "error",
    "sigValidation",
    "sigValidationMessage",
    "answerCurrentDate",
    "certificate",
    "timeStamp",
    "pdfReport",
    "pdfReportPath"
})
public class ReturnTimeStampValidationSL {

    protected String error;
    protected String sigValidation;
    protected String sigValidationMessage;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar answerCurrentDate;
    protected CertificateVOL certificate;
    protected SignerTimeStampV2 timeStamp;
    protected byte[] pdfReport;
    protected String pdfReportPath;

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setError(String value) {
        this.error = value;
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
     * Gets the value of the answerCurrentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAnswerCurrentDate() {
        return answerCurrentDate;
    }

    /**
     * Sets the value of the answerCurrentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAnswerCurrentDate(XMLGregorianCalendar value) {
        this.answerCurrentDate = value;
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
     *     {@link SignerTimeStampV2 }
     *     
     */
    public SignerTimeStampV2 getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the value of the timeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignerTimeStampV2 }
     *     
     */
    public void setTimeStamp(SignerTimeStampV2 value) {
        this.timeStamp = value;
    }

    /**
     * Gets the value of the pdfReport property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPdfReport() {
        return pdfReport;
    }

    /**
     * Sets the value of the pdfReport property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPdfReport(byte[] value) {
        this.pdfReport = value;
    }

    /**
     * Gets the value of the pdfReportPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdfReportPath() {
        return pdfReportPath;
    }

    /**
     * Sets the value of the pdfReportPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdfReportPath(String value) {
        this.pdfReportPath = value;
    }

}
