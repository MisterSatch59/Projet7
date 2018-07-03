package org.biblioWebapp.action.livre;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioWebapp.action.AbstractAction;
import org.biblioWebapp.services.generated.livreservice.LivreService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action permettant l'affichage des prets en cours de l'utilisateur connecté
 * @author Oltenos
 *
 */
public class ParcourirBiblioAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ParcourirBiblioAction.class);

	// ==================== Attributs ====================

	// ----- Paramètres en entrée

	// ----- Eléments en entrée et sortie

	// ----- Eléments en sortie

	private List<String> genres;
	private List<String> langues;

	// ==================== Getters/Setters ====================

	// ----- Paramètres en entrée (setters uniquement)

	// ----- Eléments en entrée et sortie (setters et getters)

	// ----- Eléments en sortie (getters uniquement)

	public List<String> getGenres() {
		return genres;
	}

	public List<String> getLangues() {
		return langues;
	}

	// ================= Méthodes d'action ====================

	public String action() {
		LOGGER.traceEntry();
		String result = ActionSupport.SUCCESS;

		LivreService vLivreService = this.getLivreService();
		genres = vLivreService.getGenres();
		langues = vLivreService.getLangues();

		LOGGER.traceExit(result);
		return result;
	}

}
