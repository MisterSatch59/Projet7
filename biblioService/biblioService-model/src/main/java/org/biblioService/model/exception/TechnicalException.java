package org.biblioService.model.exception;

/**
 * Exception levé lors d'un problème technique de la base de donnée
 * @author Oltenos
 *
 */
public class TechnicalException extends AutreException {
	private static final long serialVersionUID = 1L;
	
	public TechnicalException() {
		super("Erreur technique.");
	}
}
