package org.biblioService.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.DaoFactory;
import org.biblioService.model.bean.Description;
import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper pour le Bean Description
 * 
 * @author Oltenos
 *
 */
@Named("descriptionRM")
public class DescriptionRM implements RowMapper<Description> {
	private static final Logger LOGGER = LogManager.getLogger(DescriptionRM.class);
	
	@Inject
	private DaoFactory daoFactory;

	@Override
	public Description mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.traceEntry();
		
		//Enregistrement du résultats dans des variables
		int vId = pRS.getInt("id");
		String vTitre = pRS.getString("titre");
		
		Description vDescription = new Description();
		vDescription.setId(vId);
		vDescription.setTitre(vTitre);

		vDescription.getParagraphes().addAll(daoFactory.getParagrapheDao().getParagraphes(vId));
		
		LOGGER.traceExit("vAuteur = " + vDescription);
		return vDescription;
	}
}
