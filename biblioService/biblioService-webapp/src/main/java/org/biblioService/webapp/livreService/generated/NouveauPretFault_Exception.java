package org.biblioService.webapp.livreService.generated;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-09-26T08:07:25.901+02:00
 * Generated source version: 3.2.5
 */

@WebFault(name = "nouveauPretFault", targetNamespace = "http://www.example.org/LivreService/")
public class NouveauPretFault_Exception extends Exception {
	private static final long serialVersionUID = 1L;
	
	private NouveauPretFault nouveauPretFault;

    public NouveauPretFault_Exception() {
        super();
    }

    public NouveauPretFault_Exception(String message) {
        super(message);
    }

    public NouveauPretFault_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public NouveauPretFault_Exception(String message, NouveauPretFault nouveauPretFault) {
        super(message);
        this.nouveauPretFault = nouveauPretFault;
    }

    public NouveauPretFault_Exception(String message, NouveauPretFault nouveauPretFault, java.lang.Throwable cause) {
        super(message, cause);
        this.nouveauPretFault = nouveauPretFault;
    }

    public NouveauPretFault getFaultInfo() {
        return this.nouveauPretFault;
    }
}
