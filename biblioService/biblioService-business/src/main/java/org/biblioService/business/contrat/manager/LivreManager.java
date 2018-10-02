package org.biblioService.business.contrat.manager;

import java.util.List;

import org.biblioService.model.bean.DispoParBibliotheque;
import org.biblioService.model.bean.Livre;
import org.biblioService.model.bean.Pret;
import org.biblioService.model.bean.Reservation;
import org.biblioService.model.exception.AutreException;
import org.biblioService.model.exception.NotFoundException;
import org.biblioService.model.exception.ParamsInvalidException;
import org.biblioService.model.exception.TechnicalException;
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
	 * @throws AutreException 
	 */
	XMLGregorianCalendar prolongerPret(int pPretId) throws TechnicalException, NotFoundException, AutreException;

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
	 * Si aucun exemplaire n'est dispo, retourne la date du prochain retour et le nombre de personne sur liste d'attente.
	 * @param pISBN
	 * @return Map<String, Integer>
	 * @throws ParamsInvalidException 
	 * @throws NotFoundException 
	 */
	List<DispoParBibliotheque> getDispo(String pISBN) throws NotFoundException;

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

	/**
	 * Créer la reservation correspondante
	 * @param pISBN
	 * @param pBibliotheque
	 * @param pUtilisateurId
	 * @throws AutreException 
	 */
	void createReservation(String pISBN, String pBibliotheque, int pUtilisateurId) throws AutreException;

	/**
	 * Retourne la liste des réservations de l'utilisateur
	 * @param pUtilisateurId
	 * @return List<Reservation>
	 * @throws NotFoundException 
	 */
	List<Reservation> listerReservation(int pUtilisateurId) throws NotFoundException;

	/**
	 * Supprime la reservation
	 * @param pISBN
	 * @param pBibliotheque
	 * @param pUtilisateurId
	 * @throws TechnicalException 
	 */
	void deleteReservation(String pISBN, String pBibliotheque, int pUtilisateurId) throws TechnicalException;

	/**
	 * Création d'un nouveau prêt à la date du jour
	 * @param pUtilisateurId
	 * @param pExemplaireId
	 * @return 
	 * @throws AutreException 
	 */
	int nouveauPret(int pUtilisateurId, int pExemplaireId) throws AutreException;

	/**
	 * Retour d'un livre en fin de Pret
	 * @param pId
	 * @return première reservation sur la liste d'attente, est null si pas de liste d'attente
	 * @throws TechnicalException
	 * @throws AutreException 
	 */
	Reservation retourPret(int pId) throws TechnicalException, AutreException;

	/**
	 * Realise la mise à jour des liste de reservation : 
	 *  - Supprime les reservations dont un livre à été attribué il y a plus de 48h
	 *  - Retourne la liste des reservation passé en tête de liste d'attente 
	 * @return
	 * @throws AutreException 
	 */
	List<Reservation> miseAJourListesReservation() throws AutreException;

	/**
	 * Retourne la liste des prêt dont l'échéance est dans 5 jours
	 * @return
	 */
	List<Pret> infoMailRappel();

}
