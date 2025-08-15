package com.dionariao.service;


import com.dionariao.dto.SaveDicionarioDto;
import com.dionariao.model.Dicionario;

public interface DicionarioService {
	
	

	Dicionario saveNomeDicionario(SaveDicionarioDto saveDicionarioDto);
	
	Dicionario findByName( String nome) throws Exception;
}
