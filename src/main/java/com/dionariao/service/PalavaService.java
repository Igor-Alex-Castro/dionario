package com.dionariao.service;

import java.util.List;

import com.dionariao.dto.AddPalavraDto;
import com.dionariao.model.Palavra;

public interface PalavaService {

	
	Palavra addPalavra(AddPalavraDto addPalavraDto) ;
	
	List<Palavra> findAllPalavrasByDicionario(Long idDionario);
	
	Palavra findByIdAndDicionarioId(Long idPalavra, Long idDicionario);

	Palavra deleteById(Long idPalavra);

	
}
