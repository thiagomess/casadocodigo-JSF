package br.com.alura.loja.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class SystemUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Email
	@NotBlank
	@Column(unique=true)
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String senha;
	@ElementCollection(fetch=FetchType.EAGER, targetClass= TipoRole.class)
	@Enumerated(EnumType.STRING)
	@Column(name = "roles_name", nullable = false)
	@JoinTable(name="systemUser_SystemRole")
	private List<TipoRole> role;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<TipoRole> getRole() {
		return role;
	}

	public void setRole(List<TipoRole> role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
