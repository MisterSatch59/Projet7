package org.biblioService.business.impl.manager;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.business.contrat.manager.LivreManager;
import org.biblioService.model.bean.Pret;

@Named("livreManager")
public class LivreManagerImpl  extends AbstractManagerImpl implements LivreManager {
	private static final Logger LOGGER = LogManager.getLogger(LivreManagerImpl.class);
	
	private static int DUREE_PRET_EN_JOUR = 28;

	@Override
	public List<Pret> getPretEnRetard() {
		LOGGER.traceEntry();
		
		Calendar vCalendar = Calendar.getInstance();
		vCalendar.add(Calendar.DATE, -DUREE_PRET_EN_JOUR);//TODO definir la durée de pret en fichier config ici en dur à 28 jours
		
		List<Pret> vListPret = getDaoFactory().getPretDao().getPretDebutAvant(vCalendar);
		
		//Retrait des pret prolongé encore valide
		List<Pret> vListPret2 = new ArrayList<Pret>();
		for (Pret pret : vListPret) {
			if(pret.isRenouvele()) {
				Calendar vDateLimite  = pret.getDateDebut().toGregorianCalendar();
				vDateLimite.add(Calendar.DATE, 2*DUREE_PRET_EN_JOUR);
				
				if(vDateLimite.compareTo(Calendar.getInstance())<0) {// = si la date limite est avant aujourd'hui
					vListPret2.add(pret);
				}
			} else {
				vListPret2.add(pret);
			}
			
		}
		
		LOGGER.traceExit("vListPret = " + vListPret2);
		return vListPret2;
	}

}
