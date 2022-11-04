/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.arubasignservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for retriveCredentialReturn complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="retriveCredentialReturn"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="blob" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="return_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="textvalue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retriveCredentialReturn", propOrder = {
    "blob",
    "description",
    "returnCode",
    "status",
    "textvalue"
})
public class RetriveCredentialReturn {

    protected byte[] blob;
    protected String description;
    @XmlElement(name = "return_code")
    protected String returnCode;
    protected String status;
    protected String textvalue;

    /**
     * Gets the value of the blob property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBlob() {
        return blob;
    }

    /**
     * Sets the value of the blob property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBlob(byte[] value) {
        this.blob = value;
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
     * Gets the value of the textvalue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextvalue() {
        return textvalue;
    }

    /**
     * Sets the value of the textvalue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextvalue(String value) {
        this.textvalue = value;
    }

}
