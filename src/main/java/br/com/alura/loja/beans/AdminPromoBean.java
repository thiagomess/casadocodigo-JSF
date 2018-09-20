package br.com.alura.loja.beans;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.alura.loja.dao.LivroDao;
import br.com.alura.loja.models.Livro;
import br.com.alura.loja.models.Promo;
import br.com.alura.loja.websockets.PromosEndpoint;

@Model
public class AdminPromoBean {

	@Inject
	private PromosEndpoint promosEndpoint;
	@Inject
	private LivroDao livroDao;
	private Promo promo = new Promo();
	private static ExecutorService executor = Executors.newFixedThreadPool(50);
	

	
	public void enviar() {
		executor.submit(() -> {
			
			try {
				System.out.println("Antes de buscar");
				Livro livro = livroDao.buscaPorId(promo.getLivro().getId());
				altera(promo.getNovoValor(), promo.getLivro().getId());
				
				System.out.println("Antes de enviar");
				promosEndpoint.send(promo);
				Thread.sleep(promo.getTempo() * 60000); //converte os minutos em milissegundos  
				
				System.out.println("Passou da tarefa");
				altera(livro.getPreco(), livro.getId());
				System.out.println("fim");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		});
	}
	
	@Transactional
	public void altera(BigDecimal valor, Integer id) {
		System.out.println("alterando");
		livroDao.alteraValor(valor, id);
	}
	
	public Promo getPromo() {
		return promo;
	}

	public void setPromo(Promo promo) {
		this.promo = promo;
	}

	public LivroDao getLivroDao() {
		return livroDao;
	}

	public void setLivroDao(LivroDao livroDao) {
		this.livroDao = livroDao;
	}
	

}
