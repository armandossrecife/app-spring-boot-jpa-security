package com.myapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {
	/**
	 * Criptografa senha baseeada no padrão BCryptPasswordEncoder
	 * @param valor senha original
	 * @return senha criptografada
	 */
	public String criptografa(String valor){
		String senhaCriptografada = new BCryptPasswordEncoder().encode(valor);
		return senhaCriptografada;
	}

}
