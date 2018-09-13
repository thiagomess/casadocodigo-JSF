package br.com.alura.loja.infra;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


//Classe que envia e-mail usando as proprias especificações do JAVAEE


@ApplicationScoped // O CDI vai criar o objeto e o manterá vivo durante todo o contexto da aplicação
public class MailSender {

	@Resource(mappedName = "java:/jboss/mail/gmail") // A configuração esta mapeada no standalone do Wildfly
	private Session session;

	public void send(String from, String to, String subject, String messageBody) {

		MimeMessage message = new MimeMessage(session);
		try {
			message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
			message.setFrom(new InternetAddress(from)); //O from vem do Wildfly
			message.setSubject(subject);
			message.setContent(messageBody, "text/html");

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
