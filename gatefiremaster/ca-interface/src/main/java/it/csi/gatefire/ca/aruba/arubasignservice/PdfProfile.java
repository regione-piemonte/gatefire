
package it.csi.gatefire.ca.aruba.arubasignservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pdfProfile.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="pdfProfile"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="BASIC"/&gt;
 *     &lt;enumeration value="PADESBES"/&gt;
 *     &lt;enumeration value="PADESLTV"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "pdfProfile")
@XmlEnum
public enum PdfProfile {

    BASIC,
    PADESBES,
    PADESLTV;

    public String value() {
        return name();
    }

    public static PdfProfile fromValue(String v) {
        return valueOf(v);
    }

}
