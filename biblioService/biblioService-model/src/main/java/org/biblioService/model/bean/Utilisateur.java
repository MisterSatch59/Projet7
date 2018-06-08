package org.biblioService.model.bean;

/**
 * Bean utilisateur
 * @author Oltenos
 *
 */
public class Utilisateur {
	
	private int id;
	
	private String nom;
	
	private String prenom;
	
	private String email;
	
	//Getters et Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Utilisateur [nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}

}
