package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.loja.models.Livro;

public class LivroDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void salva(Livro livro) {
		manager.persist(livro);
	}

	public List<Livro> listaLivros() {
		//distinct serve para independente de quantos autores tiver, vai trazer apenas um livro
		String jpql = ("select distinct(l) from Livro l join fetch l.autores"); //permite que uma única consulta (em JPQL) à determinada entidade também traga outras entidades associadas
		return manager.createQuery(jpql, Livro.class ).getResultList();
	}
	

}
