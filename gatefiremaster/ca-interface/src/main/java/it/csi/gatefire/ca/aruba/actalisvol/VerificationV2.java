
package it.csi.gatefire.ca.aruba.actalisvol;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for VerificationV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VerificationV2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fileInfo" type="{http://actalisVol/}fileInfo"/&gt;
 *         &lt;element name="fileContent" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *         &lt;element name="verifAllaData" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="pdfReport" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerificationV2", propOrder = {
    "fileInfo",
    "fileContent",
    "verifAllaData",
    "pdfReport"
})
public class VerificationV2 {

    @XmlElement(required = true)
    protected FileInfo fileInfo;
    @XmlElement(required = true)
    @XmlMimeType("application/octet-stream")
    protected DataHandler fileContent;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar verifAllaData;
    protected boolean pdfReport;

    /**
     * Gets the value of the fileInfo property.
     * 
     * @return
     *     possible object is
     *     {@link FileInfo }
     *     
     */
    public FileInfo getFileInfo() {
        return fileInfo;
    }

    /**
     * Sets the value of the fileInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileInfo }
     *     
     */
    public void setFileInfo(FileInfo value) {
        this.fileInfo = value;
    }

    /**
     * Gets the value of the fileContent property.
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getFileContent() {
        return fileContent;
    }

    /**
     * Sets the value of the fileContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setFileContent(DataHandler value) {
        this.fileContent = value;
    }

    /**
     * Gets the value of the verifAllaData property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVerifAllaData() {
        return verifAllaData;
    }

    /**
     * Sets the value of the verifAllaData property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVerifAllaData(XMLGregorianCalendar value) {
        this.verifAllaData = value;
    }

    /**
     * Gets the value of the pdfReport property.
     * 
     */
    public boolean isPdfReport() {
        return pdfReport;
    }

    /**
     * Sets the value of the pdfReport property.
     * 
     */
    public void setPdfReport(boolean value) {
        this.pdfReport = value;
    }

}
