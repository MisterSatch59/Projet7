package org.biblioService.consumer.impl.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.DescriptionDao;
import org.biblioService.consumer.impl.rowmapper.DescriptionRM;
import org.biblioService.model.bean.Description;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Named("descriptionDao")
public class DescriptionDaoImpl extends AbstractDaoImpl implements DescriptionDao {
	private static final Logger LOGGER = LogManager.getLogger(DescriptionDaoImpl.class);
	
	@Inject
	private DescriptionRM descriptionRM;

	@Override
	public Description getDescription(int pId) {
		LOGGER.traceEntry("id = " + pId);

		// Recherche dans la base de données
		String vSQL = "SELECT * FROM public.description WHERE id = :id";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pId);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<Description> vListDescription = vJdbcTemplate.query(vSQL, vParams, descriptionRM);
		if (vListDescription.isEmpty()) {//Retourne null si la recherche ne retourne aucun resultat
			return null;
		} else {// Retoune le premier (et unique) élément sinon
			LOGGER.traceExit(vListDescription.get(0));
			return vListDescription.get(0);
		}
	}

}
