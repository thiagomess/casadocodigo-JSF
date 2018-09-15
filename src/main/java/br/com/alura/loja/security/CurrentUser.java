package br.com.alura.loja.security;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.com.alura.loja.dao.SecurityDao;
import br.com.alura.loja.models.SystemUser;

@Model
public class CurrentUser {

	@Inject
	private HttpServletRequest request;
	@Inject
	private SecurityDao securityDao;
	private SystemUser systemUser = new SystemUser();
	@Inject
	private FacesContext context;

	public SystemUser get() {
		return systemUser;
	}

	// verifica o perfil do usuario para dar acesso ou nao para uma pagina
	public boolean hasRole(String role) {
		return request.isUserInRole(role);
	}

	public String login() {
		try {
			request.login(systemUser.getEmail(), systemUser.getSenha());
		} catch (ServletException e) {

			context.addMessage(null, new FacesMessage("Usuário ou senha inválido"));
			return "error";
		}
		if (request.isUserInRole("USER")) {
			return "/livros/lista.xhtml?faces-redirect=true";
		} else
			return "/livros/usuario.xhtml?faces-redirect=true";
			
	}

	public String logout() {
		request.getSession().invalidate();

		return "/livros/lista.xhtml?faces-redirect=true";
	}

	// Exibe o usuario logado na aplicação
	@PostConstruct
	public void loadSystemUser() {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			String email = request.getUserPrincipal().getName();
			systemUser = securityDao.findByEmail(email);
		}

	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

}
