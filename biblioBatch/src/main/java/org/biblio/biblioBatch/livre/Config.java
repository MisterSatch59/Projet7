package org.biblio.biblioBatch.livre;

/**
 * Bean contenant les éléments de configuration
 * @author Oltenos
 *
 */
public class Config {

	//Mails de relance
	private String sujet;
	private String presentation;
	private String demande;
	
	//Mails réservation disponible
	private String reservationSujet;
	private String reservationPresentation;
	private String reservationDetail;

	//Mails de rappel
	private String rappelSujet;
	private String rappelPresentationUnLivre;
	private String rappelPresentationPlusieursLivre;
	private String rappelDemande;
	
	//Commun
	private String formulePolitesse;
	private String signature;
	
	private String livreServiceAdresse;

	//Getters et Setters
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

	public String getReservationSujet() {
		return reservationSujet;
	}

	public void setReservationSujet(String reservationSujet) {
		this.reservationSujet = reservationSujet;
	}

	public String getReservationPresentation() {
		return reservationPresentation;
	}

	public void setReservationPresentation(String reservationPresentation) {
		this.reservationPresentation = reservationPresentation;
	}

	public String getReservationDetail() {
		return reservationDetail;
	}

	public void setReservationDetail(String reservationDetail) {
		this.reservationDetail = reservationDetail;
	}

	public String getRappelSujet() {
		return rappelSujet;
	}

	public void setRappelSujet(String rappelSujet) {
		this.rappelSujet = rappelSujet;
	}

	public String getRappelPresentationUnLivre() {
		return rappelPresentationUnLivre;
	}

	public void setRappelPresentationUnLivre(String rappelPresentationUnLivre) {
		this.rappelPresentationUnLivre = rappelPresentationUnLivre;
	}

	public String getRappelPresentationPlusieursLivre() {
		return rappelPresentationPlusieursLivre;
	}

	public void setRappelPresentationPlusieursLivre(String rappelPresentationPlusieursLivre) {
		this.rappelPresentationPlusieursLivre = rappelPresentationPlusieursLivre;
	}

	public String getRappelDemande() {
		return rappelDemande;
	}

	public void setRappelDemande(String rappelDemande) {
		this.rappelDemande = rappelDemande;
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
