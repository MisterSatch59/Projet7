package org.biblioService.consumer.contrat.dao;

import java.util.Calendar;
import java.util.List;

import org.biblioService.model.bean.Pret;

public interface PretDao {
	
	/**
	 * Retoune la liste des Pret en cours dont la date de debut est avant la date en paramètre
	 * @param vCalendar
	 * @return List<Pret>
	 */
	List<Pret> getPretDebutAvant(Calendar pCalendar);

	/**
	 * Prolonge le pret dont l'id est en paramètre
	 * @param pId
	 * @param pDureeSupplementaire durée en jours à ajouter à la date de retour prévue
	 */
	void prolongerPret(int pId, int pDureeSupplementaire);

	/**
	 * Retourne la liste des prêt en cours de l'utilisateur
	 * @param pUtilisateurId
	 * @return List<Pret>
	 */
	List<Pret> getPretEnCours(int pUtilisateurId);

	/**
	 * Retorune le prêt corespondant à l'id
	 * @param pPretId
	 * @return Pret
	 */
	Pret getPret(int pPretId);


}
