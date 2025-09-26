package com.dionariao.dto;

import com.dionariao.model.Dicionario;
import com.dionariao.model.Palavra;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PalavraDto(
		
		Long idPalavra,
		
		@NotBlank(message = "O parâmetro 'palavra' não pode ser vazio")
		String palavra,
		
		@NotNull(message = "O parâmetro 'idDicionario' não pode ser vazio")
		Long idDicionario

		
		
		) {
	
		 public PalavraDto(Palavra palavra) {
			// TODO Auto-generated constructor stub
			 this(palavra.getId(), palavra.getNome(), palavra.getDicionario().getId() );
		}
}
