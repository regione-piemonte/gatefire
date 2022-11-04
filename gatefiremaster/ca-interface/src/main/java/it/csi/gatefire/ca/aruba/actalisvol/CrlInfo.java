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
 * <p>Java class for crlInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crlInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="crlStatus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="clStatusInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="crlThisUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="crlNextUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="serial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="expiredCertsOnCrl" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crlInfo", propOrder = {
    "crlStatus",
    "clStatusInfo",
    "crlThisUpdate",
    "crlNextUpdate",
    "serial",
    "expiredCertsOnCrl"
})
public class CrlInfo {

    protected Boolean crlStatus;
    protected String clStatusInfo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar crlThisUpdate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar crlNextUpdate;
    protected String serial;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expiredCertsOnCrl;

    /**
     * Gets the value of the crlStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCrlStatus() {
        return crlStatus;
    }

    /**
     * Sets the value of the crlStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCrlStatus(Boolean value) {
        this.crlStatus = value;
    }

    /**
     * Gets the value of the clStatusInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClStatusInfo() {
        return clStatusInfo;
    }

    /**
     * Sets the value of the clStatusInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClStatusInfo(String value) {
        this.clStatusInfo = value;
    }

    /**
     * Gets the value of the crlThisUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCrlThisUpdate() {
        return crlThisUpdate;
    }

    /**
     * Sets the value of the crlThisUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCrlThisUpdate(XMLGregorianCalendar value) {
        this.crlThisUpdate = value;
    }

    /**
     * Gets the value of the crlNextUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCrlNextUpdate() {
        return crlNextUpdate;
    }

    /**
     * Sets the value of the crlNextUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCrlNextUpdate(XMLGregorianCalendar value) {
        this.crlNextUpdate = value;
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
     * Gets the value of the expiredCertsOnCrl property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpiredCertsOnCrl() {
        return expiredCertsOnCrl;
    }

    /**
     * Sets the value of the expiredCertsOnCrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiredCertsOnCrl(XMLGregorianCalendar value) {
        this.expiredCertsOnCrl = value;
    }

}
