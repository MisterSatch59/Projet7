package org.biblioService.model.exception;

/**
 * Exception levé lors d'un problème technique de la base de donnée
 * @author Oltenos
 *
 */
public class TechnicalException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TechnicalException() {
		super("Erreur technique");
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
