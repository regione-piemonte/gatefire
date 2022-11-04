/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.arubasignservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeTransport.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="typeTransport"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="BYNARYNET"/&gt;
 *     &lt;enumeration value="FILENAME"/&gt;
 *     &lt;enumeration value="DIRECTORYNAME"/&gt;
 *     &lt;enumeration value="STREAM"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "typeTransport")
@XmlEnum
public enum TypeTransport {

    BYNARYNET,
    FILENAME,
    DIRECTORYNAME,
    STREAM;

    public String value() {
        return name();
    }

    public static TypeTransport fromValue(String v) {
        return valueOf(v);
    }

}
