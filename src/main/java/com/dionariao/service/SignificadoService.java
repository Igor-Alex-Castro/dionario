package com.dionariao.service;

import com.dionariao.model.Significado;

public interface SignificadoService {
	
	
	Significado addSignificado(String descricao, Long Palavra);

	void deleteSignificado(Long idSignificado);
	
}
