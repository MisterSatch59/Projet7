package org.biblio.biblioBatch.livre.mail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.biblio.biblioBatch.livre.Config;
import org.biblio.biblioBatch.livre.generated.LivreService;
import org.biblio.biblioBatch.livre.generated.types.Auteur;
import org.biblio.biblioBatch.livre.generated.types.Livre;
import org.biblio.biblioBatch.livre.generated.types.Pret;
import org.biblio.biblioBatch.livre.generated.types.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
/**
 * Classe effectuant l'envoie périodique des mails
 * 
 * @author Oltenos
 *
 */
public class EnvoiMessageRappel {

	@Autowired
	private EmailService emailService;

	@Autowired
	private Config config;

	@Scheduled(cron = "${envoieMail.rappel.cron}", zone = "Europe/Paris") // Périodicité d'envoi selon CRON
	public void execute() {
		System.out.println("***** Envoie des messages de rappel*****");

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(config.getLivreServiceAdresse());
		factory.setServiceClass(LivreService.class);

		LivreService client = (LivreService) factory.create();

		envoyerMailRappel(client);
	}

	/**
	 * Envoi mail de rappel avant échéance
	 * 
	 * @param client
	 */
	private void envoyerMailRappel(LivreService client) {
		List<Pret> listPretRappel = client.infoMailRappel();

		List<Utilisateur> vListUtilisateur = new ArrayList<Utilisateur>();// List des utilisateur déjà passé

		if (listPretRappel != null) {
			for (Pret pret : listPretRappel) {

				Utilisateur u = pret.getUtilisateur();// utilisateur en cours

				boolean dejaTraite = false;
				for (Utilisateur user : vListUtilisateur) {// Test si l'utilisateur à déjà été traité
					if (user.getId() == u.getId()) {
						dejaTraite = true;
					}
				}
				if (!dejaTraite) {
					List<Pret> listPretParUtilisateur = new ArrayList<Pret>();
					for (Pret pret2 : listPretRappel) {// Cherche tous les pret du mêem utilisateur
						if (pret2.getUtilisateur().getId() == u.getId()) {
							listPretParUtilisateur.add(pret2);
						}
					}
					vListUtilisateur.add(u);// Ajout de l'utilisateur au utilisateur traitée

					// Préparation et envoi du mail
					Mail mail = new Mail();
					mail.setTo(pret.getUtilisateur().getEmail());
					mail.setSubject(config.getRappelSujet());
					String vMessage = "<h1>Bonjour " + pret.getUtilisateur().getPrenom() + " " + pret.getUtilisateur().getNom() + "!</h1>";

					vMessage += "<br/>";
					if (listPretParUtilisateur.size() > 1) {
						vMessage += config.getRappelPresentationPlusieursLivre();
					} else {
						vMessage += config.getRappelPresentationUnLivre();
					}
					vMessage += "<br/>";

					int i = 0;
					for (Pret pret2 : listPretParUtilisateur) {
						vMessage += "<br/>";
						if (i != 0)
							vMessage += "----------------------------------------";
						vMessage += "<br/><br/>";
						i++;
						// Ajout des informations du livre au message
						Livre vLivre = pret2.getExemplaire().getLivre();
						vMessage += vLivre.getTitre() + " de ";
						List<Auteur> vAuteurs = vLivre.getAuteur();
						if (vAuteurs.size() == 1) {
							vMessage += vAuteurs.get(0).getPrenom() + " " + vAuteurs.get(0).getNom();
						} else {
							for (Iterator<Auteur> iterator = vAuteurs.iterator(); iterator.hasNext();) {
								Auteur auteur = iterator.next();
								vMessage += auteur.getPrenom() + " " + auteur.getNom();
								if (iterator.hasNext()) {
									vMessage += ", ";
								}
							}
						}
						vMessage += "<br/>";
						vMessage += "aux éditions " + vLivre.getEditeur().getNom();
						vMessage += "<br/>";
						vMessage += "<br/>";
						vMessage += "Emprunté depuis le : " + pret2.getDateDebut().getDay() + "/" + pret2.getDateDebut().getMonth() + "/" + pret2.getDateDebut().getYear();
						vMessage += ", la date de retour prévue est le : " + pret2.getDateRetourPrevue().getDay() + "/" + pret2.getDateRetourPrevue().getMonth() + "/" + pret2.getDateRetourPrevue().getYear();

						vMessage += "<br/>";
						vMessage += "<br/>";
						vMessage += config.getRappelDemande() + pret2.getExemplaire().getBibliotheque() + ".";
						vMessage += "<br/>";
					}
					vMessage += "<br/>";
					vMessage += "<br/>";
					vMessage += config.getFormulePolitesse();
					vMessage += "<br/>";
					vMessage += "<br/>";
					vMessage += config.getSignature();

					mail.setContent(vMessage);

					emailService.sendHtmlMessage(mail);
				}

			}
		}
	}
}
