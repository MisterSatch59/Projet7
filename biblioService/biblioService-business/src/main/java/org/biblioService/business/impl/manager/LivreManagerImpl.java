package org.biblioService.business.impl.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.business.Donnees;
import org.biblioService.business.contrat.manager.LivreManager;
import org.biblioService.model.bean.DispoParBibliotheque;
import org.biblioService.model.bean.Livre;
import org.biblioService.model.bean.Pret;
import org.biblioService.model.bean.Reservation;
import org.biblioService.model.bean.UtilisateurBD;
import org.biblioService.model.exception.AutreException;
import org.biblioService.model.exception.NotFoundException;
import org.biblioService.model.exception.TechnicalException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Implémentation de LivreManager
 * 
 * @author Oltenos
 *
 */
@Named("livreManager")
public class LivreManagerImpl extends AbstractManagerImpl implements LivreManager {
	private static final Logger LOGGER = LogManager.getLogger(LivreManagerImpl.class);

	@Inject
	private Donnees donnees;

	@Override
	public List<Pret> getPretEnRetard() {
		LOGGER.traceEntry();

		int DUREE_PRET_EN_JOUR = donnees.getDUREE_PRET_EN_JOUR();

		Calendar vCalendar = Calendar.getInstance();
		vCalendar.add(Calendar.DATE, -DUREE_PRET_EN_JOUR);

		List<Pret> vListPret = getDaoFactory().getPretDao().getPretDebutAvant(vCalendar);

		// Retrait des pret prolongés encore valide car renouvele
		List<Pret> vListPret2 = new ArrayList<Pret>();
		if (vListPret != null) {
			for (Pret vPret : vListPret) {
				if (vPret.isRenouvele()) {
					Calendar vDateLimite = vPret.getDateDebut().toGregorianCalendar();
					vDateLimite.add(Calendar.DATE, 2 * DUREE_PRET_EN_JOUR);

					if (vDateLimite.compareTo(Calendar.getInstance()) < 0) {// = si la date limite est avant aujourd'hui
						vListPret2.add(vPret);
					}
				} else {
					vListPret2.add(vPret);
				}
			}
		}

		LOGGER.traceExit("vListPret = " + vListPret2);
		return vListPret2;
	}

	@Override
	public XMLGregorianCalendar prolongerPret(int pPretId) throws TechnicalException, NotFoundException {
		LOGGER.traceEntry("pPretId = " + pPretId);

		TransactionStatus vTransactionStatus = this.getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		try {
			getDaoFactory().getPretDao().prolongerPret(pPretId,donnees.getDUREE_PRET_EN_JOUR());

			TransactionStatus vTScommit = vTransactionStatus;
			vTransactionStatus = null;
			this.getPlatformTransactionManager().commit(vTScommit);
		} finally {
			if (vTransactionStatus != null) {
				this.getPlatformTransactionManager().rollback(vTransactionStatus);
				TechnicalException vException = new TechnicalException();
				vException.setMessageErreur("Erreur technique interne.");
				throw vException;
			}
		}
		
		XMLGregorianCalendar vNewDateRetourPrevue;
		Pret vPret = getDaoFactory().getPretDao().getPret(pPretId);
		if(vPret == null) {
			NotFoundException vException = new NotFoundException();
			vException.setMessageErreur("Aucun utilisateur ne correspond à cet identifiant.");
			throw vException;
		}
		
		vNewDateRetourPrevue = vPret.getDateRetourPrevue();

		LOGGER.traceExit("vNewDateRetourPrevue = " + vNewDateRetourPrevue);
		return vNewDateRetourPrevue;
	}

	@Override
	public List<Pret> getPretEnCours(int pUtilisateurId) throws NotFoundException {
		LOGGER.traceEntry("pUtilisateurId = " + pUtilisateurId);

		// Recupération de l'utilisateur
		UtilisateurBD vUtilisateurBD = this.getDaoFactory().getUtilisateurDao().getUtilisateurBD(pUtilisateurId);

		// lance une NotFoundException si la recherche ne retourne aucun resultat
		if (vUtilisateurBD == null) {
			NotFoundException vException = new NotFoundException();
			vException.setMessageErreur("Aucun utilisateur ne correspond à cet identifiant.");
			throw vException;
		}

		// Récupération de la liste des prêts
		List<Pret> vListPret = getDaoFactory().getPretDao().getPretEnCours(pUtilisateurId);

		LOGGER.traceExit("vListPret = " + vListPret);
		return vListPret;
	}

