/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VerificationV3Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VerificationV3Response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ReturnSL" type="{http://actalisVol/}ReturnSL"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerificationV3Response", propOrder = {
    "returnSL"
})
public class VerificationV3Response {

    @XmlElement(name = "ReturnSL", required = true)
    protected ReturnSL returnSL;

    /**
     * Gets the value of the returnSL property.
     * 
     * @return
     *     possible object is
     *     {@link ReturnSL }
     *     
     */
    public ReturnSL getReturnSL() {
        return returnSL;
    }

    /**
     * Sets the value of the returnSL property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnSL }
     *     
     */
    public void setReturnSL(ReturnSL value) {
        this.returnSL = value;
    }

}
