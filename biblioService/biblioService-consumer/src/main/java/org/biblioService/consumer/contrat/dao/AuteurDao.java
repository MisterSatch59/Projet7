package org.biblioService.consumer.contrat.dao;

import java.util.List;

import org.biblioService.model.bean.Auteur;

public interface AuteurDao {

	/**
	 * Retourn l'auteur du livre à partir de son ISBN
	 * @param pISBN
	 * @return Auteur
	 */
	List<Auteur> getAuteurs(String pISBN);

}
