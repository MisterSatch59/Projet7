package org.biblioService.model.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Bean utilisateur complet pour travail en interne avec le BD
 * @author Oltenos
 *
 */
public class UtilisateurBD {
	
private int id;
	
	@NotNull
	@Size(max = 100)
	private String nom;
	
	@Size(max = 100)
	private String prenom;
	
	@NotNull
	@Size(max = 100)
	@Email
	private String email;
	
	@NotNull
	@Size(max = 100)
	private String mdp;
	
	@NotNull
	@Size(max = 20)
	private String sel;
	
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

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getSel() {
		return sel;
	}

	public void setSel(String sel) {
		this.sel = sel;
	}

	@Override
	public String toString() {
		return "UtilisateurBD [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", mdp=" + mdp + ", sel=" + sel + "]";
	}

}
