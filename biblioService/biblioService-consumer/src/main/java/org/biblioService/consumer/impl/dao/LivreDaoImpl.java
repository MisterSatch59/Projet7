package org.biblioService.consumer.impl.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.DaoFactory;
import org.biblioService.consumer.contrat.dao.LivreDao;
import org.biblioService.consumer.impl.rowmapper.LivreRM;
import org.biblioService.model.bean.Livre;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Named("livreDao")
public class LivreDaoImpl extends AbstractDaoImpl implements LivreDao{
private static final Logger LOGGER = LogManager.getLogger(AuteurDaoImpl.class);
	
	@Inject
	private LivreRM livreRM;
	
	@Inject
	private DaoFactory daoFactory;

	@Override
	public Livre getLivre(String pISBN) {
		LOGGER.traceEntry("vISBN = " + pISBN);

		// Recherche dans la base de données
		String vSQL = "SELECT * FROM public.livre WHERE isbn = :isbn";

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("isbn", pISBN);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<Livre> vListLivre = vJdbcTemplate.query(vSQL, vParams, livreRM);
		if (vListLivre.isEmpty()) {//Retourne null si la recherche ne retourne aucun resultat
			return null;
		} else {// Retoune le premier (et unique) élément sinon
			LOGGER.traceExit(vListLivre.get(0));
			return vListLivre.get(0);
		}
	}

	@Override
	public List<Livre> rechercherLivre(String pTitre, String pAuteur, String pGenre, String pLangue) {
		LOGGER.traceEntry("pTitre = " + pTitre + " pAuteur = " + pAuteur+ " pGenre = " + pGenre+ " pLangue = " + pLangue);
		
		// Recherche dans la base de données
		String vSQL = "SELECT * FROM public.livre WHERE 1=1";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		
		//Recherche des livres contenant la chaine de caractère entrée dans pTitre (ignorant la casse)
		if(pTitre!=null&&!pTitre.isEmpty()) {
			vSQL+=" AND UPPER(titre) LIKE UPPER(:titre)";
			vParams.addValue("titre", "%"+pTitre+"%");
		}
		
		//Et correspondant au genre
		if(pGenre!=null&&!pGenre.isEmpty()) {
			vSQL+=" AND EXISTS (" + 
				" SELECT 1 FROM livre_genre" + 
				" WHERE livre_genre.nom = :genre" + 
				" AND livre_genre.isbn = livre.isbn" + 
				" )";
			vParams.addValue("genre", pGenre);
		}
		
		//Et corredpondant à la langue
		if(pLangue!=null&&!pLangue.isEmpty()) {
			vSQL+=" AND langue = :langue";
			vParams.addValue("langue", pLangue);
		}
		
		//Et dont le nom ou le prenom de l'auteur contient les éléments de la chaine de caractère entrée dans pTitre (ignorant la casse) aprés split des espaces.
		if(pAuteur!=null&&!pAuteur.isEmpty()) {
			String[] vAuteurTab = pAuteur.split(" ");
			vSQL+=" AND livre.isbn IN (" + 
					" SELECT livre_auteur.isbn FROM public.livre_auteur WHERE livre_auteur.auteur_id IN (" + 
						" SELECT auteur.id FROM public.auteur WHERE";
			for (int i = 0; i < vAuteurTab.length; i++) {
				if(i>0) vSQL+=" AND";
				vSQL+=" UPPER(auteur.nom) LIKE UPPER(:val) OR UPPER(auteur.prenom) LIKE UPPER(:val)";
				vParams.addValue("val", "%"+vAuteurTab[i]+"%");
			}
			vSQL+="))";
			vParams.addValue("langue", pLangue);
		}
		
		LOGGER.debug(vSQL);
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		List<Livre> vListLivre = vJdbcTemplate.query(vSQL, vParams, livreRM);
		
		LOGGER.traceExit("vListLivre = " + vListLivre);
		return vListLivre;
	}

	@Override
	public Map<String, Integer> getDispo(String pISBN) {
		LOGGER.traceEntry("pISBN = " + pISBN);
		
		List<String> vListBibliotheque = daoFactory.getBibliothequeDao().getListBibliotheque();
		Map<String, Integer> vDispo = new HashMap<String, Integer>();
		
		for (String vBibliotheque : vListBibliotheque) {
			// Recherche dans la base de données
			String vSQL ="SELECT COUNT(exemplaire.id) FROM public.exemplaire WHERE isbn = :ISBN AND bibliotheque = :biliotheque AND exemplaire.id NOT IN (" + 
						"SELECT pret.id FROM public.pret WHERE pret.id = exemplaire.id AND pret.date_fin IS NULL" + 
						")";
			
			MapSqlParameterSource vParams = new MapSqlParameterSource();
			vParams.addValue("ISBN", pISBN);
			vParams.addValue("biliotheque", vBibliotheque);

			NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
			int vNombre = vJdbcTemplate.queryForObject(vSQL, vParams, Integer.class);
			
			vDispo.put(vBibliotheque, vNombre);
		}
		
		LOGGER.traceExit("vDispo = " + vDispo);
		return vDispo;
	}

}
