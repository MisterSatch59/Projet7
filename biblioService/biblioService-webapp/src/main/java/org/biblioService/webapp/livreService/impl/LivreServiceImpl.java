package org.biblioService.webapp.livreService.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.business.contrat.ManagerFactory;
import org.biblioService.model.bean.Livre;
import org.biblioService.model.bean.Pret;
import org.biblioService.webapp.livreService.generated.ListerPretEnCoursFault_Exception;
import org.biblioService.webapp.livreService.generated.LivreService;
import org.biblioService.webapp.livreService.generated.ProlongerPretFault_Exception;
import org.biblioService.webapp.livreService.generated.VoirDispo;
import org.biblioService.webapp.livreService.generated.VoirDispoFault_Exception;
import org.biblioService.webapp.livreService.generated.VoirDispoResponse;

public class LivreServiceImpl implements LivreService {
	
	private static final Logger LOGGER = LogManager.getLogger(LivreServiceImpl.class);
	
	@Inject
	private ManagerFactory managerFactory;

	@Override
	public List<Pret> listerPretEnCours(int utilisateurId) throws ListerPretEnCoursFault_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pret> listerPretEnRetard() {
		LOGGER.traceEntry();
		
		List<Pret> vListPret = managerFactory.getLivreManager().getPretEnRetard();
		
		LOGGER.traceExit("vListPret = " + vListPret);
		return vListPret;
	}

	@Override
	public List<Livre> rechercherLivre(String titre, String auteur, String genre, String langue) {
		// TODO Auto-generated method stub
		Livre livre = new Livre();
		livre.setLangue("Fr");
		livre.setTitre("test");
		livre.setIsbn("123456789");
		List<Livre> list = new ArrayList<Livre>();
		list.add(livre);
		return list;
	}

	@Override
	public VoirDispoResponse voirDispo(VoirDispo parameters) throws VoirDispoFault_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prolongerPret(int pretId) throws ProlongerPretFault_Exception {
		// TODO Auto-generated method stub

	}

}
