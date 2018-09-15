package br.com.alura.loja.models;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Cliente usuario;
	private String itens;
	private String uuid;
	private BigDecimal total;
	
	
	//Metodo que cria um ID unico para a compra e sera construido antes da entidade
	@PrePersist // executa método anotado antes da entidade ser persistida;
	public void createUuid() {
		this.uuid = UUID.randomUUID().toString();
	}
	

	public Cliente getUsuario() {
		return usuario;
	}

	public void setUsuario(Cliente usuario) {
		this.usuario = usuario;
	}

	public String getItens() {
		return itens;
	}

	public void setItens(String itens) {
		this.itens = itens;
	}

	public Integer getId() {
		return id;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public BigDecimal getTotal() {
		return total;
	}


	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
