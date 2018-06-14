package org.biblioService.consumer.impl.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.UtilisateurDao;
import org.biblioService.consumer.impl.rowmapper.UtilisateurRM;
import org.biblioService.model.bean.UtilisateurBD;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Named("utilisateurDao")
public class UtilisateurDaoImpl extends AbstractDaoImpl implements UtilisateurDao {
	private static final Logger LOGGER = LogManager.getLogger(UtilisateurDaoImpl.class);

	@Inject
	private UtilisateurRM utilisateurRM;

	@Override
	public void createUtilisateur(UtilisateurBD pUtilisateurBD) {
		LOGGER.traceEntry("utilisateurBD = " + pUtilisateurBD);
		
		if (pUtilisateurBD != null) {// Vérifie que l'utilisateur n'est pas null
			// Enregistrement dans la base de données
			String vSQL = "INSERT INTO public.utilisateur (email,nom,prenom,mdp,sel) VALUES (:email, :nom, :prenom, :mdp, :sel)";
			MapSqlParameterSource vParams = new MapSqlParameterSource();
			vParams.addValue("email", pUtilisateurBD.getEmail());
			vParams.addValue("nom", pUtilisateurBD.getNom());
			if(pUtilisateurBD.getPrenom()==null||pUtilisateurBD.getPrenom().isEmpty()) {
				vParams.addValue("prenom", null);
			}else {
				vParams.addValue("prenom", pUtilisateurBD.getPrenom());
			}
			vParams.addValue("mdp", pUtilisateurBD.getMdp());
			vParams.addValue("sel", pUtilisateurBD.getSel());

			NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

			vJdbcTemplate.update(vSQL, vParams);
		}

		LOGGER.traceExit();
	}

	@Override
	public UtilisateurBD getUtilisateur(String pEmail){
		LOGGER.traceEntry("email = " + pEmail);

		// Recherche de l'utilisateur dans la base de données
		String vSQL = "SELECT * FROM public.utilisateur WHERE email = :email";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("email", pEmail);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<UtilisateurBD> vListUtilisateurBD = vJdbcTemplate.query(vSQL, vParams, utilisateurRM);
		if (vListUtilisateurBD.isEmpty()) {//Retourne null si la recherche ne retourne aucun resultat
			return null;
		} else {// Retoune le premier (et unique) élément sinon
			LOGGER.traceExit(vListUtilisateurBD.get(0));
			return vListUtilisateurBD.get(0);
		}

	}

	@Override
	public UtilisateurBD getUtilisateur(int pId){
		LOGGER.traceEntry("id = " + pId);

		// Recherche de l'utilisateur dans la base de données
		String vSQL = "SELECT * FROM public.utilisateur WHERE id = :id";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pId);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<UtilisateurBD> vListUtilisateurBD = vJdbcTemplate.query(vSQL, vParams, utilisateurRM);
		if (vListUtilisateurBD.isEmpty()) {//Retourne null si la recherche ne retourne aucun resultat
			return null;
		} else {// Retoune le premier (et unique) élément sinon
			LOGGER.traceExit(vListUtilisateurBD.get(0));
			return vListUtilisateurBD.get(0);
		}
	}

	@Override
	public void updateUtilisateur(UtilisateurBD pUtilisateurBD) {
		LOGGER.traceEntry("utilisateurBD = " + pUtilisateurBD);
		if (pUtilisateurBD != null) {// Vérifie que l'utilisateur n'est pas null
			
			// Modification dans la base de données
			String vSQL = "UPDATE public.utilisateur SET nom = :nom, prenom = :prenom, email = :email, mdp = :mdp, sel = :sel WHERE id = :id";
			MapSqlParameterSource vParams = new MapSqlParameterSource();
			vParams.addValue("id", pUtilisateurBD.getId());
			vParams.addValue("email", pUtilisateurBD.getEmail());
			vParams.addValue("nom", pUtilisateurBD.getNom());
			vParams.addValue("prenom", pUtilisateurBD.getPrenom());
			vParams.addValue("mdp", pUtilisateurBD.getMdp());
			vParams.addValue("sel", pUtilisateurBD.getSel());

			NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

			vJdbcTemplate.update(vSQL, vParams);
		}

		LOGGER.traceExit();
		
	}


	@Override
	public void deleteUtilisateur(int pId) {
		LOGGER.traceEntry("id = " + pId);
		// Suppression de la base de données
		String vSQL = "DELETE FROM public.utilisateur WHERE id = :id";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pId);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		vJdbcTemplate.update(vSQL, vParams);

		LOGGER.traceExit();
	}

}
