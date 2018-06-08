package org.biblioService.business.contrat.manager;

import org.biblioService.model.bean.Utilisateur;
import org.biblioService.model.exception.AutreException;
import org.biblioService.model.exception.ParamsInvalidException;
import org.biblioService.model.exception.TechnicalException;
import org.biblioService.model.exception.NotFoundException;

/**
 * UtilisateurManager
 * 
 * @author Oltenos
 *
 */
public interface UtilisateurManager {
	
	/**
	 * Creer un Utilisateur
	 * @param pNom
	 * @param pPrenom
	 * @param pEmail
	 * @param pMdp
	 * @throws ParamsInvalidException lancé lorque les paramètres reçus sont incorrectes
	 * @throws TechnicalException lancé lors d'un problème technique de la base de donnée
	 */
	public void createUtilisateur(String pNom, String pPrenom, String pEmail, String pMdp) throws ParamsInvalidException, TechnicalException;
	
	
	/**
	 * Vérifier les identifiants (email/mot de passse) d'un utilisateur et retourne ses informations
	 * @param pEmail
	 * @param pMdp
	 * @return Utilisateur
	 * @throws AutreException lancé en cas d'erreur dans l'authentification
	 * @throws NotFoundException lancé lorque aucun utilisateur ne correspond à l'email
	 */
	public Utilisateur authentifierUtilisateur(String pEmail, String pMdp) throws AutreException, NotFoundException;

	/**
	 * Modifier les données de l'utilisateur corespondant à l'id</br>
	 * Remarque les paramètre (saud pId) peuvent être null ouvide, il seront alors modifié et l'ancienne valeur conservée
	 * @param pId
	 * @param pNouveauNom
	 * @param pNouveauPrenom
	 * @param pNouveauMail
	 * @param pNouveauMdp
	 * @throws NotFoundException lancé lorque aucun utilisateur ne correspond à l'identifiant
	 * @throws ParamsInvalidException lancé lorque les paramètres reçus sont incorrectes
	 * @throws TechnicalException lancé lors d'un problème technique de la base de donnée
	 */
	public void updateUtilisateur(int pId, String pNouveauNom, String pNouveauPrenom,String pNouveauMail, String pNouveauMdp) throws NotFoundException, ParamsInvalidException, TechnicalException;
	
	/**
	 * Supprimer l'utilisateur correspondant à l'id
	 * @param pId
	 * @throws TechnicalException lancé lors d'un problème technique de la base de donnée
	 * @throws AutreException lancé lors que l'utilisateur à encore des prêt en cours : le compte ne peut pas être supprimé
	 */
	public void deleteUtilisateur(int pId) throws TechnicalException, AutreException ;
	
}
