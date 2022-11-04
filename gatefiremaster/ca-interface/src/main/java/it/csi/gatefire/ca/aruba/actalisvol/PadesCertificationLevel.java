/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for padesCertificationLevel.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="padesCertificationLevel"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CERTIFIED_NO_CHANGES_ALLOWED"/&gt;
 *     &lt;enumeration value="CERTIFIED_SIGN_FORM_FILLING"/&gt;
 *     &lt;enumeration value="CERTIFIED_SIGN_FORM_FILLING_AND_ANNOTATIONS"/&gt;
 *     &lt;enumeration value="NOT_CERTIFIED"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "padesCertificationLevel")
@XmlEnum
public enum PadesCertificationLevel {

    CERTIFIED_NO_CHANGES_ALLOWED,
    CERTIFIED_SIGN_FORM_FILLING,
    CERTIFIED_SIGN_FORM_FILLING_AND_ANNOTATIONS,
    NOT_CERTIFIED;

    public String value() {
        return name();
    }

    public static PadesCertificationLevel fromValue(String v) {
        return valueOf(v);
    }

}
