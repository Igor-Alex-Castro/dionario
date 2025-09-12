package com.dionariao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dionariao.dto.AddPalavraDto;
import com.dionariao.exception.BusinessException;
import com.dionariao.model.Dicionario;
import com.dionariao.model.Palavra;
import com.dionariao.repository.DicionarioRepository;
import com.dionariao.repository.PalavraRepository;
import com.dionariao.service.PalavaService;

import jakarta.transaction.Transactional;

@Service
public class PalavraServiceImpl implements PalavaService {

	private final PalavraRepository palavraRepository;
	private final DicionarioRepository dicionarioRepository;
	
	public PalavraServiceImpl(PalavraRepository palavraRepository, DicionarioRepository dicionarioRepository) {
		
		this.palavraRepository = palavraRepository;
		this.dicionarioRepository = dicionarioRepository;
		
	}


	@Override
	public Palavra addPalavra(AddPalavraDto addPalavraDto)  {
		
		Long idDicionario = addPalavraDto.idDicionario();
	
		String nomePalavra = addPalavraDto.palavara();
		
		Palavra palavra = new Palavra();
		Dicionario dicionario = new Dicionario();
		
		if(idDicionario == null) {
			throw new  BusinessException("Palavra não foi salva, pois o id do dicionário não foi informado.");
		}
		
		if(!dicionarioRepository.existsById(idDicionario)) {
			throw new  BusinessException("Palavra não foi salva, pois este dicionário não existe.");
		}
		
		if(nomePalavra == null || nomePalavra.isEmpty()) {
			throw new  BusinessException("Palavra não foi salva, pois é necessário informar uma.");
		}
		
		if(palavraRepository.existsByDicionarioIdAndNome(idDicionario, nomePalavra)) {
			throw new  BusinessException("Palavra não foi salva, pois ela ja existe para este dicionário.");
		}
		
		dicionario.setId(idDicionario);
		
		palavra.setDicionario(dicionario);
		
		palavra.setNome(nomePalavra);
		
		palavraRepository.save(palavra);
		
		return palavra;
	}


	@Override
	public List<Palavra> findAllPalavrasByDicionario(Long idDionario) {
		// TODO Auto-generated method stub
		return palavraRepository.findByDicionarioId(idDionario);
	}


	@Override
	public Palavra findByIdAndDicionarioId(Long idPalavra, Long idDicionario)  {
		
		
		Palavra palavra = null;
		
		if(idPalavra == null) {
			throw new  BusinessException("Não foi possível encontrar a palavra, pois o ID da palavra não foi informado.");
		}
		
		if(idDicionario == null) {
			throw new  BusinessException("Não foi possível encontrar a palavra, pois o ID do dicionário não foi informado.");
		}
		
		if(!dicionarioRepository.existsById(idDicionario)) {
			throw new  BusinessException("Não foi possível encontrar a palavra, pois o dicionário informado não existe");
		}
		
		
		
		palavra = palavraRepository.findByIdAndDicionarioId(idPalavra, idDicionario).orElseThrow(
				() ->  new BusinessException("Não foi possível encontrar a palavra, pois a palavra informada não existe para este dicionário") );
				 
		
			
		
		return palavra;
		
		
			 
	}

	public Palavra findPalavra(Long idPalavra) {
		Optional<Palavra> palavra = palavraRepository.findById(idPalavra);
		
		return palavra.get();
	}

	@Override
	@Transactional
	public Palavra deleteById(Long idPalavra) {
		// TODO Auto-generated method stub
		Palavra palavra = findPalavra(idPalavra);
		palavraRepository.deleteById(idPalavra);
		
		return palavra;
	}

}
