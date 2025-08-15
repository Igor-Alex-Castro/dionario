package com.dionariao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dionariao.dto.AddPalavraDto;
import com.dionariao.model.Palavra;
import com.dionariao.service.PalavaService;

@RestController
@RequestMapping("/palavra")
public class PalavraController {

	
	private final PalavaService palavaService;

	public PalavraController(PalavaService palavaService) {
		
		this.palavaService = palavaService;
	}
	
	
	@PostMapping
	public ResponseEntity<Palavra> addPalavra(@RequestBody AddPalavraDto addPalavraDto){
		
		Palavra palavra = null;
		try {
			palavra = palavaService.addPalavra(addPalavraDto.palavara(), addPalavraDto.idDicionario());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(palavra);
	}
	
	@GetMapping()
	public ResponseEntity<Palavra> findByIdAndDicionarioId(@RequestParam Long idPalavra, @RequestParam Long idDicionario){
		
		Palavra palavra = null;
		try {
			palavra = palavaService.findByIdAndDicionarioId(idPalavra, idDicionario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(palavra);
	}
	
	
}
