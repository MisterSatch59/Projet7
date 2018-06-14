package org.biblioService.consumer.contrat.dao;

import org.biblioService.model.bean.Description;

public interface DescriptionDao {

	/**
	 * Retourne la Description correspondant à l'id
	 * @param pId
	 * @return Description
	 */
	Description getDescription(int pId);

}
