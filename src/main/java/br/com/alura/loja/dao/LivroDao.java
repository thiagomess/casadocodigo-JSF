package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.jpa.QueryHints;

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

	public List<Livro> ultimosLancamentos() {
		String jpql = "select l from Livro l order by l.dataPublicacao desc";
		return manager.createQuery(jpql, Livro.class).setMaxResults(5).setHint(QueryHints.HINT_CACHEABLE, true).getResultList(); //SetHint Vai cachear a busca
	
	}

	public List<Livro> demaisLivros() {
		String jpql = "select l from Livro l order by l.dataPublicacao desc";
		return manager.createQuery(jpql, Livro.class).setFirstResult(5).setHint(QueryHints.HINT_CACHEABLE, true).getResultList(); //SetHint Vai cachear a busca
	}

	//Ao inves de usar o find, para nao dar erro de lazy usamos JPQL
	public Livro buscaPorId(Integer id) {
		String jpql = "select l from Livro l join fetch l.autores where l.id = :pId";
		return manager.createQuery(jpql, Livro.class).setParameter("pId", id).getSingleResult();
	}

	public void excluir(Livro livro) {
		manager.remove(manager.merge(livro));
	}


}
