package org.biblio.biblioBatch.livre.generated;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.4
 * 2018-07-05T07:47:43.861+02:00
 * Generated source version: 3.2.4
 */

@WebFault(name = "prolongerPretFault", targetNamespace = "http://www.example.org/LivreService/")
public class ProlongerPretFault_Exception extends Exception {
	private static final long serialVersionUID = 1L;
	
	private ProlongerPretFault prolongerPretFault;

    public ProlongerPretFault_Exception() {
        super();
    }

    public ProlongerPretFault_Exception(String message) {
        super(message);
    }

    public ProlongerPretFault_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public ProlongerPretFault_Exception(String message, ProlongerPretFault prolongerPretFault) {
        super(message);
        this.prolongerPretFault = prolongerPretFault;
    }

    public ProlongerPretFault_Exception(String message, ProlongerPretFault prolongerPretFault, java.lang.Throwable cause) {
        super(message, cause);
        this.prolongerPretFault = prolongerPretFault;
    }

    public ProlongerPretFault getFaultInfo() {
        return this.prolongerPretFault;
    }
}
