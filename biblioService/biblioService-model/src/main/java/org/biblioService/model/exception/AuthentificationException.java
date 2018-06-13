package org.biblioService.model.exception;

/**
 * Exception levé lorsque l'authentification d'un utilisateur est incorrrecte
 * @author Oltenos
 *
 */
public class AuthentificationException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public AuthentificationException() {
		super("Erreur d'authentification.");
	}

	// message d'erreur
	private String messageErreur;

	// Getters et Setters
	public String getMessageErreur() {
		return messageErreur;
	}

	public void setMessageErreur(String messageErreur) {
		this.messageErreur = messageErreur;
	}

}
