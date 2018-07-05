package org.biblioService.business.contrat.manager;

import java.util.List;

import org.biblioService.model.bean.Livre;
import org.biblioService.model.bean.Pret;
import org.biblioService.model.exception.NotFoundException;
import org.biblioService.model.exception.TechnicalException;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

public interface LivreManager {

	/**
	 * Retourne la liste des prêt en retard
	 * @return List<Pret>
	 */
	List<Pret> getPretEnRetard();

	/**
	 * Prolonge le pret dont l'id est en paramètre
	 * @param pPretId
	 * @return 
	 * @throws TechnicalException 
	 * @throws NotFoundException 
	 */
	XMLGregorianCalendar prolongerPret(int pPretId) throws TechnicalException, NotFoundException;

	/**
	 * Retourne la liste des prêt en cours de l'utilisateur
	 * @param pUtilisateurId
	 * @return List<Pret>
	 * @throws NotFoundException lancé si l'utilisateur n'existe pas
	 */
	List<Pret> getPretEnCours(int pUtilisateurId) throws NotFoundException;

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
	 * Retourne la liste des genres
	 * @return List<String>
	 */
	List<String> getGenres();

	/**
	 * Retourne la liste des langues
	 * @return List<String>
	 */
	List<String> getLangues();

}
