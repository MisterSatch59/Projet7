package org.biblioService.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.model.bean.Auteur;
import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper pour le Bean Auteur
 * 
 * @author Oltenos
 *
 */
@Named("auteurRM")
public class AuteurRM implements RowMapper<Auteur> {
	private static final Logger LOGGER = LogManager.getLogger(AuteurRM.class);

	@Override
	public Auteur mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.traceEntry();
		
		//Enregistrement du résultats dans des variables
		int vId = pRS.getInt("id");
		String vNom = pRS.getString("nom");
		String vPrenom = pRS.getString("prenom");
		
		Auteur vAuteur = new Auteur();
		vAuteur.setId(vId);
		vAuteur.setNom(vNom);
		vAuteur.setPrenom(vPrenom);
		
		LOGGER.traceExit("vAuteur = " + vAuteur);
		return vAuteur;
	}
}
