package br.com.alura.loja.beans;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.alura.loja.dao.AutorDao;
import br.com.alura.loja.dao.LivroDao;
import br.com.alura.loja.infra.FileSaver;
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
	private Part capaLivro; // Especificado no java 7, serve para pegar arquivo da tela, desde que usado no
							// form enctype="multipart/form-data"

	
	@Transactional // Anotação que executa a transação
	public String salvar() throws IOException {
		livroDao.salva(livro);
		
		//Pega o arquivo da tela e salva no caminho especificado
		FileSaver fileSaver = new FileSaver();
		String caminho = fileSaver.write(capaLivro, "livros");
		livro.setCapaPath(caminho);

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

	public Part getCapaLivro() {
		return capaLivro;
	}

	public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}


}
