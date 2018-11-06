package org.biblioWebapp.action.utilisateur;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioWebapp.action.AbstractAction;
import org.biblioWebapp.services.generated.types.Utilisateur;
import org.biblioWebapp.services.generated.utilisateurservice.ModifierUtilisateurFault1;
import org.biblioWebapp.services.generated.utilisateurservice.ModifierUtilisateurFault_Exception;
import org.biblioWebapp.services.generated.utilisateurservice.UtilisateurService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action d'accés au formulaire et de modification d'un utilisateur
 * @author Oltenos
 *
 */
public class ModifierAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ModifierAction.class);

	// ==================== Attributs ====================

	// ----- Paramètres en entrée

	private String ancienMdp;
	private String mdp;
	private String mdp2;

	// ----- Eléments en entrée et sortie
	private String nom;// Egalement en sortie pour prérenmplir le formulaire avec les données
						// précédente en cas d'erreur.
	private String prenom;
	private String email;
	private boolean mailRappel;

	// ----- Eléments en sortie

	// ==================== Getters/Setters ====================

	// ----- Paramètres en entrée (setters uniquement)

	public void setAncienMdp(String ancienMdp) {
		this.ancienMdp = ancienMdp;
	}

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
	
	public boolean isMailRappel() {
		return mailRappel;
	}

	public void setMailRappel(boolean mailRappel) {
		this.mailRappel = mailRappel;
	}

	// ----- Eléments en sortie (getters uniquement)

	// ================= Méthodes d'action ====================

	public String action() {
		LOGGER.traceEntry();
		String result = ActionSupport.SUCCESS;
		Utilisateur vUtilisateur = (Utilisateur) this.getSession().get("utilisateur");

		if (nom == null) {// Arrivé sur le formulaire
			result = ActionSupport.INPUT;
			nom = vUtilisateur.getNom();
			prenom = vUtilisateur.getPrenom();
			email = vUtilisateur.getEmail();
			mailRappel = vUtilisateur.isMailRappel();
		} else {// Traitement du formulaire
			UtilisateurService vUtilisateurService = this.getUtilisateurService();
			try {
				if (mdp.isEmpty()) {
					vUtilisateurService.modifierUtilisateur(vUtilisateur.getId(), ancienMdp, nom, prenom, email, ancienMdp,mailRappel);
					
				} else {
					vUtilisateurService.modifierUtilisateur(vUtilisateur.getId(), ancienMdp, nom, prenom, email, mdp,mailRappel);
				}
				//Mise à jour de la varible utilisateur en session
				vUtilisateur.setNom(nom);
				vUtilisateur.setPrenom(prenom);
				vUtilisateur.setEmail(email);
				vUtilisateur.setMailRappel(mailRappel);
				this.getSession().put("utilisateur", vUtilisateur);
			} catch (ModifierUtilisateurFault1 e) {
				List<String> messages = e.getFaultInfo().getFaultMessages();
				for (String message : messages) {
					addActionError(message);
				}
				result = ActionSupport.INPUT;
			} catch (ModifierUtilisateurFault_Exception e) {
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

		if (nom != null) {// Pas de validation à réaliser en arrivant sur le formulaire
			if (nom.isEmpty()) {
				addFieldError("nom", "Le nom doit être renseigné.");
			}
			if (email.isEmpty()) {
				addFieldError("email", "L'e-mail doit être renseigné.");
			}
			if (ancienMdp.isEmpty()) {
				addFieldError("ancienMdp", "L'ancien mot de passe doit être renseigné.");
			}

			if (!mdp.isEmpty() && mdp2.isEmpty() || mdp.isEmpty() && !mdp2.isEmpty()) {
				addFieldError("mdp2", "Le nouveau de passe doit être renseigné et confirmé.");
			}

			if(!mdp.isEmpty()) {
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

		}

		LOGGER.traceExit("hasFieldErrors ? : " + this.hasFieldErrors());
	}

}
