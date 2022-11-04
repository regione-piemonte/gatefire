/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for signerV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="signerV2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://actalisVol/}signer"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="htmlMessage" type="{http://actalisVol/}HTMLMessage" minOccurs="0"/&gt;
 *         &lt;element name="validationStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signerV2", propOrder = {
    "htmlMessage",
    "validationStatus"
})
public class SignerV2
    extends Signer
{

    protected HTMLMessage htmlMessage;
    protected String validationStatus;

    /**
     * Gets the value of the htmlMessage property.
     * 
     * @return
     *     possible object is
     *     {@link HTMLMessage }
     *     
     */
    public HTMLMessage getHtmlMessage() {
        return htmlMessage;
    }

    /**
     * Sets the value of the htmlMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link HTMLMessage }
     *     
     */
    public void setHtmlMessage(HTMLMessage value) {
        this.htmlMessage = value;
    }

    /**
     * Gets the value of the validationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationStatus() {
        return validationStatus;
    }

    /**
     * Sets the value of the validationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationStatus(String value) {
        this.validationStatus = value;
    }

}
