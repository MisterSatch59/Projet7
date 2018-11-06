package org.biblioService.business.impl.manager;

import java.sql.Date;
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
import org.biblioService.model.bean.Exemplaire;
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

		Calendar vCalendar = Calendar.getInstance();

		List<Pret> vListPret = getDaoFactory().getPretDao().getPretDateRetourAvant(vCalendar);

		LOGGER.traceExit("vListPret = " + vListPret);
		return vListPret;
	}

	@Override
	public XMLGregorianCalendar prolongerPret(int pPretId) throws TechnicalException, NotFoundException, AutreException {
		LOGGER.traceEntry("pPretId = " + pPretId);

		// Vérifie que le pret existe et le récupére
		Pret vPret = verifierPret(pPretId);

		// vérifie que le pret n'a pas déjà été prolongé
		if (vPret.isRenouvele()) {
			AutreException vException = new AutreException("La durée de prêt à déjà été prolongé");
			vException.setMessageErreur("La durée de prêt ne peut être prolongée qu'une seule fois.");
			throw vException;
		}

		TransactionStatus vTransactionStatus = this.getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		try {
			getDaoFactory().getPretDao().prolongerPret(pPretId, donnees.getDUREE_PRET_EN_JOUR());

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
		vPret = getDaoFactory().getPretDao().getPret(pPretId);
		vNewDateRetourPrevue = vPret.getDateRetourPrevue();

		LOGGER.traceExit("vNewDateRetourPrevue = " + vNewDateRetourPrevue);
		return vNewDateRetourPrevue;
	}

	@Override
	public List<Pret> getPretEnCours(int pUtilisateurId) throws NotFoundException {
		LOGGER.traceEntry("pUtilisateurId = " + pUtilisateurId);

		// Vérifie que l'utilisateur existe et le récupére
		UtilisateurBD vUtilisateurBD = verifierUtilisateur(pUtilisateurId);

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
		LOGGER.traceEntry("pTitre = " + pTitre + " pAuteur = " + pAuteur + " pGenre = " + pGenre + " pLangue = " + pLangue);

		List<Livre> vListLivre = getDaoFactory().getLivreDao().rechercherLivre(pTitre, pAuteur, pGenre, pLangue);

		LOGGER.traceExit("vListLivre = " + vListLivre);
		return vListLivre;
	}

	@Override
	public List<DispoParBibliotheque> getDispo(String pISBN) throws NotFoundException {
		LOGGER.traceEntry("pISBN = " + pISBN);

		// Vérifie l'existance du livre
		verifierLivre(pISBN);

		Map<String, Integer> vDispo = getDaoFactory().getLivreDao().getDispo(pISBN);

		List<DispoParBibliotheque> rDispo = new ArrayList<DispoParBibliotheque>();

		Set<String> vKeys = vDispo.keySet();
		for (String vKey : vKeys) {
			DispoParBibliotheque vDispoParBibliotheque = new DispoParBibliotheque();
			vDispoParBibliotheque.setBibliotheque(vKey);
			vDispoParBibliotheque.setNombre(vDispo.get(vKey));
			rDispo.add(vDispoParBibliotheque);
		}

		for (DispoParBibliotheque dispo : rDispo) {
			if (dispo.getNombre() == 0) {
				dispo.setProchainRetour(getDaoFactory().getLivreDao().getProchainRetour(dispo.getBibliotheque(), pISBN));
				dispo.setPersonnesEnAttente(getDaoFactory().getLivreDao().getPersonnesEnAttente(dispo.getBibliotheque(), pISBN));
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

		// Vérifie l'existance du livre
		verifierLivre(pISBN);

		// Vérifie l'existance de la bibliotheque
		verifierBibliotheque(pBibliotheque);

		// Vérifie l'existance de l'utilisateur
		verifierUtilisateur(pUtilisateurId);

		// Vérifie que le livre n'est pas disponible
		Map<String, Integer> vDispo = getDaoFactory().getLivreDao().getDispo(pISBN);
		if (vDispo.get(pBibliotheque) != 0) {
			AutreException vAutreException = new AutreException("Impossible d'effectuer la réservation");
			vAutreException.setMessageErreur("Au moins un exemplaire est disponible dans cette bibliotheque");
			throw vAutreException;
		}

		// Vérifie que le livre n'est pas déjà emprunté
		List<Pret> vPretEnCours = getDaoFactory().getPretDao().getPretEnCours(pUtilisateurId);
		for (Pret pret : vPretEnCours) {
			if (pret.getExemplaire().getLivre().getIsbn().equals(pISBN)) {
				AutreException vAutreException = new AutreException("Impossible d'effectuer la réservation");
				vAutreException.setMessageErreur("Impossible de réserver un livre déjà en cours d'emprunt (y compris dans une autre bibliothèque)");
				throw vAutreException;
			}
		}
		
		//Vérifie si le livre a déjà été réservé
		List<Reservation> vListReservation = getDaoFactory().getReservationDao().getReservation(pUtilisateurId);
		for (Reservation reservation : vListReservation) {
			if(reservation.getLivre().getIsbn().equals(pISBN)) {
				AutreException vAutreException = new AutreException("Impossible d'effectuer la réservation");
				vAutreException.setMessageErreur("Impossible de réserver un livre déjà réservé (y compris dans une autre bibliothèque)");
				throw vAutreException;
			}
		}

		// Vérifie que le nombre maximal de reservation n'est pas atteint
		if (getDaoFactory().getReservationDao().getNbReservation(pISBN, pBibliotheque) >= 2 * getDaoFactory().getExemplaireDao().getNbExemplaire(pISBN, pBibliotheque)) {

			AutreException vAutreException = new AutreException("Impossible d'effectuer la réservation");
			vAutreException.setMessageErreur("Le nombre maximal de reservation est atteint");
			throw vAutreException;
		}

		// Création de la réservation
		TransactionStatus vTransactionStatus = this.getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		try {
			getDaoFactory().getReservationDao().createReservation(pISBN, pBibliotheque, pUtilisateurId);

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
	public List<Reservation> listerReservation(int pUtilisateurId) throws NotFoundException {
		LOGGER.traceEntry("pUtilisateurId = " + pUtilisateurId);

		// Vérifie l'existance de l'utilisateur
		verifierUtilisateur(pUtilisateurId);

		List<Reservation> vResult = getDaoFactory().getReservationDao().getReservation(pUtilisateurId);

		LOGGER.traceExit("vResult = " + vResult);
		return vResult;
	}

	@Override
	public void deleteReservation(String pISBN, String pBibliotheque, int pUtilisateurId) throws TechnicalException {
		LOGGER.traceEntry("pISBN = " + pISBN + " - pBibliotheque = " + pBibliotheque + " - pUtilisateurId = " + pUtilisateurId);

		TransactionStatus vTransactionStatus = this.getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		try {
			getDaoFactory().getReservationDao().deleteReservation(pISBN, pBibliotheque, pUtilisateurId);

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
	public int nouveauPret(int pUtilisateurId, int pExemplaireId) throws AutreException {
		LOGGER.traceEntry("pUtilisateurId = " + pUtilisateurId + " - pExemplaireId = " + pExemplaireId);

		// Vérifie l'existance de l'utilisateur
		verifierUtilisateur(pUtilisateurId);

		// Vérifie l'existance de l'exemplaire
		verifierExemplaire(pExemplaireId);

		// Vérification de la disponibilité de l'exemplaire
		if (getDaoFactory().getPretDao().isEmprunte(pExemplaireId)) {
			AutreException vAutreException = new AutreException("Impossible d'effectuer le prêt.");
			vAutreException.setMessageErreur("Cet exemplaire est déjà emprunté.");
			throw vAutreException;
		}

		// Création date de début (date du jour) et de retour prévu
		Date vDateDebut = new Date(Calendar.getInstance().getTime().getTime());

		Calendar vCalendar = Calendar.getInstance();
		vCalendar.add(Calendar.DATE, donnees.getDUREE_PRET_EN_JOUR());
		Date vDateRetourPrevu = new Date(vCalendar.getTime().getTime());

		// Création du Pret en persistance
		TransactionStatus vTransactionStatus = this.getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());

		int id;
		try {
			id = getDaoFactory().getPretDao().createPret(pUtilisateurId, pExemplaireId, vDateDebut, vDateRetourPrevu);

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
		
		LOGGER.traceExit("id = " + id);
		return id;
	}

	@Override
	public Reservation retourPret(int pId) throws AutreException {
		LOGGER.traceEntry("pId = " + pId);

		// Vérifie l'existance du prêt
		Pret vPret = verifierPret(pId);

		// Création date de début (date du jour) et de retour prévu
		Date vDateFin = new Date(Calendar.getInstance().getTime().getTime());

		// Vérifie que l'exemplaire n'a pas déjà été rendu
		if (vPret.getDateFin() != null) {
			AutreException vException = new AutreException("L'exemplaire à déjà été retourné");
			vException.setMessageErreur("L'exemplaire à déjà été retourné.");
			throw vException;
		}

		Reservation premierSurListeAttente = null;

		TransactionStatus vTransactionStatus = this.getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		try {
			getDaoFactory().getPretDao().retourPret(pId, vDateFin);

			// Récupération du premier sur liste d'attente ci celle-ci existe, null sinon
			premierSurListeAttente = getDaoFactory().getReservationDao().getPremierReservation(vPret.getExemplaire().getBibliotheque(), vPret.getExemplaire().getLivre().getIsbn());

			// Attribution du livre au premier de la liste si celle-ci exite
			if (premierSurListeAttente != null) {
				attribuerAuPremier(premierSurListeAttente,vPret.getExemplaire());
			}

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

		LOGGER.traceExit("vReservation = " + premierSurListeAttente);
		return premierSurListeAttente;

	}

	@Override
	public List<Reservation> miseAJourListesReservation() throws AutreException {
		LOGGER.traceEntry();

		// Récupération des reservation dont la date d'attribution (date_mail) est supérieur à 2 jours
		Calendar vCalendar = Calendar.getInstance();
		vCalendar.add(Calendar.DATE, -2);
		Date vDate = new Date(vCalendar.getTime().getTime());

		List<Reservation> ListReservationASuppr = getDaoFactory().getReservationDao().getListReservationDateAttributionAvant(vDate);

		if (ListReservationASuppr.size() == 0) {// Si la liste est vide : travail terminé...
			return null;
		} else {
			List<Reservation> vListReservationAJour = new ArrayList<Reservation>();
			TransactionStatus vTransactionStatus = this.getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
			try {
				for (Reservation reservation : ListReservationASuppr) {// Pour chaque reservation trouvée
					// Suppression de la reservation en persistance et de l'attribution du prêt
					this.deleteReservation(reservation.getLivre().getIsbn(), reservation.getBibliotheque(), reservation.getUtilisateur().getId());
					getDaoFactory().getPretDao().delete(reservation.getPret().getId());
					
					// Récupération du nouveau premier
					Reservation premierSurListeAttente = getDaoFactory().getReservationDao().getPremierReservation(reservation.getBibliotheque(), reservation.getLivre().getIsbn());
					if (premierSurListeAttente != null) {
						vListReservationAJour.add(premierSurListeAttente);

						// Attribution de l'exemplaire au premier de la liste si celle-ci exite
						if (premierSurListeAttente != null) {
							attribuerAuPremier(premierSurListeAttente,reservation.getPret().getExemplaire());
						}
					}
				}

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
			LOGGER.traceExit("vListReservationAJour = " + vListReservationAJour);
			return vListReservationAJour;

		}

	}

	private void verifierLivre(String pISBN) throws NotFoundException {
		if (getDaoFactory().getLivreDao().getLivre(pISBN) == null) {
			NotFoundException vException = new NotFoundException();
			vException.setMessageErreur("Aucun livre ne correspond à la demande.");
			throw vException;
		}
	}

	private void verifierBibliotheque(String pBibliotheque) throws NotFoundException {
		boolean exist = false;
		for (String vBibliotheque : getDaoFactory().getBibliothequeDao().getListBibliotheque()) {
			if (vBibliotheque.equals(pBibliotheque)) {
				exist = true;
				break;
			}
		}
		if (!exist) {
			NotFoundException vException = new NotFoundException();
			vException.setMessageErreur("La bibliotheque demandé n'existe pas.");
			throw vException;
		}
	}

	private UtilisateurBD verifierUtilisateur(int pUtilisateurId) throws NotFoundException {
		UtilisateurBD vUtilisateurBD = getDaoFactory().getUtilisateurDao().getUtilisateurBD(pUtilisateurId);
		if (vUtilisateurBD == null) {
			NotFoundException vException = new NotFoundException();
			vException.setMessageErreur("Aucun utilisateur ne correspond à la demande.");
			throw vException;
		}
		return vUtilisateurBD;
	}

	private void verifierExemplaire(int pExemplaireId) throws NotFoundException {
		if (getDaoFactory().getExemplaireDao().getExemplaire(pExemplaireId) == null) {
			NotFoundException vException = new NotFoundException();
			vException.setMessageErreur("Aucun exemplaire ne correspond à la demande.");
			throw vException;
		}

	}

	private Pret verifierPret(int pId) throws NotFoundException {
		Pret vPret = getDaoFactory().getPretDao().getPret(pId);
		if (vPret == null) {
			NotFoundException vException = new NotFoundException();
			vException.setMessageErreur("Aucun prêt ne correspond à la demande.");
			throw vException;
		}
		return vPret;
	}

	private void attribuerAuPremier(Reservation premierSurListeAttente, Exemplaire pExemplaire) throws AutreException {
		int vPretId = this.nouveauPret(premierSurListeAttente.getUtilisateur().getId(), pExemplaire.getId());
		Date vDateAttribution = new Date(Calendar.getInstance().getTime().getTime());
		getDaoFactory().getReservationDao().setAttribue(
				premierSurListeAttente.getBibliotheque(), premierSurListeAttente.getUtilisateur().getId(), premierSurListeAttente.getLivre().getIsbn(),
				vDateAttribution, vPretId);
	}

	@Override
	public List<Pret> infoMailRappel() {
		LOGGER.traceEntry();

		int DUREE_RAPPEL = 5;

		Calendar vCalendar = Calendar.getInstance();
		vCalendar.add(Calendar.DATE, DUREE_RAPPEL);

		List<Pret> vListPret = getDaoFactory().getPretDao().getPretRetourPrevuLe(vCalendar);

		// Retrait des prets dont l'utilisateur ne souhaite pas recevoir de mail de rappel
		List<Pret> vListPret2 = new ArrayList<Pret>();
		if (vListPret != null) {
			for (Pret vPret : vListPret) {
				if (vPret.getUtilisateur().isMailRappel()) {
					vListPret2.add(vPret);
				} 
			}
		}

		LOGGER.traceExit("vListPret = " + vListPret2);
		return vListPret2;
	}
}
