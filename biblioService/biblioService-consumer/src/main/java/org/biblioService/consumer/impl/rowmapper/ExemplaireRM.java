package org.biblioService.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.DaoFactory;
import org.biblioService.model.bean.Exemplaire;
import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper pour le Bean Exemplaire
 * 
 * @author Oltenos
 *
 */
@Named("exemplaireRM")
public class ExemplaireRM implements RowMapper<Exemplaire> {
	private static final Logger LOGGER = LogManager.getLogger(ExemplaireRM.class);
	
	@Inject
	private DaoFactory daoFactory;

	@Override
	public Exemplaire mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.traceEntry();
		
		//Enregistrement du résultats dans des variables
		int vId = pRS.getInt("id");
		String vISBN = pRS.getString("isbn");
		String vBibliotheque = pRS.getString("bibliotheque");
		
		//Création de l'objet Exemplaire
		Exemplaire vExemplaire = new Exemplaire();
		vExemplaire.setId(vId);
		vExemplaire.setBibliotheque(vBibliotheque);
		
		vExemplaire.setLivre(daoFactory.getLivreDao().getLivre(vISBN));
		
		LOGGER.traceExit("vExemplaire = " + vExemplaire);
		return vExemplaire;
	}

}
