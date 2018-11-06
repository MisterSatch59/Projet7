package org.biblioService.consumer.impl.dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.PretDao;
import org.biblioService.consumer.impl.rowmapper.PretRM;
import org.biblioService.model.bean.Pret;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Named("pretDao")
public class PretDaoImpl extends AbstractDaoImpl implements PretDao{
	private static final Logger LOGGER = LogManager.getLogger(PretDaoImpl.class);
	
	@Inject
	private PretRM pretRM;

	@Override
	public List<Pret> getPretDateRetourAvant(Calendar pCalendar) {
		LOGGER.traceEntry("pCalendar = " + pCalendar);

		// Recherche dans la base de données
		String vSQL = "SELECT * FROM public.pret WHERE date_retour_prevue < :date AND date_fin IS NULL";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("date", pCalendar.getTime());

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<Pret> vListPret = vJdbcTemplate.query(vSQL, vParams, pretRM);
		if (vListPret.isEmpty()) {//Retourne null si la recherche ne retourne aucun resultat
			return null;
		} else {// Retoune le liste
			LOGGER.traceExit("vListPret = " + vListPret);
			return vListPret;
		}
	}

	@Override
	public void prolongerPret(int pId, int dureeSupplementaire) {
		LOGGER.traceEntry("pId = " + pId);
		
		String vSQL = "UPDATE public.pret SET renouvele = true, date_retour_prevue = date_retour_prevue + :periode WHERE id = :id";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("periode", dureeSupplementaire);
		vParams.addValue("id", pId);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		vJdbcTemplate.update(vSQL, vParams);
		
		LOGGER.traceExit();
	}

	@Override
	public List<Pret> getPretEnCours(int pUtilisateurId) {
		LOGGER.traceEntry("pUtilisateurId = " + pUtilisateurId);

		// Recherche dans la base de données
		String vSQL = "SELECT * FROM public.pret WHERE utilisateur_id = :id AND date_fin IS NULL";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pUtilisateurId);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<Pret> vListPret = vJdbcTemplate.query(vSQL, vParams, pretRM);
		
		LOGGER.traceExit("vListPret = " + vListPret);
		return vListPret;
	}

	@Override
	public Pret getPret(int pId) {
		LOGGER.traceEntry("pId = " + pId);

		// Recherche dans la base de données
		String vSQL = "SELECT * FROM public.pret WHERE id = :id";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pId);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<Pret> vListPret = vJdbcTemplate.query(vSQL, vParams, pretRM);

		Pret vPret;
		if(vListPret.isEmpty()) {
			vPret = null;
		}else {
			vPret=vListPret.get(0);
		}

		LOGGER.traceExit("vPret = " + vPret);
		return vPret;
	}

	@Override
	public int createPret(int pUtilisateurId, int pExemplaireId, Date pDateDebut, Date pDateFin) {
		LOGGER.traceEntry("pUtilisateurId = " + pUtilisateurId + " - pExemplaireId = " + pExemplaireId + " - vDateDebut = " + pDateDebut + " - vDateFin = " + pDateFin);
		
		String vSQL = "INSERT INTO public.pret (date_debut,date_retour_prevue,renouvele,utilisateur_id,exemplaire_id) VALUES"
				+ "(:date_debut, :date_retour_prevue, false , :utilisateur_id, :exemplaire_id)";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("date_debut", pDateDebut);
		vParams.addValue("date_retour_prevue", pDateFin);
		vParams.addValue("utilisateur_id", pUtilisateurId);
		vParams.addValue("exemplaire_id", pExemplaireId);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		vJdbcTemplate.update(vSQL, vParams, keyHolder);
		int id = (int) keyHolder.getKeys().get("id");
		
		LOGGER.traceExit("id = " + id);
		return id;
		
	}

	@Override
	public void retourPret(int pId, Date pDateFin) {
		LOGGER.traceEntry("pId = " + pId + " - pDateFin = " + pDateFin);
		
		String vSQL = "UPDATE public.pret SET date_fin = :date_fin WHERE id = :id";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("date_fin", pDateFin);
		vParams.addValue("id", pId);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		vJdbcTemplate.update(vSQL, vParams);
		
		LOGGER.traceExit();
	}

	@Override
	public boolean isEmprunte(int pExemplaireId) {
		LOGGER.traceEntry("pExemplaireId = " + pExemplaireId);

		// Recherche dans la base de données
		String vSQL = "SELECT COUNT(*) FROM public.pret WHERE exemplaire_id = :exemplaire_id AND date_fin IS NULL";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("exemplaire_id", pExemplaireId);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		int NbPret = vJdbcTemplate.queryForObject(vSQL, vParams,Integer.class);
		
		LOGGER.traceExit("isEmprunte = " + (NbPret!=0));
		return (NbPret!=0);

	}

	@Override
	public void delete(int pId) {
		LOGGER.traceEntry("pId = " + pId);
		
		String vSQL = "DELETE FROM public.pret WHERE id = :id";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pId);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		vJdbcTemplate.update(vSQL, vParams);
		
		LOGGER.traceExit();
		
	}


	@Override
	public List<Pret> getPretRetourPrevuLe(Calendar pCalendar) {
		LOGGER.traceEntry("pCalendar = " + pCalendar);

		// Recherche dans la base de données
		String vSQL = "SELECT * FROM public.pret WHERE date_retour_prevue = :date AND date_fin IS NULL";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("date", pCalendar.getTime());

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<Pret> vListPret = vJdbcTemplate.query(vSQL, vParams, pretRM);
		if (vListPret.isEmpty()) {//Retourne null si la recherche ne retourne aucun resultat
			return null;
		} else {// Retoune le liste
			LOGGER.traceExit("vListPret = " + vListPret);
			return vListPret;
		}
	}

}
