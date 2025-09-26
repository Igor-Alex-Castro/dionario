package com.dionariao.controller;



import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.dionariao.dto.ApiResponse;

import com.dionariao.dto.DicionarioDto;
import com.dionariao.model.Dicionario;
import com.dionariao.service.DicionarioService;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@RestController
@RequestMapping("/dicionarios")
@Validated
public class DicionarioController {

	private final DicionarioService dicionarioService;
	
	
	public DicionarioController( DicionarioService dicionarioService) {
		// TODO Auto-generated constructor stub
		this.dicionarioService = dicionarioService;
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<Dicionario>> create(@RequestBody @Valid DicionarioDto saveDicionarioDto){
		
		 Dicionario dicionarioCriado = dicionarioService.create(saveDicionarioDto);

		 ApiResponse<Dicionario> response = new ApiResponse<>(
				 HttpStatus.CREATED.value(),
				 	"Dicionário criado com sucesso",
				 	dicionarioCriado
				 );
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
				
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<DicionarioDto>> update(
			@PathVariable @NotNull(message = "O parâmetro 'ID' não pode ser vazio") Long id,
			@RequestBody @Valid DicionarioDto dicionarioDto){
		
		
		 Dicionario dicionarioAtualizado = dicionarioService.update(id, dicionarioDto);

		 
		 ApiResponse<DicionarioDto> response = new ApiResponse<>(
	                HttpStatus.OK.value(),
	                "Dicionário atualizado com sucesso",
	                new DicionarioDto(dicionarioAtualizado)
	               
	        );
		 
		return ResponseEntity.ok(response);
	}

	
	@GetMapping
	public ResponseEntity<ApiResponse<List<Dicionario>>> list(){

		List<Dicionario> dicionarios = dicionarioService.listaTodosDicionario();

		 //return ResponseEntity.ok( dicionarioCriado);
		ApiResponse<List<Dicionario>> response = new ApiResponse<List<Dicionario>>( 
				HttpStatus.OK.value(),
				"Lista de dicionários carregada com sucesso",
				dicionarios);
		
		return ResponseEntity.ok(  response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Dicionario>> findByName(@PathVariable @NotNull(message = "O parâmetro  'ID' não pode ser vazio") Long id) throws Exception{
		
		Dicionario dicionario = dicionarioService.findById(id);
		
		ApiResponse<Dicionario> response = new ApiResponse<Dicionario>(
				HttpStatus.OK.value(),
				"Dicionário carregado com sucesso",
				dicionario);
		
		return ResponseEntity.ok( response);
	}
	
	@GetMapping("/search")
	public ResponseEntity<ApiResponse<List<Dicionario>>> findByNomeContainingIgnoreCase(
			@RequestParam @NotBlank(message = "O parâmetro 'nome' não pode ser vazio") String nome){
	
		List<Dicionario> dicionarios = dicionarioService.findByNomeContainingIgnoreCase(nome);
		
	
		String mensagem = dicionarios.isEmpty() ? "Nenhum dicionário encontrao"
			: "Lista de dicionários carregada com sucesso";
		
		ApiResponse<List<Dicionario>> response = new ApiResponse<List<Dicionario>>(
				HttpStatus.OK.value(),
				mensagem,
			dicionarios);
		

		
		return ResponseEntity.ok(response);
		
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable @NotNull(message = "O parâmetro  'ID' não pode ser vazio")  Long id) throws Exception{
		
		dicionarioService.deleteById(id);
		
		ApiResponse<Void> response = new ApiResponse<Void>(
				HttpStatus.NO_CONTENT.value(),
				"Dicionário excluído com sucesso",
				null);
		
		return ResponseEntity.noContent().build();
	}
}
