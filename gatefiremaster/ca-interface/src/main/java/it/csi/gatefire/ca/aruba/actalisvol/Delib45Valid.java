/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for delib45Valid complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="delib45Valid"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="warnDelib45" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="warnDelib45Cause" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "delib45Valid", propOrder = {
    "warnDelib45",
    "warnDelib45Cause"
})
public class Delib45Valid {

    protected Boolean warnDelib45;
    protected String warnDelib45Cause;

    /**
     * Gets the value of the warnDelib45 property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWarnDelib45() {
        return warnDelib45;
    }

    /**
     * Sets the value of the warnDelib45 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWarnDelib45(Boolean value) {
        this.warnDelib45 = value;
    }

    /**
     * Gets the value of the warnDelib45Cause property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarnDelib45Cause() {
        return warnDelib45Cause;
    }

    /**
     * Sets the value of the warnDelib45Cause property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarnDelib45Cause(String value) {
        this.warnDelib45Cause = value;
    }

}
