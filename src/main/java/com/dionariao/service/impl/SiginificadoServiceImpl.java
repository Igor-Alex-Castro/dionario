package com.dionariao.service.impl;



import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dionariao.model.Palavra;
import com.dionariao.model.Significado;
import com.dionariao.repository.PalavraRepository;
import com.dionariao.repository.SignificadoRepotitory;
import com.dionariao.service.SignificadoService;


@Service
public class SiginificadoServiceImpl implements SignificadoService {

	private final SignificadoRepotitory significadoRepotitory;
	private final PalavraRepository palavraRepository;
	
	public SiginificadoServiceImpl(SignificadoRepotitory significadoRepotitory, PalavraRepository palavraRepository) {
		
		this.significadoRepotitory = significadoRepotitory;
		this.palavraRepository = palavraRepository;
	}


	@Override
	public Significado addSignificado(String descricao, Long palavra) {
		
		
		Significado signficado = new Significado();
		 Optional<Palavra> palavraRetorn = palavraRepository.findById(palavra);
				
		
		signficado.setPalavra(palavraRetorn.get());
		
		signficado.setDescricao(descricao);
		
		significadoRepotitory.save(signficado);
		
		
		return signficado;
	}


	@Override
	public void deleteSignificado(Long idSignificado) {
		// TODO Auto-generated method stub
		Optional<Palavra> palavra = palavraRepository.findByOrigemId(idSignificado);
		
		
		significadoRepotitory.deleteById(idSignificado);
	}


	

}
