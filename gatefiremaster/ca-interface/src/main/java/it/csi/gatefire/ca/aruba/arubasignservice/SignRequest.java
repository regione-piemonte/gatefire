/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.arubasignservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for signRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="signRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bynaryinput" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="certID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dstNmae" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identity" type="{http://arubasignservice.arubapec.it/}auth"/&gt;
 *         &lt;element name="notity_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="notitymail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="srcName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="transport" type="{http://arubasignservice.arubapec.it/}typeTransport"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signRequest", propOrder = {
    "bynaryinput",
    "certID",
    "dstNmae",
    "identity",
    "notityId",
    "notitymail",
    "srcName",
    "transport"
})
public class SignRequest {

    protected byte[] bynaryinput;
    @XmlElement(required = true)
    protected String certID;
    protected String dstNmae;
    @XmlElement(required = true)
    protected Auth identity;
    @XmlElement(name = "notity_id")
    protected String notityId;
    protected String notitymail;
    protected String srcName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TypeTransport transport;

    /**
     * Gets the value of the bynaryinput property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBynaryinput() {
        return bynaryinput;
    }

    /**
     * Sets the value of the bynaryinput property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBynaryinput(byte[] value) {
        this.bynaryinput = value;
    }

    /**
     * Gets the value of the certID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertID() {
        return certID;
    }

    /**
     * Sets the value of the certID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertID(String value) {
        this.certID = value;
    }

    /**
     * Gets the value of the dstNmae property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDstNmae() {
        return dstNmae;
    }

    /**
     * Sets the value of the dstNmae property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDstNmae(String value) {
        this.dstNmae = value;
    }

    /**
     * Gets the value of the identity property.
     * 
     * @return
     *     possible object is
     *     {@link Auth }
     *     
     */
    public Auth getIdentity() {
        return identity;
    }

    /**
     * Sets the value of the identity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Auth }
     *     
     */
    public void setIdentity(Auth value) {
        this.identity = value;
    }

    /**
     * Gets the value of the notityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotityId() {
        return notityId;
    }

    /**
     * Sets the value of the notityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotityId(String value) {
        this.notityId = value;
    }

    /**
     * Gets the value of the notitymail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotitymail() {
        return notitymail;
    }

    /**
     * Sets the value of the notitymail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotitymail(String value) {
        this.notitymail = value;
    }

    /**
     * Gets the value of the srcName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcName() {
        return srcName;
    }

    /**
     * Sets the value of the srcName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcName(String value) {
        this.srcName = value;
    }

    /**
     * Gets the value of the transport property.
     * 
     * @return
     *     possible object is
     *     {@link TypeTransport }
     *     
     */
    public TypeTransport getTransport() {
        return transport;
    }

    /**
     * Sets the value of the transport property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeTransport }
     *     
     */
    public void setTransport(TypeTransport value) {
        this.transport = value;
    }

}
