package br.com.alura.loja.conf;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class FacesContextProducer {
	
	@RequestScoped //retornará uma instância de FacesContext para cada request, ou seja, no escopo da requisição
	@Produces //O CDI ira produzir sempre que eu dar um @Inject na classe que eu colocar o atributo
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
		
	}

}
