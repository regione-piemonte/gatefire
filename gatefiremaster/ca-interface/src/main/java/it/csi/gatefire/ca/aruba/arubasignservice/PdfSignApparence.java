
package it.csi.gatefire.ca.aruba.arubasignservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pdfSignApparence complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pdfSignApparence"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="imageBin" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="imageOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="leftx" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="lefty" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="page" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rightx" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="righty" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="testo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="bScaleFont" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="bShowDateTime" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="resizeMode" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="preservePDFA" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pdfSignApparence", propOrder = {
    "image",
    "imageBin",
    "imageOnly",
    "leftx",
    "lefty",
    "location",
    "page",
    "reason",
    "rightx",
    "righty",
    "testo",
    "bScaleFont",
    "bShowDateTime",
    "resizeMode",
    "preservePDFA"
})
public class PdfSignApparence {

    protected String image;
    protected byte[] imageBin;
    protected boolean imageOnly;
    protected int leftx;
    protected int lefty;
    protected String location;
    protected int page;
    protected String reason;
    protected int rightx;
    protected int righty;
    protected String testo;
    protected boolean bScaleFont;
    protected boolean bShowDateTime;
    protected int resizeMode;
    protected boolean preservePDFA;

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImage(String value) {
        this.image = value;
    }

    /**
     * Gets the value of the imageBin property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImageBin() {
        return imageBin;
    }

    /**
     * Sets the value of the imageBin property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImageBin(byte[] value) {
        this.imageBin = value;
    }

    /**
     * Gets the value of the imageOnly property.
     * 
     */
    public boolean isImageOnly() {
        return imageOnly;
    }

    /**
     * Sets the value of the imageOnly property.
     * 
     */
    public void setImageOnly(boolean value) {
        this.imageOnly = value;
    }

    /**
     * Gets the value of the leftx property.
     * 
     */
    public int getLeftx() {
        return leftx;
    }

    /**
     * Sets the value of the leftx property.
     * 
     */
    public void setLeftx(int value) {
        this.leftx = value;
    }

    /**
     * Gets the value of the lefty property.
     * 
     */
    public int getLefty() {
        return lefty;
    }

    /**
     * Sets the value of the lefty property.
     * 
     */
    public void setLefty(int value) {
        this.lefty = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the page property.
     * 
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the value of the page property.
     * 
     */
    public void setPage(int value) {
        this.page = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of the rightx property.
     * 
     */
    public int getRightx() {
        return rightx;
    }

    /**
     * Sets the value of the rightx property.
     * 
     */
    public void setRightx(int value) {
        this.rightx = value;
    }

    /**
     * Gets the value of the righty property.
     * 
     */
    public int getRighty() {
        return righty;
    }

    /**
     * Sets the value of the righty property.
     * 
     */
    public void setRighty(int value) {
        this.righty = value;
    }

    /**
     * Gets the value of the testo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTesto() {
        return testo;
    }

    /**
     * Sets the value of the testo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTesto(String value) {
        this.testo = value;
    }

    /**
     * Gets the value of the bScaleFont property.
     * 
     */
    public boolean isBScaleFont() {
        return bScaleFont;
    }

    /**
     * Sets the value of the bScaleFont property.
     * 
     */
    public void setBScaleFont(boolean value) {
        this.bScaleFont = value;
    }

    /**
     * Gets the value of the bShowDateTime property.
     * 
     */
    public boolean isBShowDateTime() {
        return bShowDateTime;
    }

    /**
     * Sets the value of the bShowDateTime property.
     * 
     */
    public void setBShowDateTime(boolean value) {
        this.bShowDateTime = value;
    }

    /**
     * Gets the value of the resizeMode property.
     * 
     */
    public int getResizeMode() {
        return resizeMode;
    }

    /**
     * Sets the value of the resizeMode property.
     * 
     */
    public void setResizeMode(int value) {
        this.resizeMode = value;
    }

    /**
     * Gets the value of the preservePDFA property.
     * 
     */
    public boolean isPreservePDFA() {
        return preservePDFA;
    }

    /**
     * Sets the value of the preservePDFA property.
     * 
     */
    public void setPreservePDFA(boolean value) {
        this.preservePDFA = value;
    }

}
