package org.biblioWebapp.action.utilisateur;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioWebapp.action.AbstractAction;
import org.biblioWebapp.services.generated.utilisateurservice.CreerUtilisateurFault1;
import org.biblioWebapp.services.generated.utilisateurservice.CreerUtilisateurFault_Exception;
import org.biblioWebapp.services.generated.utilisateurservice.UtilisateurService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action d'accés au formulaire et de création d'un utilisateur
 * @author Oltenos
 *
 */
public class CreerAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(CreerAction.class);

	// ==================== Attributs ====================

	// ----- Paramètres en entrée

	private String mdp;
	private String mdp2;

	// ----- Eléments en entrée et sortie
	private String nom;//Egalement en sortie pour prérenmplir le formulaire avce les données précédente en cas d'erreur.
	private String prenom;
	private String email;
	
	// ----- Eléments en sortie

	// ==================== Getters/Setters ====================

	// ----- Paramètres en entrée (setters uniquement)

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public void setMdp2(String mdp2) {
		this.mdp2 = mdp2;
	}

	// ----- Eléments en entrée et sortie (setters et getters)
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	// ----- Eléments en sortie (getters uniquement)

	// ================= Méthodes d'action ====================

	public String action() {
		LOGGER.traceEntry();
		String result = ActionSupport.SUCCESS;

		if (nom == null) {// Arrivé sur le formulaire
			result = ActionSupport.INPUT;
		} else {// Traitement du formulaire
			UtilisateurService vUtilisateurService = this.getUtilisateurService();
			try {
				vUtilisateurService.creerUtilisateur(nom, prenom, email, mdp);
			} catch (CreerUtilisateurFault1 e) {
				addActionError(e.getFaultInfo().getFaultMessage());
				result = ActionSupport.INPUT;
			} catch (CreerUtilisateurFault_Exception e) {
				List<String> messages = e.getFaultInfo().getFaultMessages();
				for (String message : messages) {
					addActionError(message);
				}
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

		if (nom != null) {// Pas de validation à réaliser en arrivant sur le formulaire
			if(nom.isEmpty()) {
				addFieldError("nom", "Le nom doit être renseigné.");
			}
			if(email.isEmpty()) {
				addFieldError("email", "L'e-mail doit être renseigné.");
			}
			if(mdp.isEmpty()) {
				addFieldError("mdp", "Le mot de passe doit être renseigné.");
			}
			if(mdp2.isEmpty()) {
				addFieldError("mdp2", "La confirmation du mot de passe doit être renseignée.");
			}

			if (!mdp.equals(mdp2)) {// verification de la conconcordance de cla confirmation du mdp
				addFieldError("mdp2", "La confirmation du mot de passe ne correspond pas.");
			} else {
				// Vérification de la compléxité du mot de passe (8 caractères, 1 lettre, 1
				// caractère spécial et 1 chiffre minimum)
				Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*\\W).{8,}$");
				Matcher matcher = pattern.matcher(mdp);
				if (!matcher.lookingAt()) {
					addFieldError("mdp", "Le mot de passe doit comporter 8 caractères, 1 lettre, 1 caractère spécial et 1 chiffre minimum.");
				}
			}

		}

		LOGGER.traceExit("hasFieldErrors ? : " + this.hasFieldErrors());
	}

}
