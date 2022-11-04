/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for certPublicKey complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="certPublicKey"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="certPublicKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="certPublicKeyLength" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "certPublicKey", propOrder = {
    "certPublicKey",
    "certPublicKeyLength"
})
public class CertPublicKey {

    protected String certPublicKey;
    protected BigInteger certPublicKeyLength;

    /**
     * Gets the value of the certPublicKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertPublicKey() {
        return certPublicKey;
    }

    /**
     * Sets the value of the certPublicKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertPublicKey(String value) {
        this.certPublicKey = value;
    }

    /**
     * Gets the value of the certPublicKeyLength property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCertPublicKeyLength() {
        return certPublicKeyLength;
    }

    /**
     * Sets the value of the certPublicKeyLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCertPublicKeyLength(BigInteger value) {
        this.certPublicKeyLength = value;
    }

}
