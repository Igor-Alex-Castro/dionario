package com.dionariao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dionariao.dto.AddPalavraDto;
import com.dionariao.model.Frase;
import com.dionariao.model.Palavra;
import com.dionariao.model.Significado;
import com.dionariao.service.FraseService;
import com.dionariao.service.PalavaService;
import com.dionariao.service.SignificadoService;

@RestController
@RequestMapping("/frase")
public class FraseController {

	
	private final FraseService  fraseService;

	public FraseController(FraseService  fraseServic) {
		
		this.fraseService = fraseServic;
	}
	
	
	@PostMapping
	public ResponseEntity<Frase> addSignificado(@RequestParam String descricao, @RequestParam Long palavra){
		
		Frase frase = null;
		try {
			 frase  = fraseService.addFrase(descricao, palavra);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok( frase );
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteFrase(@RequestParam Long idFrase){
		
		
		fraseService.deleteById(idFrase);
		return ResponseEntity.ok("Frese excluida");
	
		
	}
	
	

	
	
}
