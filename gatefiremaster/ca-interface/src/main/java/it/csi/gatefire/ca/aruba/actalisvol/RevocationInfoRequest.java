
package it.csi.gatefire.ca.aruba.actalisvol;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for revocationInfoRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="revocationInfoRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="serialnumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="issuerdn" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="crldistributionpoint" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "revocationInfoRequest", propOrder = {
    "serialnumber",
    "issuerdn",
    "crldistributionpoint"
})
public class RevocationInfoRequest {

    @XmlElement(required = true)
    protected String serialnumber;
    @XmlElement(required = true)
    protected String issuerdn;
    @XmlElement(required = true)
    protected List<String> crldistributionpoint;

    /**
     * Gets the value of the serialnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialnumber() {
        return serialnumber;
    }

    /**
     * Sets the value of the serialnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialnumber(String value) {
        this.serialnumber = value;
    }

    /**
     * Gets the value of the issuerdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuerdn() {
        return issuerdn;
    }

    /**
     * Sets the value of the issuerdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuerdn(String value) {
        this.issuerdn = value;
    }

    /**
     * Gets the value of the crldistributionpoint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crldistributionpoint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrldistributionpoint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCrldistributionpoint() {
        if (crldistributionpoint == null) {
            crldistributionpoint = new ArrayList<String>();
        }
        return this.crldistributionpoint;
    }

}
