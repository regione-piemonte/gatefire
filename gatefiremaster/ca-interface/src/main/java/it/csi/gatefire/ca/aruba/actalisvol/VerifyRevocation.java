
package it.csi.gatefire.ca.aruba.actalisvol;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="revocationRequest" type="{http://actalisVol/}revocationInfoRequest" maxOccurs="unbounded"/&gt;
 *         &lt;element name="verifAllaData" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "revocationRequest",
    "verifAllaData"
})
@XmlRootElement(name = "VerifyRevocation")
public class VerifyRevocation {

    @XmlElement(required = true)
    protected List<RevocationInfoRequest> revocationRequest;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar verifAllaData;

    /**
     * Gets the value of the revocationRequest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the revocationRequest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRevocationRequest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RevocationInfoRequest }
     * 
     * 
     */
    public List<RevocationInfoRequest> getRevocationRequest() {
        if (revocationRequest == null) {
            revocationRequest = new ArrayList<RevocationInfoRequest>();
        }
        return this.revocationRequest;
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

}
