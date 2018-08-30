package br.com.alura.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.alura.loja.dao.LivroDao;
import br.com.alura.loja.models.Livro;

@Model
public class AdminListaLivrosBean {

	@Inject
	private LivroDao dao;

	private List<Livro> livros = new ArrayList<>();

	public List<Livro> getLivros() {
		this.livros = dao.listaLivros();

		return livros;
	}

	public String novoLivro() {
		return "/livros/form?faces-redirect=true";
	}

}
