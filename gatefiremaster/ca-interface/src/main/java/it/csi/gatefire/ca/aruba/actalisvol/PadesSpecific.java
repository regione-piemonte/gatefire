
package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for padesSpecific complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="padesSpecific"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="allRevisionsCovered" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="certInDictionary" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="certificationLevel" type="{http://actalisVol/}padesCertificationLevel" minOccurs="0"/&gt;
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="multipleSigners" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="nextRevisionsSigned" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="nextRevisionsUpdateAnnotation" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="nextRevisionsUpdateForm" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="nextRevisionsUpdateObject" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="signatureDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="subFilter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "padesSpecific", propOrder = {
    "allRevisionsCovered",
    "certInDictionary",
    "certificationLevel",
    "location",
    "multipleSigners",
    "nextRevisionsSigned",
    "nextRevisionsUpdateAnnotation",
    "nextRevisionsUpdateForm",
    "nextRevisionsUpdateObject",
    "reason",
    "signatureDate",
    "subFilter"
})
public class PadesSpecific {

    protected boolean allRevisionsCovered;
    protected boolean certInDictionary;
    @XmlSchemaType(name = "string")
    protected PadesCertificationLevel certificationLevel;
    protected String location;
    protected boolean multipleSigners;
    protected boolean nextRevisionsSigned;
    protected boolean nextRevisionsUpdateAnnotation;
    protected boolean nextRevisionsUpdateForm;
    protected boolean nextRevisionsUpdateObject;
    protected String reason;
    protected String signatureDate;
    protected String subFilter;

    /**
     * Gets the value of the allRevisionsCovered property.
     * 
     */
    public boolean isAllRevisionsCovered() {
        return allRevisionsCovered;
    }

    /**
     * Sets the value of the allRevisionsCovered property.
     * 
     */
    public void setAllRevisionsCovered(boolean value) {
        this.allRevisionsCovered = value;
    }

    /**
     * Gets the value of the certInDictionary property.
     * 
     */
    public boolean isCertInDictionary() {
        return certInDictionary;
    }

    /**
     * Sets the value of the certInDictionary property.
     * 
     */
    public void setCertInDictionary(boolean value) {
        this.certInDictionary = value;
    }

    /**
     * Gets the value of the certificationLevel property.
     * 
     * @return
     *     possible object is
     *     {@link PadesCertificationLevel }
     *     
     */
    public PadesCertificationLevel getCertificationLevel() {
        return certificationLevel;
    }

    /**
     * Sets the value of the certificationLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link PadesCertificationLevel }
     *     
     */
    public void setCertificationLevel(PadesCertificationLevel value) {
        this.certificationLevel = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the multipleSigners property.
     * 
     */
    public boolean isMultipleSigners() {
        return multipleSigners;
    }

    /**
     * Sets the value of the multipleSigners property.
     * 
     */
    public void setMultipleSigners(boolean value) {
        this.multipleSigners = value;
    }

    /**
     * Gets the value of the nextRevisionsSigned property.
     * 
     */
    public boolean isNextRevisionsSigned() {
        return nextRevisionsSigned;
    }

    /**
     * Sets the value of the nextRevisionsSigned property.
     * 
     */
    public void setNextRevisionsSigned(boolean value) {
        this.nextRevisionsSigned = value;
    }

    /**
     * Gets the value of the nextRevisionsUpdateAnnotation property.
     * 
     */
    public boolean isNextRevisionsUpdateAnnotation() {
        return nextRevisionsUpdateAnnotation;
    }

    /**
     * Sets the value of the nextRevisionsUpdateAnnotation property.
     * 
     */
    public void setNextRevisionsUpdateAnnotation(boolean value) {
        this.nextRevisionsUpdateAnnotation = value;
    }

    /**
     * Gets the value of the nextRevisionsUpdateForm property.
     * 
     */
    public boolean isNextRevisionsUpdateForm() {
        return nextRevisionsUpdateForm;
    }

    /**
     * Sets the value of the nextRevisionsUpdateForm property.
     * 
     */
    public void setNextRevisionsUpdateForm(boolean value) {
        this.nextRevisionsUpdateForm = value;
    }

    /**
     * Gets the value of the nextRevisionsUpdateObject property.
     * 
     */
    public boolean isNextRevisionsUpdateObject() {
        return nextRevisionsUpdateObject;
    }

    /**
     * Sets the value of the nextRevisionsUpdateObject property.
     * 
     */
    public void setNextRevisionsUpdateObject(boolean value) {
        this.nextRevisionsUpdateObject = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of the signatureDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureDate() {
        return signatureDate;
    }

    /**
     * Sets the value of the signatureDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureDate(String value) {
        this.signatureDate = value;
    }

    /**
     * Gets the value of the subFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubFilter() {
        return subFilter;
    }

    /**
     * Sets the value of the subFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubFilter(String value) {
        this.subFilter = value;
    }

}
