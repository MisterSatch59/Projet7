package org.biblioWebapp.action.livre;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioWebapp.action.AbstractAction;
import org.biblioWebapp.services.generated.livreservice.LivreService;
import org.biblioWebapp.services.generated.livreservice.ProlongerPretFault_Exception;

import com.opensymphony.xwork2.ActionSupport;

public class ProlongerPretAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ProlongerPretAction.class);

	// ==================== Attributs ====================

	// ----- Paramètres en entrée
	
	private int id;

	// ----- Eléments en entrée et sortie

	// ----- Eléments en sortie


	// ==================== Getters/Setters ====================

	// ----- Paramètres en entrée (setters uniquement)

	public void setId(int id) {
		this.id = id;
	}
	
	// ----- Eléments en entrée et sortie (setters et getters)

	// ----- Eléments en sortie (getters uniquement)

	// ================= Méthodes d'action ====================

	public String actionAjax() {
		LOGGER.traceEntry();
		String result = ActionSupport.SUCCESS;

		LivreService vLivreService = this.getLivreService();
		try {
			vLivreService.prolongerPret(id);
		} catch (ProlongerPretFault_Exception e) {
			addActionError(e.getFaultInfo().getFaultMessage());
		}

		LOGGER.traceExit(result);
		return result;
	}

}
