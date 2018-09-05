package br.com.alura.loja.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.alura.loja.dao.LivroDao;
import br.com.alura.loja.models.Livro;

@Model
public class LivroDetalheBean {
	
	@Inject
	private LivroDao dao;
	private Livro livro;
	private Integer id;
	
	public Livro carregarDetalhe() {
		return this.livro = dao.buscaPorId(id);
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
	

}
