package br.com.alura.loja.converts;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

//Essa classe efetua a conversão da data digitada como texto para um Object do tipo Calendar.

@FacesConverter(forClass=Calendar.class)
public class CalendarConverter implements Converter {
	
	private DateTimeConverter converter = new DateTimeConverter();
	
	public CalendarConverter() {
		converter.setPattern("dd/MM/yyyy");
		converter.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String dataTexto) {
		if (dataTexto.trim().isEmpty()) {
			return null;
		}
		Date data = (Date) converter.getAsObject(context, component, dataTexto);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object dataObjeto) {
		if (dataObjeto == null) {
			return null;
		}
		Calendar calendar = (Calendar) dataObjeto;
		return converter.getAsString(context, component, calendar.getTime());
	}

}
