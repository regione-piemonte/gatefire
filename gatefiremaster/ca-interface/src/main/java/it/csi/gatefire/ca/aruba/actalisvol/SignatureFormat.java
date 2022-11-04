
package it.csi.gatefire.ca.aruba.actalisvol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for signatureFormat.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="signatureFormat"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CADES"/&gt;
 *     &lt;enumeration value="PADES"/&gt;
 *     &lt;enumeration value="XADES"/&gt;
 *     &lt;enumeration value="TST"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "signatureFormat")
@XmlEnum
public enum SignatureFormat {

    CADES,
    PADES,
    XADES,
    TST;

    public String value() {
        return name();
    }

    public static SignatureFormat fromValue(String v) {
        return valueOf(v);
    }

}
