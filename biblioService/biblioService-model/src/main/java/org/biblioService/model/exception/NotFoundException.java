package org.biblioService.model.exception;

/**
 * Exception levé lorsque aucun élément correspondant aux paramètre n'a été trouvé
 * @author Oltenos
 *
 */
public class NotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NotFoundException() {
		super("Element(s) non trouvé(s).");
	}

	// message d'erreur
	private String messageErreur;

	//Getters et Setters
	public String getMessageErreur() {
		return messageErreur;
	}

	public void setMessageErreur(String messageErreur) {
		this.messageErreur = messageErreur;
	}

}
