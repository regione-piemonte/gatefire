/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for refCRL complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refCRL"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idps" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="issuerDn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nextUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="updateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="valid" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="verifiedSerial" type="{http://actalisVol/}verifiedSerial" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refCRL", propOrder = {
    "id",
    "idps",
    "issuerDn",
    "nextUpdate",
    "updateDate",
    "valid",
    "verifiedSerial"
})
public class RefCRL {

    protected String id;
    @XmlElement(nillable = true)
    protected List<String> idps;
    protected String issuerDn;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar nextUpdate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateDate;
    protected boolean valid;
    @XmlElement(nillable = true)
    protected List<VerifiedSerial> verifiedSerial;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the idps property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idps property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdps().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIdps() {
        if (idps == null) {
            idps = new ArrayList<String>();
        }
        return this.idps;
    }

    /**
     * Gets the value of the issuerDn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuerDn() {
        return issuerDn;
    }

    /**
     * Sets the value of the issuerDn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuerDn(String value) {
        this.issuerDn = value;
    }

    /**
     * Gets the value of the nextUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNextUpdate() {
        return nextUpdate;
    }

    /**
     * Sets the value of the nextUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNextUpdate(XMLGregorianCalendar value) {
        this.nextUpdate = value;
    }

    /**
     * Gets the value of the updateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets the value of the updateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateDate(XMLGregorianCalendar value) {
        this.updateDate = value;
    }

    /**
     * Gets the value of the valid property.
     * 
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Sets the value of the valid property.
     * 
     */
    public void setValid(boolean value) {
        this.valid = value;
    }

    /**
     * Gets the value of the verifiedSerial property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the verifiedSerial property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVerifiedSerial().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VerifiedSerial }
     * 
     * 
     */
    public List<VerifiedSerial> getVerifiedSerial() {
        if (verifiedSerial == null) {
            verifiedSerial = new ArrayList<VerifiedSerial>();
        }
        return this.verifiedSerial;
    }

}
