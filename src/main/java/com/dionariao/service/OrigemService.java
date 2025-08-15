package com.dionariao.service;

import com.dionariao.dto.AddOrigemDto;
import com.dionariao.model.Origem;
import com.dionariao.model.Significado;

public interface OrigemService {
	
	

	Origem addOrigem(AddOrigemDto addOrigemDto);
	
}
