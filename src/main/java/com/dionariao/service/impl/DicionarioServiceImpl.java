package com.dionariao.service.impl;




import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dionariao.dto.DicionarioDto;
import com.dionariao.exception.BusinessException;
import com.dionariao.exception.ResourceNotFoundException;
import com.dionariao.model.Dicionario;
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
	public Dicionario create(DicionarioDto DicionarioDto) {
		// TODO Auto-generated method stub
		String nome = DicionarioDto.name();
		
		if(dicionarioRepository.existsByNome(nome)) {
			throw new  BusinessException("O dicionário com o " +  nome + " já existe" );
		}
		
		Dicionario dicionario = new Dicionario();
		
		dicionario.setNome(nome);

		return  dicionarioRepository.save(dicionario);
	}
	
	@Override
	public Dicionario update(Long id, DicionarioDto DicionarioDto) {
		// TODO Auto-generated method stub
		String nome = DicionarioDto.name();
		
		if(id == null) {
			throw new  BusinessException("É necessário informar o id");
		}
		
		if(!dicionarioRepository.existsById(id)) {
			throw new  BusinessException("Não foi possivel atualizar pois o dicionario não existe");
		}
		
		if(dicionarioRepository.existsByNome(nome)) {
			throw new  BusinessException("Não foi possivel atualizar pois existe um dicionario com esse nome");
		}
		
		Dicionario dicionario = new Dicionario();
		
		dicionario.setId(id);
		dicionario.setNome(nome);

		return  dicionarioRepository.save(dicionario);
	}

	@Override
	public Dicionario findById(Long id) throws Exception {
		
		Dicionario dionario = dicionarioRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Dicionario com o ID " + id + " não foi encontrado"));
		
		//dionario.setPalavras(null);
		
		return dionario;
	}

	@Override
	public Dicionario deleteById(Long id) {
		// TODO Auto-generated method stub
		Dicionario dicionario = dicionarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Dicionario com o id " + id + " não foi encontrado"));
		
		
		 if(palavraRepository.existsByDicionarioId(id) ) {
			 throw new BusinessException("Não é possível excluir este dicionário, pois ele possui palavras associadas"); 
		 }
		 
		dicionarioRepository.deleteById(id);
		
		return dicionario;
		 
	}

	public List<Dicionario> listaTodosDicionario() {
		
		
		List<Dicionario> dicionarios = dicionarioRepository.findAll() ;
	
		return dicionarios;
	}

	@Override
	public List<Dicionario> findByNomeContainingIgnoreCase(String nome) {
		String likeNome = "";
		
		if(nome != null || !nome.isEmpty() ) {
			likeNome = "%" + nome + "%";
		}
		// TODO Auto-generated method stub
		List<Dicionario> dicionarios = dicionarioRepository.findByNomeContainingIgnoreCase(nome);
				
		
		return dicionarios;
	}

	
}
