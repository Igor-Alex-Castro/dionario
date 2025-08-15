package com.dionariao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dionariao.model.Dicionario;
import com.dionariao.model.Palavra;
import com.dionariao.repository.DicionarioRepository;
import com.dionariao.repository.PalavraRepository;
import com.dionariao.service.PalavaService;

@Service
public class PalavraServiceImpl implements PalavaService {

	private final PalavraRepository palavraRepository;
	
	public PalavraServiceImpl(PalavraRepository palavraRepository, DicionarioRepository dicionarioRepository) {
		
		this.palavraRepository = palavraRepository;
		
	}


	@Override
	public Palavra addPalavra(String nome, Long idDionario) throws Exception {
		
		
	
		Palavra palavra = new Palavra();
		Dicionario dicionario = new Dicionario();
		
		dicionario.setId(idDionario);
		
		palavra.setDicionario(dicionario);
		
		palavra.setNome(nome);
		palavraRepository.save(palavra);
		
		return null;
	}


	@Override
	public List<Palavra> findAllPalavrasByDicionario(Long idDionario) {
		// TODO Auto-generated method stub
		return palavraRepository.findByDicionarioId(idDionario);
	}


	@Override
	public Palavra findByIdAndDicionarioId(Long idPalavra, Long idDicionario) throws Exception {
		
		
		Palavra  palavra = palavraRepository.findByIdAndDicionarioId(idPalavra, idDicionario)
			 .orElseThrow(() -> new Exception("Palavra não encontrada"));
		
			 return  palavra;
	}

}
