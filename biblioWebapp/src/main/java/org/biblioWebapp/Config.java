package org.biblioWebapp;

/**
 * Bean de configuration
 * 
 * @author Oltenos
 *
 */
public class Config {

	private String ADRESSE_UTILISATEUR_SERVICE;
	private String ADRESSE_LIVRE_SERVICE;
	
	public String getADRESSE_UTILISATEUR_SERVICE() {
		return ADRESSE_UTILISATEUR_SERVICE;
	}
	public void setADRESSE_UTILISATEUR_SERVICE(String pADRESSE_UTILISATEUR_SERVICE) {
		ADRESSE_UTILISATEUR_SERVICE = pADRESSE_UTILISATEUR_SERVICE;
	}
	public String getADRESSE_LIVRE_SERVICE() {
		return ADRESSE_LIVRE_SERVICE;
	}
	public void setADRESSE_LIVRE_SERVICE(String pADRESSE_LIVRE_SERVICE) {
		ADRESSE_LIVRE_SERVICE = pADRESSE_LIVRE_SERVICE;
	}

}
