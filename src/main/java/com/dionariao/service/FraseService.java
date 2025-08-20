package com.dionariao.service;

import com.dionariao.model.Frase;
import com.dionariao.model.Significado;

public interface FraseService {
	
	
	Frase addFrase(String descricao, Long Palavra);

	
	
	void deleteById(Long idFrase);
}
