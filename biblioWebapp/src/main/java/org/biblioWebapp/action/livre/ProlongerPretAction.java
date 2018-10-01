package org.biblioWebapp.action.livre;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioWebapp.action.AbstractAction;
import org.biblioWebapp.services.generated.livreservice.LivreService;
import org.biblioWebapp.services.generated.livreservice.ProlongerPretFault_Exception;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action AJAX permettant de prolonger la durée d'un prêt
 * @author Oltenos
 *
 */
public class ProlongerPretAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ProlongerPretAction.class);

	// ==================== Attributs ====================

	// ----- Paramètres en entrée
	
	private int id;

	// ----- Eléments en entrée et sortie

	// ----- Eléments en sortie
	
	private XMLGregorianCalendar newDateRetourPrevue;

	// ==================== Getters/Setters ====================

	// ----- Paramètres en entrée (setters uniquement)

	public void setId(int id) {
		this.id = id;
	}
	
	// ----- Eléments en entrée et sortie (setters et getters)

	// ----- Eléments en sortie (getters uniquement)
	
	public XMLGregorianCalendar getNewDateRetourPrevue() {
		return newDateRetourPrevue;
	}

	// ================= Méthodes d'action ====================

	public String actionAjax() throws ProlongerPretFault_Exception {
		LOGGER.traceEntry();
		String result = ActionSupport.SUCCESS;

		LivreService vLivreService = this.getLivreService();
		newDateRetourPrevue = vLivreService.prolongerPret(id);

		LOGGER.traceExit(result);
		return result;
	}

}
