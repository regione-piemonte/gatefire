
package it.csi.gatefire.ca.aruba.actalisvol;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transientDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transientDocument"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="certs" type="{http://actalisVol/}refCertificate" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="crls" type="{http://actalisVol/}refCRL" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ocspresps" type="{http://actalisVol/}refOcsp" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="refsDocs" type="{http://actalisVol/}transientDocument" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="shortName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="signed" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="signerInfo" type="{http://actalisVol/}signatureInformation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transientDocument", propOrder = {
    "certs",
    "crls",
    "id",
    "ocspresps",
    "refsDocs",
    "shortName",
    "signed",
    "signerInfo"
})
public class TransientDocument {

    @XmlElement(nillable = true)
    protected List<RefCertificate> certs;
    @XmlElement(nillable = true)
    protected List<RefCRL> crls;
    protected String id;
    @XmlElement(nillable = true)
    protected List<RefOcsp> ocspresps;
    @XmlElement(nillable = true)
    protected List<TransientDocument> refsDocs;
    protected String shortName;
    protected boolean signed;
    @XmlElement(nillable = true)
    protected List<SignatureInformation> signerInfo;

    /**
     * Gets the value of the certs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCerts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefCertificate }
     * 
     * 
     */
    public List<RefCertificate> getCerts() {
        if (certs == null) {
            certs = new ArrayList<RefCertificate>();
        }
        return this.certs;
    }

    /**
     * Gets the value of the crls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrls().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefCRL }
     * 
     * 
     */
    public List<RefCRL> getCrls() {
        if (crls == null) {
            crls = new ArrayList<RefCRL>();
        }
        return this.crls;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the ocspresps property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ocspresps property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOcspresps().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefOcsp }
     * 
     * 
     */
    public List<RefOcsp> getOcspresps() {
        if (ocspresps == null) {
            ocspresps = new ArrayList<RefOcsp>();
        }
        return this.ocspresps;
    }

    /**
     * Gets the value of the refsDocs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refsDocs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefsDocs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransientDocument }
     * 
     * 
     */
    public List<TransientDocument> getRefsDocs() {
        if (refsDocs == null) {
            refsDocs = new ArrayList<TransientDocument>();
        }
        return this.refsDocs;
    }

    /**
     * Gets the value of the shortName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets the value of the shortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

    /**
     * Gets the value of the signed property.
     * 
     */
    public boolean isSigned() {
        return signed;
    }

    /**
     * Sets the value of the signed property.
     * 
     */
    public void setSigned(boolean value) {
        this.signed = value;
    }

    /**
     * Gets the value of the signerInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signerInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSignerInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SignatureInformation }
     * 
     * 
     */
    public List<SignatureInformation> getSignerInfo() {
        if (signerInfo == null) {
            signerInfo = new ArrayList<SignatureInformation>();
        }
        return this.signerInfo;
    }

}
