package org.biblioService.webapp.impl;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.business.contrat.ManagerFactory;
import org.biblioService.model.bean.Utilisateur;
import org.biblioService.model.exception.IdentificationException;
import org.biblioService.model.exception.UtilisateurNotFoundException;
import org.biblioService.webapp.contrat.UtilisateurService;

public class UtilisateurServiceImpl implements UtilisateurService {
	
	private static final Logger LOGGER = LogManager.getLogger(UtilisateurServiceImpl.class);
	
	@Inject
	private ManagerFactory managerFactory;

	@Override
	public void creerUtilisateur(String pNom, String pPrenom, String pEmail, String pMdp) {
		LOGGER.traceEntry("nom = " + pNom + " prenom = " + pPrenom + " email = " + pEmail + " mdp = " + pMdp);

		managerFactory.getUtilisateurManager().createUtilisateur(pNom, pPrenom, pEmail, pMdp);
		
		LOGGER.traceExit();
	}

	@Override
	public int authentifierUtilisateur(String pEmail, String pMdp) throws IdentificationException, UtilisateurNotFoundException {
		LOGGER.traceEntry("email = " + pEmail + " mdp = " + pMdp);

		int result = managerFactory.getUtilisateurManager().authentifierUtilisateur(pEmail, pMdp);
		
		LOGGER.traceExit(result);
		return result;
	}

	@Override
	public Utilisateur infoUtilisateur(int pId) throws UtilisateurNotFoundException {
		LOGGER.traceEntry("id = " + pId);

		Utilisateur result = managerFactory.getUtilisateurManager().getUtilisateur(pId);
		
		LOGGER.traceExit(result);
		return result;
	}

	@Override
	public void modifierUtilisateur(int pId, String pAncienMdp, String pNouveauNom, String pNouveauPrenom,String pNouveauMail, String pNouveauMdp) throws IdentificationException, UtilisateurNotFoundException {
		LOGGER.traceEntry("id = " + pId + "ancienMdp = " + pAncienMdp + " nouveauNom = " + pNouveauNom + " nouveauPrenom = " + pNouveauPrenom + " nouveauMail = " + pNouveauMail + " nouveauMdp = " + pNouveauMdp);

		managerFactory.getUtilisateurManager().updateUtilisateur(pId, pAncienMdp, pNouveauNom, pNouveauPrenom, pNouveauMail, pNouveauMdp);
		
		LOGGER.traceExit();

	}

	@Override
	public void supprimerUtilisateur(int pId, String pMdp) throws IdentificationException, UtilisateurNotFoundException {
		LOGGER.traceEntry("id = " + pId + " mdp = " + pMdp);
		
		managerFactory.getUtilisateurManager().deleteUtilisateur(pId, pMdp);
		
		LOGGER.traceExit();
	}

}
