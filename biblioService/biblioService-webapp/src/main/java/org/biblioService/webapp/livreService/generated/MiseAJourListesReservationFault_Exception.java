package org.biblioService.webapp.livreService.generated;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-09-26T08:48:31.419+02:00
 * Generated source version: 3.2.5
 */

@WebFault(name = "miseAJourListesReservationFault", targetNamespace = "http://www.example.org/LivreService/")
public class MiseAJourListesReservationFault_Exception extends Exception {
	private static final long serialVersionUID = 1L;
	
	private MiseAJourListesReservationFault miseAJourListesReservationFault;

    public MiseAJourListesReservationFault_Exception() {
        super();
    }

    public MiseAJourListesReservationFault_Exception(String message) {
        super(message);
    }

    public MiseAJourListesReservationFault_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public MiseAJourListesReservationFault_Exception(String message, MiseAJourListesReservationFault miseAJourListesReservationFault) {
        super(message);
        this.miseAJourListesReservationFault = miseAJourListesReservationFault;
    }

    public MiseAJourListesReservationFault_Exception(String message, MiseAJourListesReservationFault miseAJourListesReservationFault, java.lang.Throwable cause) {
        super(message, cause);
        this.miseAJourListesReservationFault = miseAJourListesReservationFault;
    }

    public MiseAJourListesReservationFault getFaultInfo() {
        return this.miseAJourListesReservationFault;
    }
}
