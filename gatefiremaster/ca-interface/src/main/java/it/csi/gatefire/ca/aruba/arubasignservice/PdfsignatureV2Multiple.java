
package it.csi.gatefire.ca.aruba.arubasignservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pdfsignatureV2_multiple complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pdfsignatureV2_multiple"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="identity" type="{http://arubasignservice.arubapec.it/}auth" minOccurs="0"/&gt;
 *         &lt;element name="SignRequestV2" type="{http://arubasignservice.arubapec.it/}signRequestV2" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Apparence" type="{http://arubasignservice.arubapec.it/}pdfSignApparence" minOccurs="0"/&gt;
 *         &lt;element name="pdfprofile" type="{http://arubasignservice.arubapec.it/}pdfProfile" minOccurs="0"/&gt;
 *         &lt;element name="dict_signed_attributes" type="{http://arubasignservice.arubapec.it/}dictionarySignedAttributes" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pdfsignatureV2_multiple", propOrder = {
    "identity",
    "signRequestV2",
    "apparence",
    "pdfprofile",
    "dictSignedAttributes"
})
public class PdfsignatureV2Multiple {

    protected Auth identity;
    @XmlElement(name = "SignRequestV2", nillable = true)
    protected List<SignRequestV2> signRequestV2;
    @XmlElement(name = "Apparence")
    protected PdfSignApparence apparence;
    @XmlSchemaType(name = "string")
    protected PdfProfile pdfprofile;
    @XmlElement(name = "dict_signed_attributes")
    protected DictionarySignedAttributes dictSignedAttributes;

    /**
     * Gets the value of the identity property.
     * 
     * @return
     *     possible object is
     *     {@link Auth }
     *     
     */
    public Auth getIdentity() {
        return identity;
    }

    /**
     * Sets the value of the identity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Auth }
     *     
     */
    public void setIdentity(Auth value) {
        this.identity = value;
    }

    /**
     * Gets the value of the signRequestV2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signRequestV2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSignRequestV2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SignRequestV2 }
     * 
     * 
     */
    public List<SignRequestV2> getSignRequestV2() {
        if (signRequestV2 == null) {
            signRequestV2 = new ArrayList<SignRequestV2>();
        }
        return this.signRequestV2;
    }

    /**
     * Gets the value of the apparence property.
     * 
     * @return
     *     possible object is
     *     {@link PdfSignApparence }
     *     
     */
    public PdfSignApparence getApparence() {
        return apparence;
    }

    /**
     * Sets the value of the apparence property.
     * 
     * @param value
     *     allowed object is
     *     {@link PdfSignApparence }
     *     
     */
    public void setApparence(PdfSignApparence value) {
        this.apparence = value;
    }

    /**
     * Gets the value of the pdfprofile property.
     * 
     * @return
     *     possible object is
     *     {@link PdfProfile }
     *     
     */
    public PdfProfile getPdfprofile() {
        return pdfprofile;
    }

    /**
     * Sets the value of the pdfprofile property.
     * 
     * @param value
     *     allowed object is
     *     {@link PdfProfile }
     *     
     */
    public void setPdfprofile(PdfProfile value) {
        this.pdfprofile = value;
    }

    /**
     * Gets the value of the dictSignedAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link DictionarySignedAttributes }
     *     
     */
    public DictionarySignedAttributes getDictSignedAttributes() {
        return dictSignedAttributes;
    }

    /**
     * Sets the value of the dictSignedAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DictionarySignedAttributes }
     *     
     */
    public void setDictSignedAttributes(DictionarySignedAttributes value) {
        this.dictSignedAttributes = value;
    }

}
