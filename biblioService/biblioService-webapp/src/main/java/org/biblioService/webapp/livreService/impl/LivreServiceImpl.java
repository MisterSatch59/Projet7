package org.biblioService.webapp.livreService.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.business.contrat.ManagerFactory;
import org.biblioService.model.bean.Livre;
import org.biblioService.model.bean.Pret;
import org.biblioService.model.exception.NotFoundException;
import org.biblioService.model.exception.TechnicalException;
import org.biblioService.webapp.livreService.generated.ListerPretEnCoursFault;
import org.biblioService.webapp.livreService.generated.ListerPretEnCoursFault_Exception;
import org.biblioService.webapp.livreService.generated.LivreService;
import org.biblioService.webapp.livreService.generated.ProlongerPretFault;
import org.biblioService.webapp.livreService.generated.ProlongerPretFault_Exception;
import org.biblioService.webapp.livreService.generated.VoirDispoFault_Exception;
import org.biblioService.webapp.livreService.generated.types.NombreEtBibliotheque;

import java.util.Map;
import java.util.Set;

public class LivreServiceImpl implements LivreService {
	
	private static final Logger LOGGER = LogManager.getLogger(LivreServiceImpl.class);
	
	@Inject
	private ManagerFactory managerFactory;

	@Override
	public List<Pret> listerPretEnCours(int pUtilisateurId) throws ListerPretEnCoursFault_Exception {
		LOGGER.traceEntry("pUtilisateurId = " + pUtilisateurId);
		
		List<Pret> vListPret;
		try {
			vListPret = managerFactory.getLivreManager().getPretEnCours(pUtilisateurId);
		} catch (NotFoundException e) {
			LOGGER.debug(e);
			ListerPretEnCoursFault vListerPretEnCoursFault = new ListerPretEnCoursFault();
			vListerPretEnCoursFault.setFaultMessage(e.getMessageErreur());
			throw new ListerPretEnCoursFault_Exception(e.getMessage(),vListerPretEnCoursFault);
		}
		
		LOGGER.traceExit("vListPret = " + vListPret);
		return vListPret;
	}

	@Override
	public List<Pret> listerPretEnRetard() {
		LOGGER.traceEntry();
		
		List<Pret> vListPret = managerFactory.getLivreManager().getPretEnRetard();
		
		LOGGER.traceExit("vListPret = " + vListPret);
		return vListPret;
	}

	@Override
	public List<Livre> rechercherLivre(String pTitre, String pAuteur, String pGenre, String pLangue) {
		LOGGER.traceEntry("pTitre = " + pTitre + " pAuteur = " + pAuteur+ " pGenre = " + pGenre+ " pLangue = " + pLangue);
		
		List<Livre> vListLivre = managerFactory.getLivreManager().rechercherLivre(pTitre, pAuteur, pGenre, pLangue);
		
		LOGGER.traceExit("vListLivre = " + vListLivre);
		return vListLivre;
	}

	@Override
	public void prolongerPret(int pISBN) throws ProlongerPretFault_Exception {
		LOGGER.traceEntry("pPretId = " + pISBN);
		
		try {
			managerFactory.getLivreManager().prolongerPret(pISBN);
		} catch (TechnicalException e) {
			LOGGER.debug(e);
			ProlongerPretFault vProlongerPretFault = new ProlongerPretFault();
			vProlongerPretFault.setFaultMessage(e.getMessageErreur());
			throw new ProlongerPretFault_Exception(e.getMessage(),vProlongerPretFault);
		}
		
		LOGGER.traceExit();
	}

	@Override
	public List<String> getGenres() {
		LOGGER.traceEntry();
		
		List<String> vListGenre = managerFactory.getLivreManager().getGenres();
		
		LOGGER.traceExit("vListGenre = " + vListGenre);
		return vListGenre;
	}

	@Override
	public List<String> getLangues() {
		LOGGER.traceEntry();
		
		List<String> vListLangue = managerFactory.getLivreManager().getLangues();
		
		LOGGER.traceExit("vListGenre = " + vListLangue);
		return vListLangue;
	}

	@Override
	public List<NombreEtBibliotheque> voirDispo(String pISBN) throws VoirDispoFault_Exception {
		LOGGER.traceEntry("pISBN = " + pISBN);
		
		Map <String, Integer> vDispo = managerFactory.getLivreManager().getDispo(pISBN);
		List<NombreEtBibliotheque> rDispo =  new ArrayList<NombreEtBibliotheque>();
		
		Set<String> vKeys = vDispo.keySet();
		for (String vKey : vKeys) {
			NombreEtBibliotheque vNombreEtBibliotheque = new NombreEtBibliotheque();
			vNombreEtBibliotheque.setBibliotheque(vKey);
			vNombreEtBibliotheque.setNombre(vDispo.get(vKey));
			rDispo.add(vNombreEtBibliotheque);
		}
		return rDispo;
	}

}
