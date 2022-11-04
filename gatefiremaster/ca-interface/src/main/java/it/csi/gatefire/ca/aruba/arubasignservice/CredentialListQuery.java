
package it.csi.gatefire.ca.aruba.arubasignservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for credentialListQuery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="credentialListQuery"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="appidentity" type="{http://arubasignservice.arubapec.it/}applicationAuth" minOccurs="0"/&gt;
 *         &lt;element name="constraints" type="{http://arubasignservice.arubapec.it/}queryConstraint" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="domain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "credentialListQuery", propOrder = {
    "appidentity",
    "constraints",
    "domain"
})
public class CredentialListQuery {

    protected ApplicationAuth appidentity;
    @XmlElement(nillable = true)
    protected List<QueryConstraint> constraints;
    protected String domain;

    /**
     * Gets the value of the appidentity property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationAuth }
     *     
     */
    public ApplicationAuth getAppidentity() {
        return appidentity;
    }

    /**
     * Sets the value of the appidentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationAuth }
     *     
     */
    public void setAppidentity(ApplicationAuth value) {
        this.appidentity = value;
    }

    /**
     * Gets the value of the constraints property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the constraints property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConstraints().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QueryConstraint }
     * 
     * 
     */
    public List<QueryConstraint> getConstraints() {
        if (constraints == null) {
            constraints = new ArrayList<QueryConstraint>();
        }
        return this.constraints;
    }

    /**
     * Gets the value of the domain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets the value of the domain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomain(String value) {
        this.domain = value;
    }

}
