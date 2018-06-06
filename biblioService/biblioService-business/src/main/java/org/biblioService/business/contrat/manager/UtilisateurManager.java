package org.biblioService.business.contrat.manager;

import org.biblioService.model.bean.Utilisateur;
import org.biblioService.model.exception.IdentificationException;
import org.biblioService.model.exception.UtilisateurNotFoundException;

/**
 * UtilisateurManager
 * 
 * @author Oltenos
 *
 */
public interface UtilisateurManager {
	
	/**
	 * Enregistre l'utilisateur dans la base de données<br/>
	 * Rq : un sel sera généré et le mot de passe sécurisé
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param mdp
	 */
	public void createUtilisateur(String pNom, String pPrenom, String pEmail, String pMdp);
	
	
	/**
	 * Vérifier les identifiants (email/mot de passse) d'un utilisateur et retourne son identifiant
	 * @param email
	 * @param mdp
	 * @return identifiant de l'utilisateur
	 * @throws IdentificationException lancé en cas d'erreur dans l'authentification
	 * @throws UtilisateurNotFoundException lancé lorque aucun utilisateur ne correspond à l'email
	 */
	public int authentifierUtilisateur(String pEmail, String pMdp) throws IdentificationException, UtilisateurNotFoundException;

	/**
	 * Obtenir les informations de l'utilisateur à partir de son identifiant
	 * @param id
	 * @return Utilisateur
	 * @throws UtilisateurNotFoundException lancé lorque aucun utilisateur ne correspond à l'identifiant
	 */
	public Utilisateur getUtilisateur(int pId) throws UtilisateurNotFoundException;

	/**
	 * Modifier les données de l'utilisateur corespondant à l'id
	 * @param id
	 * @param ancienMdp
	 * @param nouveauNom
	 * @param nouveauPrenom
	 * @param nouveauMail
	 * @param nouveauMdp
	 * @throws IdentificationException lancé en cas d'erreur dans l'authentification (avant modification)
	 * @throws UtilisateurNotFoundException lancé lorque aucun utilisateur ne correspond à l'identifiant
	 */
	public void updateUtilisateur(int pId, String pAncienMdp, String pNouveauNom, String pNouveauPrenom,String pNouveauMail, String pNouveauMdp) throws IdentificationException, UtilisateurNotFoundException;
	
	/**
	 * Supprimer l'utilisateur correspondant à l'id
	 * @param id
	 * @param mdp
	 * @throws IdentificationException lancé en cas d'erreur dans l'authentification (avant suppression)
	 * @throws UtilisateurNotFoundException lancé en cas d'erreur dans l'authentification
	 */
	public void deleteUtilisateur(int pId, String pMdp) throws IdentificationException, UtilisateurNotFoundException;
	
}
