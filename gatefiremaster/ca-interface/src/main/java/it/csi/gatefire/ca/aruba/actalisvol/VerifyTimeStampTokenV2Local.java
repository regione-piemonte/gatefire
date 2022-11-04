/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="timeStampToken" type="{http://actalisVol/}fileInfo"/&gt;
 *         &lt;element name="fileInfo" type="{http://actalisVol/}fileInfo"/&gt;
 *         &lt;element name="pdfReport" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "timeStampToken",
    "fileInfo",
    "pdfReport"
})
@XmlRootElement(name = "VerifyTimeStampTokenV2Local")
public class VerifyTimeStampTokenV2Local {

    @XmlElement(required = true)
    protected FileInfo timeStampToken;
    @XmlElement(required = true)
    protected FileInfo fileInfo;
    protected boolean pdfReport;

    /**
     * Gets the value of the timeStampToken property.
     * 
     * @return
     *     possible object is
     *     {@link FileInfo }
     *     
     */
    public FileInfo getTimeStampToken() {
        return timeStampToken;
    }

    /**
     * Sets the value of the timeStampToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileInfo }
     *     
     */
    public void setTimeStampToken(FileInfo value) {
        this.timeStampToken = value;
    }

    /**
     * Gets the value of the fileInfo property.
     * 
     * @return
     *     possible object is
     *     {@link FileInfo }
     *     
     */
    public FileInfo getFileInfo() {
        return fileInfo;
    }

    /**
     * Sets the value of the fileInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileInfo }
     *     
     */
    public void setFileInfo(FileInfo value) {
        this.fileInfo = value;
    }

    /**
     * Gets the value of the pdfReport property.
     * 
     */
    public boolean isPdfReport() {
        return pdfReport;
    }

    /**
     * Sets the value of the pdfReport property.
     * 
     */
    public void setPdfReport(boolean value) {
        this.pdfReport = value;
    }

}
