package org.biblioService.business.contrat;

import org.biblioService.business.contrat.manager.LivreManager;
import org.biblioService.business.contrat.manager.UtilisateurManager;

/**
 * ManagerFactory
 * 
 * @author Oltenos
 *
 */
public interface ManagerFactory {

	/**
	 * Retourne l'UtilisateurManager
	 * 
	 * @return UtilisateurManager
	 */
	public UtilisateurManager getUtilisateurManager();

	/**
	 * Retourne le LivreManager
	 * @return LivreManager
	 */
	public LivreManager getLivreManager();

}
