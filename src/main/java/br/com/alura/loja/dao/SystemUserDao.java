package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.loja.models.SystemUser;

public class SystemUserDao {
	
	@PersistenceContext
	private EntityManager manager;

	public void salvar(SystemUser user) {
		manager.persist(user);
	}

	public List<SystemUser> listaUsuarios() {
		return manager.createQuery("select distinct su from SystemUser su join fetch su.role", SystemUser.class).getResultList();
	}

	public void remove(SystemUser user) {
		manager.remove(manager.merge(user));
	}
}
