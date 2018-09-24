package org.biblioService.consumer.impl.rowmapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.DaoFactory;
import org.biblioService.model.bean.Reservation;
import org.biblioService.model.bean.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper pour le Bean Reservation
 * 
 * @author Oltenos
 *
 */
@Named("reservationRM")
public class ReservationRM implements RowMapper<Reservation> {
	private static final Logger LOGGER = LogManager.getLogger(ReservationRM.class);
	
	@Inject
	private DaoFactory daoFactory;

	@Override
	public Reservation mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.traceEntry();

		int vUtilisateurId = pRS.getInt("utilisateur_id");
		String vISBN = pRS.getString("isbn");
		String vBibliotheque = pRS.getString("bibliotheque");
		Date vDateResa = pRS.getDate("date_resa");
		Date vDateMail = pRS.getDate("date_mail");

		Reservation vReservation = new Reservation();
		vReservation.setLivre(daoFactory.getLivreDao().getLivre(vISBN));
		vReservation.setBibliotheque(vBibliotheque);
		vReservation.setUtilisateur(new Utilisateur(daoFactory.getUtilisateurDao().getUtilisateurBD(vUtilisateurId)));
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(vDateResa);
		try {
			vReservation.setDateResa(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		} catch (DatatypeConfigurationException e) {
			LOGGER.debug(e);
		}
		
		if(vDateMail!=null) {
			c.setTime(vDateMail);
			try {
				vReservation.setDateMail(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
			} catch (DatatypeConfigurationException e) {
				LOGGER.debug(e);
			}
		}else {
			vReservation.setDateMail(null);
		}
		
		
		LOGGER.traceExit("vReservation = " + vReservation);
		return vReservation;
	}
}
