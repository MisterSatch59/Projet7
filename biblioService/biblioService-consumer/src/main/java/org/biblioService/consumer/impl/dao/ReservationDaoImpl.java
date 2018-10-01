package org.biblioService.consumer.impl.dao;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.ReservationDao;
import org.biblioService.consumer.impl.rowmapper.ReservationRM;
import org.biblioService.model.bean.Reservation;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Named("reservationDao")
public class ReservationDaoImpl extends AbstractDaoImpl implements ReservationDao {
	private static final Logger LOGGER = LogManager.getLogger(ReservationDaoImpl.class);
	
	@Inject
	ReservationRM reservationRM;

	@Override
	public void createReservation(String pISBN, String pBibliotheque, int pUtilisateurId) {
		LOGGER.traceEntry("pISBN = " + pISBN, " - pBibliotheque = " + " - pUtilisateurId = " + pUtilisateurId);
		
		if (pISBN != null && pBibliotheque!=null) {
			// Enregistrement dans la base de données
			String vSQL = "INSERT INTO public.reservation (bibliotheque,isbn,utilisateur_id,date_resa) VALUES (:bibliotheque, :isbn, :utilisateur_id, now())";
			MapSqlParameterSource vParams = new MapSqlParameterSource();
			vParams.addValue("bibliotheque", pBibliotheque);
			vParams.addValue("isbn", pISBN);
			vParams.addValue("utilisateur_id", pUtilisateurId);

			NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

			vJdbcTemplate.update(vSQL, vParams);
		}

		LOGGER.traceExit();

	}

	@Override
	public List<Reservation> getReservation(int pUtilisateurId) {
		LOGGER.traceEntry("pUtilisateurId = " + pUtilisateurId);

		String vSQL = "SELECT * FROM reservation WHERE utilisateur_id = :utilisateur_id AND date_mail IS NULL";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("utilisateur_id", pUtilisateurId);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<Reservation> vListReservation = vJdbcTemplate.query(vSQL, vParams, reservationRM);

		LOGGER.traceExit("vListReservation = " + vListReservation);
		return vListReservation;
	}

	@Override
	public void deleteReservation(String pISBN, String pBibliotheque, int pUtilisateurId) {
		LOGGER.traceEntry("pISBN = " + pISBN, " - pBibliotheque = " + " - pUtilisateurId = " + pUtilisateurId);
		
		if (pISBN != null && pBibliotheque!=null) {
			// Enregistrement dans la base de données
			String vSQL = "DELETE FROM public.reservation WHERE bibliotheque = :bibliotheque AND isbn = :isbn AND utilisateur_id = :utilisateur_id";
			MapSqlParameterSource vParams = new MapSqlParameterSource();
			vParams.addValue("bibliotheque", pBibliotheque);
			vParams.addValue("isbn", pISBN);
			vParams.addValue("utilisateur_id", pUtilisateurId);

			NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

			vJdbcTemplate.update(vSQL, vParams);
		}

		LOGGER.traceExit();
		
	}

	@Override
	public int getNbReservation(String pISBN, String pBibliotheque) {
		LOGGER.traceEntry("pISBN = " + pISBN + " - pBibliotheque = " + pBibliotheque);

		String vSQL = "SELECT COUNT(*) FROM reservation WHERE bibliotheque = :bibliotheque AND isbn = :isbn";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("bibliotheque", pBibliotheque);
		vParams.addValue("isbn", pISBN);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		Integer vNbReservation = vJdbcTemplate.queryForObject(vSQL, vParams,Integer.class);

		LOGGER.traceExit("vNbReservation = " + vNbReservation);
		return vNbReservation;
	}


	@Override
	public Reservation getPremierReservation(String pBibliotheque, String pIsbn) {
		LOGGER.traceEntry("pBibliotheque = " + pBibliotheque + " - pIsbn = " + pIsbn);

		String vSQL = "SELECT * FROM reservation WHERE bibliotheque = :bibliotheque AND isbn = :isbn ORDER BY date_resa ASC";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("isbn", pIsbn);
		vParams.addValue("bibliotheque", pBibliotheque);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<Reservation> vListReservation = vJdbcTemplate.query(vSQL, vParams, reservationRM);
		
		if(vListReservation.size()==0) {
			LOGGER.traceExit("vReservation = " + null);
			return null;
		}else {
			LOGGER.traceExit("vReservation = " + vListReservation.get(0));
			return vListReservation.get(0);
		}

	}


	@Override
	public void setAttribue(String pBibliotheque, int pUtilisateurId, String pIsbn, Date pDateAttribution, int pPretId) {
		LOGGER.traceEntry("pIsbn = " + pIsbn, " - pBibliotheque = " + " - pUtilisateurId = " + pUtilisateurId + " - pDateAttribution = " + pDateAttribution);
		
		// Enregistrement dans la base de données
		String vSQL = "UPDATE public.reservation SET date_mail = :date_mail, pret_id = :pret_id WHERE bibliotheque = :bibliotheque AND isbn = :isbn AND utilisateur_id = :utilisateur_id";
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("bibliotheque", pBibliotheque);
		vParams.addValue("isbn", pIsbn);
		vParams.addValue("utilisateur_id", pUtilisateurId);
		vParams.addValue("date_mail", pDateAttribution);
		vParams.addValue("pret_id", pPretId);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		vJdbcTemplate.update(vSQL, vParams);

		LOGGER.traceExit();
		
	}

	@Override
	public List<Reservation> getListReservationDateAttributionAvant(Date pDate) {
		LOGGER.traceEntry("pDate = " + pDate);

		String vSQL = "SELECT * FROM reservation WHERE date_mail < :date_mail";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("date_mail", pDate);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<Reservation> vListReservation = vJdbcTemplate.query(vSQL, vParams, reservationRM);

		LOGGER.traceExit("vListReservation = " + vListReservation);
		return vListReservation;
	}	

}
