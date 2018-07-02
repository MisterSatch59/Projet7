package org.biblioWebapp.action;

import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.biblioWebapp.services.generated.livreservice.LivreService;
import org.biblioWebapp.services.generated.utilisateurservice.UtilisateurService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Superclasse de toute les classes contenant la session et l'accés aux services
 * @author Oltenos
 *
 */
public abstract class AbstractAction extends ActionSupport  implements SessionAware {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(AbstractAction.class);


	private LivreService livreService;
	
	private UtilisateurService utilisateurService;
	
	private Map<String, Object> session;
	
	protected LivreService getLivreService() {
		LOGGER.traceEntry();

		if(livreService==null) {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setAddress("http://localhost:8080/biblioService/services/livre");
			factory.setServiceClass(LivreService.class);

			livreService = (LivreService) factory.create();
		}

		LOGGER.traceExit(livreService);
		return livreService;
	}
	
	protected UtilisateurService getUtilisateurService() {
		LOGGER.traceEntry();

		if(utilisateurService==null) {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setAddress("http://localhost:8080/biblioService/services/utilisateur");
			factory.setServiceClass(UtilisateurService.class);

			utilisateurService = (UtilisateurService) factory.create();
		}

		LOGGER.traceExit(utilisateurService);
		return utilisateurService;
	}
	

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		LOGGER.traceEntry();
		return session;
	}
	
}
