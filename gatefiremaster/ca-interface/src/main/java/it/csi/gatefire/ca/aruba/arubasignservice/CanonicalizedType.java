/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.arubasignservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for canonicalizedType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="canonicalizedType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ALGO_ID_C14N11_OMIT_COMMENTS"/&gt;
 *     &lt;enumeration value="ALGO_ID_C14N11_WITH_COMMENTS"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "canonicalizedType")
@XmlEnum
public enum CanonicalizedType {

    @XmlEnumValue("ALGO_ID_C14N11_OMIT_COMMENTS")
    ALGO_ID_C_14_N_11_OMIT_COMMENTS("ALGO_ID_C14N11_OMIT_COMMENTS"),
    @XmlEnumValue("ALGO_ID_C14N11_WITH_COMMENTS")
    ALGO_ID_C_14_N_11_WITH_COMMENTS("ALGO_ID_C14N11_WITH_COMMENTS");
    private final String value;

    CanonicalizedType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CanonicalizedType fromValue(String v) {
        for (CanonicalizedType c: CanonicalizedType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
