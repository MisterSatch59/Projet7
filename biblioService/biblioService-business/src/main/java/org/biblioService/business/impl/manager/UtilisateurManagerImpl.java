package org.biblioService.business.impl.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Named;
import javax.validation.ConstraintViolation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.business.contrat.manager.UtilisateurManager;
import org.biblioService.business.impl.PasswordUtils;
import org.biblioService.model.bean.Utilisateur;
import org.biblioService.model.bean.UtilisateurBD;
import org.biblioService.model.exception.AuthentificationException;
import org.biblioService.model.exception.AutreException;
import org.biblioService.model.exception.ParamsInvalidException;
import org.biblioService.model.exception.TechnicalException;
import org.biblioService.model.exception.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Implémentation de UtilisateurManager
 * 
 * @author Oltenos
 *
 */
@Named("utilisateurManager")
public class UtilisateurManagerImpl extends AbstractManagerImpl implements UtilisateurManager {
	private static final Logger LOGGER = LogManager.getLogger(UtilisateurManagerImpl.class);

	private final int TAILLE_SEL = 20;

	@Override
	public void createUtilisateur(String pNom, String pPrenom, String pEmail, String pMdp) throws ParamsInvalidException, TechnicalException {
		LOGGER.traceEntry("nom = " + pNom + " prenom = " + pPrenom + " email = " + pEmail + " mdp = " + pMdp);
		
		List<String> vLlistErreur = new ArrayList<String>();//Stocke les messages d'erreur
		
		//Vérification de la nullité des paramètres
		if(pNom==null||pNom.isEmpty()) {
			vLlistErreur.add("Le nom doit être renseigné.");
		}
		if(pEmail==null||pEmail.isEmpty()) {
			vLlistErreur.add("L'email doit être renseigné.");
		}
		if(pMdp==null||pMdp.isEmpty()) {
			vLlistErreur.add("Le mot de passe doit être renseigné.");
		}
		
		// Validation des paramètres (JSR 303/349) et compléxité du mdp
		vLlistErreur.addAll(this.validationParametres(pNom, pPrenom, pEmail, pMdp));
		
		//Lance une ParamsInvalidException si au moins un paramètre est invalide
		if (!vLlistErreur.isEmpty()) {
			ParamsInvalidException vException = new ParamsInvalidException();
			vException.setListErreur(vLlistErreur);
			throw vException;
		}

		// Création du bean utilisateur à partir des paramètres
		UtilisateurBD vUtilisateurBD = new UtilisateurBD();
		vUtilisateurBD.setNom(pNom);
		vUtilisateurBD.setPrenom(pPrenom);
		vUtilisateurBD.setEmail(pEmail);
		
		// Sécurisation (hachage) du mdp
		vUtilisateurBD.setSel(PasswordUtils.getSalt(TAILLE_SEL));
		String vMdp = PasswordUtils.generateSecurePassword(pMdp, vUtilisateurBD.getSel());
		vUtilisateurBD.setMdp(vMdp);

		// Appel du DAO pour création dans la BD dans une transaction
		TransactionStatus vTransactionStatus = this.getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		try {
			this.getDaoFactory().getUtilisateurDao().createUtilisateur(vUtilisateurBD);

			TransactionStatus vTScommit = vTransactionStatus;
			vTransactionStatus = null;
			this.getPlatformTransactionManager().commit(vTScommit);
		} catch (DuplicateKeyException e) {//Si l'email existe dejà (index avec condition d'unicité sur la colonne email)
			this.getPlatformTransactionManager().rollback(vTransactionStatus);
			vTransactionStatus = null;
			ParamsInvalidException vException = new ParamsInvalidException();
			vLlistErreur.add("L'email est déjà utilisé par un autre utilisateur, merci d'entrer un autre email.");
			vException.setListErreur(vLlistErreur);
			throw vException;
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
	public Utilisateur authentifierUtilisateur(String pEmail, String pMdp) throws AuthentificationException, NotFoundException {
		LOGGER.traceEntry("email = " + pEmail + " mdp = " + pMdp);

		// Récupération de l'utilisateur correspondant à l'email - NotFoundException si n'existe pas dans la base de données
		UtilisateurBD vUtilisateurBD = this.getDaoFactory().getUtilisateurDao().getUtilisateur(pEmail);
		
		if(vUtilisateurBD==null) {
			// lance une NotFoundException si la recherche ne retourne aucun resultat
			NotFoundException vException = new NotFoundException();
			vException.setMessageErreur("Aucun utilisateur ne correspond à cet email.");
			throw vException;
		}

		// Vérification du mdp - AuthentificationException si le mdp ne correspond pas
		verifierMdp(pMdp, vUtilisateurBD);

		Utilisateur vUtilisateur = new Utilisateur();
		vUtilisateur.setId(vUtilisateurBD.getId());
		vUtilisateur.setNom(vUtilisateurBD.getNom());
		vUtilisateur.setPrenom(vUtilisateurBD.getPrenom());
		vUtilisateur.setEmail(vUtilisateurBD.getEmail());

		LOGGER.traceExit(vUtilisateur);
		return vUtilisateur;
	}

	@Override
	public void updateUtilisateur(int pId, String pAncienMdp, String pNouveauNom, String pNouveauPrenom, String pNouveauMail, String pNouveauMdp) throws NotFoundException, AuthentificationException, ParamsInvalidException, TechnicalException {
		LOGGER.traceEntry("id = " + pId + " ancienMdp = " + pAncienMdp + " nouveauNom = " + pNouveauNom + " nouveauPrenom = " + pNouveauPrenom + " nouveauMail = " + pNouveauMail + " nouveauMdp = " + pNouveauMdp);

		//Recupération de l'utilisateur
		UtilisateurBD vUtilisateurBD = this.getDaoFactory().getUtilisateurDao().getUtilisateur(pId);
		
		// lance une NotFoundException si la recherche ne retourne aucun resultat
		if(vUtilisateurBD==null) {
			NotFoundException vException = new NotFoundException();
			vException.setMessageErreur("Aucun utilisateur ne correspond à cet identifiant.");
			throw vException;
		}
		
		//Vérifie l'ancien Mdp - AuthentificationException si le mdp ne correspond pas
		verifierMdp(pAncienMdp, vUtilisateurBD);
		
		List<String> vLlistErreur;//Stocke les messages d'erreur
		
		// Validation des paramètres (avec JSR 303/349) et compléxité du mdp
		vLlistErreur = this.validationParametres(pNouveauNom, pNouveauPrenom, pNouveauMail, pNouveauMdp);
		
		//Lance une ParamsInvalidException si au moins un paramètre non null et non vide est invalide
		if (!vLlistErreur.isEmpty()) {
			ParamsInvalidException vException = new ParamsInvalidException();
			vException.setListErreur(vLlistErreur);
			throw vException;
		}
		
		// Modification du bean utilisateur à partir des paramètres non null et non vide (les paramètres null ou vide sont ignorés)
		if(pNouveauNom!=null&&!pNouveauNom.isEmpty())
			vUtilisateurBD.setNom(pNouveauNom);
		if(pNouveauPrenom!=null&&!pNouveauPrenom.isEmpty())
			vUtilisateurBD.setPrenom(pNouveauPrenom);
		if(pNouveauMail!=null&&!pNouveauMail.isEmpty())
			vUtilisateurBD.setEmail(pNouveauMail);
		
		// Sécurisation (hachage) du nouveauMdp
		if(pNouveauMdp!=null&&!pNouveauMdp.isEmpty()) {
			vUtilisateurBD.setSel(PasswordUtils.getSalt(TAILLE_SEL));
			String vMdp = PasswordUtils.generateSecurePassword(pNouveauMdp, vUtilisateurBD.getSel());
			vUtilisateurBD.setMdp(vMdp);
		}

		// Appel du DAO pour modification de l'utilisateur dans la BD dans une transaction
		TransactionStatus vTransactionStatus = this.getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		try {
			this.getDaoFactory().getUtilisateurDao().updateUtilisateur(vUtilisateurBD);

			TransactionStatus vTScommit = vTransactionStatus;
			vTransactionStatus = null;
			this.getPlatformTransactionManager().commit(vTScommit);
		} catch (DuplicateKeyException e) {// Si l'email existe dejà (index avec condition d'unicité sur la colonne email)
			this.getPlatformTransactionManager().rollback(vTransactionStatus);
			vTransactionStatus = null;
			ParamsInvalidException vException = new ParamsInvalidException();
			vLlistErreur.add("L'email est déjà utilisé par un autre utilisateur, merci d'entrer un autre email.");
			vException.setListErreur(vLlistErreur);
			throw vException;
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
	public void deleteUtilisateur(int pId, String pMdp) throws NotFoundException, AuthentificationException,TechnicalException, AutreException {
		LOGGER.traceEntry("id = " + pId);
		
		// Recupération de l'utilisateur
		UtilisateurBD vUtilisateurBD = this.getDaoFactory().getUtilisateurDao().getUtilisateur(pId);
		
		// lance une NotFoundException si la recherche ne retourne aucun resultat
		if(vUtilisateurBD==null) {
			NotFoundException vException = new NotFoundException();
			vException.setMessageErreur("Aucun utilisateur ne correspond à cet identifiant.");
			throw vException;
		}

		// Vérifie le Mdp - AuthentificationException si le mdp ne correspond pas
		verifierMdp(pMdp, vUtilisateurBD);

		// Appel du DAO pour suppression de l'utilisateur dans la BD dans une transaction
		TransactionStatus vTransactionStatus = this.getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		try {
			this.getDaoFactory().getUtilisateurDao().deleteUtilisateur(pId);

			TransactionStatus vTScommit = vTransactionStatus;
			vTransactionStatus = null;
			this.getPlatformTransactionManager().commit(vTScommit);
		}catch (DataIntegrityViolationException e) {// Si un emprunt existe encore pour cet utilisateur
			this.getPlatformTransactionManager().rollback(vTransactionStatus);
			vTransactionStatus = null;
			AutreException vAutreException = new AutreException("Le compte utilisaeur ne peut pas être supprimé.");
			vAutreException.setMessageErreur("Le compte utilisateur ne peut être supprimé car des prêts sont encore en cours.");
			throw vAutreException;
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

	/**
	 * Validation des paramètres (avec JSR 303/349) et compléxité du mdp et retourne la liste des messages erreur (vide si aucune erreur)</br>
	 * Rq : Ne teste pas la nullité des paramètres
	 * @param pNom
	 * @param pPrenom
	 * @param pEmail
	 * @param pMdp
	 * @return List<String>
	 * @throws ParamsInvalidException
	 */
	private List<String> validationParametres(String pNom, String pPrenom, String pEmail, String pMdp) throws ParamsInvalidException {

		List<String> vLlistErreur = new ArrayList<String>();
		Set<ConstraintViolation<UtilisateurBD>> vViolations;
		
		if(pNom!=null&&!pNom.isEmpty()) {
			vViolations = this.getValidator().validateValue(UtilisateurBD.class,"nom", pNom);
			if (!vViolations.isEmpty()) {
				vLlistErreur.add("Le nom de l'utilisateur est incorrect (entre 1 et 100 caractères).");
			}
		}
		
		if(pPrenom!=null&&!pPrenom.isEmpty()) {
			vViolations = this.getValidator().validateValue(UtilisateurBD.class, "prenom", pPrenom);
			if (!vViolations.isEmpty()) {
				vLlistErreur.add("Le(s) prenom(s) de l'utilisateur est (sont) incorrect(s) (entre 1 et 100 caractères).");
			}
		}
		
		if(pEmail!=null&&!pEmail.isEmpty()) {
			vViolations = this.getValidator().validateValue(UtilisateurBD.class, "email", pEmail);
			if (!vViolations.isEmpty()) {
				vLlistErreur.add("L'email est incorrect (email correct avec maximum 100 caractères).");
			}
		}
		
		if(pMdp!=null&&!pMdp.isEmpty()) {
			// Vérification de la compléxité du mot de passe (8 caractères, 1 lettre, 1
			// caractère spécial et 1 chiffre minimum)
			Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*\\W).{8,}$");
			Matcher matcher = pattern.matcher(pMdp);
			if (!matcher.lookingAt()) {
				vLlistErreur.add(
						"Le mot de passe est incorrect (Le mot de passe doit contenir : Plus de 8 caratères, au moins 1 lettre, au moins 1 chiffre, au moins 1 caractère spécial).");
			}
		}

		return vLlistErreur;
	}
	
	/**
	 * Vérifie que le mdp proposé correspond au mdp de l'utilisateur, lance une AuthentificationException si ça ne correspond pas
	 * @param pMdp
	 * @param pUtilisateurBD
	 * @throws AuthentificationException lancé si le mdp ne correspond pas au mdp de l'utilisateur
	 */
	private void verifierMdp(String pMdp, UtilisateurBD pUtilisateurBD) throws AuthentificationException {
		String vSel = pUtilisateurBD.getSel();
		String vMdpPropose = PasswordUtils.generateSecurePassword(pMdp, vSel);
		if (!vMdpPropose.equals(pUtilisateurBD.getMdp())) {
			AuthentificationException vAuthentificationException = new AuthentificationException();
			vAuthentificationException.setMessageErreur("Le mot de passe ne correspond pas.");
			throw vAuthentificationException;
		}
	}

}
