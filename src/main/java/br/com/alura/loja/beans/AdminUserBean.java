package br.com.alura.loja.beans;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.alura.loja.dao.SystemUserDao;
import br.com.alura.loja.models.SystemUser;
import br.com.alura.loja.models.TipoRole;
import br.com.alura.loja.security.PassGenerator;

@Model
public class AdminUserBean {

	@Inject
	private SystemUserDao systemUserDao;
	private SystemUser user = new SystemUser();
	private PassGenerator generator = new PassGenerator();
	private String senhaTela;
	@Inject
	private FacesContext context;

	@Transactional
	public void salvar() {

		try {
			String senhaCriptografada = generator.generate(this.senhaTela);
			user.setSenha(senhaCriptografada);
			systemUserDao.salvar(user);
			context.addMessage("formSalva:home", new FacesMessage("Usuário cadastrado com sucesso"));

			limpaTela();
		} catch (Exception e) {
			context.addMessage("formSalva:email", new FacesMessage("Este e-mail já está em uso"));
		}

	}

	private void limpaTela() {
		user = new SystemUser();
		senhaTela = null;
	}

	public TipoRole[] getRoles() {
		return TipoRole.values();
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public String getSenhaTela() {
		return senhaTela;
	}

	public void setSenhaTela(String senhaTela) {
		this.senhaTela = senhaTela;
	}

}
