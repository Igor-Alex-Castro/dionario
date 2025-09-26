package com.dionariao.controller;

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
import com.dionariao.dto.PalavraDto;
import com.dionariao.model.Palavra;
import com.dionariao.service.PalavaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/palavras")
@Validated
public class PalavraController {

	
	private final PalavaService palavaService;

	public PalavraController(PalavaService palavaService) {
		
		this.palavaService = palavaService;
	}
	
	
	@PostMapping
	public ResponseEntity<ApiResponse<Palavra>> create(@RequestBody  @Valid PalavraDto palavraDto){
		
		
		
		
		Palavra palavra = palavaService.create(palavraDto);
	
		ApiResponse<Palavra> response = new ApiResponse<>(
				HttpStatus.CREATED.value(),
				"Palavra criada com suscesso",
				palavra
			); 

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Palavra>> update(
			@PathVariable @NotNull(message = "O parâmetro 'ID' não pode ser vazio") Long id,
			@RequestBody @Valid PalavraDto palavraDto){
		
		Palavra palavra = palavaService.update(id, palavraDto);
		
		ApiResponse<Palavra> response = new ApiResponse<>(
				HttpStatus.OK.value(),
				"Palavra atualizado com sucesso",
				palavra
				);
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping()
	public ResponseEntity<Palavra> findByIdAndDicionarioId(@RequestParam(required = false) Long idPalavra, @RequestParam(required = false) Long idDicionario){
		
			Palavra palavra = null;
	
			palavra = palavaService.findByIdAndDicionarioId(idPalavra, idDicionario);
	
		
			return ResponseEntity.ok(palavra);
	}
	
	@DeleteMapping()
	public ResponseEntity<String> deletePalavra(@RequestParam Long idPalavra){
		
		Palavra palavra = palavaService.deleteById(idPalavra);
		
		return ResponseEntity.ok( "A Palavra " + palavra.getNome() + " foi deletada com sucesso" );
	}
}
