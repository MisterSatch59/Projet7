package org.biblioService.consumer.contrat.dao;

import org.biblioService.model.bean.UtilisateurBD;
import org.biblioService.model.exception.NotFoundException;

/**
 * UtilisateurDAO
 * 
 * @author Oltenos
 *
 */
public interface UtilisateurDao {

	/**
	 * Crée l'utilisateur en paramètre dans la base de données
	 * @param pUtilisateurBD
	 */
	void createUtilisateur(UtilisateurBD pUtilisateurBD);

	/**
	 * Retourne l'utilisateur correspondant à l'email
	 * @param pEmail
	 * @return Utilisateur
	 * @throws NotFoundException lancé lorque aucun utilisateur ne correspond à l'email
	 */
	UtilisateurBD getUtilisateur(String pEmail) throws NotFoundException;

	
	/**
	 * Retourne l'utilisateur correspondant à l'id
	 * @param pId
	 * @return UtilisateurBD
	 * @throws NotFoundException
	 */
	UtilisateurBD getUtilisateur(int pId) throws NotFoundException;

	/**
	 * Modifie utilisateur dans la base de données
	 * @param vUtilisateurBD
	 */
	void updateUtilisateur(UtilisateurBD pUtilisateurBD);

	/**
	 * Supprime l'utilisateur correspondant à l'id de la base de données
	 * @param pId
	 */
	void deleteUtilisateur(int pId);

}
