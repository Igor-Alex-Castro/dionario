package com.dionariao.service;


import java.util.List;
import java.util.Optional;

import com.dionariao.dto.IdNomeDioDto;
import com.dionariao.dto.SaveDicionarioDto;
import com.dionariao.model.Dicionario;

public interface DicionarioService {
	
	

	Dicionario saveNomeDicionario(SaveDicionarioDto saveDicionarioDto);
	
	Dicionario findByName( String nome) throws Exception;

	Optional<Dicionario> deleteById(Long idDicionario);


	List<IdNomeDioDto> listaTodosDicionario();


}
