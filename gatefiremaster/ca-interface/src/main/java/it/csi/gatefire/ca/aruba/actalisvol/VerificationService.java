package it.csi.gatefire.ca.aruba.actalisvol;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.3.5
 * 2021-03-26T16:16:45.221+01:00
 * Generated source version: 3.3.5
 *
 */
@WebService(targetNamespace = "http://actalisVol/", name = "VerificationService")
@XmlSeeAlso({ObjectFactory.class})
public interface VerificationService {

    @WebMethod(operationName = "VerifyTimeStampData")
    @Action(input = "http://actalisVol/VerificationService/VerifyTimeStampDataRequest", output = "http://actalisVol/VerificationService/VerifyTimeStampDataResponse")
    @RequestWrapper(localName = "VerifyTimeStampData", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyTimeStampData")
    @ResponseWrapper(localName = "VerifyTimeStampDataResponse", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyTimeStampDataResponse")
    @WebResult(name = "return", targetNamespace = "")
    public it.csi.gatefire.ca.aruba.actalisvol.ReturnTimeStampDataValidation verifyTimeStampData(

        @WebParam(name = "fileContent", targetNamespace = "")
        javax.activation.DataHandler fileContent
    );

    @WebMethod(operationName = "VerificationV2")
    @Action(input = "http://actalisVol.it/actalisVol/services/VerificationV2", output = "http://actalisVol.it/actalisVol/services/VerificationV2")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebResult(name = "VerificationV2Response", targetNamespace = "http://actalisVol/", partName = "parameters")
    public VerificationV2Response verificationV2(

        @WebParam(partName = "parameters", name = "VerificationV2", targetNamespace = "http://actalisVol/")
        VerificationV2 parameters
    );

    @WebMethod(operationName = "VerifyP7MDetached")
    @Action(input = "http://actalisVol/VerificationService/VerifyP7MDetachedRequest", output = "http://actalisVol/VerificationService/VerifyP7MDetachedResponse")
    @RequestWrapper(localName = "VerifyP7MDetached", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyP7MDetached")
    @ResponseWrapper(localName = "VerifyP7MDetachedResponse", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyP7MDetachedResponse")
    @WebResult(name = "return", targetNamespace = "")
    public it.csi.gatefire.ca.aruba.actalisvol.Return verifyP7MDetached(

        @WebParam(name = "fileContent", targetNamespace = "")
        javax.activation.DataHandler fileContent,
        @WebParam(name = "fileOrig", targetNamespace = "")
        javax.activation.DataHandler fileOrig,
        @WebParam(name = "verifAllaData", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar verifAllaData
    );

    @WebMethod(operationName = "VerificationV3Transient")
    @Action(input = "http://actalisVol.it/actalisVol/services/VerificationV3Transient", output = "http://actalisVol.it/actalisVol/services/VerificationV3Transient")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebResult(name = "VerificationV3TransientResponse", targetNamespace = "http://actalisVol/", partName = "parameters")
    public VerificationV3TransientResponse verificationV3Transient(

        @WebParam(partName = "parameters", name = "VerificationV3Transient", targetNamespace = "http://actalisVol/")
        VerificationV3Transient parameters
    );

    @WebMethod(operationName = "VerifyTimeStampTokenV2Local")
    @Action(input = "http://actalisVol.it/actalisVol/services/VerifyTimeStampTokenV2Local", output = "http://actalisVol.it/actalisVol/services/VerifyTimeStampTokenV2Local")
    @RequestWrapper(localName = "VerifyTimeStampTokenV2Local", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyTimeStampTokenV2Local")
    @ResponseWrapper(localName = "VerifyTimeStampTokenV2LocalResponse", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyTimeStampTokenV2LocalResponse")
    @WebResult(name = "return", targetNamespace = "")
    public it.csi.gatefire.ca.aruba.actalisvol.ReturnTimeStampValidationSL verifyTimeStampTokenV2Local(

        @WebParam(name = "timeStampToken", targetNamespace = "")
        it.csi.gatefire.ca.aruba.actalisvol.FileInfo timeStampToken,
        @WebParam(name = "fileInfo", targetNamespace = "")
        it.csi.gatefire.ca.aruba.actalisvol.FileInfo fileInfo,
        @WebParam(name = "pdfReport", targetNamespace = "")
        boolean pdfReport
    );

    @WebMethod(operationName = "VerificationV3")
    @Action(input = "http://actalisVol.it/actalisVol/services/VerificationV3", output = "http://actalisVol.it/actalisVol/services/VerificationV3")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebResult(name = "VerificationV3Response", targetNamespace = "http://actalisVol/", partName = "parameters")
    public VerificationV3Response verificationV3(

        @WebParam(partName = "parameters", name = "VerificationV3", targetNamespace = "http://actalisVol/")
        VerificationV3 parameters
    );

    @WebMethod(operationName = "VerifyTimeStampDataV2")
    @Action(input = "http://actalisVol/VerificationService/VerifyTimeStampDataV2Request", output = "http://actalisVol/VerificationService/VerifyTimeStampDataV2Response")
    @RequestWrapper(localName = "VerifyTimeStampDataV2", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyTimeStampDataV2")
    @ResponseWrapper(localName = "VerifyTimeStampDataResponseV2", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyTimeStampDataResponseV2")
    @WebResult(name = "return", targetNamespace = "")
    public it.csi.gatefire.ca.aruba.actalisvol.ReturnTimeStampDataValidationSL verifyTimeStampDataV2(

        @WebParam(name = "fileInfo", targetNamespace = "")
        it.csi.gatefire.ca.aruba.actalisvol.FileInfo fileInfo,
        @WebParam(name = "fileContent", targetNamespace = "")
        javax.activation.DataHandler fileContent,
        @WebParam(name = "pdfReport", targetNamespace = "")
        boolean pdfReport
    );

    @WebMethod(operationName = "VerifyP7M")
    @Action(input = "http://actalisVol/VerificationService/VerifyP7MRequest", output = "http://actalisVol/VerificationService/VerifyP7MResponse")
    @RequestWrapper(localName = "VerifyP7M", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyP7M")
    @ResponseWrapper(localName = "VerifyP7MResponse", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyP7MResponse")
    @WebResult(name = "return", targetNamespace = "")
    public it.csi.gatefire.ca.aruba.actalisvol.Return verifyP7M(

        @WebParam(name = "fileContent", targetNamespace = "")
        javax.activation.DataHandler fileContent,
        @WebParam(name = "verifAllaData", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar verifAllaData
    );

    @WebMethod(operationName = "VerifyTimeStampToken")
    @Action(input = "http://actalisVol.it/actalisVol/services/VerifyTimeStampToken", output = "http://actalisVol.it/actalisVol/services/VerifyTimeStampToken")
    @RequestWrapper(localName = "VerifyTimeStampToken", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyTimeStampToken")
    @ResponseWrapper(localName = "VerifyTimeStampTokenResponse", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyTimeStampTokenResponse")
    @WebResult(name = "return", targetNamespace = "")
    public it.csi.gatefire.ca.aruba.actalisvol.ReturnTimeStampValidation verifyTimeStampToken(

        @WebParam(name = "timeStampToken", targetNamespace = "")
        java.lang.String timeStampToken,
        @WebParam(name = "fileTimeStamped", targetNamespace = "")
        javax.activation.DataHandler fileTimeStamped
    );

    @WebMethod(operationName = "VerifyP7MSubLevel")
    @Action(input = "http://actalisVol/VerificationService/VerifyP7MSubLevelRequest", output = "http://actalisVol/VerificationService/VerifyP7MSubLevelResponse")
    @RequestWrapper(localName = "VerifyP7MSubLevel", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyP7MSubLevel")
    @ResponseWrapper(localName = "VerifyP7MSubLevelResponse", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyP7MSubLevelResponse")
    @WebResult(name = "return", targetNamespace = "")
    public it.csi.gatefire.ca.aruba.actalisvol.ReturnSL verifyP7MSubLevel(

        @WebParam(name = "fileContent", targetNamespace = "")
        javax.activation.DataHandler fileContent,
        @WebParam(name = "verifAllaData", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar verifAllaData
    );

    @WebMethod(operationName = "Verification")
    @Action(input = "http://actalisVol.it/actalisVol/services/Verification", output = "http://actalisVol.it/actalisVol/services/Verification")
    @RequestWrapper(localName = "Verification", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.Verification")
    @ResponseWrapper(localName = "VerificationResponse", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerificationResponse")
    @WebResult(name = "Return", targetNamespace = "")
    public it.csi.gatefire.ca.aruba.actalisvol.Return verification(

        @WebParam(name = "fileInfo", targetNamespace = "")
        it.csi.gatefire.ca.aruba.actalisvol.FileInfo fileInfo,
        @WebParam(name = "fileContent", targetNamespace = "")
        javax.activation.DataHandler fileContent,
        @WebParam(name = "verifAllaData", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar verifAllaData
    );

    @WebMethod(operationName = "VerificationV2Local")
    @Action(input = "http://actalisVol.it/actalisVol/services/VerificationV2Local", output = "http://actalisVol.it/actalisVol/services/VerificationV2Local")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebResult(name = "VerificationV2LocalResponse", targetNamespace = "http://actalisVol/", partName = "parameters")
    public VerificationV2LocalResponse verificationV2Local(

        @WebParam(partName = "parameters", name = "VerificationV2Local", targetNamespace = "http://actalisVol/")
        VerificationV2Local parameters
    );

    @WebMethod(operationName = "VerifyPDF")
    @Action(input = "http://actalisVol/VerificationService/VerifyPDFRequest", output = "http://actalisVol/VerificationService/VerifyPDFResponse")
    @RequestWrapper(localName = "VerifyPDF", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyPDF")
    @ResponseWrapper(localName = "VerifyPDFResponse", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyPDFResponse")
    @WebResult(name = "return", targetNamespace = "")
    public it.csi.gatefire.ca.aruba.actalisvol.Return verifyPDF(

        @WebParam(name = "fileContent", targetNamespace = "")
        javax.activation.DataHandler fileContent,
        @WebParam(name = "verifAllaData", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar verifAllaData
    );

    @WebMethod(operationName = "VerifyTimeStampTokenV2")
    @Action(input = "http://actalisVol.it/actalisVol/services/VerifyTimeStampTokenV2", output = "http://actalisVol.it/actalisVol/services/VerifyTimeStampTokenV2")
    @RequestWrapper(localName = "VerifyTimeStampTokenV2", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyTimeStampTokenV2")
    @ResponseWrapper(localName = "VerifyTimeStampTokenResponseV2", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyTimeStampTokenResponseV2")
    @WebResult(name = "return", targetNamespace = "")
    public it.csi.gatefire.ca.aruba.actalisvol.ReturnTimeStampValidationSL verifyTimeStampTokenV2(

        @WebParam(name = "timeStampToken", targetNamespace = "")
        java.lang.String timeStampToken,
        @WebParam(name = "fileInfo", targetNamespace = "")
        it.csi.gatefire.ca.aruba.actalisvol.FileInfo fileInfo,
        @WebParam(name = "fileTimeStamped", targetNamespace = "")
        javax.activation.DataHandler fileTimeStamped,
        @WebParam(name = "pdfReport", targetNamespace = "")
        boolean pdfReport
    );

    @WebMethod(operationName = "VerificationV3Local")
    @Action(input = "http://actalisVol.it/actalisVol/services/VerificationV3Local", output = "http://actalisVol.it/actalisVol/services/VerificationV3Local")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @WebResult(name = "VerificationV3LocalResponse", targetNamespace = "http://actalisVol/", partName = "parameters")
    public VerificationV3LocalResponse verificationV3Local(

        @WebParam(partName = "parameters", name = "VerificationV3Local", targetNamespace = "http://actalisVol/")
        VerificationV3Local parameters
    );

    @WebMethod(operationName = "VerifyCertificate")
    @Action(input = "http://actalisVol/VerificationService/VerifyCertificateRequest", output = "http://actalisVol/VerificationService/VerifyCertificateResponse")
    @RequestWrapper(localName = "VerifyCertificate", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyCertificate")
    @ResponseWrapper(localName = "VerifyCertificateResponse", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyCertificateResponse")
    @WebResult(name = "return", targetNamespace = "")
    public it.csi.gatefire.ca.aruba.actalisvol.ReturnCertificateValidation verifyCertificate(

        @WebParam(name = "Certificate", targetNamespace = "")
        java.lang.String certificate,
        @WebParam(name = "verifAllaData", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar verifAllaData
    );

    @WebMethod(operationName = "VerifyRevocation")
    @Action(input = "http://actalisVol/VerificationService/VerifyRevocationRequest", output = "http://actalisVol/VerificationService/VerifyRevocationResponse")
    @RequestWrapper(localName = "VerifyRevocation", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyRevocation")
    @ResponseWrapper(localName = "VerifyRevocationResponse", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyRevocationResponse")
    @WebResult(name = "return", targetNamespace = "")
    public it.csi.gatefire.ca.aruba.actalisvol.ReturnVerifyRevocation verifyRevocation(

        @WebParam(name = "revocationRequest", targetNamespace = "")
        java.util.List<it.csi.gatefire.ca.aruba.actalisvol.RevocationInfoRequest> revocationRequest,
        @WebParam(name = "verifAllaData", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar verifAllaData
    );

    @WebMethod(operationName = "VerifyXML")
    @Action(input = "http://actalisVol/VerificationService/VerifyXMLRequest", output = "http://actalisVol/VerificationService/VerifyXMLResponse")
    @RequestWrapper(localName = "VerifyXML", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyXML")
    @ResponseWrapper(localName = "VerifyXMLResponse", targetNamespace = "http://actalisVol/", className = "it.csi.gatefire.ca.aruba.actalisvol.VerifyXMLResponse")
    @WebResult(name = "return", targetNamespace = "")
    public it.csi.gatefire.ca.aruba.actalisvol.Return verifyXML(

        @WebParam(name = "fileContent", targetNamespace = "")
        javax.activation.DataHandler fileContent,
        @WebParam(name = "verifAllaData", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar verifAllaData
    );
}