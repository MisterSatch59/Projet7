package org.biblioService.business.contrat.manager;

import java.util.List;

import org.biblioService.model.bean.Pret;

public interface LivreManager {

	/**
	 * Retourne la liste des prêt en retard
	 * @return List<Pret>
	 */
	List<Pret> getPretEnRetard();

}
