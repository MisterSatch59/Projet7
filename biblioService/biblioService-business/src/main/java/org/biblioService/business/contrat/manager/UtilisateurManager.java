package org.biblioService.business.contrat.manager;

import org.biblioService.model.bean.Utilisateur;
import org.biblioService.model.exception.AuthentificationException;
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
	 * @throws AuthentificationException lancé en cas d'erreur dans l'authentification
	 * @throws NotFoundException lancé lorque aucun utilisateur ne correspond à l'email
	 */
	public Utilisateur authentifierUtilisateur(String pEmail, String pMdp) throws AuthentificationException, NotFoundException;

	/**
	 * Modifier les données de l'utilisateur correspondant à l'id</br>
	 * Remarque les paramètres (saud pId) peuvent être null ou vide, il ne seront alors pas modifié et l'ancienne valeur sera conservée
	 * @param pId
	 * @param pAncienMdp
	 * @param pNouveauNom
	 * @param pNouveauPrenom
	 * @param pNouveauMail
	 * @param pNouveauMdp
	 * @throws NotFoundException lancé lorque aucun utilisateur ne correspond à l'identifiant
	 * @throws AuthentificationException lancé en cas d'erreur dans l'authentification
	 * @throws ParamsInvalidException  lancé lorque les paramètres reçus sont incorrectes
	 * @throws TechnicalException  lancé lors d'un problème technique de la base de donnée
	 */
	public void updateUtilisateur(int pId, String pAncienMdp, String pNouveauNom, String pNouveauPrenom,String pNouveauMail, String pNouveauMdp) throws NotFoundException, AuthentificationException, ParamsInvalidException, TechnicalException;
	
	/**
	 * Supprimer l'utilisateur correspondant à l'id
	 * @param pId
	 * @param pMdp
	 * @throws NotFoundException lancé lorque aucun utilisateur ne correspond à l'identifiant
	 * @throws AuthentificationException lancé en cas d'erreur dans l'authentification
	 * @throws TechnicalException lancé lors d'un problème technique de la base de donnée
	 * @throws AutreException lancé lors que l'utilisateur à encore des prêt en cours : le compte ne peut pas être supprimé
	 */
	public void deleteUtilisateur(int pId, String pMdp) throws NotFoundException, AuthentificationException,TechnicalException, AutreException ;
	
}
