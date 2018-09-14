package br.com.alura.loja.security;

import java.security.MessageDigest;

import org.jboss.security.Base64Encoder;

public class PassGenerator {

	public String generate(String senhaTexto) {
		try {
			byte[] digest = MessageDigest.getInstance("sha-256").digest(senhaTexto.getBytes());

			return Base64Encoder.encode(digest);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new PassGenerator().generate("123"));
	}

}
