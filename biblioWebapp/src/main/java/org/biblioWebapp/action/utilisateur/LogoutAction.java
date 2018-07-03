package org.biblioWebapp.action.utilisateur;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.biblioWebapp.action.AbstractAction;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action de déconnection d'un compte utilisateur
 * @author Oltenos
 *
 */
public class LogoutAction extends AbstractAction implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(LogoutAction.class);

	// ==================== Attributs ====================

	// ----- Paramètres en entrée

	// ----- Eléments en entrée et sortie

	// ----- Eléments en sortie

	// ==================== Getters/Setters ====================

	// ----- Paramètres en entrée (setters uniquement)

	// ----- Eléments en entrée et sortie (setters et getters)

	// ----- Eléments en sortie (getters uniquement)

	// ================= Eléments Struts =======================
	private HttpServletRequest servletRequest;

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
	// ================= Méthodes d'action ====================

	public String action() {
		LOGGER.traceEntry();
		String result = ActionSupport.SUCCESS;

		// Retrait de l'utilisateur de la session
		this.getSession().remove("utilisateur");
		// Invalidation de la session
		this.servletRequest.getSession().invalidate();

		LOGGER.traceExit(result);
		return result;
	}
}
