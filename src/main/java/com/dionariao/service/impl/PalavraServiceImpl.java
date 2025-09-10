package com.dionariao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dionariao.dto.AddPalavraDto;
import com.dionariao.model.Dicionario;
import com.dionariao.model.Palavra;
import com.dionariao.repository.DicionarioRepository;
import com.dionariao.repository.PalavraRepository;
import com.dionariao.service.PalavaService;

import jakarta.transaction.Transactional;

@Service
public class PalavraServiceImpl implements PalavaService {

	private final PalavraRepository palavraRepository;
	
	public PalavraServiceImpl(PalavraRepository palavraRepository, DicionarioRepository dicionarioRepository) {
		
		this.palavraRepository = palavraRepository;
		
	}


	@Override
	public Palavra addPalavra(AddPalavraDto addPalavraDto)  {
		
		
	
		Palavra palavra = new Palavra();
		Dicionario dicionario = new Dicionario();
		
		dicionario.setId(addPalavraDto.idDicionario());
		
		palavra.setDicionario(dicionario);
		
		palavra.setNome(addPalavraDto.palavara());
		
		if(addPalavraDto.idPalavra() != null) {
			palavra.setId(addPalavraDto.idPalavra());
		}
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
		try {
			palavra = palavraRepository.findByIdAndDicionarioId(idPalavra, idDicionario)
				 .orElseThrow(() -> new Exception("Palavra não encontrada"));
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
