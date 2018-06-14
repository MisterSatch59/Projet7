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

}
