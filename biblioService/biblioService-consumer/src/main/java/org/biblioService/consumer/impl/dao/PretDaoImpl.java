package org.biblioService.consumer.impl.dao;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.PretDao;
import org.biblioService.consumer.impl.rowmapper.PretRM;
import org.biblioService.model.bean.Pret;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Named("pretDao")
public class PretDaoImpl extends AbstractDaoImpl implements PretDao{
	private static final Logger LOGGER = LogManager.getLogger(PretDaoImpl.class);
	
	@Inject
	private PretRM pretRM;

	@Override
	public List<Pret> getPretDebutAvant(Calendar pCalendar) {
		LOGGER.traceEntry("pCalendar = " + pCalendar);

		// Recherche dans la base de données
		String vSQL = "SELECT * FROM public.pret WHERE date_debut < :date AND date_fin IS NULL";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("date", pCalendar.getTime());

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<Pret> vListPret = vJdbcTemplate.query(vSQL, vParams, pretRM);
		if (vListPret.isEmpty()) {//Retourne null si la recherche ne retourne aucun resultat
			return null;
		} else {// Retoune le liste
			LOGGER.traceExit("vListPret = " + vListPret);
			return vListPret;
		}
	}

}
