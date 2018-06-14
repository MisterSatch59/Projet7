package org.biblioService.model.exception;

/**
 * Exception levé lorsque l'authentification d'un utilisateur est incorrrecte
 * @author Oltenos
 *
 */
public class AuthentificationException extends AutreException {
	private static final long serialVersionUID = 1L;
	
	public AuthentificationException() {
		super("Erreur d'authentification.");
	}

}
