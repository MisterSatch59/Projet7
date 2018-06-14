package org.biblioService.consumer.impl.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.LivreDao;
import org.biblioService.consumer.impl.rowmapper.LivreRM;
import org.biblioService.model.bean.Livre;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Named("livreDao")
public class LivreDaoImpl extends AbstractDaoImpl implements LivreDao{
private static final Logger LOGGER = LogManager.getLogger(AuteurDaoImpl.class);
	
	@Inject
	private LivreRM livreRM;

	@Override
	public Livre getLivre(String pISBN) {
		LOGGER.traceEntry("vISBN = " + pISBN);

		// Recherche dans la base de données
		String vSQL = "SELECT * FROM public.livre WHERE isbn = :isbn";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("isbn", pISBN);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<Livre> vListLivre = vJdbcTemplate.query(vSQL, vParams, livreRM);
		if (vListLivre.isEmpty()) {//Retourne null si la recherche ne retourne aucun resultat
			return null;
		} else {// Retoune le premier (et unique) élément sinon
			LOGGER.traceExit(vListLivre.get(0));
			return vListLivre.get(0);
		}
	}

}
