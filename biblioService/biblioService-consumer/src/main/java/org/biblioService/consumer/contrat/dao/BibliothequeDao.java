package org.biblioService.consumer.contrat.dao;

import java.util.List;

public interface BibliothequeDao {
	
	/**
	 * Retourne la liste des bibliotheque de la ville
	 * @return List<String>
	 */
	List<String> getListBibliotheque();

}
