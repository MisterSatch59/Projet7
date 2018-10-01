package org.biblioService.consumer.contrat.dao;

import org.biblioService.model.bean.UtilisateurBD;

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
	void createUtilisateurBD(UtilisateurBD pUtilisateurBD);

	/**
	 * Retourne l'utilisateur correspondant à l'email
	 * @param pEmail
	 * @return Utilisateur
	 */
	UtilisateurBD getUtilisateurBD(String pEmail);

	/**
	 * Retourne l'utilisateur correspondant à l'id
	 * @param pId
	 * @return UtilisateurBD
	 */
	UtilisateurBD getUtilisateurBD(int pId);

	/**
	 * Modifie utilisateur dans la base de données
	 * @param vUtilisateurBD
	 */
	void updateUtilisateurBD(UtilisateurBD pUtilisateurBD);

	/**
	 * Supprime l'utilisateur correspondant à l'id de la base de données
	 * @param pId
	 */
	void deleteUtilisateur(int pId);

}
