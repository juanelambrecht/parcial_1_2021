package ar.unrn.ui;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.unrn.modelo.Observer;

public class EmailMonitorDeCarga implements Observer{

	@Override
	public void notificar(String informacion) {
		// remitente
				String to = "test@example.com";
				// destinatario
				String from = "from@example.com";

				// usuario y clave que se obtiene desde Mailtrap
				final String username = "c2e7f23b592f02";
				final String password = "023196946c08b3";
				String host = "smtp.mailtrap.io";
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");// it’s optional in Mailtrap
				props.put("mail.smtp.host", host);

				props.put("mail.smtp.port", "2525");
				// Get the Session object.
				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
				try {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(from));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
					message.setSubject("Carga de Combustible Estacion de Servicio...");
					message.setText(informacion);
					// Send message
					Transport.send(message);
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
			}
	
	
	
}
