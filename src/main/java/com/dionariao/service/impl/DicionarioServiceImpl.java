package com.dionariao.service.impl;



import org.springframework.stereotype.Service;

import com.dionariao.dto.SaveDicionarioDto;
import com.dionariao.model.Dicionario;
import com.dionariao.repository.DicionarioRepository;
import com.dionariao.service.DicionarioService;



@Service
public class DicionarioServiceImpl implements DicionarioService {

	
	private final DicionarioRepository dicionarioRepository;
	

	public DicionarioServiceImpl(DicionarioRepository dicionarioRepository, PalavraServiceImpl palavraServiceImpl) {
		
		this.dicionarioRepository = dicionarioRepository;
		
	}

	@Override
	public Dicionario saveNomeDicionario(SaveDicionarioDto saveDicionarioDto) {
		// TODO Auto-generated method stub
		
		Dicionario dicionario = new Dicionario();
		dicionario.setNome(saveDicionarioDto.name());
		
		return  dicionarioRepository.save(dicionario);
	}

	@Override
	public Dicionario findByName(String nome) throws Exception {
		
		Dicionario dionario = dicionarioRepository.findByNome(nome)
		.orElseThrow(() -> new Exception("Nome do dicionário não encontrado"));
		
		//dionario.setPalavras(null);
		
		return dionario;
	}

	
}
