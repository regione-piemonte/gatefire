
package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for certRevocationInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="certRevocationInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="certRevoked" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="revocationReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="revocationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="crlInfo" type="{http://actalisVol/}crlInfo" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "certRevocationInfo", propOrder = {
    "certRevoked",
    "revocationReason",
    "revocationDate",
    "crlInfo"
})
public class CertRevocationInfo {

    protected Boolean certRevoked;
    protected String revocationReason;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar revocationDate;
    protected CrlInfo crlInfo;

    /**
     * Gets the value of the certRevoked property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCertRevoked() {
        return certRevoked;
    }

    /**
     * Sets the value of the certRevoked property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCertRevoked(Boolean value) {
        this.certRevoked = value;
    }

    /**
     * Gets the value of the revocationReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevocationReason() {
        return revocationReason;
    }

    /**
     * Sets the value of the revocationReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevocationReason(String value) {
        this.revocationReason = value;
    }

    /**
     * Gets the value of the revocationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRevocationDate() {
        return revocationDate;
    }

    /**
     * Sets the value of the revocationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRevocationDate(XMLGregorianCalendar value) {
        this.revocationDate = value;
    }

    /**
     * Gets the value of the crlInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CrlInfo }
     *     
     */
    public CrlInfo getCrlInfo() {
        return crlInfo;
    }

    /**
     * Sets the value of the crlInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrlInfo }
     *     
     */
    public void setCrlInfo(CrlInfo value) {
        this.crlInfo = value;
    }

}
