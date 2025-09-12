package com.dionariao.service.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dionariao.dto.IdNomeDioDto;
import com.dionariao.dto.SaveDicionarioDto;
import com.dionariao.exception.BusinessException;
import com.dionariao.exception.DicionarioNaoEncontradoException;
import com.dionariao.exception.ResourceNotFoundException;
import com.dionariao.model.Dicionario;
import com.dionariao.model.Palavra;
import com.dionariao.repository.DicionarioRepository;
import com.dionariao.repository.PalavraRepository;
import com.dionariao.service.DicionarioService;



@Service
public class DicionarioServiceImpl implements DicionarioService {

	
	private final DicionarioRepository dicionarioRepository;
	private final PalavraRepository palavraRepository;

	public DicionarioServiceImpl(DicionarioRepository dicionarioRepository, PalavraRepository palavraRepository ) {
		
		this.dicionarioRepository = dicionarioRepository;
		this.palavraRepository = palavraRepository;
		
	}

	@Override
	public Dicionario saveNomeDicionario(SaveDicionarioDto saveDicionarioDto) {
		// TODO Auto-generated method stub
		Dicionario dicionario = new Dicionario();
		dicionario.setNome(saveDicionarioDto.name());
		
		if(saveDicionarioDto.id() != null) {
	
			dicionario.setId(saveDicionarioDto.id());
			
			List<Palavra> palavras = palavraRepository.findByDicionarioId(saveDicionarioDto.id());
				
			if(!palavras.isEmpty()) {
				dicionario.setPalavras(palavras);
			}
		}
		
		
		return  dicionarioRepository.save(dicionario);
	}

	@Override
	public Dicionario findByName(String nome) throws Exception {
		
		Dicionario dionario = dicionarioRepository.findByNome(nome)
		.orElseThrow(() -> new ResourceNotFoundException("Dicionario com " + nome + " não foi encontrado"));
		
		//dionario.setPalavras(null);
		
		return dionario;
	}

	@Override
	public Dicionario deleteById(Long idDicionario) {
		// TODO Auto-generated method stub
		Dicionario dicionario = dicionarioRepository.findById(idDicionario)
				.orElseThrow(() -> new ResourceNotFoundException("Dicionario com o id " + idDicionario + " não foi encontrado"));
		
		
		 if(palavraRepository.existsByDicionarioId(idDicionario) ) {
			 throw new BusinessException("Não é possível excluir este dicionário, pois ele possui palavras associadas"); 
		 }
		 
		dicionarioRepository.deleteById(idDicionario);
		
		return dicionario;
		 
	}

	public List<IdNomeDioDto> listaTodosDicionario() {
		List<IdNomeDioDto> listIdNomeDioDto = new ArrayList<IdNomeDioDto>();
		
		List<Dicionario> dicionarios = dicionarioRepository.findAll();
		
		for (Dicionario dicionario : dicionarios) {
			listIdNomeDioDto.add(new IdNomeDioDto(dicionario.getId(), dicionario.getNome()));
		}
		
		return listIdNomeDioDto;
	}

	
}
