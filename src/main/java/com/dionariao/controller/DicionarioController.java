package com.dionariao.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;



@RestController
@RequestMapping("/dio")
@Validated
public class DicionarioController {

	private final DicionarioService dicionarioService;
	
	
	public DicionarioController( DicionarioService dicionarioService) {
		// TODO Auto-generated constructor stub
		this.dicionarioService = dicionarioService;
	}
	
	@PostMapping
	public ResponseEntity<String> create(@RequestBody @Valid SaveDicionarioDto saveDicionarioDto){
		
		 Dicionario dicionarioCriado = dicionarioService.create(saveDicionarioDto);

		return ResponseEntity.ok( dicionarioCriado.getNome());
	}
	
	@PutMapping
	public ResponseEntity<String> update(@RequestBody @Valid SaveDicionarioDto saveDicionarioDto){
		
		 Dicionario dicionarioCriado = dicionarioService.update(saveDicionarioDto);

		return ResponseEntity.ok( dicionarioCriado.getNome());
	}

	
	@GetMapping("/lista")
	public ResponseEntity<List<IdNomeDioDto>> list(){

		List<IdNomeDioDto> listIdNomeDioDto = dicionarioService.listaTodosDicionario();

		 //return ResponseEntity.ok( dicionarioCriado);
		 return ResponseEntity.ok( listIdNomeDioDto);
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity<Dicionario> findByName(@PathVariable @NotBlank(message = "O parâmetro 'nome' não pode ser vazio") String nome) throws Exception{
		
		Dicionario dicionario = dicionarioService.findByName(nome);
		return ResponseEntity.ok( dicionario);
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) throws Exception{
		
		Dicionario dicionario =  dicionarioService.deleteById(id);
		return ResponseEntity.ok("Dicionario " +  dicionario.getNome() + "  excluido com sucesso");
	}
}
