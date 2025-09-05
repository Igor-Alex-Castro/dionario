package com.dionariao.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dionariao.dto.IdNomeDioDto;
import com.dionariao.dto.SaveDicionarioDto;
import com.dionariao.model.Dicionario;
import com.dionariao.service.DicionarioService;


@RestController
@RequestMapping("/dio")
public class DicionarioController {

	private final DicionarioService dicionarioService;
	
	
	public DicionarioController( DicionarioService dicionarioService) {
		// TODO Auto-generated constructor stub
		this.dicionarioService = dicionarioService;
	}
	
	@PostMapping
	public ResponseEntity<String> create(@RequestBody SaveDicionarioDto saveDicionarioDto){
		
		 Dicionario dicionarioCriado = dicionarioService.saveNomeDicionario(saveDicionarioDto);

		return ResponseEntity.ok( dicionarioCriado.getNome());
	}
	

	
	@GetMapping("/lista")
	public ResponseEntity<List<IdNomeDioDto>> list(){

		List<IdNomeDioDto> listIdNomeDioDto = dicionarioService.listaTodosDicionario();

		 //return ResponseEntity.ok( dicionarioCriado);
		 return ResponseEntity.ok( listIdNomeDioDto);
	}
	
	@GetMapping
	public ResponseEntity<String> findByName(@RequestParam String nomeDicionario) throws Exception{
		
		Dicionario dicionario = dicionarioService.findByName(nomeDicionario);
		return ResponseEntity.ok( dicionario.getNome());
	}
	
	
	
	@DeleteMapping()
	public ResponseEntity<String> deleteById(@RequestParam Long idDicionario) throws Exception{
		
		Optional<Dicionario> dicionario =  dicionarioService.deleteById(idDicionario);
		return ResponseEntity.ok("Dicionario " +  dicionario.get().getNome() + "  excluido com sucesso");
	}
}
