package org.biblioService.business.impl.manager;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.business.contrat.manager.UtilisateurManager;
import org.biblioService.model.bean.Utilisateur;
import org.biblioService.model.exception.IdentificationException;
import org.biblioService.model.exception.UtilisateurNotFoundException;

/**
 * Implémentation de UtilisateurManager
 * 
 * @author Oltenos
 *
 */
@Named("utilisateurManager")
public class UtilisateurManagerImpl implements UtilisateurManager{
	private static final Logger LOGGER = LogManager.getLogger(UtilisateurManagerImpl.class);

	@Override
	public void createUtilisateur(String pNom, String pPrenom, String pEmail, String pMdp) {
		LOGGER.traceEntry("nom = " + pNom + " prenom = " + pPrenom + " email = " + pEmail + " mdp = " + pMdp);

		//TODO
		
		LOGGER.traceExit();		
	}

	@Override
	public int authentifierUtilisateur(String pEmail, String pMdp) throws IdentificationException, UtilisateurNotFoundException {
		LOGGER.traceEntry("email = " + pEmail + " mdp = " + pMdp);

		int result = 50;
		//TODO
		
		LOGGER.traceExit(50);
		return result;
	}

	@Override
	public Utilisateur getUtilisateur(int pId) throws UtilisateurNotFoundException {
		LOGGER.traceEntry("id = " + pId);
		
		// TODO Auto-generated method stub
		Utilisateur result = new Utilisateur();
		
		LOGGER.traceExit(result);
		return result;
	}

	@Override
	public void updateUtilisateur(int pId, String pAncienMdp, String pNouveauNom, String pNouveauPrenom, String pNouveauMail, String pNouveauMdp) throws IdentificationException, UtilisateurNotFoundException {
		LOGGER.traceEntry("id = " + pId + "ancienMdp = " + pAncienMdp + " nouveauNom = " + pNouveauNom + " nouveauPrenom = " + pNouveauPrenom + " nouveauMail = " + pNouveauMail + " nouveauMdp = " + pNouveauMdp);
		
		// TODO Auto-generated method stub
		
		LOGGER.traceExit();
	}

	@Override
	public void deleteUtilisateur(int pId, String pMdp) throws IdentificationException, UtilisateurNotFoundException {
		LOGGER.traceEntry("id = " + pId + " mdp = " + pMdp);
		
		// TODO Auto-generated method stub
		
		LOGGER.traceExit();
	}




}
