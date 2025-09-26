package com.dionariao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dionariao.dto.PalavraDto;
import com.dionariao.model.Palavra;
import com.dionariao.model.Significado;
import com.dionariao.service.PalavaService;
import com.dionariao.service.SignificadoService;

@RestController
@RequestMapping("/significado")
public class SignificadoController {

	
	private final SignificadoService significadoService;

	public SignificadoController(SignificadoService significadoService) {
		
		this.significadoService = significadoService;
	}
	
	
	@PostMapping
	public ResponseEntity<Significado> addSignificado(@RequestParam String descricao, @RequestParam Long palavra){
		
		Significado significado = null;
		
		significado  = significadoService.addSignificado(descricao, palavra);
		
		
		return ResponseEntity.ok(significado);
	}
	

	@DeleteMapping
	public ResponseEntity<String> deleteSignificado(@RequestParam Long idSignificado){
		
		significadoService.deleteSignificado(idSignificado);
		
		return ResponseEntity.ok("Significado excluido");
	}
	
}
