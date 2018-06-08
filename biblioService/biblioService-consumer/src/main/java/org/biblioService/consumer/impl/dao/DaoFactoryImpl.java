package org.biblioService.consumer.impl.dao;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.DaoFactory;
import org.biblioService.consumer.contrat.dao.UtilisateurDao;

/**
 * Implementation de DaoFactory
 * 
 * @author Oltenos
 *
 */
@Named("daoFactory")
public class DaoFactoryImpl implements DaoFactory {
	private static final Logger LOGGER = LogManager.getLogger(DaoFactoryImpl.class);

	@Inject
	private UtilisateurDao utilisateurDao;

	@Override
	public UtilisateurDao getUtilisateurDao() {
		LOGGER.traceExit(utilisateurDao);
		return utilisateurDao;
	}

}
