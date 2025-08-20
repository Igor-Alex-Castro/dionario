package com.dionariao.service;

import java.util.List;

import com.dionariao.model.Palavra;

public interface PalavaService {

	
	Palavra addPalavra(String nome, Long idDionario) throws Exception;
	
	List<Palavra> findAllPalavrasByDicionario(Long idDionario);
	
	Palavra findByIdAndDicionarioId(Long idPalavra, Long idDicionario) throws Exception;

	void deleteById(Long idPalavra);

	
}
