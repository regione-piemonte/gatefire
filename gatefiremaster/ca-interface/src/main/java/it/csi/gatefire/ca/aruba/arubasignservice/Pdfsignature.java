/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.arubasignservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pdfsignature complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pdfsignature"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SignRequest" type="{http://arubasignservice.arubapec.it/}signRequest" minOccurs="0"/&gt;
 *         &lt;element name="Apparence" type="{http://arubasignservice.arubapec.it/}pdfSignApparence" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pdfsignature", propOrder = {
    "signRequest",
    "apparence"
})
public class Pdfsignature {

    @XmlElement(name = "SignRequest")
    protected SignRequest signRequest;
    @XmlElement(name = "Apparence")
    protected PdfSignApparence apparence;

    /**
     * Gets the value of the signRequest property.
     * 
     * @return
     *     possible object is
     *     {@link SignRequest }
     *     
     */
    public SignRequest getSignRequest() {
        return signRequest;
    }

    /**
     * Sets the value of the signRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignRequest }
     *     
     */
    public void setSignRequest(SignRequest value) {
        this.signRequest = value;
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

}
