package org.biblioService.consumer.impl.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.ExemplaireDao;
import org.biblioService.consumer.impl.rowmapper.ExemplaireRM;
import org.biblioService.model.bean.Exemplaire;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Named("exemplaireDao")
public class ExemplaireDaoImpl extends AbstractDaoImpl implements ExemplaireDao {
private static final Logger LOGGER = LogManager.getLogger(ExemplaireDaoImpl.class);
	
	@Inject
	private ExemplaireRM exemplaireRM;
	
	@Override
	public Exemplaire getExemplaire(int pId) {
		LOGGER.traceEntry("id = " + pId);

		// Recherche dans la base de données
		String vSQL = "SELECT * FROM public.exemplaire WHERE id = :id";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pId);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<Exemplaire> vListExemplaire = vJdbcTemplate.query(vSQL, vParams, exemplaireRM);
		if (vListExemplaire.isEmpty()) {//Retourne null si la recherche ne retourne aucun resultat
			return null;
		} else {// Retoune le premier (et unique) élément sinon
			LOGGER.traceExit(vListExemplaire.get(0));
			return vListExemplaire.get(0);
		}
	}

}
