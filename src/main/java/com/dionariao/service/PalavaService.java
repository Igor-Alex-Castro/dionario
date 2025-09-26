package com.dionariao.service;

import java.util.List;

import com.dionariao.dto.PalavraDto;
import com.dionariao.dto.PalavraDto;
import com.dionariao.model.Palavra;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface PalavaService {

	
	Palavra create(PalavraDto palavraDto) ;
	
	List<Palavra> findAllPalavrasByDicionario(Long idDionario);
	
	Palavra findByIdAndDicionarioId(Long idPalavra, Long idDicionario);

	Palavra deleteById(Long idPalavra);

	Palavra update( Long id, @Valid PalavraDto palavraDto);

	
}
