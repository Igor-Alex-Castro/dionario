package com.dionariao.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveDicionarioDto (
		@NotBlank(message = "O parâmetro 'nome' não pode ser vazio")
		String name, 
		
		Long id
		
		) {}
