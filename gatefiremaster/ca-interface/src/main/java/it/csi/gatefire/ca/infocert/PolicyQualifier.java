//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.03.08 at 01:14:07 PM CET 
//


package it.csi.gatefire.ca.infocert;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "policyQualifierID",
    "cpsUri",
    "explicitText"
})
@XmlRootElement(name = "policyQualifier")
public class PolicyQualifier {

    @XmlElement(required = true)
    protected String policyQualifierID;
    protected String cpsUri;
    protected String explicitText;

    /**
     * Gets the value of the policyQualifierID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyQualifierID() {
        return policyQualifierID;
    }

    /**
     * Sets the value of the policyQualifierID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyQualifierID(String value) {
        this.policyQualifierID = value;
    }

    /**
     * Gets the value of the cpsUri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpsUri() {
        return cpsUri;
    }

    /**
     * Sets the value of the cpsUri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpsUri(String value) {
        this.cpsUri = value;
    }

    /**
     * Gets the value of the explicitText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExplicitText() {
        return explicitText;
    }

    /**
     * Sets the value of the explicitText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExplicitText(String value) {
        this.explicitText = value;
    }

}
