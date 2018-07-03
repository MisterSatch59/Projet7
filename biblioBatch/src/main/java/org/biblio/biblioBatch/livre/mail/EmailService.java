package org.biblio.biblioBatch.livre.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
/**
 * Class contenant les méthode d'envoie de mail
 * @author Oltenos
 *
 */
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	public void sendSimpleMessage(Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
		message.setTo(mail.getTo());

		emailSender.send(message);
	}

	public void sendHtmlMessage(Mail mail) {

		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			
			helper.setTo(mail.getTo());
			helper.setSubject(mail.getSubject());
			helper.setText(mail.getContent(), true);

			emailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
