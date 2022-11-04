package it.csi.gatefire.repository.assertion;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.3.5
 * 2021-03-29T15:46:33.503+02:00
 * Generated source version: 3.3.5
 *
 */
@WebServiceClient(name = "wso2carbon-sts",
                  wsdlLocation = "file:/C:/Users/rizzom/AppData/Local/Temp/tempdir8472404402940169856.tmp/assertion_1.wsdl",
                  targetNamespace = "http://ws.apache.org/axis2")
public class Wso2CarbonSts extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ws.apache.org/axis2", "wso2carbon-sts");
    public final static QName Wso2CarbonStsHttpsSoap11Endpoint = new QName("http://ws.apache.org/axis2", "wso2carbon-stsHttpsSoap11Endpoint");
    public final static QName Wso2CarbonStsHttpsSoap12Endpoint = new QName("http://ws.apache.org/axis2", "wso2carbon-stsHttpsSoap12Endpoint");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/rizzom/AppData/Local/Temp/tempdir8472404402940169856.tmp/assertion_1.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(Wso2CarbonSts.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/rizzom/AppData/Local/Temp/tempdir8472404402940169856.tmp/assertion_1.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public Wso2CarbonSts(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public Wso2CarbonSts(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Wso2CarbonSts() {
        super(WSDL_LOCATION, SERVICE);
    }

    public Wso2CarbonSts(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public Wso2CarbonSts(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public Wso2CarbonSts(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns Wso2CarbonStsPortType
     */
    @WebEndpoint(name = "wso2carbon-stsHttpsSoap11Endpoint")
    public Wso2CarbonStsPortType getWso2CarbonStsHttpsSoap11Endpoint() {
        return super.getPort(Wso2CarbonStsHttpsSoap11Endpoint, Wso2CarbonStsPortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Wso2CarbonStsPortType
     */
    @WebEndpoint(name = "wso2carbon-stsHttpsSoap11Endpoint")
    public Wso2CarbonStsPortType getWso2CarbonStsHttpsSoap11Endpoint(WebServiceFeature... features) {
        return super.getPort(Wso2CarbonStsHttpsSoap11Endpoint, Wso2CarbonStsPortType.class, features);
    }


    /**
     *
     * @return
     *     returns Wso2CarbonStsPortType
     */
    @WebEndpoint(name = "wso2carbon-stsHttpsSoap12Endpoint")
    public Wso2CarbonStsPortType getWso2CarbonStsHttpsSoap12Endpoint() {
        return super.getPort(Wso2CarbonStsHttpsSoap12Endpoint, Wso2CarbonStsPortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Wso2CarbonStsPortType
     */
    @WebEndpoint(name = "wso2carbon-stsHttpsSoap12Endpoint")
    public Wso2CarbonStsPortType getWso2CarbonStsHttpsSoap12Endpoint(WebServiceFeature... features) {
        return super.getPort(Wso2CarbonStsHttpsSoap12Endpoint, Wso2CarbonStsPortType.class, features);
    }

}
