package org.biblio.biblioBatch.livre.mail;

import java.util.Iterator;
import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.biblio.biblioBatch.livre.Config;
import org.biblio.biblioBatch.livre.generated.LivreService;
import org.biblio.biblioBatch.livre.generated.MiseAJourListesReservationFault_Exception;
import org.biblio.biblioBatch.livre.generated.types.Auteur;
import org.biblio.biblioBatch.livre.generated.types.Livre;
import org.biblio.biblioBatch.livre.generated.types.Reservation;
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
public class EnvoiMessageReservation {

	@Autowired
	private EmailService emailService;

	@Autowired
	private Config config;

	@Scheduled(cron = "${envoieMail.reservation.cron}", zone = "Europe/Paris") // Périodicité d'envoi selon CRON
	public void execute() {
		System.out.println("***** Envoie des messages d'information de disponibilité des livres réservés(suite mise à jour)*****");

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(config.getLivreServiceAdresse());
		factory.setServiceClass(LivreService.class);

		LivreService client = (LivreService) factory.create();

		try {
			miseAjourReservation(client);
		} catch (MiseAJourListesReservationFault_Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Envoi mails d'information de disponibilité des livres réservés(suite mise à jour)
	 * 
	 * @param client
	 * @throws MiseAJourListesReservationFault_Exception
	 */
	private void miseAjourReservation(LivreService client) throws MiseAJourListesReservationFault_Exception {

		List<Reservation> vListReservation = client.miseAJourListesReservation();

		if (vListReservation != null) {
			for (Reservation reservation : vListReservation) {
				Mail mail = new Mail();
				mail.setTo(reservation.getUtilisateur().getEmail());
				mail.setSubject(config.getReservationSujet());

				String vMessage = "<h1>Bonjour " + reservation.getUtilisateur().getPrenom() + " " + reservation.getUtilisateur().getNom() + "!</h1>";

				vMessage += "<br/>";

				vMessage += config.getReservationPresentation() + reservation.getBibliotheque();
				vMessage += "<br/>";
				vMessage += "<br/>";
				vMessage += "<strong>" + config.getReservationDetail() + "</strong>";
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
				vMessage += "Réservé le : " + reservation.getDateResa().getDay() + "/" + reservation.getDateResa().getMonth() + "/" + reservation.getDateResa().getYear();

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

}
