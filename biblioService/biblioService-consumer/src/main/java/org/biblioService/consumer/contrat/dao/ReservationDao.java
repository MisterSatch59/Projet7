package org.biblioService.consumer.contrat.dao;

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
	 * @return
	 */
	int getNbReservation(String pISBN, String pBibliotheque);

}
