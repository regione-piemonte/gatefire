/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tstSpecific complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tstSpecific"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="relatedToDoc" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tstSpecific", propOrder = {
    "relatedToDoc"
})
public class TstSpecific {

    protected boolean relatedToDoc;

    /**
     * Gets the value of the relatedToDoc property.
     * 
     */
    public boolean isRelatedToDoc() {
        return relatedToDoc;
    }

    /**
     * Sets the value of the relatedToDoc property.
     * 
     */
    public void setRelatedToDoc(boolean value) {
        this.relatedToDoc = value;
    }

}
