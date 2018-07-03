package org.biblioWebapp.action.livre;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioWebapp.action.AbstractAction;
import org.biblioWebapp.services.generated.livreservice.ListerPretEnCoursFault_Exception;
import org.biblioWebapp.services.generated.livreservice.LivreService;
import org.biblioWebapp.services.generated.types.Pret;
import org.biblioWebapp.services.generated.types.Utilisateur;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action permettant l'affichage des prets en cours de l'utilisateur
 * @author Oltenos
 *
 */
public class PretEnCoursAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(PretEnCoursAction.class);

	// ==================== Attributs ====================

	// ----- Paramètres en entrée

	// ----- Eléments en entrée et sortie

	// ----- Eléments en sortie

	private List<Pret> listPret;

	// ==================== Getters/Setters ====================

	// ----- Paramètres en entrée (setters uniquement)

	// ----- Eléments en entrée et sortie (setters et getters)

	// ----- Eléments en sortie (getters uniquement)

	public List<Pret> getListPret() {
		return listPret;
	}

	// ================= Méthodes d'action ====================

	public String action() {
		LOGGER.traceEntry();
		String result = ActionSupport.SUCCESS;
		
		Utilisateur vUtilisateur = (Utilisateur) this.getSession().get("utilisateur");

		LivreService vLivreService = this.getLivreService();
		try {
			listPret = vLivreService.listerPretEnCours(vUtilisateur.getId());
		} catch (ListerPretEnCoursFault_Exception e) {
			addActionError(e.getFaultInfo().getFaultMessage());
		}
		
		LOGGER.traceExit(result);
		return result;
	}

}
