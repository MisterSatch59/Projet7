package org.biblioService.webapp.contrat;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.biblioService.model.bean.Utilisateur;
import org.biblioService.model.exception.IdentificationException;
import org.biblioService.model.exception.UtilisateurNotFoundException;

@WebService (name = "UtilisateurService")
public interface UtilisateurService {
	
	/**
	 * Creer un Utilisateur
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param mdp
	 */
	@WebMethod
	public void creerUtilisateur(@WebParam(name="nom") String pNom, @WebParam(name="prenom") String pPrenom, @WebParam(name="email") String pEmail, @WebParam(name="mdp") String pMdp);
	
	/**
	 * Vérifier les identifiants (email/mot de passse) d'un utilisateur et retourne son identifiant
	 * @param email
	 * @param mdp
	 * @return identifiant de l'utilisateur
	 * @throws IdentificationException lancé en cas d'erreur dans l'authentification
	 * @throws UtilisateurNotFoundException lancé lorque aucun utilisateur ne correspond à l'email
	 */
	@WebMethod
	public int authentifierUtilisateur(@WebParam(name="email") String pEmail, @WebParam(name="mdp") String pMdp) throws IdentificationException, UtilisateurNotFoundException;

	/**
	 * Obtenir les informations de l'utilisateur à partir de son identifiant
	 * @param id
	 * @return Utilisateur
	 * @throws UtilisateurNotFoundException lancé lorque aucun utilisateur ne correspond à l'identifiant
	 */
	@WebMethod
	public Utilisateur infoUtilisateur(@WebParam(name="id") int pId) throws UtilisateurNotFoundException;

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
	@WebMethod
	public void modifierUtilisateur(@WebParam(name="id") int pId, @WebParam(name="ancienMdp") String pAncienMdp, @WebParam(name="nouveauNom") String pNouveauNom, @WebParam(name="nouveauPrenom") String pNouveauPrenom, @WebParam(name="nouveauEmail") String pNouveauMail, @WebParam(name="nouveauMdp") String pNouveauMdp) throws IdentificationException, UtilisateurNotFoundException;
	
	/**
	 * Supprimer l'utilisateur correspondant à l'id
	 * @param id
	 * @param mdp
	 * @throws IdentificationException lancé en cas d'erreur dans l'authentification (avant suppression)
	 * @throws UtilisateurNotFoundException lancé en cas d'erreur dans l'authentification
	 */
	@WebMethod
	public void supprimerUtilisateur(@WebParam(name="id") int pId, @WebParam(name="mdp") String pMdp) throws IdentificationException, UtilisateurNotFoundException;
	
}
