package org.biblioService.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.BibliothequeDao;
import org.springframework.jdbc.core.JdbcTemplate;

@Named("bibliothequeDao")
public class BibliothequeDaoImpl extends AbstractDaoImpl implements BibliothequeDao {
	private static final Logger LOGGER = LogManager.getLogger(BibliothequeDaoImpl.class);
	

	@Override
	public List<String> getListBibliotheque() {
		// Recherche dans la base de données
		String vSQL = "SELECT * FROM public.bibliotheque";

		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());

		List<String> vListBibliotheque = vJdbcTemplate.queryForList(vSQL, String.class);

		LOGGER.traceExit("vListBibliotheque = " + vListBibliotheque);
		return vListBibliotheque;
	}

}
