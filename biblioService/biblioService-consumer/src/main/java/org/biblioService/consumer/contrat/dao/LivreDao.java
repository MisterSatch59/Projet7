package org.biblioService.consumer.contrat.dao;

import java.util.List;
import java.util.Map;

import org.biblioService.model.bean.Livre;

public interface LivreDao {

	/**
	 * Retourne le livre correspoindant à l'ISBN
	 * @param vISBN
	 * @return Livre
	 */
	Livre getLivre(String pISBN);

	/**
	 * Retourne la liste des livres correspondants aux critères de recherche
	 * @param pTitre
	 * @param pAuteur
	 * @param pGenre
	 * @param pLangue
	 * @return List<Livre>
	 */
	List<Livre> rechercherLivre(String pTitre, String pAuteur, String pGenre, String pLangue);

	/**
	 * Retourne le nombre d'exemplaire disponible d'un livre par bibliotheque
	 * @param pISBN
	 * @return Map<String, Integer>
	 */
	Map<String, Integer> getDispo(String pISBN);

}