	@Override
	public List<Livre> rechercherLivre(String pTitre, String pAuteur, String pGenre, String pLangue) {
		LOGGER.traceEntry(
				"pTitre = " + pTitre + " pAuteur = " + pAuteur + " pGenre = " + pGenre + " pLangue = " + pLangue);

		List<Livre> vListLivre = getDaoFactory().getLivreDao().rechercherLivre(pTitre, pAuteur, pGenre, pLangue);

		LOGGER.traceExit("vListLivre = " + vListLivre);
		return vListLivre;
	}

	@Override
	public List<DispoParBibliotheque> getDispo(String pISBN) {
		LOGGER.traceEntry("pISBN = " + pISBN);

		Map<String, Integer> vDispo = getDaoFactory().getLivreDao().getDispo(pISBN);
		
		List<DispoParBibliotheque> rDispo =  new ArrayList<DispoParBibliotheque>();
		
		Set<String> vKeys = vDispo.keySet();
		for (String vKey : vKeys) {
			DispoParBibliotheque vDispoParBibliotheque = new DispoParBibliotheque();
			vDispoParBibliotheque.setBibliotheque(vKey);
			vDispoParBibliotheque.setNombre(vDispo.get(vKey));
			rDispo.add(vDispoParBibliotheque);
		}
		
		for (DispoParBibliotheque dispo : rDispo) {
			if(dispo.getNombre()==0) {
				dispo.setProchainRetour(getDaoFactory().getLivreDao().getProchainRetour(dispo.getBibliotheque(),pISBN));
				dispo.setPersonnesEnAttente(getDaoFactory().getLivreDao().getPersonnesEnAttente(dispo.getBibliotheque(),pISBN));
			}
		}

		LOGGER.traceExit("vDispo = " + vDispo);
		return rDispo;
	}

	@Override
	public List<String> getGenres() {
		LOGGER.traceEntry();

		List<String> vListGenre = getDaoFactory().getGenreDao().getGenres();

		LOGGER.traceExit("vListGenre = " + vListGenre);
		return vListGenre;
	}

	@Override
	public List<String> getLangues() {
		LOGGER.traceEntry();

		List<String> vListLangue = getDaoFactory().getLangueDao().getLangues();

		LOGGER.traceExit("vListLangue = " + vListLangue);
		return vListLangue;
	}


	@Override
	public void createReservation(String pISBN, String pBibliotheque, int pUtilisateurId) throws AutreException {
		LOGGER.traceEntry("pISBN = " + pISBN, " - pBibliotheque = " + " - pUtilisateurId = " + pUtilisateurId);
		
		List<Pret> vPretEnCours = getDaoFactory().getPretDao().getPretEnCours(pUtilisateurId);
		for (Pret pret : vPretEnCours) {
			if(pret.getExemplaire().getLivre().getIsbn().equals(pISBN)) {
				throw new AutreException("Impossible de réserver un livre déjà en cours d'emprunt (y compris dans une autre bibliothèque)");
			}
		}
		
		TransactionStatus vTransactionStatus = this.getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		try {
			getDaoFactory().getReservationDao().createReservation(pISBN,pBibliotheque,pUtilisateurId);

			TransactionStatus vTScommit = vTransactionStatus;
			vTransactionStatus = null;
			this.getPlatformTransactionManager().commit(vTScommit);
		} finally {
			if (vTransactionStatus != null) {
				this.getPlatformTransactionManager().rollback(vTransactionStatus);
				TechnicalException vException = new TechnicalException();
				vException.setMessageErreur("Erreur technique interne.");
				throw vException;
			}
		}

		LOGGER.traceExit();
	}

	
	@Override
	public List<Reservation> listerReservation(int pUtilisateurId) {
		LOGGER.traceEntry("pUtilisateurId = " + pUtilisateurId);
		
		List<Reservation> vResult = getDaoFactory().getReservationDao().getReservation(pUtilisateurId);
		
		LOGGER.traceEntry("vResult = " + vResult);
		return vResult;
	}


	@Override
	public void deleteReservation(String pISBN, String pBibliotheque, int pUtilisateurId) throws TechnicalException {
		LOGGER.traceEntry("pISBN = " + pISBN, " - pBibliotheque = " + " - pUtilisateurId = " + pUtilisateurId);
		
		TransactionStatus vTransactionStatus = this.getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		try {
			getDaoFactory().getReservationDao().deleteReservation(pISBN,pBibliotheque,pUtilisateurId);

			TransactionStatus vTScommit = vTransactionStatus;
			vTransactionStatus = null;
			this.getPlatformTransactionManager().commit(vTScommit);
		} finally {
			if (vTransactionStatus != null) {
				this.getPlatformTransactionManager().rollback(vTransactionStatus);
				TechnicalException vException = new TechnicalException();
				vException.setMessageErreur("Erreur technique interne.");
				throw vException;
			}
		}
		
	}

	
	
}
