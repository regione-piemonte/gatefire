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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for markReturn complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="markReturn"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="binaryoutput" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dstPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="return_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stream" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "markReturn", propOrder = {
    "binaryoutput",
    "description",
    "dstPath",
    "returnCode",
    "status",
    "stream"
})
public class MarkReturn {

    protected byte[] binaryoutput;
    protected String description;
    protected String dstPath;
    @XmlElement(name = "return_code")
    protected String returnCode;
    protected String status;
    @XmlMimeType("application/octet-stream")
    protected DataHandler stream;

    /**
     * Gets the value of the binaryoutput property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBinaryoutput() {
        return binaryoutput;
    }

    /**
     * Sets the value of the binaryoutput property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBinaryoutput(byte[] value) {
        this.binaryoutput = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the dstPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDstPath() {
        return dstPath;
    }

    /**
     * Sets the value of the dstPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDstPath(String value) {
        this.dstPath = value;
    }

    /**
     * Gets the value of the returnCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * Sets the value of the returnCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnCode(String value) {
        this.returnCode = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
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

}
