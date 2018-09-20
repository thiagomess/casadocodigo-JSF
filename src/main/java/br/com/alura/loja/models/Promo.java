package br.com.alura.loja.models;

import java.math.BigDecimal;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class Promo {
	
	private String titulo;
	private Livro livro = new Livro();
	private Integer tempo;
	private BigDecimal novoValor;
	
	
	//Transforma a promoção em Json 
	public String toJSON() {
		JsonObjectBuilder promo = Json.createObjectBuilder();
		promo.add("titulo", titulo)
			.add("livroId", livro.getId())
			.add("tempo", tempo);
		
		return promo.build().toString();
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public BigDecimal getNovoValor() {
		return novoValor;
	}

	public void setNovoValor(BigDecimal novoValor) {
		this.novoValor = novoValor;
	}
	
	
}
