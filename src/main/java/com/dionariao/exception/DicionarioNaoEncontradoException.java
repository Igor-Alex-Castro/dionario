package com.dionariao.exception;

public class DicionarioNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DicionarioNaoEncontradoException(String nome) {
		// TODO Auto-generated constructor stub
		super("Dicionário com nome '" + nome + "'não encontrado");
	}

}
