package org.biblioService.consumer.impl.dao;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.AuteurDao;
import org.biblioService.consumer.contrat.dao.DaoFactory;
import org.biblioService.consumer.contrat.dao.DescriptionDao;
import org.biblioService.consumer.contrat.dao.EditeurDao;
import org.biblioService.consumer.contrat.dao.ExemplaireDao;
import org.biblioService.consumer.contrat.dao.GenreDao;
import org.biblioService.consumer.contrat.dao.LivreDao;
import org.biblioService.consumer.contrat.dao.ParagrapheDao;
import org.biblioService.consumer.contrat.dao.PretDao;
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
	
	@Inject
	private LivreDao livreDao;

	@Override
	public LivreDao getLivreDao() {
		LOGGER.traceExit(livreDao);
		return livreDao;
	}

	@Inject
	private ExemplaireDao exemplaireDao;
	
	@Override
	public ExemplaireDao getExemplaireDao() {
		return exemplaireDao;
	}
	
	@Inject
	private PretDao pretDao;

	@Override
	public PretDao getPretDao() {
		return pretDao;
	}
	
	@Inject
	private EditeurDao editeurDao;

	@Override
	public EditeurDao getEditeurDao() {
		return editeurDao;
	}
	
	@Inject
	private DescriptionDao descriptionDao;

	@Override
	public DescriptionDao getDescriptionDao() {
		return descriptionDao;
	}
	
	@Inject
	private AuteurDao auteurDao;

	@Override
	public AuteurDao getAuteurDao() {
		return auteurDao;
	}
	
	@Inject
	private GenreDao genreDao;

	@Override
	public GenreDao getGenreDao() {
		return genreDao;
	}

	@Inject
	private ParagrapheDao paragrapheDao;
	
	@Override
	public ParagrapheDao getParagrapheDao() {
		return paragrapheDao;
	}

}
