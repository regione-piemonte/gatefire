/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
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
 *         &lt;element name="timeStampToken" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fileTimeStamped" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
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
    "fileTimeStamped"
})
@XmlRootElement(name = "VerifyTimeStampToken")
public class VerifyTimeStampToken {

    @XmlElement(required = true)
    protected String timeStampToken;
    @XmlElement(required = true)
    @XmlMimeType("application/octet-stream")
    protected DataHandler fileTimeStamped;

    /**
     * Gets the value of the timeStampToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeStampToken() {
        return timeStampToken;
    }

    /**
     * Sets the value of the timeStampToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeStampToken(String value) {
        this.timeStampToken = value;
    }

    /**
     * Gets the value of the fileTimeStamped property.
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getFileTimeStamped() {
        return fileTimeStamped;
    }

    /**
     * Sets the value of the fileTimeStamped property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setFileTimeStamped(DataHandler value) {
        this.fileTimeStamped = value;
    }

}
