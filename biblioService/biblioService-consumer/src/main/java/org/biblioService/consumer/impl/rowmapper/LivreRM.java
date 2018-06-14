package org.biblioService.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.DaoFactory;
import org.biblioService.model.bean.Livre;
import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper pour le Bean Livre
 * 
 * @author Oltenos
 *
 */
@Named("livreRM")
public class LivreRM implements RowMapper<Livre> {
	private static final Logger LOGGER = LogManager.getLogger(LivreRM.class);
	
	@Inject
	private DaoFactory daoFactory;

	@Override
	public Livre mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.traceEntry();
		
		//Enregistrement du résultats dans des variables
		String vISBN = pRS.getString("isbn");
		String vTitre = pRS.getString("titre");
		int vEditeurId = pRS.getInt("editeur_id");
		Date vDatePublication = pRS.getDate("date_publication");
		int vDescriptionId = pRS.getInt("description_id");
		String vLangue = pRS.getString("langue");
		
		//Création de l'objet Livre
		Livre vLivre = new Livre();
		vLivre.setIsbn(vISBN);
		vLivre.setTitre(vTitre);
		vLivre.setEditeur(daoFactory.getEditeurDao().getEditeur(vEditeurId));
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(vDatePublication);
		try {
			vLivre.setDatePublication(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		} catch (DatatypeConfigurationException e) {
			LOGGER.debug(e);
		}
		vLivre.setDescription(daoFactory.getDescriptionDao().getDescription(vDescriptionId));
		vLivre.setLangue(vLangue);
		
		vLivre.getAuteur().addAll(daoFactory.getAuteurDao().getAuteurs(vISBN));
		vLivre.getGenre().addAll(daoFactory.getGenreDao().getGenres(vISBN));
		
		LOGGER.traceExit("vLivre = " + vLivre);
		return vLivre;
	}

}
