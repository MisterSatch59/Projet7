package org.biblioWebapp.action.livre;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioWebapp.action.AbstractAction;
import org.biblioWebapp.services.generated.livreservice.LivreService;
import org.biblioWebapp.services.generated.livreservice.VoirDispoFault_Exception;
import org.biblioWebapp.services.generated.types.NombreEtBibliotheque;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action AJAX permettant d'obnir la disponibilité d'un livre dans les bibliothèques
 * @author Oltenos
 *
 */
public class DispoAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(DispoAction.class);

	// ==================== Attributs ====================

	// ----- Paramètres en entrée
	
	private String isbn;

	// ----- Eléments en entrée et sortie

	// ----- Eléments en sortie

	private List<NombreEtBibliotheque> listDispo;

	// ==================== Getters/Setters ====================

	// ----- Paramètres en entrée (setters uniquement)
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	// ----- Eléments en entrée et sortie (setters et getters)

	// ----- Eléments en sortie (getters uniquement)
	
	public List<NombreEtBibliotheque> getListDispo() {
		return listDispo;
	}

	// ================= Méthodes d'action ====================

	public String actionAjax() {
		LOGGER.traceEntry();
		String result = ActionSupport.SUCCESS;

		LivreService vLivreService = this.getLivreService();
		try {
			listDispo = vLivreService.voirDispo(isbn);
		} catch (VoirDispoFault_Exception e) {
			addActionError(e.getFaultInfo().getFaultMessage());
		}
		
		LOGGER.traceExit(result);
		return result;
	}

}
