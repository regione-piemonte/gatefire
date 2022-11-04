
package it.csi.gatefire.ca.aruba.arubasignservice;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for verifyRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="verifyRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="binaryinput" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="dstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="notity_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="notitymail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="srcName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stream" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="transport" type="{http://arubasignservice.arubapec.it/}typeTransport" minOccurs="0"/&gt;
 *         &lt;element name="type" type="{http://arubasignservice.arubapec.it/}documentType" minOccurs="0"/&gt;
 *         &lt;element name="verdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verifyRequest", propOrder = {
    "binaryinput",
    "dstName",
    "notityId",
    "notitymail",
    "srcName",
    "stream",
    "transport",
    "type",
    "verdate"
})
public class VerifyRequest {

    protected byte[] binaryinput;
    protected String dstName;
    @XmlElement(name = "notity_id")
    protected String notityId;
    protected String notitymail;
    protected String srcName;
    @XmlMimeType("application/octet-stream")
    protected DataHandler stream;
    @XmlSchemaType(name = "string")
    protected TypeTransport transport;
    @XmlSchemaType(name = "string")
    protected DocumentType type;
    protected String verdate;

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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentType }
     *     
     */
    public DocumentType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentType }
     *     
     */
    public void setType(DocumentType value) {
        this.type = value;
    }

    /**
     * Gets the value of the verdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerdate() {
        return verdate;
    }

    /**
     * Sets the value of the verdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerdate(String value) {
        this.verdate = value;
    }

}
