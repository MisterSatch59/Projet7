package org.biblioService.webapp.livreService.impl;

import java.util.List;

import javax.inject.Inject;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.business.contrat.ManagerFactory;
import org.biblioService.model.bean.Livre;
import org.biblioService.model.bean.DispoParBibliotheque;
import org.biblioService.model.bean.Pret;
import org.biblioService.model.bean.Reservation;
import org.biblioService.model.exception.AutreException;
import org.biblioService.model.exception.NotFoundException;
import org.biblioService.model.exception.TechnicalException;
import org.biblioService.webapp.livreService.generated.CreerReservationFault_Exception;
import org.biblioService.webapp.livreService.generated.ListerPretEnCoursFault;
import org.biblioService.webapp.livreService.generated.ListerPretEnCoursFault_Exception;
import org.biblioService.webapp.livreService.generated.LivreService;
import org.biblioService.webapp.livreService.generated.MiseAJourListesReservationFault_Exception;
import org.biblioService.webapp.livreService.generated.NouveauPretFault_Exception;
import org.biblioService.webapp.livreService.generated.ProlongerPretFault;
import org.biblioService.webapp.livreService.generated.ProlongerPretFault_Exception;
import org.biblioService.webapp.livreService.generated.RetourPretFault_Exception;
import org.biblioService.webapp.livreService.generated.SupprimerReservationFault_Exception;
import org.biblioService.webapp.livreService.generated.VoirDispoFault_Exception;

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
	public XMLGregorianCalendar prolongerPret(int pISBN) throws ProlongerPretFault_Exception {
		LOGGER.traceEntry("pPretId = " + pISBN);

		XMLGregorianCalendar vNewDateRetourPrevue;

		try {
			vNewDateRetourPrevue = managerFactory.getLivreManager().prolongerPret(pISBN);
		} catch (AutreException e) {
			LOGGER.debug(e);
			ProlongerPretFault vProlongerPretFault = new ProlongerPretFault();
			vProlongerPretFault.setFaultMessage(e.getMessageErreur());
			throw new ProlongerPretFault_Exception(e.getMessage(), vProlongerPretFault);
		}

		LOGGER.traceExit("vNewDateRetourPrevue = " + vNewDateRetourPrevue);
		return vNewDateRetourPrevue;
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
	public List<DispoParBibliotheque> voirDispo(String pISBN) throws VoirDispoFault_Exception {
		LOGGER.traceEntry("pISBN = " + pISBN);
		
		List<DispoParBibliotheque> vDispo = managerFactory.getLivreManager().getDispo(pISBN);

		LOGGER.traceExit("vDispo = " + vDispo);
		return vDispo;
	}
	
	@Override
	public void creerReservation(String pISBN, String pBibliotheque, int pUtilisateurId) throws CreerReservationFault_Exception {
		LOGGER.traceEntry("pISBN = " + pISBN + " - pBibliotheque = " + " - pUtilisateurId = " + pUtilisateurId);
		
		try {
			managerFactory.getLivreManager().createReservation(pISBN,pBibliotheque,pUtilisateurId);
		} catch (AutreException e) {
			LOGGER.debug(e);
			throw new CreerReservationFault_Exception(e.getMessage());
		}

		LOGGER.traceExit();
	}

	@Override
	public List<Reservation> listerReservation(int pUtilisateurId) {
		LOGGER.traceEntry("pUtilisateurId = " + pUtilisateurId);

		List<Reservation> vResult = managerFactory.getLivreManager().listerReservation(pUtilisateurId);
		
		LOGGER.traceEntry("vResult = " + vResult);
		return vResult;
	}
	
	@Override
	public void supprimerReservation(String pISBN, String pBibliotheque, int pUtilisateurId) throws SupprimerReservationFault_Exception{
		LOGGER.traceEntry("pISBN = " + pISBN + " - pBibliotheque = " + pBibliotheque + " - pUtilisateurId = " + pUtilisateurId);
		
		try {
			managerFactory.getLivreManager().deleteReservation(pISBN,pBibliotheque,pUtilisateurId);
		} catch (TechnicalException e) {
			LOGGER.debug(e);
			throw new SupprimerReservationFault_Exception(e.getMessage());
		}

		LOGGER.traceExit();
		
	}

	@Override
	public void nouveauPret(int pUtilisateurId, int pExemplaireId) throws NouveauPretFault_Exception {
		LOGGER.traceEntry("pUtilisateurId = " + pUtilisateurId + " - pExemplaireId = " + pExemplaireId);
		
		try {
			managerFactory.getLivreManager().nouveauPret(pUtilisateurId,pExemplaireId);
		} catch (TechnicalException e) {
			LOGGER.debug(e);
			throw new NouveauPretFault_Exception(e.getMessageErreur());
		}

		LOGGER.traceExit();
		
	}

	@Override
	public Reservation retourPret(int pId) throws RetourPretFault_Exception {
		LOGGER.traceEntry("pId = " + pId);
		
		Reservation premierSurListeAttente = null;
		try {
			premierSurListeAttente = managerFactory.getLivreManager().retourPret(pId);
		} catch (AutreException e) {
			LOGGER.debug(e);
			throw new RetourPretFault_Exception(e.getMessage());
		}

		LOGGER.traceExit("vReservation = " + premierSurListeAttente);
		return premierSurListeAttente;
		
	}

	@Override
	public List<Reservation> miseAJourListesReservation() throws MiseAJourListesReservationFault_Exception {
		LOGGER.traceEntry();
		
		List<Reservation> vListReservationAJour = null;
		try {
			vListReservationAJour = managerFactory.getLivreManager().miseAJourListesReservation();
		} catch (TechnicalException e) {
			LOGGER.debug(e);
			throw new MiseAJourListesReservationFault_Exception(e.getMessage());
		}

		LOGGER.traceExit("vListReservationAJour = " + vListReservationAJour);
		return vListReservationAJour;
	}
	



}
