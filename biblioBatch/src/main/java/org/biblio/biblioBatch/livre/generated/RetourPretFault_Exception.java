package org.biblio.biblioBatch.livre.generated;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.4
 * 2018-09-27T17:14:38.612+02:00
 * Generated source version: 3.2.4
 */

@WebFault(name = "retourPretFault", targetNamespace = "http://www.example.org/LivreService/")
public class RetourPretFault_Exception extends Exception {
	private static final long serialVersionUID = 1L;
	
	private RetourPretFault retourPretFault;

    public RetourPretFault_Exception() {
        super();
    }

    public RetourPretFault_Exception(String message) {
        super(message);
    }

    public RetourPretFault_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public RetourPretFault_Exception(String message, RetourPretFault retourPretFault) {
        super(message);
        this.retourPretFault = retourPretFault;
    }

    public RetourPretFault_Exception(String message, RetourPretFault retourPretFault, java.lang.Throwable cause) {
        super(message, cause);
        this.retourPretFault = retourPretFault;
    }

    public RetourPretFault getFaultInfo() {
        return this.retourPretFault;
    }
}
