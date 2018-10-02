package org.biblioService.webapp.utilisateurService.impl;

import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.business.contrat.ManagerFactory;
import org.biblioService.model.bean.Utilisateur;
import org.biblioService.model.exception.AutreException;
import org.biblioService.model.exception.ParamsInvalidException;
import org.biblioService.webapp.utilisateurService.generated.AuthentifierUtilisateurFault;
import org.biblioService.webapp.utilisateurService.generated.AuthentifierUtilisateurFault_Exception;
import org.biblioService.webapp.utilisateurService.generated.CreerUtilisateurFault;
import org.biblioService.webapp.utilisateurService.generated.CreerUtilisateurFault1;
import org.biblioService.webapp.utilisateurService.generated.CreerUtilisateurFault_Exception;
import org.biblioService.webapp.utilisateurService.generated.CreerUtilisateurParamsFault;
import org.biblioService.webapp.utilisateurService.generated.ModifierUtilisateurFault;
import org.biblioService.webapp.utilisateurService.generated.ModifierUtilisateurFault1;
import org.biblioService.webapp.utilisateurService.generated.ModifierUtilisateurFault_Exception;
import org.biblioService.webapp.utilisateurService.generated.ModifierUtilisateurParamsFault;
import org.biblioService.webapp.utilisateurService.generated.SupprimerUtilisateurFault;
import org.biblioService.webapp.utilisateurService.generated.SupprimerUtilisateurFault_Exception;
import org.biblioService.webapp.utilisateurService.generated.UtilisateurService;

public class UtilisateurServiceImpl implements UtilisateurService {
	
	private static final Logger LOGGER = LogManager.getLogger(UtilisateurServiceImpl.class);
	
	@Inject
	private ManagerFactory managerFactory;

	@Override
	public void creerUtilisateur(String pNom, String pPrenom, String pEmail, String pMdp) throws CreerUtilisateurFault1, CreerUtilisateurFault_Exception {
		LOGGER.traceEntry("nom = " + pNom + " prenom = " + pPrenom + " email = " + pEmail + " mdp = " + pMdp);
		
		try {
			managerFactory.getUtilisateurManager().createUtilisateur(pNom, pPrenom, pEmail, pMdp);
		} catch (ParamsInvalidException e) {
			CreerUtilisateurParamsFault vCreerUtilisateurParamsFault = new CreerUtilisateurParamsFault();
			vCreerUtilisateurParamsFault.getFaultMessages().addAll(e.getListErreur());
			throw new CreerUtilisateurFault_Exception(e.getMessage(), vCreerUtilisateurParamsFault);
		} catch (AutreException e) {
			LOGGER.debug(e);
			CreerUtilisateurFault vCreerUtilisateurFault = new CreerUtilisateurFault();
			vCreerUtilisateurFault.setFaultMessage(e.getMessageErreur());
			throw new CreerUtilisateurFault1(e.getMessage(),vCreerUtilisateurFault);
		}

		LOGGER.traceExit();
		
	}

	@Override
	public Utilisateur authentifierUtilisateur(String pEmail, String pMdp) throws AuthentifierUtilisateurFault_Exception {
		LOGGER.traceEntry("email = " + pEmail + " mdp = " + pMdp);

		Utilisateur vUtilisateur = null;
		try {
			vUtilisateur = managerFactory.getUtilisateurManager().authentifierUtilisateur(pEmail, pMdp);
		} catch (AutreException e) {
			LOGGER.debug(e);
			AuthentifierUtilisateurFault vAuthentifierUtilisateurFault =  new AuthentifierUtilisateurFault();
			vAuthentifierUtilisateurFault.setFaultMessage(e.getMessageErreur());
			throw new AuthentifierUtilisateurFault_Exception(e.getMessage(), vAuthentifierUtilisateurFault);
		}
		
		LOGGER.traceExit(vUtilisateur);
		return vUtilisateur;
	}

	@Override
	public void modifierUtilisateur(int pId, String pAncienMdp, String pNouveauNom, String pNouveauPrenom,String pNouveauMail, String pNouveauMdp, Boolean pNouveauMailRappel) throws ModifierUtilisateurFault1, ModifierUtilisateurFault_Exception {
		LOGGER.traceEntry("id = " + pId + " ancienMdp = " + pAncienMdp + " nouveauNom = " + pNouveauNom + " nouveauPrenom = " + pNouveauPrenom + " nouveauMail = " + pNouveauMail + " nouveauMdp = " + pNouveauMdp);

		try {
			managerFactory.getUtilisateurManager().updateUtilisateur(pId, pAncienMdp, pNouveauNom, pNouveauPrenom, pNouveauMail, pNouveauMdp, pNouveauMailRappel);
		} catch (AutreException e) {
			LOGGER.debug(e);
			ModifierUtilisateurFault vModifierUtilisateurFault =  new ModifierUtilisateurFault();
			vModifierUtilisateurFault.setFaultMessage(e.getMessageErreur());
			throw new ModifierUtilisateurFault_Exception(e.getMessage(),vModifierUtilisateurFault);
		} catch (ParamsInvalidException e) {
			ModifierUtilisateurParamsFault vModifierUtilisateurParamsFault = new ModifierUtilisateurParamsFault();
			vModifierUtilisateurParamsFault.getFaultMessages().addAll(e.getListErreur());
			throw new ModifierUtilisateurFault1(e.getMessage(), vModifierUtilisateurParamsFault);
		}
		
		LOGGER.traceExit();

	}

	@Override
	public void supprimerUtilisateur(int pId, String pMdp) throws SupprimerUtilisateurFault_Exception {
		LOGGER.traceEntry("id = " + pId + " mdp = " + pMdp);
		
		try {
			managerFactory.getUtilisateurManager().deleteUtilisateur(pId, pMdp);
		} catch (AutreException e) {
			SupprimerUtilisateurFault vSupprimerUtilisateurFault = new SupprimerUtilisateurFault();
			vSupprimerUtilisateurFault.setFaultMessage(e.getMessageErreur());
			throw new SupprimerUtilisateurFault_Exception(e.getMessage(), vSupprimerUtilisateurFault);
		}
		
		LOGGER.traceExit();
	}

}
