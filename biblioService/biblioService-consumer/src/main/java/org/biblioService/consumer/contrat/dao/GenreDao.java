package org.biblioService.consumer.contrat.dao;

import java.util.List;

public interface GenreDao {

	/**
	 * Retourn la liste des genres du livre à partir de son ISBN
	 * @param pISBN
	 * @return String
	 */
	List<String> getGenres(String vISBN);

}
