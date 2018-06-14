package org.biblioService.model.exception;

/**
 * Exception levé lorsque aucun élément correspondant aux paramètre n'a été trouvé
 * @author Oltenos
 *
 */
public class NotFoundException extends AutreException {
	private static final long serialVersionUID = 1L;
	
	public NotFoundException() {
		super("Element(s) non trouvé(s).");
	}

}
