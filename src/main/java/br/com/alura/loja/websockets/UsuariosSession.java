package br.com.alura.loja.websockets;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

@ApplicationScoped // O CDI vai criar o objeto e o manterá vivo durante todo o contexto da aplicação
public class UsuariosSession {
	
	private List<Session> sessions = new ArrayList<>();
	
	public void add(Session session) {
		sessions.add(session);
	}

	public List<Session> getUsuarios() {
		return sessions;
	}

	public void remove(Session session) {
		sessions.remove(session);
	}
	
	

}
