package br.com.alura.loja.converts;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.alura.loja.models.Livro;

@FacesConverter(value="livroConverter")
public class LivroConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null || id.trim().isEmpty()) {
			return null;
		}
		
		Livro livro = new Livro();
		livro.setId(Integer.valueOf(id));
		
		return livro;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object livroObject) {
		if (livroObject == null) {
			return null;
		}
		Livro livro = (Livro) livroObject;
		return livro.getId().toString();
	}

}
