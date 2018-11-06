package org.biblioService.consumer.contrat.dao;

import javax.inject.Named;

import org.biblioService.model.bean.Exemplaire;

@Named("exemplaireDao")
public interface ExemplaireDao {

	/**
	 * Retourne l'exemplaire correpondanat à l'id
	 * @param pId
	 * @return Exemplaire
	 */
	Exemplaire getExemplaire(int pId);

	/**
	 * Retourne le ,ombre d'explaire du livre concerné dans la bibliotheque concerné
	 * @param pISBN
	 * @param pBibliotheque
	 * @return int
	 */
	int getNbExemplaire(String pISBN, String pBibliotheque);

}
