package br.com.alura.loja.conf;

import javax.jms.JMSDestinationDefinition;


//Configuração para o PagamentoService conseguir se comunicar com a EnviaEmailCompra

@JMSDestinationDefinition(
		name="java:/jms/topics/CarrinhoComprasTopico",
		interfaceName="javax.jms.Topic")
public class ConfigureJMSDestination {

}
