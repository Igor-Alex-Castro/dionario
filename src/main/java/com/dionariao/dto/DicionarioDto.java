package com.dionariao.dto;

import com.dionariao.model.Dicionario;

import jakarta.validation.constraints.NotBlank;

public record DicionarioDto (
		@NotBlank(message = "O parâmetro 'nome' não pode ser vazio")
		String name, 
		
		Long id
		
		) {

	public DicionarioDto(Dicionario dicionario) {
		// TODO Auto-generated constructor stub
		this(dicionario.getNome(), dicionario.getId());
	}}
