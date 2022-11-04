/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.repository.xds.iti57;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;

import org.apache.cxf.annotations.DataBinding;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.lcm.SubmitObjectsRequest;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rs.RegistryResponseType;

import it.csi.gatefire.repository.xds.FseXdsJaxbDataBinding;

/**
 * Provides the ITI-57 web-service interface.
 */
@WebService(targetNamespace = "urn:ihe:iti:xds-b:2010", name = "DocumentRegistry_PortType", portName = "DocumentRegistry_Port_Soap12")
@XmlSeeAlso({
    org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rim.ObjectFactory.class,
    org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.lcm.ObjectFactory.class,
    org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rs.ObjectFactory.class,
    org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.query.ObjectFactory.class })
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@DataBinding(FseXdsJaxbDataBinding.class)
public interface Iti57PortType {

    /**
     * Updates a set of documents according to the ITI-57 specification.
     * @param body
     *          the request.
     * @return the response.
     */
    @WebResult(name = "RegistryResponse",
            targetNamespace = "urn:oasis:names:tc:ebxml-regrep:xsd:rs:3.0",
            partName = "body")
    @Action(input = "urn:ihe:iti:2010:UpdateDocumentSet", output = "urn:ihe:iti:2010:UpdateDocumentSetResponse")
    @WebMethod(operationName = "DocumentRegistry_UpdateDocumentSet")
    RegistryResponseType documentRegistryUpdateDocumentSet(
            @WebParam(partName = "body", name = "SubmitObjectsRequest", targetNamespace = "urn:oasis:names:tc:ebxml-regrep:xsd:lcm:3.0")
                    SubmitObjectsRequest body
    );
}