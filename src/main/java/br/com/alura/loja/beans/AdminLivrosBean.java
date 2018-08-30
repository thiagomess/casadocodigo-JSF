package br.com.alura.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.alura.loja.dao.AutorDao;
import br.com.alura.loja.dao.LivroDao;
import br.com.alura.loja.models.Autor;
import br.com.alura.loja.models.Livro;

//CDI
@Named
@RequestScoped
public class AdminLivrosBean {

	private Livro livro = new Livro();

	@Inject
	private LivroDao livroDao;
	@Inject
	private AutorDao autorDao;
	private List<Integer> autoresId = new ArrayList<>();

	@Transactional
	public String salvar() {

		for (Integer autorId : autoresId) {
			livro.getAutores().add(new Autor(autorId));

		}

		System.out.println("livro cadastrado" + livro);
		livroDao.salva(livro);
		this.livro = new Livro();
	    this.autoresId = new ArrayList<>();
	    
		return listaLivros();
	}
	
	public String listaLivros() {
		return  "/livros/lista?faces-redirect=true";
	}

	public List<Autor> getAutores() {
		return autorDao.listaAutores();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Integer> getAutoresId() {
		return autoresId;
	}

	public void setAutoresId(List<Integer> autoresId) {
		this.autoresId = autoresId;
	}

}
