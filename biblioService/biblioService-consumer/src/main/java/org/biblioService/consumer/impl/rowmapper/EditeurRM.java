package org.biblioService.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.model.bean.Editeur;
import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper pour le Bean Editeur
 * 
 * @author Oltenos
 *
 */
@Named("editeurRM")
public class EditeurRM implements RowMapper<Editeur> {
	private static final Logger LOGGER = LogManager.getLogger(EditeurRM.class);

	@Override
	public Editeur mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.traceEntry();
		
		//Enregistrement du résultats dans des variables
		int vId = pRS.getInt("id");
		String vNom = pRS.getString("nom");
		
		Editeur vEditeur = new Editeur();
		vEditeur.setId(vId);
		vEditeur.setNom(vNom);
		
		LOGGER.traceExit("vEditeur = " + vEditeur);
		return vEditeur;
	}

}
