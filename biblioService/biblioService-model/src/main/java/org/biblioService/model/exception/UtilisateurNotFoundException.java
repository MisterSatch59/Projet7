package org.biblioService.model.exception;

public class UtilisateurNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	// code d'erreur
	private String codeErreur;

	// message d'erreur
	private String messageErreur;

	//Getters et Setters
	public String getCodeErreur() {
		return codeErreur;
	}

	public void setCodeErreur(String codeErreur) {
		this.codeErreur = codeErreur;
	}

	public String getMessageErreur() {
		return messageErreur;
	}

	public void setMessageErreur(String messageErreur) {
		this.messageErreur = messageErreur;
	}

}
