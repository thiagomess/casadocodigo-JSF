package br.com.alura.loja.websockets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import br.com.alura.loja.models.Promo;

/*
 * Classe que envia as promoçoes para a tela via WebSocket
*/

@ServerEndpoint("/canal/promos")  //Sera o canal que fara ligação com a página
public class PromosEndpoint {

	@Inject
	private UsuariosSession usuarios;

	//Adiciona o usuario na lista
	@OnOpen
	public void onMenssage(Session session) {
		usuarios.add(session);
	}

	//Envia para a pagina via JSON
	public void send(Promo promo) {
		
		List<Session> sessions = usuarios.getUsuarios(); //captura os usuarios que estao na pagina
		for (Session session : sessions) { //varre os usuarios e sai disparando o alert para todos
			if (session.isOpen()) {
				try {
					session.getBasicRemote().sendText(promo.toJSON());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//remove o usuario da lista caso ele saia da pagina
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		usuarios.remove(session);
		System.out.println(closeReason.getCloseCode());
	}

}
