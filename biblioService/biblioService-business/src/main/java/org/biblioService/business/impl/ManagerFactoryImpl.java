package org.biblioService.business.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.biblioService.business.contrat.ManagerFactory;
import org.biblioService.business.contrat.manager.UtilisateurManager;


/**
 * Implémentation de ManagerFactory
 * 
 * @author Oltenos
 *
 */
@Named("managerFactory")
public class ManagerFactoryImpl implements ManagerFactory {

	@Inject
	private UtilisateurManager utilisateurManager;

	@Override
	public UtilisateurManager getUtilisateurManager() {
		return utilisateurManager;
	}
	
}
