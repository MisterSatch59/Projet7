package org.biblioService.consumer.impl.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.EditeurDao;
import org.biblioService.consumer.impl.rowmapper.EditeurRM;
import org.biblioService.model.bean.Editeur;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Named("editeurDao")
public class EditeurDaoImpl extends AbstractDaoImpl implements EditeurDao {
private static final Logger LOGGER = LogManager.getLogger(DescriptionDaoImpl.class);
	
	@Inject
	private EditeurRM editeurRM;
	
	@Override
	public Editeur getEditeur(int pId) {
		LOGGER.traceEntry("id = " + pId);

		// Recherche de dans la base de données
		String vSQL = "SELECT * FROM public.editeur WHERE id = :id";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pId);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<Editeur> vListEditeur = vJdbcTemplate.query(vSQL, vParams, editeurRM);
		if (vListEditeur.isEmpty()) {//Retourne null si la recherche ne retourne aucun resultat
			return null;
		} else {// Retoune le premier (et unique) élément sinon
			LOGGER.traceExit(vListEditeur.get(0));
			return vListEditeur.get(0);
		}
	}

}
