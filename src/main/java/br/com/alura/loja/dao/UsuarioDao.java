package br.com.alura.loja.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.loja.models.Cliente;

public class UsuarioDao {
	
	@PersistenceContext
	private EntityManager manager;

	public void salvar(Cliente usuario) {
		manager.persist(usuario);
	}
	
	

}
