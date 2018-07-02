package org.biblioWebapp.action.livre;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioWebapp.action.AbstractAction;
import org.biblioWebapp.services.generated.livreservice.LivreService;
import org.biblioWebapp.services.generated.types.Livre;

import com.opensymphony.xwork2.ActionSupport;

public class RechercherAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(RechercherAction.class);

	// ==================== Attributs ====================

	// ----- Paramètres en entrée
	
	private String titre;
	private String auteur;
	private String genre;
	private String langue;

	// ----- Eléments en entrée et sortie

	// ----- Eléments en sortie

	private List<Livre> listLivre;

	// ==================== Getters/Setters ====================

	// ----- Paramètres en entrée (setters uniquement)

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}
	
	// ----- Eléments en entrée et sortie (setters et getters)

	// ----- Eléments en sortie (getters uniquement)
	
	public List<Livre> getListLivre() {
		return listLivre;
	}

	// ================= Méthodes d'action ====================

	public String actionAjax() {
		LOGGER.traceEntry();
		String result = ActionSupport.SUCCESS;

		LivreService vLivreService = this.getLivreService();
		listLivre = vLivreService.rechercherLivre(titre, auteur, genre, langue);
		
		LOGGER.traceExit(result);
		return result;
	}



}
