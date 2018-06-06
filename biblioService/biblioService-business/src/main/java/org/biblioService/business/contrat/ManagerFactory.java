package org.biblioService.business.contrat;

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

}
