package org.biblioService.consumer.contrat.dao;

import java.util.List;

public interface LangueDao {

	/**
	 * Retourne la liste des langues possibles
	 * @return List<String>
	 */
	List<String> getLangues();

}
