package br.com.alura.loja.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.alura.loja.dao.LivroDao;
import br.com.alura.loja.models.CarrinhoCompras;
import br.com.alura.loja.models.CarrinhoItem;
import br.com.alura.loja.models.Livro;

@Model
public class CarrinhoComprasBean {
	
	@Inject
	private LivroDao dao;
	@Inject 
	private CarrinhoCompras carrinho;
	
	
	public String add(Integer id) {
		Livro livro = dao.buscaPorId(id);
		CarrinhoItem item = new CarrinhoItem(livro);
		carrinho.add(item);
		
		return "/carrinho?faces-redirect=true";
	}
	
	public List<CarrinhoItem> getItens(){
		return carrinho.getItens();
	}
	
	public void remover(CarrinhoItem item) {
		carrinho.remover(item);
		
	}
	

}
