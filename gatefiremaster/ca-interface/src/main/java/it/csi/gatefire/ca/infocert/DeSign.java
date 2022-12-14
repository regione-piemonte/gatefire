//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.03.08 at 01:14:07 PM CET 
//


package it.csi.gatefire.ca.infocert;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "signedDataOrTimeStamp"
})
@XmlRootElement(name = "deSign")
public class DeSign {

    @XmlAttribute(name = "release")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String release;
    @XmlAttribute(name = "releaseDate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String releaseDate;
    @XmlAttribute(name = "controlloCACRL")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String controlloCACRL;
    @XmlAttribute(name = "controlloCRLPKCS7")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String controlloCRLPKCS7;
    @XmlAttribute(name = "controlloCRLTS")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String controlloCRLTS;
    @XmlAttribute(name = "controlloValidita")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String controlloValidita;
    @XmlAttribute(name = "controlloSigningCertificateAttr")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String controlloSigningCertificateAttr;
    @XmlAttribute(name = "controlloSHA1Agosto2010")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String controlloSHA1Agosto2010;
    @XmlAttribute(name = "controlloSHA1Luglio2011")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String controlloSHA1Luglio2011;
    @XmlAttribute(name = "controlloFirmeAnnidiate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String controlloFirmeAnnidiate;
    @XmlAttribute(name = "verificaTS101903")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String verificaTS101903;
    @XmlAttribute(name = "serverRelease")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String serverRelease;
    @XmlAttribute(name = "downloadCRL")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String downloadCRL;
    @XmlAttribute(name = "useProxy")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String useProxy;
    @XmlAttribute(name = "useSOCKS")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String useSOCKS;
    @XmlElements({
        @XmlElement(name = "signedData", required = true, type = SignedData.class),
        @XmlElement(name = "timeStamp", required = true, type = TimeStamp.class)
    })
    protected List<Object> signedDataOrTimeStamp;

    /**
     * Gets the value of the release property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelease() {
        return release;
    }

    /**
     * Sets the value of the release property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelease(String value) {
        this.release = value;
    }

    /**
     * Gets the value of the releaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets the value of the releaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReleaseDate(String value) {
        this.releaseDate = value;
    }

    /**
     * Gets the value of the controlloCACRL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlloCACRL() {
        return controlloCACRL;
    }

    /**
     * Sets the value of the controlloCACRL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlloCACRL(String value) {
        this.controlloCACRL = value;
    }

    /**
     * Gets the value of the controlloCRLPKCS7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlloCRLPKCS7() {
        return controlloCRLPKCS7;
    }

    /**
     * Sets the value of the controlloCRLPKCS7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlloCRLPKCS7(String value) {
        this.controlloCRLPKCS7 = value;
    }

    /**
     * Gets the value of the controlloCRLTS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlloCRLTS() {
        return controlloCRLTS;
    }

    /**
     * Sets the value of the controlloCRLTS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlloCRLTS(String value) {
        this.controlloCRLTS = value;
    }

    /**
     * Gets the value of the controlloValidita property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlloValidita() {
        return controlloValidita;
    }

    /**
     * Sets the value of the controlloValidita property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlloValidita(String value) {
        this.controlloValidita = value;
    }

    /**
     * Gets the value of the controlloSigningCertificateAttr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlloSigningCertificateAttr() {
        return controlloSigningCertificateAttr;
    }

    /**
     * Sets the value of the controlloSigningCertificateAttr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlloSigningCertificateAttr(String value) {
        this.controlloSigningCertificateAttr = value;
    }

    /**
     * Gets the value of the controlloSHA1Agosto2010 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlloSHA1Agosto2010() {
        return controlloSHA1Agosto2010;
    }

    /**
     * Sets the value of the controlloSHA1Agosto2010 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlloSHA1Agosto2010(String value) {
        this.controlloSHA1Agosto2010 = value;
    }

    /**
     * Gets the value of the controlloSHA1Luglio2011 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlloSHA1Luglio2011() {
        return controlloSHA1Luglio2011;
    }

    /**
     * Sets the value of the controlloSHA1Luglio2011 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlloSHA1Luglio2011(String value) {
        this.controlloSHA1Luglio2011 = value;
    }

    /**
     * Gets the value of the controlloFirmeAnnidiate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlloFirmeAnnidiate() {
        return controlloFirmeAnnidiate;
    }

    /**
     * Sets the value of the controlloFirmeAnnidiate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlloFirmeAnnidiate(String value) {
        this.controlloFirmeAnnidiate = value;
    }

    /**
     * Gets the value of the verificaTS101903 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerificaTS101903() {
        return verificaTS101903;
    }

    /**
     * Sets the value of the verificaTS101903 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerificaTS101903(String value) {
        this.verificaTS101903 = value;
    }

    /**
     * Gets the value of the serverRelease property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerRelease() {
        return serverRelease;
    }

    /**
     * Sets the value of the serverRelease property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerRelease(String value) {
        this.serverRelease = value;
    }

    /**
     * Gets the value of the downloadCRL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDownloadCRL() {
        return downloadCRL;
    }

    /**
     * Sets the value of the downloadCRL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDownloadCRL(String value) {
        this.downloadCRL = value;
    }

    /**
     * Gets the value of the useProxy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseProxy() {
        return useProxy;
    }

    /**
     * Sets the value of the useProxy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseProxy(String value) {
        this.useProxy = value;
    }

    /**
     * Gets the value of the useSOCKS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseSOCKS() {
        return useSOCKS;
    }

    /**
     * Sets the value of the useSOCKS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseSOCKS(String value) {
        this.useSOCKS = value;
    }

    /**
     * Gets the value of the signedDataOrTimeStamp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signedDataOrTimeStamp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSignedDataOrTimeStamp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SignedData }
     * {@link TimeStamp }
     * 
     * 
     */
    public List<Object> getSignedDataOrTimeStamp() {
        if (signedDataOrTimeStamp == null) {
            signedDataOrTimeStamp = new ArrayList<Object>();
        }
        return this.signedDataOrTimeStamp;
    }

}
