package com.dionariao.service.impl;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dionariao.dto.AddOrigemDto;
import com.dionariao.model.Origem;
import com.dionariao.model.Palavra;

import com.dionariao.repository.OrigiemRepotitory;
import com.dionariao.repository.PalavraRepository;

import com.dionariao.service.OrigemService;



@Service
public class OrigemServiceImpl implements OrigemService {

	@Autowired
	private  OrigiemRepotitory origemRepotitory;
	
	@Autowired
	private  PalavraRepository palavraRepository;
	
	public OrigemServiceImpl(OrigiemRepotitory origemRepotitory, PalavraRepository palavraRepository) {
		
		this.origemRepotitory = origemRepotitory;
		this.palavraRepository = palavraRepository;
	}




	@Override
	public Origem addOrigem(AddOrigemDto addOrigemDto) {
		
		Origem origem = new Origem();
		Optional<Palavra> palavraRetorn = palavraRepository.findById(addOrigemDto.idPalavra());
				
		
		origem.setTipo(addOrigemDto.tipo());
		
		origem.setTitulo(addOrigemDto.titulo());
		origem.setPag(addOrigemDto.pag());
		
		palavraRetorn.get().setOrigem(origem);
		
		origemRepotitory.save(origem);
		
		if(!palavraRetorn.isEmpty()) {
			palavraRepository.save(palavraRetorn.get());
		}
		
		return origem;
	}




	@Override
	public void deleteOrigem(Long idOrigem) {
		// TODO Auto-generated method stub
		
		
		//Optional<Origem> origem  = origemRepotitory.findById(idOrigem);
		Optional<Palavra> palavra = palavraRepository.findByOrigemId(idOrigem);
		
		palavra.get().setOrigem(null);
		
		//Origem origem = palavra.get().getOrigem();
		
		palavraRepository.save(palavra.get());
		
		origemRepotitory.deleteById(idOrigem);
	}

}
