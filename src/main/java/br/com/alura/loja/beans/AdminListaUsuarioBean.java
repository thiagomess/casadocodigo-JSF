package br.com.alura.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.alura.loja.dao.SystemUserDao;
import br.com.alura.loja.models.SystemUser;

@Model
public class AdminListaUsuarioBean {
	
	@Inject
	private SystemUserDao dao;
	@Inject
	private FacesContext context;
	private List<SystemUser> user = new ArrayList<>();
	
	
	public List<SystemUser> getUser() {
		this.user = dao.listaUsuarios();
		return user;
	}
	
	@Transactional
	public void excluir(SystemUser usuario) {
		dao.remove(usuario);
		context.addMessage(null,new FacesMessage("Usuário excluído com sucesso"));
		
	}

}
