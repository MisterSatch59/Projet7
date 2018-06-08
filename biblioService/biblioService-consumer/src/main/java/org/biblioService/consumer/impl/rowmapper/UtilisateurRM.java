package org.biblioService.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.model.bean.UtilisateurBD;
import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper pour le Bean UtilisateurBD
 * 
 * @author Oltenos
 *
 */
@Named("utilisateurRM")
public class UtilisateurRM implements RowMapper<UtilisateurBD> {

	private static final Logger LOGGER = LogManager.getLogger(UtilisateurRM.class);

	@Override
	public UtilisateurBD mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.traceEntry();

		int vId = pRS.getInt("id");
		String vNom = pRS.getString("nom");
		String vPrenom = pRS.getString("prenom");
		String vEmail = pRS.getString("email");
		String vMdp = pRS.getString("mdp");
		String vSel = pRS.getString("sel");

		UtilisateurBD vUtilisateurBD = new UtilisateurBD();
		
		vUtilisateurBD.setId(vId);
		vUtilisateurBD.setNom(vNom);
		vUtilisateurBD.setPrenom(vPrenom);
		vUtilisateurBD.setEmail(vEmail);
		vUtilisateurBD.setMdp(vMdp);
		vUtilisateurBD.setSel(vSel);
		
		LOGGER.traceExit(vUtilisateurBD);
		return vUtilisateurBD;
	}
}
