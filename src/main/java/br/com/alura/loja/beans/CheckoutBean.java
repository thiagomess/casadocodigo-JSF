package br.com.alura.loja.beans;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import br.com.alura.loja.models.CarrinhoCompras;
import br.com.alura.loja.models.Compra;
import br.com.alura.loja.models.Cliente;

@Model
public class CheckoutBean {
	
	private Cliente usuario = new Cliente();

	@Inject 
	private CarrinhoCompras carrinho;
	@Inject
	private FacesContext context;
	
	@Transactional
	public void finalizar() {
		Compra compra = new Compra();
		compra.setUsuario(usuario);
		carrinho.salvar(compra);

		// redirecionando o usuario para outra tela para efetuar o servico de pagamento
		String contextPath = context.getExternalContext().getRequestContextPath();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
		response.setHeader("Location", contextPath + "/service/pagamento?uuid=" + compra.getUuid());
	}
	
	public Cliente getUsuario() {
		return usuario;
	}

	public void setUsuario(Cliente usuario) {
		this.usuario = usuario;
	}
	

}
