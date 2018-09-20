package br.com.alura.loja.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import br.com.alura.loja.dao.LivroDao;
import br.com.alura.loja.models.Livro;

/*
 * Classe que disponibiliza uma webService com os ultimos livros em XML ou JSON
*/
@Path("/livros")
public class LivroResource {
	
	@Inject
	private LivroDao dao;
	
	@GET
	@Path("/lancamentos")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) //Ela retornará XML e JSON
	@Wrapped(element= "livros") //Tag principal
	public List<Livro> ultimosLancamentos() {
		return dao.ultimosLancamentos();
	}
	

}
