/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.arubasignservice;

import java.util.ArrayList;
import java.util.List;
import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for encryptedEnvelopReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="encryptedEnvelopReq"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="transport" type="{http://arubasignservice.arubapec.it/}typeTransport"/&gt;
 *         &lt;element name="binaryinput" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="srcName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="notifymail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="notify_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stream" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="recipients" type="{http://www.w3.org/2001/XMLSchema}base64Binary" maxOccurs="unbounded"/&gt;
 *         &lt;element name="algorithm" type="{http://arubasignservice.arubapec.it/}encryptionAlgorithm"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "encryptedEnvelopReq", propOrder = {
    "user",
    "password",
    "transport",
    "binaryinput",
    "srcName",
    "dstName",
    "notifymail",
    "notifyId",
    "stream",
    "recipients",
    "algorithm"
})
public class EncryptedEnvelopReq {

    @XmlElement(required = true)
    protected String user;
    @XmlElement(required = true)
    protected String password;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TypeTransport transport;
    protected byte[] binaryinput;
    protected String srcName;
    protected String dstName;
    protected String notifymail;
    @XmlElement(name = "notify_id")
    protected String notifyId;
    @XmlMimeType("application/octet-stream")
    protected DataHandler stream;
    @XmlElement(required = true)
    protected List<byte[]> recipients;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EncryptionAlgorithm algorithm;

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
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

    /**
     * Gets the value of the binaryinput property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBinaryinput() {
        return binaryinput;
    }

    /**
     * Sets the value of the binaryinput property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBinaryinput(byte[] value) {
        this.binaryinput = value;
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
     * Gets the value of the dstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDstName() {
        return dstName;
    }

    /**
     * Sets the value of the dstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDstName(String value) {
        this.dstName = value;
    }

    /**
     * Gets the value of the notifymail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotifymail() {
        return notifymail;
    }

    /**
     * Sets the value of the notifymail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotifymail(String value) {
        this.notifymail = value;
    }

    /**
     * Gets the value of the notifyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotifyId() {
        return notifyId;
    }

    /**
     * Sets the value of the notifyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotifyId(String value) {
        this.notifyId = value;
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

    /**
     * Gets the value of the recipients property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recipients property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecipients().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * byte[]
     * 
     */
    public List<byte[]> getRecipients() {
        if (recipients == null) {
            recipients = new ArrayList<byte[]>();
        }
        return this.recipients;
    }

    /**
     * Gets the value of the algorithm property.
     * 
     * @return
     *     possible object is
     *     {@link EncryptionAlgorithm }
     *     
     */
    public EncryptionAlgorithm getAlgorithm() {
        return algorithm;
    }

    /**
     * Sets the value of the algorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptionAlgorithm }
     *     
     */
    public void setAlgorithm(EncryptionAlgorithm value) {
        this.algorithm = value;
    }

}
