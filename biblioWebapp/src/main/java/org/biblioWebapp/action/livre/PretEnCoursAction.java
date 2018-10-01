package org.biblioWebapp.action.livre;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioWebapp.action.AbstractAction;
import org.biblioWebapp.services.generated.livreservice.ListerPretEnCoursFault_Exception;
import org.biblioWebapp.services.generated.livreservice.LivreService;
import org.biblioWebapp.services.generated.types.Pret;
import org.biblioWebapp.services.generated.types.Utilisateur;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action permettant l'affichage des prets en cours de l'utilisateur
 * @author Oltenos
 *
 */
public class PretEnCoursAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(PretEnCoursAction.class);

	// ==================== Attributs ====================

	// ----- Paramètres en entrée

	// ----- Eléments en entrée et sortie

	// ----- Eléments en sortie

	private Map<Pret,Boolean> mapPret;

	// ==================== Getters/Setters ====================

	// ----- Paramètres en entrée (setters uniquement)

	// ----- Eléments en entrée et sortie (setters et getters)

	// ----- Eléments en sortie (getters uniquement)

	public Map<Pret, Boolean> getMapPret() {
		return mapPret;
	}

	// ================= Méthodes d'action ====================

	public String action() {
		LOGGER.traceEntry();
		String result = ActionSupport.SUCCESS;
		
		Utilisateur vUtilisateur = (Utilisateur) this.getSession().get("utilisateur");

		LivreService vLivreService = this.getLivreService();
		try {
			mapPret= new HashMap<Pret,Boolean>();
			List<Pret> listPret = vLivreService.listerPretEnCours(vUtilisateur.getId());
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
	        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
	        XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
			for (Pret pret : listPret) {
				if(pret.isRenouvele()||pret.getDateRetourPrevue().compare(now)<0) {//Renouvelable que si pas déjà renouvelé et si la date de retour prévu est future
					mapPret.put(pret,false);
				}else {
					mapPret.put(pret,true);
				}
			}
		} catch (ListerPretEnCoursFault_Exception e) {
			addActionError(e.getFaultInfo().getFaultMessage());
		} catch (DatatypeConfigurationException e) {
			LOGGER.debug(e);
		}
		
		LOGGER.traceExit(result);
		return result;
	}

}
