
package it.csi.gatefire.ca.aruba.arubasignservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.csi.gatefire.common.json.PasswordSerializer;


/**
 * <p>Java class for auth complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="auth"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="delegated_domain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegated_password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegated_user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ext_auth_blobvalue" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="ext_auth_value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ext_authtype" type="{http://arubasignservice.arubapec.it/}credentialsType" minOccurs="0"/&gt;
 *         &lt;element name="otpPwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="typeHSM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="typeOtpAuth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userPWD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "auth", propOrder = {
    "delegatedDomain",
    "delegatedPassword",
    "delegatedUser",
    "extAuthBlobvalue",
    "extAuthValue",
    "extAuthtype",
    "otpPwd",
    "typeHSM",
    "typeOtpAuth",
    "user",
    "userPWD"
})
public class Auth {

    @XmlElement(name = "delegated_domain")
    protected String delegatedDomain;
    @XmlElement(name = "delegated_password")
    protected String delegatedPassword;
    @XmlElement(name = "delegated_user")
    protected String delegatedUser;
    @XmlElement(name = "ext_auth_blobvalue")
    protected byte[] extAuthBlobvalue;
    @XmlElement(name = "ext_auth_value")
    protected String extAuthValue;
    @XmlElement(name = "ext_authtype")
    @XmlSchemaType(name = "string")
    protected CredentialsType extAuthtype;
    protected String otpPwd;
    protected String typeHSM;
    protected String typeOtpAuth;
    protected String user;
    protected String userPWD;

    /**
     * Gets the value of the delegatedDomain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelegatedDomain() {
        return delegatedDomain;
    }

    /**
     * Sets the value of the delegatedDomain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelegatedDomain(String value) {
        this.delegatedDomain = value;
    }

    /**
     * Gets the value of the delegatedPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @JsonSerialize(using = PasswordSerializer.class)
    public String getDelegatedPassword() {
        return delegatedPassword;
    }

    /**
     * Sets the value of the delegatedPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelegatedPassword(String value) {
        this.delegatedPassword = value;
    }

    /**
     * Gets the value of the delegatedUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelegatedUser() {
        return delegatedUser;
    }

    /**
     * Sets the value of the delegatedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelegatedUser(String value) {
        this.delegatedUser = value;
    }

    /**
     * Gets the value of the extAuthBlobvalue property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getExtAuthBlobvalue() {
        return extAuthBlobvalue;
    }

    /**
     * Sets the value of the extAuthBlobvalue property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setExtAuthBlobvalue(byte[] value) {
        this.extAuthBlobvalue = value;
    }

    /**
     * Gets the value of the extAuthValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtAuthValue() {
        return extAuthValue;
    }

    /**
     * Sets the value of the extAuthValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtAuthValue(String value) {
        this.extAuthValue = value;
    }

    /**
     * Gets the value of the extAuthtype property.
     * 
     * @return
     *     possible object is
     *     {@link CredentialsType }
     *     
     */
    public CredentialsType getExtAuthtype() {
        return extAuthtype;
    }

    /**
     * Sets the value of the extAuthtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link CredentialsType }
     *     
     */
    public void setExtAuthtype(CredentialsType value) {
        this.extAuthtype = value;
    }

    /**
     * Gets the value of the otpPwd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtpPwd() {
        return otpPwd;
    }

    /**
     * Sets the value of the otpPwd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @JsonSerialize(using = PasswordSerializer.class)
    public void setOtpPwd(String value) {
        this.otpPwd = value;
    }

    /**
     * Gets the value of the typeHSM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeHSM() {
        return typeHSM;
    }

    /**
     * Sets the value of the typeHSM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeHSM(String value) {
        this.typeHSM = value;
    }

    /**
     * Gets the value of the typeOtpAuth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeOtpAuth() {
        return typeOtpAuth;
    }

    /**
     * Sets the value of the typeOtpAuth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeOtpAuth(String value) {
        this.typeOtpAuth = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Gets the value of the userPWD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @JsonSerialize(using = PasswordSerializer.class)
    public String getUserPWD() {
        return userPWD;
    }

    /**
     * Sets the value of the userPWD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserPWD(String value) {
        this.userPWD = value;
    }

}
