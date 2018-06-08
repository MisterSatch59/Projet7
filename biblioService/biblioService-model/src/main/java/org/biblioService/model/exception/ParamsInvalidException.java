package org.biblioService.model.exception;

import java.util.List;

/**
 * Exception levé lorsque les paramètres d'une requete sont invalides
 * @author Oltenos
 *
 */
public class ParamsInvalidException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ParamsInvalidException() {
		super("Paramètre(s) invalide(s)!");
		
	}

	// list des messages d'erreur
	private List<String> listErreur;

	// Getters et Setters
	public List<String> getListErreur() {
		return listErreur;
	}

	public void setListErreur(List<String> listErreur) {
		this.listErreur = listErreur;
	}

}
