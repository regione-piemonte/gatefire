/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fileInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fileInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fileExtension" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="autoDetectType" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fileInfo", propOrder = {
    "fileName",
    "fileExtension",
    "autoDetectType"
})
public class FileInfo {

    @XmlElement(required = true)
    protected String fileName;
    @XmlElement(required = true)
    protected String fileExtension;
    @XmlElement(defaultValue = "false")
    protected boolean autoDetectType;

    /**
     * Gets the value of the fileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the value of the fileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * Gets the value of the fileExtension property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileExtension() {
        return fileExtension;
    }

    /**
     * Sets the value of the fileExtension property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileExtension(String value) {
        this.fileExtension = value;
    }

    /**
     * Gets the value of the autoDetectType property.
     * 
     */
    public boolean isAutoDetectType() {
        return autoDetectType;
    }

    /**
     * Sets the value of the autoDetectType property.
     * 
     */
    public void setAutoDetectType(boolean value) {
        this.autoDetectType = value;
    }

}
