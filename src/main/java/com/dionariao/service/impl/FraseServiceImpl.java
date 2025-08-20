package com.dionariao.service.impl;



import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dionariao.model.Frase;
import com.dionariao.model.Palavra;
import com.dionariao.model.Significado;
import com.dionariao.repository.FraseRepotitory;
import com.dionariao.repository.PalavraRepository;
import com.dionariao.repository.SignificadoRepotitory;
import com.dionariao.service.FraseService;
import com.dionariao.service.SignificadoService;


@Service
public class FraseServiceImpl implements FraseService {

	private final FraseRepotitory  fraseRepotitory;
	private final PalavraRepository palavraRepository;
	
	public FraseServiceImpl(FraseRepotitory  fraseRepotitory, PalavraRepository palavraRepository) {
		
		this.fraseRepotitory = fraseRepotitory;
		this.palavraRepository = palavraRepository;
	}


	@Override
	public Frase addFrase(String descricao, Long palavra) {
		
		
		Frase frase = new Frase();
		 Optional<Palavra> palavraRetorn = palavraRepository.findById(palavra);
				
		
		 frase.setPalavra(palavraRetorn.get());
		
		 frase.setDescricao(descricao);
		
		fraseRepotitory.save( frase);
		
		
		return  frase;
	}


	@Override
	public void deleteById(Long idFrase) {
		// TODO Auto-generated method stub
		
		fraseRepotitory.deleteById(idFrase);
		
	}


	

}
