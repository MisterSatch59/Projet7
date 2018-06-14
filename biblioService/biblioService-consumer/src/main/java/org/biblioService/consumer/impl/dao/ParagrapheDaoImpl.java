package org.biblioService.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.ParagrapheDao;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Named("paragrapheDao")
public class ParagrapheDaoImpl extends AbstractDaoImpl implements ParagrapheDao{
	private static final Logger LOGGER = LogManager.getLogger(ParagrapheDaoImpl.class);

	@Override
	public List<String> getParagraphes(int pDescritpionId) {
		LOGGER.traceEntry("pDescritpionId = " + pDescritpionId);

		// Recherche dans la base de données
		String vSQL = "SELECT texte FROM public.paragraphe WHERE decription_id = :description_id ORDER BY ordre ASC";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("description_id", pDescritpionId);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<String> vListParagraphes = vJdbcTemplate.queryForList(vSQL, vParams, String.class);
		if (vListParagraphes.isEmpty()) {//Retourne null si la recherche ne retourne aucun resultat
			return null;
		} else {// Retoune le liste
			LOGGER.traceExit("vListParagraphes = " + vListParagraphes);
			return vListParagraphes;
		}
	}

}
