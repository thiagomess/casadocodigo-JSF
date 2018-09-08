package br.com.alura.loja.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.alura.loja.dao.LivroDao;
import br.com.alura.loja.models.Livro;

@Model
public class HomeBean {


	@Inject
	private LivroDao dao;

	public List<Livro> ultimosLancamentos() {
		System.out.println("Entrando nos ultimos Lancamentos");
		return dao.ultimosLancamentos();
	}

	public List<Livro> demaisLivros(){
		System.out.println("Entrando nos demais livros");
		return dao.demaisLivros();
	}
}
