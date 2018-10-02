package org.biblioService.consumer.contrat.dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.biblioService.model.bean.Pret;

public interface PretDao {

	/**
	 * Retoune la liste des Pret en cours dont la date de retour prévue est avant la date en paramètre
	 * 
	 * @param vCalendar
	 * @return List<Pret>
	 */
	List<Pret> getPretDateRetourAvant(Calendar pCalendar);

	/**
	 * Prolonge le pret dont l'id est en paramètre
	 * 
	 * @param pId
	 * @param pDureeSupplementaire
	 *            durée en jours à ajouter à la date de retour prévue
	 */
	void prolongerPret(int pId, int pDureeSupplementaire);

	/**
	 * Retourne la liste des prêt en cours de l'utilisateur
	 * 
	 * @param pUtilisateurId
	 * @return List<Pret>
	 */
	List<Pret> getPretEnCours(int pUtilisateurId);

	/**
	 * Retorune le prêt corespondant à l'id
	 * 
	 * @param pPretId
	 * @return Pret
	 */
	Pret getPret(int pPretId);

	/**
	 * Création d'un nouveau prêt à la date du jour
	 * 
	 * @param pUtilisateurId
	 * @param pExemplaireId
	 * @param vDateFin
	 * @param vDateDebut
	 * @return id
	 */
	int createPret(int pUtilisateurId, int pExemplaireId, Date pDateDebut, Date pDateFin);

	/**
	 * Retour d'un pret
	 * @param pId
	 * @param pDateFin
	 */
	void retourPret(int pId, Date pDateFin);

	/**
	 * Indique si l'exemplaire est déjà emprunté
	 * @param pExemplaireId
	 * @return boolean
	 */
	boolean isEmprunte(int pExemplaireId);

	/**
	 * Supprime le pret
	 * @param pId
	 */
	void delete(int pId);

	/**
	 * Retourne la list des prêt dont la date de retour correspond à celle en paramètre
	 * @param vCalendar
	 * @return List<Pret>
	 */
	List<Pret> getPretRetourPrevuLe(Calendar pCalendar);

}
