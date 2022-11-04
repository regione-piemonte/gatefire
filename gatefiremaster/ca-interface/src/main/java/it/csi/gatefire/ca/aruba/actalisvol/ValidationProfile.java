
package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for validationProfile.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="validationProfile"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ALL_PROFILES"/&gt;
 *     &lt;enumeration value="QUALIFIED_ELECTRONIC_SIGNATURE_PROFILE"/&gt;
 *     &lt;enumeration value="CNS_ADVANCED_ELECTRONIC_SIGNATURE_PROFILE"/&gt;
 *     &lt;enumeration value="ADVANCED_ELECTRONIC_SIGNATURE_PROFILE"/&gt;
 *     &lt;enumeration value="GRAPHOMETRIC_SIGNATURE_PROFILE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "validationProfile")
@XmlEnum
public enum ValidationProfile {

    ALL_PROFILES,
    QUALIFIED_ELECTRONIC_SIGNATURE_PROFILE,
    CNS_ADVANCED_ELECTRONIC_SIGNATURE_PROFILE,
    ADVANCED_ELECTRONIC_SIGNATURE_PROFILE,
    GRAPHOMETRIC_SIGNATURE_PROFILE;

    public String value() {
        return name();
    }

    public static ValidationProfile fromValue(String v) {
        return valueOf(v);
    }

}
