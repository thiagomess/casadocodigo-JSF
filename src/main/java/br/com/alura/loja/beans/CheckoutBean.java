package br.com.alura.loja.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.alura.loja.models.CarrinhoCompras;
import br.com.alura.loja.models.Usuario;

@Model
public class CheckoutBean {
	
	private Usuario usuario = new Usuario();

	@Inject 
	private CarrinhoCompras carrinho;
	
	@Transactional
	public void finalizar() {
		carrinho.salvar(usuario);
		
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
