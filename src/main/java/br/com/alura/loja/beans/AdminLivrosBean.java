package br.com.alura.loja.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.alura.loja.dao.AutorDao;
import br.com.alura.loja.dao.LivroDao;
import br.com.alura.loja.models.Autor;
import br.com.alura.loja.models.Livro;

//CDI pode ser usado assim ou a anotação @Model
@Named
@RequestScoped
public class AdminLivrosBean {

	private Livro livro = new Livro();

	@Inject
	private LivroDao livroDao;
	@Inject
	private AutorDao autorDao;
	@Inject
	private FacesContext context;

	@Transactional // Anotação que executa a transação
	public String salvar() {
		livroDao.salva(livro);

		// Serve para adicionar a mensagem ao escopo de flash e a mensagem sobrevive até
		// a proxima pagina que sera redirecionada
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Livro cadastrado com sucesso"));

		return listaLivros();
	}

	public String listaLivros() {
		return "/livros/lista?faces-redirect=true";
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

}
