
package it.csi.gatefire.ca.aruba.arubasignservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pkcs7signhash complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pkcs7signhash"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SignRequestV2" type="{http://arubasignservice.arubapec.it/}signRequestV2" minOccurs="0"/&gt;
 *         &lt;element name="countersignature" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="excludeSigningTime" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pkcs7signhash", propOrder = {
    "signRequestV2",
    "countersignature",
    "excludeSigningTime"
})
public class Pkcs7Signhash {

    @XmlElement(name = "SignRequestV2")
    protected SignRequestV2 signRequestV2;
    protected boolean countersignature;
    protected boolean excludeSigningTime;

    /**
     * Gets the value of the signRequestV2 property.
     * 
     * @return
     *     possible object is
     *     {@link SignRequestV2 }
     *     
     */
    public SignRequestV2 getSignRequestV2() {
        return signRequestV2;
    }

    /**
     * Sets the value of the signRequestV2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignRequestV2 }
     *     
     */
    public void setSignRequestV2(SignRequestV2 value) {
        this.signRequestV2 = value;
    }

    /**
     * Gets the value of the countersignature property.
     * 
     */
    public boolean isCountersignature() {
        return countersignature;
    }

    /**
     * Sets the value of the countersignature property.
     * 
     */
    public void setCountersignature(boolean value) {
        this.countersignature = value;
    }

    /**
     * Gets the value of the excludeSigningTime property.
     * 
     */
    public boolean isExcludeSigningTime() {
        return excludeSigningTime;
    }

    /**
     * Sets the value of the excludeSigningTime property.
     * 
     */
    public void setExcludeSigningTime(boolean value) {
        this.excludeSigningTime = value;
    }

}
