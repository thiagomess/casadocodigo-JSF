package br.com.alura.loja.models;

public enum TipoRole {
	
	ADMIN("Administrador"), 
	USER("Usuário");
	
	private String label;
	


	TipoRole(String label){
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

}
