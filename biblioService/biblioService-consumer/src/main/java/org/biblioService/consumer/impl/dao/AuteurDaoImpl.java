package org.biblioService.consumer.impl.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.AuteurDao;
import org.biblioService.consumer.impl.rowmapper.AuteurRM;
import org.biblioService.model.bean.Auteur;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Named("auteurDao")
public class AuteurDaoImpl extends AbstractDaoImpl implements AuteurDao {
	private static final Logger LOGGER = LogManager.getLogger(AuteurDaoImpl.class);
	
	@Inject
	private AuteurRM auteurRM;

	@Override
	public List<Auteur> getAuteurs(String pISBN) {
		LOGGER.traceEntry("pISBN = " + pISBN);

		// Recherche dans la base de données
		String vSQL = "SELECT * FROM public.auteur " + 
				"WHERE id IN ( " + 
				"SELECT livre_auteur.auteur_id FROM livre_auteur WHERE livre_auteur.isbn = :isbn" + 
				" )";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("isbn", pISBN);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<Auteur> vListAuteur = vJdbcTemplate.query(vSQL, vParams, auteurRM);
		if (vListAuteur.isEmpty()) {//Retourne null si la recherche ne retourne aucun resultat
			return null;
		} else {// Retoune le liste
			LOGGER.traceExit("vListAuteur = " + vListAuteur);
			return vListAuteur;
		}
	}

}
