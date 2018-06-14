package org.biblioService.consumer.contrat.dao;

import org.biblioService.model.bean.Editeur;

public interface EditeurDao {

	/**
	 * Retourne l'Editeur correspondant à l'id
	 * @param pId
	 * @return Editeur
	 */
	Editeur getEditeur(int pId);

}
