package br.com.alura.loja.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.com.alura.loja.dao.CompraDao;
import br.com.alura.loja.infra.MailSender;
import br.com.alura.loja.models.Compra;

//Configuração para ficar ouvindo e esperando a mensagem enviada pela PagamentoService
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topics/CarrinhoComprasTopico")})

public class EnviaEmailCompra implements MessageListener { // implementa para receber a mensagem

	@Inject
	private MailSender mailSender;
	@Inject
	private CompraDao compraDao;

	public void onMessage(Message message) {

		try {
			TextMessage textMessage = (TextMessage) message; // converte a mensagem em String
			Compra compra = compraDao.buscaPorUuid(textMessage.getText());

			String messageBody = "Olá " + compra.getUsuario().getNome()
					+ ", sua compra foi realizada com sucesso, com o número de pedido: " + compra.getUuid();
			mailSender.send("compras@casacodigo.com.br", compra.getUsuario().getEmail(), "Nova Compra na CDC",
					messageBody);

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
