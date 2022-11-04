/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.repository.xds.iti41;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;

import org.apache.cxf.annotations.DataBinding;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.ProvideAndRegisterDocumentSetRequestType;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rs.RegistryResponseType;

import it.csi.gatefire.repository.xds.FseXdsJaxbDataBinding;

/**
 * Provides the ITI-41 web-service interface.
 */
@WebService(targetNamespace = "urn:ihe:iti:xds-b:2007", name = "DocumentRepository_PortType", portName = "DocumentRepository_Port_Soap12")
@XmlSeeAlso({ org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rim.ObjectFactory.class,
		org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.lcm.ObjectFactory.class,
		org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rs.ObjectFactory.class,
		org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.query.ObjectFactory.class })
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@DataBinding(FseXdsJaxbDataBinding.class)
public interface Iti41PortType {

	/**
	 * Provides and registers a set of documents according to the ITI-41
	 * specification.
	 * 
	 * @param body the request.
	 * @return the response.
	 */
	@WebResult(name = "RegistryResponse", targetNamespace = "urn:oasis:names:tc:ebxml-regrep:xsd:rs:3.0", partName = "body")
	@Action(input = "urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-b", output = "urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-bResponse")
	@WebMethod(operationName = "DocumentRepository_ProvideAndRegisterDocumentSet-b")
	RegistryResponseType documentRepositoryProvideAndRegisterDocumentSetB(
			@WebParam(partName = "body", name = "ProvideAndRegisterDocumentSetRequest", targetNamespace = "urn:ihe:iti:xds-b:2007") ProvideAndRegisterDocumentSetRequestType body);
}