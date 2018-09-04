package br.com.alura.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.alura.loja.dao.LivroDao;
import br.com.alura.loja.infra.FileSaver;
import br.com.alura.loja.models.Livro;

@Model
public class AdminListaLivrosBean {

	@Inject
	private LivroDao dao;
	@Inject
	private FacesContext context;

	private List<Livro> livros = new ArrayList<>();

	public List<Livro> getLivros() {
		this.livros = dao.listaLivros();

		return livros;
	}

	public String novoLivro() {
		return "/livros/form?faces-redirect=true";
	}
	
	//melhoria, pois no curso nao tinha
	@Transactional
	public void excluir(Livro livro) {
		
		FileSaver fileSaver = new FileSaver();
		fileSaver.remove(livro.getCapaPath());
		dao.excluir(livro);
		
		context.addMessage(null, new FacesMessage("Livro excluído com sucesso"));
		
	}

}
