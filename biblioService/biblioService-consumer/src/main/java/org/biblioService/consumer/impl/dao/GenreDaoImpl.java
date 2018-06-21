package org.biblioService.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.GenreDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Named("genreDao")
public class GenreDaoImpl extends AbstractDaoImpl implements GenreDao {
	private static final Logger LOGGER = LogManager.getLogger(GenreDaoImpl.class);

	@Override
	public List<String> getGenres(String pISBN) {
		LOGGER.traceEntry("pISBN = " + pISBN);

		// Recherche dans la base de données
		String vSQL = "SELECT nom FROM public.livre_genre WHERE isbn = :isbn";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("isbn", pISBN);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<String> vListGenre = vJdbcTemplate.queryForList(vSQL, vParams, String.class);
		if (vListGenre.isEmpty()) {//Retourne null si la recherche ne retourne aucun resultat
			return null;
		} else {// Retoune le liste
			LOGGER.traceExit("vListGenre = " + vListGenre);
			return vListGenre;
		}
	}

	@Override
	public List<String> getGenres() {
		LOGGER.traceEntry();

		// Recherche dans la base de données
		String vSQL = "SELECT nom FROM public.genre ";

		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());

		List<String> vListGenre = vJdbcTemplate.queryForList(vSQL, String.class);

		LOGGER.traceExit("vListGenre = " + vListGenre);
		return vListGenre;
	}
}
