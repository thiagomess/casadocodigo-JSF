package br.com.alura.loja.service;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.alura.loja.dao.CompraDao;
import br.com.alura.loja.models.Compra;

@Path("/pagamento")
public class PagamentoService {
	
	@Context
	private ServletContext context;
	@Inject
	private CompraDao compraDao;
	@Inject
	private PagamentoGateway pagamentoGateway;
	@Inject
	private JMSContext jmsContext; //Injeta o contexto para o jms
	@Resource(name="java:/jms/topics/CarrinhoComprasTopico") //Configurações do destino do JMS
	private Destination destination;

	
	// integracoes Assincronas do java7 através de um pool
	private static ExecutorService executor = Executors.newFixedThreadPool(50);
	
	@POST //Aqui é o metodo que sera passado POST ou GET //@Suspended informa q é assyncrono
	public void pagar(@Suspended final AsyncResponse ar, @QueryParam("uuid") String uuid){ // @QueryParam pega o paramentro do HTTP e pass para a variavel do metodo
		
		Compra compra = compraDao.buscaPorUuid(uuid);
		String contextPath =context.getContextPath();
		JMSProducer producer = jmsContext.createProducer(); //cria o JMS
		
		//executa a thread
		executor.submit(() -> {
			try {
				String RespostaDoServicoRest = pagamentoGateway.pagar(compra.getTotal()); //Chamando o serviço rest
				System.out.println("resposta rest: " + RespostaDoServicoRest);
				
				producer.send(destination, compra.getUuid()); // Envia o uuid por JMS para a ckasse EnviaEmailCompra
				
				//Redirecionando para a tela nacional mandando a mensagem
				URI responseUri = UriBuilder.fromPath("http://localhost:8080" + contextPath + "/index.xhtml")
						.queryParam("msg", "Compra Realizada com Sucesso").build();
				Response response = Response.seeOther(responseUri).build();
				
				ar.resume(response);
			} catch (Exception e) {
				ar.resume(new WebApplicationException(e));
			}
		});
	}
}
