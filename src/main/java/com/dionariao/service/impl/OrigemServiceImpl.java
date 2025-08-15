package com.dionariao.service.impl;



import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dionariao.dto.AddOrigemDto;
import com.dionariao.dto.AddPalavraDto;
import com.dionariao.model.Origem;
import com.dionariao.model.Palavra;
import com.dionariao.model.Significado;
import com.dionariao.repository.OrigiemRepotitory;
import com.dionariao.repository.PalavraRepository;
import com.dionariao.repository.SignificadoRepotitory;
import com.dionariao.service.OrigemService;
import com.dionariao.service.SignificadoService;


@Service
public class OrigemServiceImpl implements OrigemService {

	private final OrigiemRepotitory origemRepotitory;
	private final PalavraRepository palavraRepository;
	
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
		
		origem.setPalavra(palavraRetorn.get());
		origemRepotitory.save(origem);
		
		return origem;
	}

}
