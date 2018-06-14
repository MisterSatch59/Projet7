package org.biblioService.consumer.contrat.dao;

import java.util.List;

public interface ParagrapheDao {

	/**
	 * Retourne la liste des paragraphes d'une Description à partir de son id
	 * @param pDescritpionId
	 * @return List<String>
	 */
	List<String> getParagraphes(int pDescritpionId);

}
