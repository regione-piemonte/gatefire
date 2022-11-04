
package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for VerificationV3Transient complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VerificationV3Transient"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="transientDocument" type="{http://actalisVol/}transientDocument"/&gt;
 *         &lt;element name="verifAllaData" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="pdfReport" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="profile" type="{http://actalisVol/}validationProfile" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerificationV3Transient", propOrder = {
    "transientDocument",
    "verifAllaData",
    "pdfReport",
    "profile"
})
public class VerificationV3Transient {

    @XmlElement(required = true)
    protected TransientDocument transientDocument;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar verifAllaData;
    protected boolean pdfReport;
    @XmlSchemaType(name = "string")
    protected ValidationProfile profile;

    /**
     * Gets the value of the transientDocument property.
     * 
     * @return
     *     possible object is
     *     {@link TransientDocument }
     *     
     */
    public TransientDocument getTransientDocument() {
        return transientDocument;
    }

    /**
     * Sets the value of the transientDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransientDocument }
     *     
     */
    public void setTransientDocument(TransientDocument value) {
        this.transientDocument = value;
    }

    /**
     * Gets the value of the verifAllaData property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVerifAllaData() {
        return verifAllaData;
    }

    /**
     * Sets the value of the verifAllaData property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVerifAllaData(XMLGregorianCalendar value) {
        this.verifAllaData = value;
    }

    /**
     * Gets the value of the pdfReport property.
     * 
     */
    public boolean isPdfReport() {
        return pdfReport;
    }

    /**
     * Sets the value of the pdfReport property.
     * 
     */
    public void setPdfReport(boolean value) {
        this.pdfReport = value;
    }

    /**
     * Gets the value of the profile property.
     * 
     * @return
     *     possible object is
     *     {@link ValidationProfile }
     *     
     */
    public ValidationProfile getProfile() {
        return profile;
    }

    /**
     * Sets the value of the profile property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationProfile }
     *     
     */
    public void setProfile(ValidationProfile value) {
        this.profile = value;
    }

}
