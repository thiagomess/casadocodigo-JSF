package br.com.alura.loja.security;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.alura.loja.dao.SecurityDao;
import br.com.alura.loja.models.SystemUser;

@Model
public class CurrentUser {
	
	@Inject
	private HttpServletRequest request;
	@Inject
	private SecurityDao securityDao;
	private SystemUser systemUser;
	
	public SystemUser get() {
		return systemUser;
	}
	
	public boolean hasRole(String role) {
		return request.isUserInRole(role);
	}
	
	@PostConstruct
	public void loadSystemUser() {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			String email = request.getUserPrincipal().getName();
			systemUser = securityDao.findByEmail(email);
		}
		
	}
	
}
