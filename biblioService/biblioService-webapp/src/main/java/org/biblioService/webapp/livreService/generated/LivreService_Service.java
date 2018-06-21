package org.biblioService.webapp.livreService.generated;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.4
 * 2018-06-19T21:40:55.695+02:00
 * Generated source version: 3.2.4
 *
 */
@WebServiceClient(name = "LivreService",
                  wsdlLocation = "file:/C:/dev/eclipse-workspace/Projet4/biblioService/biblioService-webapp/src/main/resources/wsdl/LivreService.wsdl",
                  targetNamespace = "http://www.example.org/LivreService/")
public class LivreService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.example.org/LivreService/", "LivreService");
    public final static QName LivreServiceSOAP = new QName("http://www.example.org/LivreService/", "LivreServiceSOAP");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/dev/eclipse-workspace/Projet4/biblioService/biblioService-webapp/src/main/resources/wsdl/LivreService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(LivreService_Service.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:/C:/dev/eclipse-workspace/Projet4/biblioService/biblioService-webapp/src/main/resources/wsdl/LivreService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public LivreService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public LivreService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LivreService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    public LivreService_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public LivreService_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public LivreService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns LivreService
     */
    @WebEndpoint(name = "LivreServiceSOAP")
    public LivreService getLivreServiceSOAP() {
        return super.getPort(LivreServiceSOAP, LivreService.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LivreService
     */
    @WebEndpoint(name = "LivreServiceSOAP")
    public LivreService getLivreServiceSOAP(WebServiceFeature... features) {
        return super.getPort(LivreServiceSOAP, LivreService.class, features);
    }

}