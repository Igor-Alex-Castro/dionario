package com.dionariao.service;


import java.util.List;
import java.util.Optional;

import com.dionariao.dto.DicionarioDto;
import com.dionariao.model.Dicionario;

public interface DicionarioService {
	
	

	Dicionario create(DicionarioDto DicionarioDto);
	
	Dicionario update(Long id, DicionarioDto DicionarioDto);
	
	Dicionario findById( Long id) throws Exception;

	Dicionario deleteById(Long id);


	List<Dicionario> listaTodosDicionario();

	List<Dicionario> findByNomeContainingIgnoreCase(String nome);


}
