package org.biblioService.consumer.contrat.dao;

import org.biblioService.model.bean.Livre;

public interface LivreDao {

	/**
	 * Retourne le livre correspoindant à l'ISBN
	 * @param vISBN
	 * @return Livre
	 */
	Livre getLivre(String vISBN);

}
