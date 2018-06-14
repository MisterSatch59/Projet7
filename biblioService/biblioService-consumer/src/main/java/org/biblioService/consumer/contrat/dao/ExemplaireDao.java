package org.biblioService.consumer.contrat.dao;

import javax.inject.Named;

import org.biblioService.model.bean.Exemplaire;

@Named("exemplaireDao")
public interface ExemplaireDao {

	/**
	 * Retourne l'exemplaire correpondanat à l'id
	 * @param pId
	 * @return
	 */
	Exemplaire getExemplaire(int pId);

}
