package br.com.alura.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import br.com.alura.loja.dao.CompraDao;

@Named // Permite que o HTML acesse essa classe no model
@SessionScoped // Salva a sessao, usando CDI
public class CarrinhoCompras implements Serializable {

	private static final long serialVersionUID = 1L;

	private Set<CarrinhoItem> itens = new HashSet<>();

	public void add(CarrinhoItem item) {
		itens.add(item);
	}
	
	@Inject
	private CompraDao compraDao;
	
	public List<CarrinhoItem> getItens() {
		return new ArrayList<CarrinhoItem>(itens);
	}

	public BigDecimal getTotalItem(CarrinhoItem item) {
		return item.getLivro().getPreco().multiply(new BigDecimal(item.getQuantidade()));
	}

	public BigDecimal getTotalCompra() {
		BigDecimal total = BigDecimal.ZERO;
		

		for (CarrinhoItem carrinhoItem : itens) {
			total = total.add(carrinhoItem.getLivro().getPreco()
					.multiply(new BigDecimal(carrinhoItem.getQuantidade())));
		}
		return total;
	}
	
	public void remover(CarrinhoItem item) {
		this.itens.remove(item);
	}
	
/*	public Integer getQuantidadeTotal() {
		Integer total = 0;
		for (CarrinhoItem carrinhoItem : itens) {
			total =+ carrinhoItem.getQuantidade();
		}
		return total;
	}*/
	
	//Usando java 8
	public Integer getQuantidadeTotal() {
		return itens.stream().mapToInt(item -> item.getQuantidade()).sum();
	}
	
	@Transactional
	public void salvar(Usuario usuario) {
		Compra compra = new Compra();
		compra.setUsuario(usuario);
		compra.setItens(this.toJson());
		compraDao.salvar(compra);
		
		
		
	}
	
	public String toJson() {
	
		JsonArrayBuilder builder = Json.createArrayBuilder();
		
		for (CarrinhoItem item : itens) {
			builder.add(Json.createObjectBuilder()
					.add("titulo", item.getLivro().getTitulo())
					.add("preco", item.getLivro().getPreco())
					.add("quantidade", item.getQuantidade())
					.add("total livro", getTotalItem(item))
					);
		}
		
		String json = builder.build().toString();
		System.out.println(json);
		return json;
		
	}
	

}
