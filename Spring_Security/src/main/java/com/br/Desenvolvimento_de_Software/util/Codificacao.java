package com.br.Desenvolvimento_de_Software.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Codificacao {
	
	 private static Codificacao Codificacao = new Codificacao();
	    public BCryptPasswordEncoder codificacao_senha;

	    public static Codificacao getInstance() {
	        if (Codificacao != null)
	            return Codificacao;
	        return new Codificacao();
	    }

	    private Codificacao() {
	    	codificacao_senha = new BCryptPasswordEncoder();
	    }

}
