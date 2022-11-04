/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.arubasignservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for credentialsType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="credentialsType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SMS"/&gt;
 *     &lt;enumeration value="ARUBACALL"/&gt;
 *     &lt;enumeration value="CNS2"/&gt;
 *     &lt;enumeration value="PAPERTOKEN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "credentialsType")
@XmlEnum
public enum CredentialsType {

    SMS("SMS"),
    ARUBACALL("ARUBACALL"),
    @XmlEnumValue("CNS2")
    CNS_2("CNS2"),
    PAPERTOKEN("PAPERTOKEN");
    private final String value;

    CredentialsType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CredentialsType fromValue(String v) {
        for (CredentialsType c: CredentialsType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
