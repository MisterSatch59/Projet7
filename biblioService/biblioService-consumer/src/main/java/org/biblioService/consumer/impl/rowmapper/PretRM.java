package org.biblioService.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.DaoFactory;
import org.biblioService.model.bean.Pret;
import org.biblioService.model.bean.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper pour le Bean Pret
 * 
 * @author Oltenos
 *
 */
@Named("pretRM")
public class PretRM implements RowMapper<Pret> {
	private static final Logger LOGGER = LogManager.getLogger(PretRM.class);
	
	@Inject
	private DaoFactory daoFactory;

	@Override
	public Pret mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.traceEntry();
		
		//Enregistrement du résultats dans des variables
		int vId = pRS.getInt("id");
		Date vDateDebut = pRS.getDate("date_debut");
		Date vDateFin = pRS.getDate("date_fin");
		boolean vRenouvele = pRS.getBoolean("renouvele");
		int vUtilisateurId = pRS.getInt("utilisateur_id");
		int vExemplaireId = pRS.getInt("exemplaire_id");
		
		//Création de l'objet Pret
		Pret vPret = new Pret();
		vPret.setId(vId);
		
		GregorianCalendar c = (GregorianCalendar) GregorianCalendar.getInstance();
		c.setTime(vDateDebut);
		try {
			vPret.setDateDebut(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		} catch (DatatypeConfigurationException e) {
			LOGGER.debug(e);
		}
		
		if(vDateFin!=null) {
			Calendar c1 = (GregorianCalendar) GregorianCalendar.getInstance();
			c1.setTime(vDateFin);
			try {
				vPret.setDateFin(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
			} catch (DatatypeConfigurationException e) {
				LOGGER.debug(e);
			}
		} else {
			vPret.setDateFin(null);
		}

		vPret.setRenouvele(vRenouvele);

		vPret.setUtilisateur(new Utilisateur(daoFactory.getUtilisateurDao().getUtilisateur(vUtilisateurId)));
		
		vPret.setExemplaire(daoFactory.getExemplaireDao().getExemplaire(vExemplaireId));
		
		LOGGER.traceExit("vPret = " + vPret);
		return vPret;
	}

}
