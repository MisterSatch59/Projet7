package org.biblio.biblioBatch.livre.mail;

import java.util.Iterator;
import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.biblio.biblioBatch.livre.Config;
import org.biblio.biblioBatch.livre.generated.LivreService;
import org.biblio.biblioBatch.livre.generated.MiseAJourListesReservationFault_Exception;
import org.biblio.biblioBatch.livre.generated.types.Auteur;
import org.biblio.biblioBatch.livre.generated.types.Livre;
import org.biblio.biblioBatch.livre.generated.types.Pret;
import org.biblio.biblioBatch.livre.generated.types.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
/**
 * Classe effectuant l'envoie périodique des mails de relance
 * @author Oltenos
 *
 */
public class EnvoiMessage {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private Config config;

	@Scheduled(cron = "${envoieMail.cron}", zone = "Europe/Paris")//Périodicité d'envoi selon CRON
	public void execute() {
		System.out.println("Envoie des messages");
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(config.getLivreServiceAdresse());
		factory.setServiceClass(LivreService.class);

		LivreService client = (LivreService) factory.create();
		
		envoyerMailRelance(client);
		
		try {
			miseAjourReservation(client);
		} catch (MiseAJourListesReservationFault_Exception e) {
			e.printStackTrace();
		}

	}

	private void envoyerMailRelance(LivreService client) {
		List<Pret> listPretEnRetard = client.listerPretEnRetard();

		for (Pret pret : listPretEnRetard) {
			Mail mail = new Mail();
			mail.setTo(pret.getUtilisateur().getEmail());
			mail.setSubject(config.getSujet());

			String vMessage = "<h1>Bonjour " + pret.getUtilisateur().getPrenom() + " " + pret.getUtilisateur().getNom() + "!</h1>";

			vMessage += "<br/>";

			vMessage += config.getPresentation();
			vMessage += "<br/>";
			vMessage += "<br/>";

			// Ajout des informations du livre au message
			Livre vLivre = pret.getExemplaire().getLivre();
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
			vMessage += "Emprunté depuis le : " + pret.getDateDebut().getDay() +"/"+ pret.getDateDebut().getMonth() +"/"+ pret.getDateDebut().getYear();
			vMessage += ", la date de retour prévue était le : " + pret.getDateRetourPrevue().getDay() +"/"+ pret.getDateRetourPrevue().getMonth() +"/"+ pret.getDateRetourPrevue().getYear();
			

			vMessage += "<br/>";
			vMessage += "<br/>";
			vMessage += "<h3>"+ config.getDemande() + pret.getExemplaire().getBibliotheque() + "</h3>";

			vMessage += "<br/>";
			vMessage += config.getFormulePolitesse();
			vMessage += "<br/>";
			vMessage += "<br/>";
			vMessage += config.getSignature();

			mail.setContent(vMessage);

			emailService.sendHtmlMessage(mail);
		}
	}
	
	private void miseAjourReservation(LivreService client) throws MiseAJourListesReservationFault_Exception {

		List<Reservation> vListReservation = client.miseAJourListesReservation();
		
		for (Reservation reservation : vListReservation) {
			System.out.println(reservation);
			
			Mail mail = new Mail();
			mail.setTo(reservation.getUtilisateur().getEmail());
			mail.setSubject("Biblio - Le livre que vous avez réservé est disponible!");

			String vMessage = "<h1>Bonjour " + reservation.getUtilisateur().getPrenom() + " " + reservation.getUtilisateur().getNom() + "!</h1>";

			vMessage += "<br/>";

			vMessage += "Nous avons le plaisir de vous informer que le livre ci-dessous que vous avez réservé est disponible et peut être récupéré à la bibliothèque suivante : " + reservation.getBibliotheque();
			vMessage += "<br/>";
			vMessage += "<br/>";
			vMessage += "Détails de la réservation : ";
			vMessage += "<br/>";

			// Ajout des informations du livre au message
			Livre vLivre = reservation.getLivre();
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
			vMessage += "Réservé le : " + reservation.getDateResa().getDay() +"/"+ reservation.getDateResa().getMonth() +"/"+ reservation.getDateResa().getYear();

			vMessage += "<br/>";
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
