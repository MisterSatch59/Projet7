package org.biblioService.consumer.contrat.dao;

import java.sql.Date;
import java.util.List;

import org.biblioService.model.bean.Reservation;

public interface ReservationDao {

	/**
	 * Créer la reservation correspondante dan sla base de données
	 * @param pISBN
	 * @param pBibliotheque
	 * @param pUtilisateurId
	 */
	void createReservation(String pISBN, String pBibliotheque, int pUtilisateurId);

	/**
	 * Retourne la liste des réservation de l'utilisateur
	 * @param pUtilisateurId
	 * @return List<Reservation>
	 */
	List<Reservation> getReservation(int pUtilisateurId);

	/**
	 * Supprime la reservation
	 * @param pISBN
	 * @param pBibliotheque
	 * @param pUtilisateurId
	 */
	void deleteReservation(String pISBN, String pBibliotheque, int pUtilisateurId);

	/**
	 * Retourne le nombre de reservation du livre dans la bibliotheque
	 * @return int
	 */
	int getNbReservation(String pISBN, String pBibliotheque);

	/**
	 * Retourne la réservation la plus ancienne pour le livre concerné dans la bibliothéque concerné
	 * retourne null si aucune liste d'attente
	 * @param pBibliotheque
	 * @param pIsbn
	 * @return Reservation ou null
	 */
	Reservation getPremierReservation(String pBibliotheque, String pIsbn);

	/**
	 * Enregistre en persistance la date d'attribution d'un exemplaire à la réservation
	 * @param pBibliotheque
	 * @param pUtilisateurId
	 * @param pIsbn
	 * @param pDateAttribution
	 */
	void setAttribue(String pBibliotheque, int pUtilisateurId, String pIsbn, Date pDateAttribution);

	/**
	 * Retourne la list des reservation dont la date d'attribution est antérieur à la date en paramètre
	 * @param pDate
	 * @return List<Reservation>
	 */
	List<Reservation> getListReservationDateAttributionAvant(Date pDate);

}
