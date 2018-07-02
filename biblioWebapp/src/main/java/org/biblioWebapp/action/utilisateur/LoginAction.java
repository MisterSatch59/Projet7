package org.biblioWebapp.action.utilisateur;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.biblioWebapp.action.AbstractAction;
import org.biblioWebapp.services.generated.types.Utilisateur;
import org.biblioWebapp.services.generated.utilisateurservice.AuthentifierUtilisateurFault_Exception;
import org.biblioWebapp.services.generated.utilisateurservice.UtilisateurService;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends AbstractAction implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(LoginAction.class);

	// ==================== Attributs ====================

	// ----- Paramètres en entrée

	private String mdp;

	// ----- Eléments en entrée et sortie

	private String email;// Egalement en sortie pour prérenmplir le formulaire avce les données
							// précédente en cas d'erreur.

	// ----- Eléments en sortie

	// ==================== Getters/Setters ====================

	// ----- Paramètres en entrée (setters uniquement)

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	// ----- Eléments en entrée et sortie (setters et getters)

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// ----- Eléments en sortie (getters uniquement)

	// ================= Eléments Struts =======================
	private HttpServletRequest servletRequest;

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
	// ================= Méthodes d'action ====================

	public String action() {
		LOGGER.traceEntry();
		String result = ActionSupport.SUCCESS;

		if (email == null) {// Arrivé sur le formulaire
			result = ActionSupport.INPUT;
		} else {// Traitement du formulaire
			UtilisateurService vUtilisateurService = this.getUtilisateurService();
			try {
				Utilisateur vUtilisateur = vUtilisateurService.authentifierUtilisateur(email, mdp);
				this.servletRequest.changeSessionId();
				this.getSession().put("utilisateur", vUtilisateur);
			} catch (AuthentifierUtilisateurFault_Exception e) {
				addActionError(e.getFaultInfo().getFaultMessage());
				result = ActionSupport.INPUT;
			}

		}

		LOGGER.traceExit(result);
		return result;
	}

	/**
	 * Validation du formulaire
	 */
	@Override
	public void validate() {
		LOGGER.traceEntry();

		if (email != null) {// Pas de validation à réaliser en arrivant sur le formulaire
			if (email.isEmpty()) {
				addFieldError("email", "L'e-mail doit être renseigné.");
			}
			if (mdp.isEmpty()) {
				addFieldError("mdp", "Le mot de passe doit être renseigné.");
			}
		}

		LOGGER.traceExit("hasFieldErrors ? : " + this.hasFieldErrors());
	}

}
