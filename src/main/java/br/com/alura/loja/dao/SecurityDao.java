package br.com.alura.loja.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.loja.models.SystemUser;

public class SecurityDao {

	@PersistenceContext
	private EntityManager manager;

	public SystemUser findByEmail(String email) {
		return manager.createQuery("select su from SystemUser su where su.email = :pEmail", SystemUser.class)
				.setParameter("pEmail", email).getSingleResult();
	}

}
