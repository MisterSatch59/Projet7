
package org.biblioService.webapp.utilisateurService.generated;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.4
 * 2018-06-14T08:00:48.907+02:00
 * Generated source version: 3.2.4
 */

@WebFault(name = "supprimerUtilisateurFault", targetNamespace = "http://www.example.org/UtilisateurService/")
public class SupprimerUtilisateurFault_Exception extends Exception {
	private static final long serialVersionUID = 1L;
	
	private SupprimerUtilisateurFault supprimerUtilisateurFault;

    public SupprimerUtilisateurFault_Exception() {
        super();
    }

    public SupprimerUtilisateurFault_Exception(String message) {
        super(message);
    }

    public SupprimerUtilisateurFault_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public SupprimerUtilisateurFault_Exception(String message, SupprimerUtilisateurFault supprimerUtilisateurFault) {
        super(message);
        this.supprimerUtilisateurFault = supprimerUtilisateurFault;
    }

    public SupprimerUtilisateurFault_Exception(String message, SupprimerUtilisateurFault supprimerUtilisateurFault, java.lang.Throwable cause) {
        super(message, cause);
        this.supprimerUtilisateurFault = supprimerUtilisateurFault;
    }

    public SupprimerUtilisateurFault getFaultInfo() {
        return this.supprimerUtilisateurFault;
    }
}
