package org.biblio.biblioBatch.livre.mail;

import java.util.Iterator;
import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.biblio.biblioBatch.livre.Config;
import org.biblio.biblioBatch.livre.generated.LivreService;
import org.biblio.biblioBatch.livre.generated.types.Auteur;
import org.biblio.biblioBatch.livre.generated.types.Livre;
import org.biblio.biblioBatch.livre.generated.types.Pret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EnvoiMessage {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private Config config;

	@Scheduled(cron = "0 0 8 * * MON-FRI", zone = "Europe/Paris")//Envoi du lundi au vendredi à 08h00
	public void execute() {
		System.out.println("Envoie des messages");
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress("http://localhost:8080/biblioService/services/livre");
		factory.setServiceClass(LivreService.class);

		LivreService client = (LivreService) factory.create();

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
}
