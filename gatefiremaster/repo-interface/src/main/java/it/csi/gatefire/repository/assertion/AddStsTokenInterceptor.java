package it.csi.gatefire.repository.assertion;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.security.SecurityConstants;
import org.apache.cxf.ws.security.tokenstore.SecurityToken;
import org.apache.cxf.ws.security.wss4j.PolicyBasedWSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.PolicyBasedWSS4JOutInterceptor;
import org.apache.cxf.ws.security.wss4j.PolicyBasedWSS4JStaxInInterceptor;
import org.apache.wss4j.common.WSS4JConstants;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AddStsTokenInterceptor extends AbstractSoapInterceptor {
	private SecurityToken securityToken;

	private String wsAddressTo;

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		Header h = findSecurityHeader(message, true);
		Element el = (Element) h.getObject();
		el.appendChild(el.getOwnerDocument().importNode(securityToken.getToken(), true));
		if (StringUtils.hasLength(wsAddressTo))
		{
			setWsAddressTo(message, wsAddressTo);
		}

	}

	public AddStsTokenInterceptor(SecurityToken securityToken, String wsAddressTo) {
		super(Phase.PRE_PROTOCOL);
		addAfter(PolicyBasedWSS4JOutInterceptor.class.getName());
		addAfter(PolicyBasedWSS4JInInterceptor.class.getName());
		addAfter(PolicyBasedWSS4JStaxInInterceptor.class.getName());
		this.securityToken = securityToken;
		this.wsAddressTo = wsAddressTo;
	}

	protected Header findSecurityHeader(SoapMessage message, boolean create) {
		String actor = (String) message.getContextualProperty(SecurityConstants.ACTOR);
		for (Header h : message.getHeaders()) {
			QName n = h.getName();
			if ("Security".equals(n.getLocalPart()) && (n.getNamespaceURI().equals(WSS4JConstants.WSSE_NS)
					|| n.getNamespaceURI().equals(WSS4JConstants.WSSE11_NS))) {
				String receivedActor = ((SoapHeader) h).getActor();
				if (actor == null || actor.equalsIgnoreCase(receivedActor)) {
					return h;
				}
			}
		}
		if (!create) {
			return null;
		}
		Document doc = DOMUtils.getEmptyDocument();
		Element el = doc.createElementNS(WSS4JConstants.WSSE_NS, "wsse:Security");
		el.setAttributeNS(WSS4JConstants.XMLNS_NS, "xmlns:wsse", WSS4JConstants.WSSE_NS);

		SoapHeader sh = new SoapHeader(new QName(WSS4JConstants.WSSE_NS, "Security"), el);
		sh.setMustUnderstand(true);
		if (actor != null && actor.length() > 0) {
			sh.setActor(actor);
		}
		message.getHeaders().add(sh);
		return sh;
	}

	private void setWsAddressTo(SoapMessage message, String to) {
		for (Header h : message.getHeaders()) {
			QName n = h.getName();
			if ("To".equals(n.getLocalPart())) {
				try {
					@SuppressWarnings("unchecked")
					JAXBElement<AttributedURIType> obj = (JAXBElement<AttributedURIType>) h.getObject();

					obj.getValue().setValue(to);

				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}
	}

}
