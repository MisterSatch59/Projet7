package org.biblioService.consumer.contrat.dao;

import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

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

	/**
	 * Retourne la date du prochain retour prévu pour le livre concerné dans la bibliotheque concerné
	 * @param pBibliotheque
	 * @param pISBN
	 * @return XMLGregorianCalendar
	 */
	XMLGregorianCalendar getProchainRetour(String pBibliotheque, String pISBN);

	/**
	 * Retourne le nombre de personne dans la liste d'attente pour le livre concerné dans la bibliotheque concerné
	 * @param pBibliotheque
	 * @param pISBN
	 * @return Integer
	 */
	Integer getPersonnesEnAttente(String pBibliotheque, String pISBN);

}
