package org.biblioService.business.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.business.contrat.ManagerFactory;
import org.biblioService.business.contrat.manager.LivreManager;
import org.biblioService.business.contrat.manager.UtilisateurManager;


/**
 * Implémentation de ManagerFactory
 * 
 * @author Oltenos
 *
 */
@Named("managerFactory")
public class ManagerFactoryImpl implements ManagerFactory {
	private static final Logger LOGGER = LogManager.getLogger(ManagerFactoryImpl.class);

	@Inject
	private UtilisateurManager utilisateurManager;

	@Override
	public UtilisateurManager getUtilisateurManager() {
		LOGGER.traceExit(utilisateurManager);
		return utilisateurManager;
	}

	@Inject
	private LivreManager livreManager;
	
	@Override
	public LivreManager getLivreManager() {
		LOGGER.traceExit(livreManager);
		return livreManager;
	}
	
	
	
}
