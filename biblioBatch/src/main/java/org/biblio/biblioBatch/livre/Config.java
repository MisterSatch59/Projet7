package org.biblio.biblioBatch.livre;

/**
 * Bean contenant les éléments de configuration
 * @author Oltenos
 *
 */
public class Config {

	private String sujet;
	private String presentation;
	private String demande;
	private String formulePolitesse;
	private String signature;
	
	private String livreServiceAdresse;

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public String getDemande() {
		return demande;
	}

	public void setDemande(String demande) {
		this.demande = demande;
	}

	public String getFormulePolitesse() {
		return formulePolitesse;
	}

	public void setFormulePolitesse(String formulePolitesse) {
		this.formulePolitesse = formulePolitesse;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getLivreServiceAdresse() {
		return livreServiceAdresse;
	}

	public void setLivreServiceAdresse(String livreServiceAdresse) {
		this.livreServiceAdresse = livreServiceAdresse;
	}

}
