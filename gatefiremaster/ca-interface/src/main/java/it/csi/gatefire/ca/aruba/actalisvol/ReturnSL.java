/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import java.util.ArrayList;
import java.util.List;
import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ReturnSL complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReturnSL"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="operation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="answerCurrentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="verificaAllaData" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="originalFile" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="signers" type="{http://actalisVol/}signerV2" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="subLevel" type="{http://actalisVol/}ReturnSL" minOccurs="0"/&gt;
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
@XmlType(name = "ReturnSL", propOrder = {
    "error",
    "operation",
    "answerCurrentDate",
    "verificaAllaData",
    "originalFile",
    "signers",
    "subLevel",
    "pdfReport",
    "pdfReportPath"
})
public class ReturnSL {

    protected String error;
    protected String operation;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar answerCurrentDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar verificaAllaData;
    @XmlMimeType("application/octet-stream")
    protected DataHandler originalFile;
    @XmlElement(nillable = true)
    protected List<SignerV2> signers;
    protected ReturnSL subLevel;
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
     * Gets the value of the operation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Sets the value of the operation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation(String value) {
        this.operation = value;
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
     * Gets the value of the verificaAllaData property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVerificaAllaData() {
        return verificaAllaData;
    }

    /**
     * Sets the value of the verificaAllaData property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVerificaAllaData(XMLGregorianCalendar value) {
        this.verificaAllaData = value;
    }

    /**
     * Gets the value of the originalFile property.
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getOriginalFile() {
        return originalFile;
    }

    /**
     * Sets the value of the originalFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setOriginalFile(DataHandler value) {
        this.originalFile = value;
    }

    /**
     * Gets the value of the signers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSigners().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SignerV2 }
     * 
     * 
     */
    public List<SignerV2> getSigners() {
        if (signers == null) {
            signers = new ArrayList<SignerV2>();
        }
        return this.signers;
    }

    /**
     * Gets the value of the subLevel property.
     * 
     * @return
     *     possible object is
     *     {@link ReturnSL }
     *     
     */
    public ReturnSL getSubLevel() {
        return subLevel;
    }

    /**
     * Sets the value of the subLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnSL }
     *     
     */
    public void setSubLevel(ReturnSL value) {
        this.subLevel = value;
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
