package it.csi.gatefire.repository.assertion;

import java.net.URI;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.cxf.rt.security.claims.Claim;
import org.apache.cxf.rt.security.claims.ClaimCollection;

public class GatefireClaimCollection extends ClaimCollection {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private URI dialect = URI.create("http://schemas.xmlsoap.org/ws/2005/05/identity");
	private String dialectPrefix = "wsid";

	@Override
	public URI getDialect() {
		return dialect;
	}

	@Override
	public void setDialect(URI dialect) {
		this.dialect = dialect;
	}

	private URI claimsDialect = URI.create("http://wso2.org/claims");

	private String claimsPrefix = "wsp";

	@Override
	public void serialize(XMLStreamWriter writer, String prefix, String namespace) throws XMLStreamException {
		writer.writeStartElement(prefix, "Claims", namespace);
		//writer.writeNamespace(claimsPrefix, namespace);
		writer.writeAttribute(null, "Dialect", claimsDialect.toString());

		for (Claim claim : this) {
			claim.serialize(writer, dialectPrefix, dialect.toString());
		}

		writer.writeEndElement();
	}

	@Override
	public String getDialectPrefix() {
		return dialectPrefix;
	}

	@Override
	public void setDialectPrefix(String dialectPrefix) {
		this.dialectPrefix = dialectPrefix;
	}

	public URI getClaimsDialect() {
		return claimsDialect;
	}

	public void setClaimsDialect(URI claimsDialect) {
		this.claimsDialect = claimsDialect;
	}

	public String getClaimsPrefix() {
		return claimsPrefix;
	}

	public void setClaimsPrefix(String claimsPrefix) {
		this.claimsPrefix = claimsPrefix;
	}

}
