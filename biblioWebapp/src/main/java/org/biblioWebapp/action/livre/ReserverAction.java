package org.biblioWebapp.action.livre;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioWebapp.action.AbstractAction;
import org.biblioWebapp.services.generated.livreservice.CreerReservationFault_Exception;
import org.biblioWebapp.services.generated.livreservice.LivreService;
import org.biblioWebapp.services.generated.livreservice.SupprimerReservationFault_Exception;
import org.biblioWebapp.services.generated.types.Utilisateur;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action pour réserver un livre indisponible
 * @author Oltenos
 *
 */
public class ReserverAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ReserverAction.class);

	// ==================== Attributs ====================

	// ----- Paramètres en entrée
	
	private String isbn;
	private String bibliotheque;

	// ----- Eléments en entrée et sortie

	// ----- Eléments en sortie

	// ==================== Getters/Setters ====================

	// ----- Paramètres en entrée (setters uniquement)
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setBibliotheque(String bibliotheque) {
		this.bibliotheque = bibliotheque;
	}
	
	
	// ----- Eléments en entrée et sortie (setters et getters)

	// ----- Eléments en sortie (getters uniquement)

	// ================= Méthodes d'action ====================

	public String action() {
		LOGGER.traceEntry("isbn = " + isbn + " - bibliotheque = " + bibliotheque);
		String result = ActionSupport.SUCCESS;
		
		Utilisateur vUtilisateur = (Utilisateur) this.getSession().get("utilisateur");

		LivreService vLivreService = this.getLivreService();
		try {
			vLivreService.creerReservation(isbn, bibliotheque, vUtilisateur.getId());
		} catch (CreerReservationFault_Exception e) {
			addActionMessage(e.getMessage() + " : " + e.getFaultInfo().getFaultMessage());
		}
		
		LOGGER.traceExit(result);
		return result;
	}
	
	public String actionAjaxAnnuler() throws SupprimerReservationFault_Exception {
		LOGGER.traceEntry("isbn = " + isbn + " - bibliotheque = " + bibliotheque);
		String result = ActionSupport.SUCCESS;
		
		Utilisateur vUtilisateur = (Utilisateur) this.getSession().get("utilisateur");

		LivreService vLivreService = this.getLivreService();
		vLivreService.supprimerReservation(isbn, bibliotheque, vUtilisateur.getId());
		
		LOGGER.traceExit(result);
		return result;
	}
	
}
