package br.com.alura.loja.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.alura.loja.models.Promo;
import br.com.alura.loja.websockets.PromosEndpoint;

@Model
public class AdminPromoBean {

	@Inject
	private PromosEndpoint promosEndpoint;
	private Promo promo = new Promo();
	
	public void enviar() {
		promosEndpoint.send(promo);
	}
	
	public Promo getPromo() {
		return promo;
	}

	public void setPromo(Promo promo) {
		this.promo = promo;
	}
	
	
}
